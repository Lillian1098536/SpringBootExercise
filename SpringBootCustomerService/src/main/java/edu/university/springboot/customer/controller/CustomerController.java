package edu.university.springboot.customer.controller;


import edu.university.springboot.customer.dto.CustomerDto;
import edu.university.springboot.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerDtos);
    }

    @ResponseBody
    @RequestMapping(value = "/getSortedCustomers", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getSortedCustomers() {
        List<CustomerDto> customerDtos = customerService.getSortedCustomers(customerService.getAllCustomers());
        return ResponseEntity.ok().body(customerDtos);
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return ResponseEntity.ok().body(customerDto);
    }

    @ResponseBody
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @ResponseBody
    @RequestMapping(value = "/addCustomers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> addCustomer(@RequestBody List<CustomerDto> customerDtos) {
        customerService.addCustomers(customerDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDtos);
    }

    @ResponseBody
    @RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCustomer/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Integer customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerDto);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAllCustomers", method = RequestMethod.DELETE)
    public ResponseEntity<List<CustomerDto>> deleteAllCustomers() {
        customerService.deleteAllCustomers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
