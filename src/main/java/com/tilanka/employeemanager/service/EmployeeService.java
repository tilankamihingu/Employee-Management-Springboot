package com.tilanka.employeemanager.service;

import com.tilanka.employeemanager.exception.UserNotFoundException;
import com.tilanka.employeemanager.model.Employee;
import com.tilanka.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployee(){
        return employeeRepo.findAll();
    }

   /* public Employee updateEmployee(Employee employee){
        //return employeeRepo.save(employee);
    }*/

    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("user by id" + id + "not found" ));
    }

    /*public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }*/



    //public void updateEmployee(Long id, Employee employee) {

    //}



    public Employee updateEmployee(Long id, Employee employee) {
        employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("user by id" + id + "not found"));

        employee.setName(employee.getName());
        employee.setEmail(employee.getEmail());
        employee.setJobTitle(employee.getJobTitle());
        employee.setPhone(employee.getPhone());
        employee.setImageUrl(employee.getImageUrl());

        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("user by id" + id + "not found"));

        employeeRepo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);

    }
}
