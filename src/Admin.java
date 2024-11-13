import java.util.*;

public class Admin implements User{

    private String username;
    private String password;

    Admin(String name, String password){
        this.username = name;
        this.password = password;
    }

    public static void getMaxF(){
        Map<Product, Integer> productCountMap = new HashMap<>();

        for (Order order : Order.all_orders) {
            for (Map.Entry<Product, Integer> entry : order.getOrder().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                productCountMap.put(product, productCountMap.getOrDefault(product, 0) + quantity);
            }
        }

        Product mostOrderedProduct = null;
        int maxQuantity = 0;

        for (Map.Entry<Product, Integer> entry : productCountMap.entrySet()) {
            if (entry.getValue() > maxQuantity) {
                maxQuantity = entry.getValue();
                mostOrderedProduct = entry.getKey();
            }
        }

        if (mostOrderedProduct != null){
            System.out.println("Most ordered product: " + mostOrderedProduct.getName() + " with quantity: " + maxQuantity);
        }
        else {
            System.out.println("Most ordered product: No products found.");
        }
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to the Administrator Interface!");

        System.out.print("Enter Admin Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        int flag1 = 0;
        for(int i = 0; i < User.admin_list.size(); i++) {
            if (User.admin_list.get(i).getUserName().equals(name) && User.admin_list.get(i).getPassword().equals(password)) {
                flag1 = 1;
                break;
            }
        }

        if(flag1 == 0){
            System.out.println("You do not have an admin access");
        }

        else{
            while(true){
                System.out.println("\nHi " + name + "!");

                System.out.println("1. Manage Menu");
                System.out.println("2. Manage Orders");
                System.out.println("3. Generate report");
                System.out.println("4. Back");
                System.out.print("Enter Your Choice: ");

                int choice = scanner.nextInt();

                if(choice == 1){

                    while(true){
                        System.out.println("1. Add Items");
                        System.out.println("2. Update Existing Items");
                        System.out.println("3. Remove Items");
                        System.out.println("4. Back");
                        System.out.print("Enter Your Choice: ");

                        int choice2 = scanner.nextInt();

                        if(choice2 == 1){

                            System.out.print("Select Category: ");

                            System.out.println("1. Beverages");
                            System.out.println("2. Snacks");
                            System.out.println("3. Meals");

                            System.out.print("Enter Your Choice: ");
                            int choice3 = scanner.nextInt();

                            if(choice3 > 3 || choice3 < 1){
                                System.out.println("Invalid Choice");
                            }

                            else{
                                int ind = choice3 - 1;

                                System.out.println("Enter Item Name: ");
                                String itemName = scanner.nextLine();

                                System.out.println("Enter Item Quantity: ");
                                int quantity = scanner.nextInt();

                                System.out.println("Enter Item Price: ");
                                double price = scanner.nextDouble();

                                Product prod = new Product(itemName, price);

                                Menu_Item.menu_list.get(ind).add(prod);
                                Menu_Item.menu_list.get(ind).sort(Comparator.comparingDouble(Product::getPrice));

                                System.out.print("Item has been successfully added!");
                            }
                        }

                        else if(choice2 == 2){

                            System.out.print("Select Category: ");

                            System.out.println("1. Beverages");
                            System.out.println("2. Snacks");
                            System.out.println("3. Meals");

                            System.out.print("Enter Your Choice: ");
                            int choice3 = scanner.nextInt();

                            if(choice3 > 3 || choice3 < 1){
                                System.out.println("Invalid Choice");
                            }

                            else{
                                int ind = choice3 - 1;

                                if(Menu_Item.menu_list.get(ind).size() == 0){
                                    System.out.print("No items to display");
                                }

                                else{
                                    for(int i = 0; i<Menu_Item.menu_list.get(ind).size(); i++){
                                        System.out.println(i+1 + ". " + Menu_Item.menu_list.get(ind).get(i).getName());
                                    }

                                    System.out.print("Select Item to edit: ");
                                    int choice4 = scanner.nextInt();

                                    System.out.print("Enter New Name: ");
                                    String newName = scanner.nextLine();

                                    System.out.print("Enter New Price: ");
                                    double newPrice = scanner.nextDouble();

                                    Menu_Item.menu_list.get(ind).get(choice4-1).updateItem(newName, newPrice);

                                    System.out.print("Item details have been successfully updated!");
                                }
                            }

                        }

                        else if(choice2 == 3){

                            System.out.print("Select Category: ");

                            System.out.println("1. Beverages");
                            System.out.println("2. Snacks");
                            System.out.println("3. Meals");

                            System.out.print("Enter Your Choice: ");
                            int choice3 = scanner.nextInt();

                            if(choice3 > 3 || choice3 < 1){
                                System.out.println("Invalid Choice");
                            }

                            else{
                                int ind = choice3 - 1;

                                if(Menu_Item.menu_list.get(ind).size() == 0){
                                    System.out.print("No items to display");
                                }

                                else{
                                    for(int i = 0; i<Menu_Item.menu_list.get(ind).size(); i++){
                                        System.out.println(i+1 + ". " + Menu_Item.menu_list.get(ind).get(i).getName());
                                    }

                                    System.out.print("Select Item to remove: ");
                                    int choice4 = scanner.nextInt();

                                    Product item = Menu_Item.menu_list.get(ind).get(choice4-1);

                                    item.setIs_avaliable(false);
                                    Menu_Item.menu_list.get(ind).remove(choice4-1);

                                    System.out.print("Item has been successfully removed!");

                                    for (Order i : Order.vip_active_orders) {
                                        if(i.getOrder().containsKey(item)){
                                            i.setStatus(5);
                                            Order.vip_active_orders.remove(i);
                                        }
                                    }

                                    for (Order i : Order.regular_active_orders) {
                                        if(i.getOrder().containsKey(item)){
                                            i.setStatus(5);
                                            Order.regular_active_orders.remove(i);
                                        }
                                    }
                                }
                            }
                        }

                        else if(choice2 == 4){
                            break;
                        }

                        else{
                            System.out.println("Invalid Choice");
                        }
                    }
                }

                else if(choice == 2){
                    while(true){
                        System.out.println("1. View Pending Orders");
                        System.out.println("2. Update Order Status");
                        System.out.println("3. Process Refunds");
                        System.out.println("4. Handle Special Requests");
                        System.out.println("5. Back");
                        System.out.print("Enter Your Choice: ");

                        int choice2 = scanner.nextInt();

                        if(choice2 == 1){

                            int cnt = 1;

                            for (Order i : Order.vip_active_orders) {
                                System.out.println(cnt + ". ");
                                i.printOrder();
                                cnt++;
                            }

                            for (Order i : Order.regular_active_orders) {
                                System.out.println(cnt + ". ");
                                i.printOrder();
                                cnt++;
                            }
                        }

                        else if(choice2 == 2){

                            if(Order.vip_active_orders.isEmpty() && Order.regular_active_orders.isEmpty()){
                                System.out.println("No active orders");
                            }

                            else{
                                Order order = null;

                                if(!Order.vip_active_orders.isEmpty()){
                                    order = Order.vip_active_orders.peek();
                                }

                                else{
                                    order = Order.regular_active_orders.peek();
                                }

                                System.out.println("Select Order Status");
                                System.out.println("1. Preparing");
                                System.out.println("2. Out for Delivery");
                                System.out.println("3. Delivered");
                                System.out.print("Enter Your Choice: ");
                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                if(choice3 == 1){
                                    order.setStatus(1);
                                    System.out.println("Order Status has been updated!");
                                }

                                else if(choice3 == 2){
                                    order.setStatus(2);
                                    System.out.println("Order Status has been updated!");
                                }

                                else if(choice3 == 3){
                                    order.setStatus(6);
                                    System.out.println("Order Status has been updated!");
                                }

                                else if(choice3 != 1 && choice3 != 2 && choice3 != 3){
                                    System.out.println("Invalid Choice");
                                }
                            }
                        }

                        else if(choice2 == 3){

                            System.out.println("Orders cancelled by Customer: ");

                            int cnt = 1;

                            for (Order i : Order.vip_active_orders) {
                                if(Objects.equals(i.getStatus(), Order.status_list.get(3))){
                                    System.out.println(cnt + ". ");
                                    i.setStatus(4);
                                    Order.vip_active_orders.remove(i);
                                    cnt++;
                                }
                            }

                            for (Order i : Order.regular_active_orders) {
                                if(Objects.equals(i.getStatus(), Order.status_list.get(3))){
                                    System.out.println(cnt + ". ");
                                    i.setStatus(4);
                                    Order.regular_active_orders.remove(i);
                                    cnt++;
                                }
                            }

                            if(cnt == 1) System.out.println("Not Found");

                            else{
                                System.out.println("Order status changed to 'Refund Initiated'");
                                System.out.println("Order removed from active orders");
                            }
                        }

                        else if(choice2 == 4){

                            System.out.println("Active Orders with Special Requests: ");

                            int cnt = 1;

                            for (Order i : Order.vip_active_orders) {
                                if(i.Get_special_req() != null){
                                    System.out.println(cnt + ". ");
                                    i.printOrder();
                                    i.Get_special_req();
                                    cnt++;
                                }
                            }

                            for (Order i : Order.regular_active_orders) {
                                if(i.Get_special_req() != null){
                                    System.out.println(cnt + ". ");
                                    i.printOrder();
                                    i.Get_special_req();
                                    cnt++;
                                }
                            }

                            if(cnt == 1) System.out.println("Not Found");
                        }

                        else if(choice2 == 5){
                            break;
                        }

                        else{
                            System.out.println("Invalid Choice");
                        }
                    }
                }

                else if(choice == 3){

                    System.out.println("\nDaily Sales Report\n");

                    int total_sales = 0;

                    for(Order i : Order.all_orders){
                        if(i.getStatus() != Order.status_list.get(3) && i.getStatus() != Order.status_list.get(4) && i.getStatus() != Order.status_list.get(5)){
                            int temp = 0;

                            for (Map.Entry<Product, Integer> entry : i.getOrder().entrySet()) {
                                Product prod = entry.getKey();
                                int quantity = entry.getValue();
                                double price = prod.getPrice();

                                temp += quantity * price;
                            }

                            total_sales = temp;
                        }
                    }

                    System.out.println("Total Orders: " + Order.all_orders.size());
                    System.out.println("Total Sales: " + total_sales);
                    getMaxF();
                }

                else if(choice == 4){
                    break;
                }

                else{
                    System.out.println("Invalid Choice");
                }
            }
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUserName() {
        return username;
    }
}