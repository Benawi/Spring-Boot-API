package com.bena.springboot.bena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SpringBootApplication
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
@RestController
@RequestMapping("bena/api")
public class Application {
	private final CustomerRepository customerRepository;

	public Application(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping
	public List<Customer> getCostomer()
	{
		return customerRepository.findAll();
	}
	record NewCustoemrRequest(String name,String email, int age){}
	@PostMapping
	public void addCustomer(@RequestBody NewCustoemrRequest newCustoemrRequest){
		Customer customer=new Customer();
		customer.setName(newCustoemrRequest.name);
		customer.setEmail(newCustoemrRequest.email);
		customer.setAge(newCustoemrRequest.age);
		customerRepository.save(customer);

	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}
	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id,@RequestBody NewCustoemrRequest newCustoemrRequest){
		Customer customer=new Customer();
		customerRepository.findById(id).ifPresent(update->{
			update.setName(newCustoemrRequest.name);
			update.setEmail(newCustoemrRequest.email);
			update.setAge(newCustoemrRequest.age);
			customerRepository.save(update);
		});

	}
//   @GetMapping("/")
//	public Great message(){
//		return  new Great("hi this is my demo",List.of("java","go","python"),new Person("benawi",29,30_000));
//   }
//  record Person(String name,int age, double salary){}
//	record Great(String greeting,List<String> programing, Person person){
//  }
//	class Great {
//		private final  String greating;
//
//		Great(String greating) {
//			this.greating = greating;
//		}
//
//		public String getGreating() {
//			return greating;
//		}
//
//		@Override
//		public String toString() {
//			return "Great{" +
//					"greating='" + greating + '\'' +
//					'}';
//		}
//
//		@Override
//		public boolean equals(Object o) {
//			if (this == o) return true;
//			if (o == null || getClass() != o.getClass()) return false;
//			Great great = (Great) o;
//			return Objects.equals(greating, great.greating);
//		}
//
//		@Override
//		public int hashCode() {
//			return Objects.hash(greating);
//		}
//	}

}
