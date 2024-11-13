import java.util.*;

public class Customer implements User{

    protected boolean vip;
    private String name;
    private String password;
    private static LinkedHashMap<Product, Integer> Cart = new LinkedHashMap<>();
    static ArrayList<Order> order_list = new ArrayList<>();

    private Set<Product> products_ordered = new HashSet<>();

    Customer(String name, String password){
        this.name = name;
        this.password = password;
        this.vip = false;
    }

    Customer(String name, String password, boolean vip){
        this.name = name;
        this.password = password;
        this.vip = vip;
    }

    public static void displayCatMenu_Reverse(int ind){
        if(Menu_Item.menu_list.get(ind).size() == 0) {
            System.out.print("No items to display");
        }

        else{
            for(int i = Menu_Item.menu_list.get(ind).size()-1; i>= 0; i++) {
                System.out.println(i + 1 + ". " + Menu_Item.menu_list.get(ind).get(i).getName() + ": " + Menu_Item.menu_list.get(ind).get(i).getPrice());
            }
        }

        System.out.println();
    }

    public static void displayCatMenu(int ind){
        if(Menu_Item.menu_list.get(ind).size() == 0) {
            System.out.print("No items to display");
        }

        else{
            for(int i = 0; i < Menu_Item.menu_list.get(ind).size(); i++) {
                System.out.println(i + 1 + ". " + Menu_Item.menu_list.get(ind).get(i).getName() + ": " + Menu_Item.menu_list.get(ind).get(i).getPrice());
            }
        }

        System.out.println();
    }

    public static void printCart(Customer current_user){

        if (current_user.Cart.isEmpty()){
            System.out.println("No items to display");
        }

        else{
            System.out.printf("%-5s %-15s %-10s %-10s%n", "S.No.", "Product", "Quantity", "Price");

            int i = 1;
            for(Map.Entry<Product, Integer> entry : current_user.Cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double price = product.getPrice();

                System.out.printf("%-5d %-15s %-10d Rs. %-10.2f%n", i, product.getName(), quantity, price);
                i++;
            }
        }
        System.out.println();
    }

    public static int calculateCartTotal(Customer current_user){
        int total = 0;

        for (Map.Entry<Product, Integer> entry : current_user.Cart.entrySet()) {
            Product item = entry.getKey();
            int quantity = entry.getValue();
            double price = item.getPrice();

            total += quantity * price;
        }

        return total;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to Customer Interface!");

        while(true){
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){

                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Set Password: ");
                String password = scanner.nextLine();

                int flag = 0;
                for(int i = 0; i < User.cust_list.size(); i++) {
                    if (User.cust_list.get(i).getUserName().equals(name) && User.cust_list.get(i).getPassword().equals(password)) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 0){
                    Customer cust = new Customer(name, password);
                    cust_list.add(cust);
                    System.out.println("User successfully registered");
                }
                else{
                    System.out.println("User already exists, please login");
                }

            }

            else if(choice == 2){

                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Set Password: ");
                String password = scanner.nextLine();


                int flag = 0;
                Customer current_user = null;
                for(int i = 0; i < User.cust_list.size(); i++) {
                    if (User.cust_list.get(i).getUserName().equals(name) && User.cust_list.get(i).getPassword().equals(password)) {
                        flag = 1;
                        current_user = User.cust_list.get(i);
                        break;
                    }
                }

                if (flag == 1){
                    System.out.println("\nHi " + name + "!");

                    while(true){
                        System.out.println("1. Browse Menu");
                        System.out.println("2. View/Update Cart");
                        System.out.println("3. Track Order");
                        System.out.println("4. Submit Review");
                        System.out.println("5. Upgrade to VIP Customer");
                        System.out.println("6. Back");
                        System.out.print("Please choose an option: ");

                        int choice2 = scanner.nextInt();
                        scanner.nextLine();

                        if(choice2 == 1){

                            while(true){
                                System.out.println("1. View Entire Menu");
                                System.out.println("2. View Menu by Category");
                                System.out.println("3. Search Menu");
                                System.out.println("4. Sort by price: High to Low");
                                System.out.println("5. Back");
                                System.out.print("Enter Choice: ");

                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                if(choice3 == 1){
                                    System.out.println("Beverages: ");
                                    displayCatMenu(0);

                                    System.out.println("Snacks: ");
                                    displayCatMenu(1);

                                    System.out.println("Meals: ");
                                    displayCatMenu(2);
                                }

                                else if(choice3 == 2){
                                    System.out.print("Select Category: \n");

                                    System.out.println("1. Beverages");
                                    System.out.println("2. Snacks");
                                    System.out.println("3. Meals");

                                    System.out.print("Enter Your Choice: ");
                                    int choice4 = scanner.nextInt();

                                    if(choice4 > 3 || choice4 < 1){
                                        System.out.println("Invalid Choice");
                                    }

                                    else {
                                        int ind = choice4 - 1;
                                        displayCatMenu(ind);
                                    }
                                }

                                else if(choice3 == 3){
                                    System.out.print("Enter Keyword: ");
                                    String key = scanner.nextLine();

                                    key = key.toLowerCase(Locale.ROOT);
                                    int flag1 = 0;

                                    for(int j = 0; j<3; j++){
                                        for(int i = 0; i<Menu_Item.menu_list.get(j).size(); i++){
                                            if((Menu_Item.menu_list.get(j).get(i).getName().toLowerCase(Locale.ROOT).contains(key))){

                                                flag1 = 1;
                                                System.out.println("Product Name: " + Menu_Item.menu_list.get(j).get(i).getName());
                                                System.out.println("Product Price: " + Menu_Item.menu_list.get(j).get(i).getPrice() + "\n");

                                            }
                                        }
                                    }

                                    if(flag1 == 0){
                                        System.out.println("No results found\n");
                                    }
                                }

                                else if(choice3 == 4){
                                    System.out.println("Beverages: ");
                                    displayCatMenu_Reverse(0);

                                    System.out.println("Snacks: ");
                                    displayCatMenu_Reverse(1);

                                    System.out.println("Meals: ");
                                    displayCatMenu_Reverse(2);
                                }

                                else if(choice3 == 5){
                                    break;
                                }

                                else{
                                    System.out.println("Invalid Choice");
                                }
                            }
                        }

                        else if(choice2 == 2){
                            while(true){
                                System.out.println("1. View Cart");
                                System.out.println("2. Add Items to Cart");
                                System.out.println("3. Remove Item from Cart");
                                System.out.println("4. Modify Quantities");
                                System.out.println("5. View Total");
                                System.out.println("6. Checkout");
                                System.out.println("7. Back");

                                System.out.print("Enter Your Choice: ");
                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                if(choice3 == 1){
                                    printCart(current_user);
                                }

                                else if(choice3 == 2){

                                    System.out.print("Select Category: \n");

                                    System.out.println("1. Beverages");
                                    System.out.println("2. Snacks");
                                    System.out.println("3. Meals");

                                    System.out.print("Enter Your Choice: ");
                                    int choice4 = scanner.nextInt();

                                    if(choice4 > 3 || choice4 < 1){
                                        System.out.println("Invalid Choice");
                                    }

                                    else{
                                        int ind = choice4 - 1;

                                        if(Menu_Item.menu_list.get(ind).size() == 0){
                                            System.out.print("No items to display");
                                        }

                                        else{
                                            displayCatMenu(ind);
                                            System.out.println();

                                            System.out.print("Enter Item to add: ");
                                            int choice5 = scanner.nextInt();
                                            scanner.nextLine();

                                            if(choice5 < 1 || choice5 > Menu_Item.menu_list.get(ind).size()){
                                                System.out.println("Invalid Choice");
                                            }

                                            else{

                                                System.out.print("Enter Quantity: ");
                                                int quantity = scanner.nextInt();
                                                scanner.nextLine();

                                                current_user.Cart.put(Menu_Item.menu_list.get(ind).get(choice5 - 1), quantity);
                                            }
                                        }
                                    }
                                }

                                else if(choice3 == 3){
                                    printCart(current_user);

                                    System.out.print("Select item to remove: ");
                                    int item_ind = scanner.nextInt();
                                    scanner.nextLine();

                                    List<Product> items = new ArrayList<>(current_user.Cart.keySet());

                                    if (item_ind > 0 && item_ind <= items.size()) {
                                        Product itemToRemove = items.get(item_ind - 1);
                                        current_user.Cart.remove(itemToRemove);
                                        System.out.println("Item removed successfully.");
                                    }

                                    else{
                                        System.out.println("Invalid item selection.");
                                    }
                                }

                                else if(choice3 == 4){

                                    printCart(current_user);

                                    System.out.print("Select item to modify quantity: ");
                                    int item_ind = scanner.nextInt();
                                    scanner.nextLine();

                                    List<Product> items = new ArrayList<>(current_user.Cart.keySet());

                                    if (item_ind > 0 && item_ind <= items.size()) {
                                        Product itemToUpdate = items.get(item_ind - 1);

                                        System.out.print("Enter New Quantity: ");

                                        int new_quantity = scanner.nextInt();
                                        scanner.nextLine();

                                        if (new_quantity > 0) {
                                            current_user.Cart.put(itemToUpdate, new_quantity);
                                            System.out.println("Quantity updated successfully.");
                                        }

                                        else if (new_quantity == 0) {
                                            current_user.Cart.remove(itemToUpdate);
                                            System.out.println("Item removed from the cart.");
                                        }

                                        else{
                                            System.out.println("Invalid quantity. Must be 0 or greater.");
                                        }
                                    }

                                    else{
                                        System.out.println("Invalid item selection.");
                                    }
                                }

                                else if(choice3 == 5){

                                    int total = calculateCartTotal(current_user);

                                    System.out.println("Cart Total Payable: Rs." + total);
                                }

                                else if(choice3 == 6){

                                    int total = calculateCartTotal(current_user);

                                    System.out.println("Cart Total Payable: Rs." + total);

                                    System.out.println("Enter UPI ID: ");
                                    String UPI_ID = scanner.nextLine();

                                    System.out.println("Enter Address: ");
                                    String address = scanner.nextLine();

                                    Order ordr = new Order(current_user.Cart, current_user);

                                    System.out.print("Do you have any special requests for your order(y/n): ");
                                    String ch = scanner.nextLine();

                                    if(ch.toLowerCase().equals("y")){
                                        System.out.print("Enter request: ");
                                        String request = scanner.nextLine();

                                        ordr.Set_special_req(request);
                                    }

                                    current_user.order_list.add(ordr);

                                    current_user.products_ordered.addAll(current_user.Cart.keySet());

                                    if(current_user.vip){
                                        Order.regular_active_orders.add(ordr);
                                    }
                                    else{
                                        Order.vip_active_orders.add(ordr);
                                    }

                                    Order.all_orders.add(ordr);

                                    System.out.println("Order Successful!");
                                }

                                else if(choice3 == 7){
                                    break;
                                }

                                else{
                                    System.out.println("Invalid Choice");
                                }
                            }
                        }

                        else if(choice2 == 3){

                            while(true){
                                System.out.println("1. View Order Status");
                                System.out.println("2. Cancel Order");
                                System.out.println("3. View Order History");
                                System.out.println("4. Back");
                                System.out.print("Enter Your Choice: ");

                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                if(choice3 == 1){
                                    System.out.println("Order Status: " + current_user.order_list.getLast().getStatus());
                                }

                                else if(choice3 == 2){
                                    current_user.order_list.getLast().setStatus(3);

                                    System.out.println("Your order has been cancelled! Refund will be initiated shortly");
                                }

                                else if(choice3 == 3){

                                    if(current_user.order_list.isEmpty()){
                                        System.out.println("No items to display");
                                    }

                                    else{
                                        for (int i = 0; i < current_user.order_list.size(); i++){

                                            System.out.println(i+1);

                                            System.out.printf("%-5s %-15s %-10s %-10s%n", "S.No.", "Product", "Quantity", "Price");

                                            int j = 1;
                                            Map<Product, Integer> order = current_user.order_list.get(i).getOrder();
                                            for(Map.Entry<Product, Integer> entry : order.entrySet()) {
                                                Product product = entry.getKey();
                                                int quantity = entry.getValue();
                                                double price = product.getPrice();

                                                System.out.printf("%-5d %-15s %-10d $%-10.2f%n", j, product.getName(), quantity, price);
                                                j++;
                                            }

                                            System.out.print("Select order number to reorder. 0 to skip: ");
                                            int ch = scanner.nextInt();
                                            scanner.nextLine();

                                            if(ch != 0){
                                                current_user.Cart = current_user.order_list.get(ch-1).getOrder();

                                                System.out.println("Your selection has been added to Cart!");
                                            }
                                        }

                                        System.out.println();
                                    }

                                }

                                else if(choice3 == 4){
                                    break;
                                }

                                else{
                                    System.out.println("Invalid Choice");
                                }
                            }
                        }

                        else if(choice2 == 4){

                            while(true){
                                System.out.println("1. View Reviews");
                                System.out.println("2. Provide Review");
                                System.out.println("3. Back");
                                System.out.print("Enter Your Choice: ");

                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                if(choice3 == 1){
                                    if(Menu_Item.reviews_list.isEmpty()){
                                        System.out.println("No reviews to display");
                                    }

                                    else{
                                        for(int i = 0; i<Menu_Item.reviews_list.size(); i++){
                                            Menu_Item.reviews_list.get(i).viewReviews();
                                        }
                                    }
                                }

                                else if(choice3 == 2){
                                    if(current_user.products_ordered.isEmpty()){
                                        System.out.println("You cannot review without ordering");
                                    }

                                    else{
                                        System.out.println("Select item to review");
                                        int index = 1;
                                        for (Product product : current_user.products_ordered) {
                                            System.out.println(index + ". " + product.getName());
                                            index++;
                                        }

                                        System.out.print("Enter Choice: ");
                                        int choice4 = scanner.nextInt();
                                        scanner.nextLine();

                                        List<Product> productList = new ArrayList<>(current_user.products_ordered);

                                        if (choice4 > 0 && choice4 <= productList.size()) {
                                            Product itemToReview = productList.get(choice4 - 1);

                                            System.out.print("Give Star Rating: ");
                                            Integer starRating = scanner.nextInt();
                                            scanner.nextLine();

                                            System.out.print("Give Review: ");
                                            String reviewText = scanner.nextLine();

                                            Review<Object> review = new Review<>(itemToReview, current_user.getUserName());

                                            review.addReview(starRating);
                                            review.addReview(reviewText);

                                            Menu_Item.reviews_list.add(review);

                                            System.out.println("Your review has been recorded!\n");
                                        }

                                        else{
                                            System.out.println("Invalid choice. Please select a valid item number.");
                                        }
                                    }
                                }

                                else if(choice3 == 3){
                                    break;
                                }

                                else {
                                    System.out.println("Invalid Choice");
                                }
                            }
                        }

                        else if(choice2 == 5){

                            if(current_user.vip){
                                System.out.println("You are already a VIP Customer");
                            }

                            else{
                                while(true){
                                    System.out.println("1. Become VIP Customer by paying Rs. 250");
                                    System.out.println("2. Back");
                                    System.out.print("Enter Option: ");

                                    int choice3 = scanner.nextInt();

                                    if(choice3 == 1){
                                        System.out.print("Enter UPI ID: ");
                                        scanner.nextLine();
                                        String temp = scanner.nextLine();

                                        System.out.println("Transaction succcessful!");
                                        System.out.println("You are now a VIP Customer\n");

                                        current_user.vip = true;

                                        break;
                                    }

                                    else if(choice3 == 2){
                                        break;
                                    }

                                    else{
                                        System.out.println("Invalid Choice");
                                    }
                                }
                            }
                        }

                        else if(choice2 == 6){
                            break;
                        }

                        else{
                            System.out.println("Invalid option");
                        }
                    }
                }

                else{
                    System.out.println("Incorrect Username or Password entered");
                }
            }

            else if(choice == 3){
                break;
            }

            else{
                System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUserName() {
        return this.name;
    }
}