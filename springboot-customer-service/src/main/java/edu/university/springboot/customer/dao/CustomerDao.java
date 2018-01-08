package edu.university.springboot.customer.dao;

import edu.university.springboot.customer.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer customerId);
    void addCustomer(Customer customer);
    void addCustomers(List<Customer> customers);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer customerId);
    void deleteAllCustomers();

}
