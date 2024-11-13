import java.util.*;

public interface User {

    ArrayList<Admin> admin_list = new ArrayList<>();
    ArrayList<Customer> cust_list = new ArrayList<>();


    String getUserName();
    String getPassword();
}
