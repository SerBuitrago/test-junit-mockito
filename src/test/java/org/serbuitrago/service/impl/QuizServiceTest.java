package org.serbuitrago.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.serbuitrago.model.Quiz;
import org.serbuitrago.repository.IQuestionRepository;
import org.serbuitrago.repository.IQuizRepository;
import org.serbuitrago.service.IQuizService;

import static org.serbuitrago.Data.*;

public class QuizServiceTest {

	IQuizRepository iQuizRepository;
	IQuestionRepository iQuestionRepository;

	IQuizService iQuizService;
	 
	Long VALUE_FIND_BY_ID = 1L;
	String VALUE_FIND_BY_NAME = "Matematica";
	String VALUE_FIND_BY_NAME_QUESTION = "Derivadas";

	@BeforeEach
	void beforeEach() {
		iQuizRepository = mock(IQuizRepository.class);
		iQuestionRepository = mock(IQuestionRepository.class);

		iQuizService = new QuizService(iQuizRepository, iQuestionRepository);
		
		when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ);
		when(iQuestionRepository.findQuestionByQuizId(VALUE_FIND_BY_ID)).thenReturn(DATA_LIST_QUESTION);
	}

	@Tag("quiz")
	@Nested
	@DisplayName("Clase prueba nombre del quiz.")
	class QuizServiceTestFindByName {
		@Test
		@DisplayName("Consulta por el nombre.")
		void findByName() {
			Optional<Quiz> quiz = iQuizService.findByName(VALUE_FIND_BY_NAME);
			assertTrue(quiz.isPresent());
		}
	}

	@Tag("quiz")
	@Tag("question")
	@Nested
	@DisplayName("Clase prueba las preguntas del quiz.")
	class QuizServiceTestFindQuestion {
		@Test
		@DisplayName("Consulta preguntas por el nombre del quiz.")
		void findQuizByName() {
			Quiz quiz = iQuizService.findQuizByName(VALUE_FIND_BY_NAME);
			assertEquals(DATA_LIST_QUESTION.size(), quiz.getQuiestions().size());
			assertTrue(quiz.getQuiestions().contains(VALUE_FIND_BY_NAME_QUESTION));
		}
		
		@Test
		@DisplayName("Consulta preguntas por el nombre del quiz y verifica que se ha llamado el metodo.")
		void findQuizByNameVerify() {
			iQuizService.findQuizByName(VALUE_FIND_BY_NAME);
			verify(iQuizRepository).findAll();
			verify(iQuestionRepository).findQuestionByQuizId(VALUE_FIND_BY_ID);
		}
	}
}
