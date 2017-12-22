package edu.university.security.controller;

import edu.university.security.model.Employee;
import edu.university.security.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeData;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST)
    public String newEmployee(Employee employee) {
        employeeData.save(employee);
        return ("redirect:/employee/listEmployees");
    }

    @RequestMapping(value = "/addNewEmployee", method = RequestMethod.GET)
    public ModelAndView addNewEmployee() {
        Employee emp = new Employee();
        return new ModelAndView("newEmployee", "form", emp);
    }

    @RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
    public ModelAndView employees() {
        List<Employee> allEmployees = employeeData.findAll();
        return new ModelAndView("allEmployees", "employees", allEmployees);
    }

    @ResponseBody
    @RequestMapping(value = "/listPageableEmployees", method = RequestMethod.GET)
    public ResponseEntity<Page<Employee>> pageableEmployees(Pageable pageable) {
        return ResponseEntity.ok().body(employeeData.findAll(pageable));
    }

}
