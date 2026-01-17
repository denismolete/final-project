package com.example.finalproject.config;

import com.example.finalproject.model.Product;
import com.example.finalproject.model.Role;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", "admin", Role.ADMIN));
            userRepository.save(new User("manager", "manager", Role.USER));
            userRepository.save(new User("client", "client", Role.CLIENT));
            System.out.println("Default users created: admin/admin, manager/manager, client/client");
        }

        if (productRepository.count() == 0) {
            productRepository.save(new Product("Laptop", 1200.00, 10));
            productRepository.save(new Product("Mouse", 25.50, 50));
            productRepository.save(new Product("Keyboard", 75.00, 30));
            System.out.println("Default products created");
        }
    }
}
