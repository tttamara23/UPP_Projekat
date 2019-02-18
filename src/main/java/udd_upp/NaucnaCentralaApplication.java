package udd_upp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableJpaRepositories
public class NaucnaCentralaApplication implements WebMvcConfigurer {

	
	public static void main(String[] args) {
		SpringApplication.run(NaucnaCentralaApplication.class, args);
	}	

}
