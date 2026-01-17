package com.example.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.boot.CommandLineRunner demo(
			com.example.finalproject.service.SQLStorageEngine sqlStorageEngine) {
		return (args) -> {
			try {
				System.out.println("Checking SQL Storage Engine...");
				sqlStorageEngine.checkConnection();
				int count = sqlStorageEngine.countUsersDirectly();
				System.out.println("Total users in DB (via SQLStorageEngine): " + count);

				// Demo ValidationException
				if (count < 0) {
					throw new com.example.finalproject.exception.ValidationException("Count cannot be negative");
				}

			} catch (com.example.finalproject.exception.StorageException e) {
				System.err.println("Storage Error: " + e.getMessage());
			} catch (com.example.finalproject.exception.ValidationException e) {
				System.err.println("Validation Error: " + e.getMessage());
			}
		};
	}
}
