package com.example.e_hotel.controller;

import com.example.e_hotel.exceptions.ResourceNotFoundException;
import com.example.e_hotel.model.Customer;
import com.example.e_hotel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer c = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID " + id));
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        // Could do additional checks, e.g. ensure email is not taken
        Customer saved = customerRepo.save(newCustomer);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedData) {
        Customer existing = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID " + id));
        existing.setEmail(updatedData.getEmail());
        existing.setPassword(updatedData.getPassword());
        existing.setName(updatedData.getName());
        // etc
        Customer saved = customerRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        try {
            customerRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
