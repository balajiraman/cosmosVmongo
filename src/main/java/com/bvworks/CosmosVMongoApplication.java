package com.bvworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.bvworks.model.Customer;
import com.bvworks.nosql.mongo.repository.CustomerRepository;

@SpringBootApplication
@RestController


public class CosmosVMongoApplication {

	@Autowired
	private CustomerRepository repository;

	@Value("${connectionString}")
	private String connectionString = "defaultValue\n";

	@GetMapping("get")
	public String get(){
		
		return connectionString;
	}

	@GetMapping("/findAll")
	public String findAll(){
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		return ("FindAll - Method Executed");
	}

	@GetMapping("/create")
	public String create(){
		System.out.println("Poupulating Test Records...");
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		return ("Successfully Created Records..");
	}

	@GetMapping("/delete")
	public String delete(){
		System.out.println("Deleting Records...");
		repository.deleteAll();
		return ("Request for Deleting Records");
	}
	public void run (String... varl) throws Exception{
		System.out.println(String.format("%nConnection String Stored in Azure Key Value: %n%s%n",connectionString));
	}

	public static void main(String[] args) {
		SpringApplication.run(CosmosVMongoApplication.class, args);
	}

}
