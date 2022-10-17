import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class main {


    public static void main(String[] args) {

        System.out.println("Check?");
        //https://blog.devgenius.io/15-practical-exercises-help-you-master-java-stream-api-3f9c86b1cf82
        //https://stackify.com/streams-guide-java-8/
        //https://www.geeksforgeeks.org/stream-in-java/

//        List<Employee> emps = new ArrayList<>();
//        Employee Emp0 = new Employee("Adam","CEO",15000);
//        emps.add(Emp0);
//        Employee Emp1 = new Employee("Alex", "CFO",15000);
//        emps.add(Emp1);
//        Employee Emp2 = new Employee("Bu", "SD",5000);
//        emps.add(Emp2);
//        Employee Emp3 = new Employee("Tinisha", "SD",5000);
//        emps.add(Emp3);
//        Employee Emp4 = new Employee("Bianca","CFO",15000);
//        emps.add(Emp4);

        List<Market.Order> orders = new ArrayList<>();

        Market.Product pro1 = new Market.Product("baby chair","Baby",1000.0);
        Market.Product pro2 = new Market.Product("The Sun shine","Books",10.0);
        Market.Product pro3 = new Market.Product("The Change","Books",100.0);
        Market.Product pro4 = new Market.Product("Algo and Data Struct","Books",200.0);

        Market.Customer cus1 = new Market.Customer("Adam",2);
        Market.Customer cus2 = new Market.Customer("Alex",1);
        Market.Customer cus3 = new Market.Customer("Bianca",4);

        LocalDate date1 = LocalDate.of(2022, 10, 8);
        LocalDate date2 = LocalDate.of(2022, 10, 10);
        Market.Order or1 = new Market.Order(date1, date2,"new",cus1);
        Market.Order or2 = new Market.Order(date1, date2,"new",cus2);
        Market.Order or3 = new Market.Order(date1, date2,"new",cus3);
        or1.addProducts(pro1);
        or1.addProducts(pro2);
        or1.addProducts(pro3);
        or1.addProducts(pro4);

        orders.add(or1);
        orders.add(or2);
        orders.add(or3);

        //System.out.println(getBooksUnder100(or1.getProducts()));
        //System.out.println(getBabyCatg(orders));
        System.out.println(getProductsTier2(orders));
    }

    //Exercise 1 — Obtain a list of products belongs to category “Books” with price > 100
    public static List<Market.Product> getBooksUnder100(Set<Market.Product> products)
    {
        return products.stream()
                .filter(pro -> pro.getCategory().equalsIgnoreCase("Books"))
                .filter(pro->pro.getPrice() > 100.0)
                .collect(Collectors.toList());
    }

    //Exercise 2 — Obtain a list of order with products belong to category “Baby”
    public static List<Market.Order> getBabyCatg(List<Market.Order> orders)
    {
        return orders.stream()
                .filter(or ->
                        or.getProducts()
                        .stream()
                                .anyMatch(pro->pro.getCategory().equalsIgnoreCase("Books"))
                ).collect(Collectors.toList());
    }

    //Exercise 3 — Obtain a list of product with category = “Toys” and then apply 10% discount
    public static List<Market.Product> getToysProductsWith10Discount(List<Market.Product> products)
    {
        return products.stream()
                .filter(pro -> pro.getCategory().equalsIgnoreCase("Toys"))
                .map(pro -> pro.withPrice(pro.getPrice() * 0.9)).
                collect(Collectors.toList());
    }

    //Exercise 4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
    public static List<Market.Product> getProductsTier2(List<Market.Order> order)
    {
        return   order.stream()
            .filter(o -> o.getCustomer().getTier() == 2)
            .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
            .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2022, 11, 1)) <= 0)
            .flatMap(o -> o.getProducts().stream())
            .distinct()
            .collect(Collectors.toList());
    }

    //Exercise 5 — Get the cheapest products of “Books” category
    public static Optional<Market.Product> cheapestBook(List<Market.Product> products)
    {
        return   products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .sorted(Comparator.comparing(Market.Product::getPrice))
                .findFirst();
    }

}
