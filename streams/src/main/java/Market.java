import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Market {

    public static class Customer {
        @Id
        private UUID id;
        private String name;
        private Integer tier;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTier() {
            return tier;
        }

        public void setTier(Integer tier) {
            this.tier = tier;
        }

        public Customer(String name, Integer tier) {
            this.id = UUID.randomUUID();
            this.name = name;
            this.tier = tier;
        }


        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", tier=" + tier +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return Objects.equals(id, customer.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Order {
        @Id
        private UUID id;
        private LocalDate orderDate;
        private LocalDate deliveryDate;
        private String status;

        private Customer customer;
        Set<Product> products;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public LocalDate getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
        }

        public LocalDate getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(LocalDate deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Set<Product> getProducts() {
            return products;
        }

        public void addProducts(Product product) {
            this.products.add(product);
        }

        public Order(LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer) {
            this.id = UUID.randomUUID();
            this.orderDate = orderDate;
            this.deliveryDate = deliveryDate;
            this.status = status;
            this.customer = customer;
            products = new HashSet<>();
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", orderDate=" + orderDate +
                    ", deliveryDate=" + deliveryDate +
                    ", status='" + status + '\'' +
                    ", customer=" + customer +
                    ", products=" + products +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return Objects.equals(id, order.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
    public static class Product {
        @Id
        private UUID id;

        private String name;
        private String category;
        @With private Double price;
        private Set<Order> orders;

        public Product(String name, String category, Double price) {
            this.id = UUID.randomUUID();
            this.name = name;
            this.category = category;
            this.price = price;
            this.orders = new HashSet<>();
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Set<Order> getOrders() {
            return orders;
        }

        public void addOrders(Order order) {
            this.orders.add(order);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    ", price=" + price +
                    ", orders=" + orders +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(id, product.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        public Product withPrice(double v) {
            price = v;
            return this;
        }
    }


}
