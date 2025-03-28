package com.example.e_hotel.controller;

import com.example.e_hotel.model.Customer;
import com.example.e_hotel.repository.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable int id) {
        return customerRepository.findById(id);
    }

    @GetMapping("/login")
    public ResponseEntity<Customer> loginByName(@RequestParam String name) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer c : customers) {
            if (c.getFullName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(c);
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        updatedCustomer.setCustomerID(id);
        return customerRepository.save(updatedCustomer);
    }



    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerRepository.deleteById(id);
    }
}
