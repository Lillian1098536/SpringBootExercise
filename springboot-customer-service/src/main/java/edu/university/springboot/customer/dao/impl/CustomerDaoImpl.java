package edu.university.springboot.customer.dao.impl;

import edu.university.springboot.customer.dao.CustomerDao;
import edu.university.springboot.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> getAllCustomers() {
        String hql = "FROM Customer as cst ORDER BY cst.customerId";
        log.info("Query getAllCustomers={}", hql);
        return (List<Customer>)entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        log.info("getCustomerById={}", customerId);
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customer.setCreatedAt(Calendar.getInstance().getTime());
        customer.setUpdatedAt(Calendar.getInstance().getTime());
        log.info("addCustomer={}", customer);
        entityManager.persist(customer);
    }

    @Override
    public void addCustomers(List<Customer> customers) {
        customers.forEach(c -> this.addCustomer(c));
    }

    @Override
    public void updateCustomer(Customer customer) {
        customer.setUpdatedAt(Calendar.getInstance().getTime());
        log.info("updateCustomer={}", customer);
        Customer updatedCustomer = this.getCustomerById(customer.getCustomerId());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setMembershipLevel(customer.getMembershipLevel());
        updatedCustomer.setParentFamilyMember(customer.getParentFamilyMember());
        entityManager.flush();
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        log.info("deleteCustomer customerId={}", customerId);
        entityManager.remove(this.getCustomerById(customerId));
    }

    @Override
    public void deleteAllCustomers() {
        String nativeQuery = "TRUNCATE customer";
        log.info("Query deleteAllCustomers={}", nativeQuery);
        entityManager.createNativeQuery(nativeQuery);
    }
}
