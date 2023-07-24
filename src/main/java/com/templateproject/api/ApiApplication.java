package com.templateproject.api;

import com.templateproject.api.entity.Role;
import com.templateproject.api.repository.RoleRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(
			RoleRepository roleRepository
	) {
		return (args) -> {
			// manually creates roles on startup
			if (roleRepository.findByName("ROLE_USER").isEmpty()) {
				Role userRole = new Role("ROLE_USER");
				roleRepository.save(userRole);
			}
			if (roleRepository.findByName("ROLE_PETSITTER").isEmpty()) {
				Role petSitterRole = new Role("ROLE_PETSITTER");
				roleRepository.save(petSitterRole);
			}
		};
	}

}
