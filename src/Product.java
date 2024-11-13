public class Product extends Menu_Item{

    private double price;
    private boolean is_avaliable;

    Product(String name, double price) {
        super(name);

        this.price = price;
        this.is_avaliable = true;
    }

    public void updateItem(String name, double price){
        this.name = name;
        this.price = price;
        this.is_avaliable = true;
    }


    public String getName() {
        return this.name;
    }

    public void setIs_avaliable(boolean is_avaliable) {
        this.is_avaliable = is_avaliable;
    }

    public boolean getIs_avaliable() {
        return this.is_avaliable;
    }

    public double getPrice() {
        return price;
    }
}