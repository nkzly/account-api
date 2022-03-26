package com.nkzly.accountapi;

import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.repository.CustomerRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountApiApplication implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    public AccountApiApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Customer customer = customerRepository.save(new Customer("Nihat", "Kzly"));
        Customer customer2 = customerRepository.save(new Customer("Duman", "Kzly"));

        System.out.println(customer);
        System.out.println(customer2);

    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version) {
        return new OpenAPI()
                .info(new Info()
                        .title("Account API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Account API Licence")));
    }

}
