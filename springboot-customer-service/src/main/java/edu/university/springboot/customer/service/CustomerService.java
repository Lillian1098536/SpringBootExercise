package edu.university.springboot.customer.service;

import edu.university.springboot.customer.dao.CustomerDao;
import edu.university.springboot.customer.dto.CustomerDto;
import edu.university.springboot.customer.exception.CustomerNotFoundException;
import edu.university.springboot.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@EntityScan(basePackageClasses = {Customer.class})
public class CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerDao customerDao;


    public CustomerService(ModelMapper modelMapper, CustomerDao customerDao) {
        this.modelMapper = modelMapper;
        this.customerDao = customerDao;
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerDao.getAllCustomers();
        if (customers == null || customers.size() == 0) {
            log.info("getAllCustomers not found");
            throw new CustomerNotFoundException();
        }
        return customers.stream().map(c -> modelMapper.map(c, CustomerDto.class)).collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Integer customerId) {
        Customer customer = customerDao.getCustomerById(customerId);
        if (customer == null) {
            log.info("getCustomerById not found");
            throw new CustomerNotFoundException();
        }
        return modelMapper.map(customer, CustomerDto.class);
    }

    public void addCustomer(CustomerDto customerDto) {
        customerDao.addCustomer(modelMapper.map(customerDto, Customer.class));
    }

    public void addCustomers(List<CustomerDto> customerDtos) {
        List<Customer> customers = customerDtos.stream().map(dto -> modelMapper.map(dto, Customer.class))
                .collect(Collectors.toList());
        customerDao.addCustomers(customers);
    }

    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = customerDao.getCustomerById(customerDto.getCustomerId());
        if (customer == null) {
            log.info("updateCustomer not found");
            throw new CustomerNotFoundException();
        }
        customerDao.updateCustomer(modelMapper.map(customerDto, Customer.class));
    }

    public void deleteCustomer(Integer customerId) {
        customerDao.deleteCustomer(customerId);
    }

    public void deleteAllCustomers() {
        customerDao.deleteAllCustomers();
    }

    /**
     * Sort the input Customers list in the order of
     * 1) sort in order membershipLevel where (PREMIUM > ENTERPRISE > COMMUNITY)
     * 2) If a member has a parent, the parent and all other siblings are treated at highest membership level.
     * 3) Also they are grouped together with parent being the first member.
     * 4) Siblings are sorted alphabetically by first name followed by last name
     * 5) If 2 parents have same membership level then they are sorted by first name followed by last name
     * 6) There can be duplicates of firstName and lastName however the id will be different
     * Note: You are required to use Java 8 streams
     * Bonus if you can use parallel streams where ever possible
     *
     * @return
     */
    public List<CustomerDto> getSortedCustomers(List<CustomerDto> customers) {
        if (customers == null || customers.size() == 0) {
            return null;
        }
        familyMembershipLevelMap = new HashMap<>();
        familyHeadMap = FAMILY_HEAD_MAP.apply(customers);
        customers.stream().filter(c -> c != null).forEach(c -> {
            int key = c.getParentFamilyMember() == -1 ? c.getCustomerId() : c.getParentFamilyMember();
            int value = Optional.ofNullable(familyMembershipLevelMap.get(key)).orElse(Integer.MAX_VALUE);
            familyMembershipLevelMap.put(key,
                    value < c.getMembershipLevel().ordinal() ? value : c.getMembershipLevel().ordinal());
        });
        log.info("familyMembershipLevelMap={}", familyMembershipLevelMap);
        log.info("familyHeadMap={}", familyHeadMap);
        return customers.parallelStream().filter(c -> c != null).sorted(customerDtoComparator
                .thenComparing(CustomerDto::getFirstName).thenComparing(CustomerDto::getLastName)
                .thenComparing(CustomerDto::getCustomerId)).collect(Collectors.toList());
    }

    protected final static Function<List<CustomerDto>, Map<Integer, CustomerDto>> FAMILY_HEAD_MAP = (customerDtos) -> customerDtos.stream()
            .filter(c -> c.getParentFamilyMember() == -1).collect(Collectors.toMap(CustomerDto::getCustomerId, Function.identity()));

    protected Map<Integer, Integer> familyMembershipLevelMap;
    protected Map<Integer, CustomerDto> familyHeadMap;

    private Comparator<CustomerDto> customerDtoComparator = new Comparator<CustomerDto>() {

        @Override
        public int compare(CustomerDto o1, CustomerDto o2) {
            if (o1 == o2) {
                return 0;
            }
            int familyNum1 = o1.getParentFamilyMember() == -1 ? o1.getCustomerId() : o1.getParentFamilyMember();
            int familyNum2 = o2.getParentFamilyMember() == -1 ? o2.getCustomerId() : o2.getParentFamilyMember();

            int memberLevel1 = familyMembershipLevelMap.get(familyNum1);
            int mumberLevel2 = familyMembershipLevelMap.get(familyNum2);

            CustomerDto head1 = familyHeadMap.get(familyNum1);
            CustomerDto head2 = familyHeadMap.get(familyNum2);

            if (memberLevel1 != mumberLevel2) {
                return memberLevel1 - mumberLevel2;
            } else if (o1.getParentFamilyMember() == -1) {
                return -1;
            } else if (o2.getParentFamilyMember() == -1) {
                return 1;
            }
            return 0;
        }
    };

}
