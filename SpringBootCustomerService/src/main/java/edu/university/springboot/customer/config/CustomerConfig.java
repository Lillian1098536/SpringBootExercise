package edu.university.springboot.customer.config;

import edu.university.springboot.customer.dao.CustomerDao;
import edu.university.springboot.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomerService customerService(final ModelMapper modelMapper, final CustomerDao customerDao) {
        return new CustomerService(modelMapper, customerDao);
    }

}
