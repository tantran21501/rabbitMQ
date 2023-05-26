package net.javaguides.departmentservice;

import net.javaguides.departmentservice.entity.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);

	}

}
