package edu.university.springboot.customer.service;

import edu.university.springboot.customer.dao.CustomerDao;
import edu.university.springboot.customer.dto.CustomerDto;
import edu.university.springboot.customer.model.MembershipLevel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@Slf4j
@RunWith(JUnit4.class)
public class CustomerServiceTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CustomerDao customerDao;
    @InjectMocks
    private CustomerService customerService;

    private CustomerDto parentPremium1;
    private CustomerDto xchildPremium1;
    private CustomerDto achildPremium1;
    private CustomerDto parentEnterprise1;
    private CustomerDto xchildEnterprise1;
    private CustomerDto achildEnterprise1;
    private CustomerDto parentCommunity1;
    private CustomerDto xchildCommunity1;
    private CustomerDto achildCommunity1;
    private CustomerDto parentCommunity2;

    @Before
    public void setUp() throws Exception {

        modelMapper = mock(ModelMapper.class);
        customerDao = mock(CustomerDao.class);
        customerService = new CustomerService(modelMapper, customerDao);

        parentPremium1 = CustomerDto.of(1, "John", "Tester", MembershipLevel.PREMIUM, -1);
        xchildPremium1 = CustomerDto.of(11, "xbabyjohn", "Tester", MembershipLevel.COMMUNITY, 1);
        achildPremium1 = CustomerDto.of(12, "ababyjohn", "Tester", MembershipLevel.PREMIUM, 1);

        parentEnterprise1 = CustomerDto.of(2, "Michael", "Manager", MembershipLevel.COMMUNITY, -1);
        xchildEnterprise1 = CustomerDto.of(21, "xbabyMichael", "Manager", MembershipLevel.ENTERPRISE, 2);
        achildEnterprise1 = CustomerDto.of(22, "ababyMichael", "Manager", MembershipLevel.COMMUNITY, 2);

        parentCommunity1 = CustomerDto.of(3, "Dave", "Developer", MembershipLevel.COMMUNITY, -1);
        xchildCommunity1 = CustomerDto.of(31, "xbabyDave", "Developer", MembershipLevel.COMMUNITY, 3);
        achildCommunity1 = CustomerDto.of(32, "ababyDave", "Developer", MembershipLevel.COMMUNITY, 3);

        parentCommunity2 = CustomerDto.of(4, "Apple", "Developer", MembershipLevel.COMMUNITY, -1);

    }

    @Test
    public void getSortedCustomers() throws Exception {

        List<CustomerDto> input = new ArrayList<>();
        input.add(xchildEnterprise1);
        input.add(achildPremium1);
        input.add(parentCommunity1);
        input.add(achildEnterprise1);
        input.add(parentEnterprise1);
        input.add(parentPremium1);
        input.add(xchildPremium1);
        input.add(parentCommunity2);
        input.add(xchildCommunity1);
        input.add(achildCommunity1);

        List<CustomerDto> expected = new ArrayList<>();
        expected.add(parentPremium1);
        expected.add(achildPremium1);
        expected.add(xchildPremium1);

        expected.add(parentEnterprise1);
        expected.add(achildEnterprise1);
        expected.add(xchildEnterprise1);

        expected.add(parentCommunity2);

        expected.add(parentCommunity1);
        expected.add(achildCommunity1);
        expected.add(xchildCommunity1);

        List<CustomerDto> result = customerService.getSortedCustomers(input);
        log.debug("Sorted Customers={}", result);
        assertEquals(expected, result);
    }

}