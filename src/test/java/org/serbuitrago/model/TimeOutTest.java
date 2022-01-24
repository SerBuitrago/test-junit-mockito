package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TimeOutTest {
	
	@Tag("Second")
	@Nested
	class TimeOutTestSecond{
		@Test
		@DisplayName("Permite probar el timeout de 5s.")
		@Timeout(3)
		void timeOut() throws InterruptedException {
			System.out.println("Probando TimeOut 5s.");
			TimeUnit.SECONDS.sleep(2);
		}
	}
	
	@Tag("Milisecond")
	@Nested
	class TimeOutTestMilisecond{
		@Test
		@DisplayName("Permite probar el timeout de 1100ms.")
		@Timeout(value = 1100, unit = TimeUnit.MILLISECONDS)
		void timeOut() throws InterruptedException {
			System.out.println("Probando TimeOut 1100ms.");
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}
	
	@Tag("Assert")
	@Nested
	class TimeOutTestAssert{
		
		@Tag("Milisecond")
		@Tag("Second")
		@Test
		@DisplayName("Probando TimeOut con Assertions 4000ms.")
		void timeAssert(){
			System.out.println("Probando TimeOut con Assertions 5s.");
			assertTimeout(Duration.ofSeconds(5), () ->{
				TimeUnit.MILLISECONDS.sleep(4000);
			});
		}
	}

}
