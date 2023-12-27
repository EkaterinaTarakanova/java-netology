package ru.netology.etarakanova.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.etarakanova.domain.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
@AllArgsConstructor
@Component
public class CustomerService {
    private final ArrayList<Customer> customers;
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    public Customer addNewCustomer(Customer item) {
        customers.add(item);
        return item;
    }

    public void removeCustomer(int id) {
        customers.removeIf(c -> c.getId() == id);
    }
    @PostConstruct
    public void initStorage() {
        customers.add(new Customer(1, "Spring"));
        customers.add(new Customer(2, "Boot"));
    }
}
