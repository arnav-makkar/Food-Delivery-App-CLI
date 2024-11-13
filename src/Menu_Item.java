import java.util.*;

public class Menu_Item {
    protected String name;

    static ArrayList<Product> beverage_list = new ArrayList<>();
    static ArrayList<Product> snacks_list = new ArrayList<>();
    static ArrayList<Product> meals_list = new ArrayList<>();

    public static ArrayList<Review> reviews_list;

    static ArrayList<ArrayList<Product>> menu_list = new ArrayList<>(
            Arrays.asList(beverage_list, snacks_list, meals_list)
    );

    Menu_Item(String name){
        this.name = name;
    }
}