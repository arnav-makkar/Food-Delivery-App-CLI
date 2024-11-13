import java.util.*;

class Review<T> {

    public List<T> reviews;

    public Product product;
    public String cust_name;

    public Review(Product product, String name) {
        this.cust_name = name;
        this.product = product;
        reviews = new ArrayList<>();
    }

    public void addReview(T review) {
        reviews.add(review);
    }

    public void viewReviews() {
        System.out.println("Name: " + cust_name + "\nProduct: " + product.name);
        for (T rev : reviews) {
            System.out.println(rev);
        }

        System.out.println();
    }
}