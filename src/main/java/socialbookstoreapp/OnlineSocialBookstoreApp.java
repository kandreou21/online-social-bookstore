package socialbookstoreapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})  // na fugei
public class OnlineSocialBookstoreApp {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSocialBookstoreApp.class, args);
	}

}
