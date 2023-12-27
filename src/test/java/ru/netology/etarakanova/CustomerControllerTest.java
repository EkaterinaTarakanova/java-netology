package ru.netology.etarakanova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.etarakanova.controller.CustomerController;
import ru.netology.etarakanova.controller.dto.CustomerDTO;
import ru.netology.etarakanova.controller.dto.CustomersGetResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    CustomerController customerController;

    @Test
    public void getCustomersTest() {
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    public void getCustomerTest() {
        CustomerDTO customer = customerController.getCustomer(1);

        assertEquals(1, customer.getId());
        assertEquals("Spring", customer.getName());
    }

    @Test
    public void addCustomerTest() {
        CustomersGetResponse customers = customerController.getCustomers();
        int size = customers.getCustomers().size();
        CustomerDTO customer = new CustomerDTO(3, "Kate");
        customerController.addNewCustomer(customer);
        CustomersGetResponse newCustomers = customerController.getCustomers();
        int newSize = newCustomers.getCustomers().size();
        assertEquals(1, newSize - size);

        CustomerDTO customerDto = newCustomers.getCustomers().get(newSize - 1);
        assertEquals(3, customerDto.getId());
        assertEquals("Kate", customerDto.getName());
    }

    @Test
    public void removeCustomerTest(){
        CustomerDTO customer = new CustomerDTO(3, "Kate");
        customerController.addNewCustomer(customer);
        customerController.removeCustomer(3);
        CustomersGetResponse customers = customerController.getCustomers();

        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());

        assertEquals(2, customers.getCustomers().size());
    }

}