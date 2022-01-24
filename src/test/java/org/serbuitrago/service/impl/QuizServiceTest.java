package org.serbuitrago.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.serbuitrago.model.Quiz;
import org.serbuitrago.repository.IQuizRepository;
import org.serbuitrago.repository.impl.QuizRepository;
import org.serbuitrago.service.IQuizService;

public class QuizServiceTest {
	
	@Nested
	@DisplayName("Clase prueba nombre del quiz.")
	class QuizServiceTestFindByName{
		
		@Test
		@DisplayName("Consulta por el nombre.")
		void findByName() {
			
			IQuizRepository repository = new QuizRepository();
			IQuizService service = new QuizService(repository);
			
			Quiz quiz = service.findByName("Matematica");
			
			assertNotNull(quiz);
			assertEquals(1L, quiz.getId());
			assertEquals("Matematica", quiz.getName());
		}
	}
}
