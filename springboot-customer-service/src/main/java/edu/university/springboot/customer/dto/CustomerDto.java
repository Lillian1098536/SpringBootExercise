package edu.university.springboot.customer.dto;

import edu.university.springboot.customer.model.MembershipLevel;

import java.io.Serializable;

public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer customerId;
    private String firstName;
    private String lastName;
    private MembershipLevel membershipLevel;
    private int parentFamilyMember;

    public CustomerDto() { }

    public CustomerDto(Integer customerId, String firstName, String lastName, MembershipLevel membershipLevel,
                       int parentFamilyMember) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.membershipLevel = membershipLevel;
        this.parentFamilyMember = parentFamilyMember;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public CustomerDto setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public CustomerDto setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
        return this;
    }

    public int getParentFamilyMember() {
        return parentFamilyMember;
    }

    public CustomerDto setParentFamilyMember(int parentFamilyMember) {
        this.parentFamilyMember = parentFamilyMember;
        return this;
    }

    public static CustomerDto of(Integer customerId, String firstName, String lastName,
                                 MembershipLevel membershipLevel, int parentFamilyMember) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customerId).setFirstName(firstName).setLastName(lastName)
                .setMembershipLevel(membershipLevel).setParentFamilyMember(parentFamilyMember);
        return customerDto;
    }

}
