package com.example.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public Employee findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Employee e) {
        repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
