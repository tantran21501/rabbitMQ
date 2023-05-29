package net.javaguides.organizationservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@OpenAPIDefinition(
		info = @Info(
				title = "Organization Service REST APIs",
				description = "Organization Service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name="Tan Tran",
						email="tan09qlmt@gmail.com",
						url="https://github.com/tantran21501"
				),
				license = @License(
						name="Apache License, Version 2.0",
						url="https://github.com/tantran21501"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Organization Service REST APIs Documentation",
				url="https://github.com/tantran21501"
		)
)
@SpringBootApplication
//@EnableEurekaClient
public class OrganizationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
