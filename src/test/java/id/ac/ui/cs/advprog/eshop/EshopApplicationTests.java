package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTest {

	@Test
	void contextLoads() {
		// This will fail if the Spring application context cannot start
	}

	@Test
	void mainMethodRunsWithoutExceptions() {
		assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
	}
}
