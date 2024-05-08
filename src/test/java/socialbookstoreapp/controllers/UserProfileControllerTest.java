package socialbookstoreapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class UserProfileControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

}
