package org.serbuitrago.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.MockitoAnnotations;
import org.serbuitrago.model.Quiz;
import org.serbuitrago.repository.IQuestionRepository;
import org.serbuitrago.repository.IQuizRepository;

import static org.serbuitrago.Data.*;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

	@Mock
	IQuizRepository iQuizRepository;
	@Mock
	IQuestionRepository iQuestionRepository;

	@InjectMocks
	QuizService iQuizService;
	 
	Long VALUE_FIND_BY_ID = 1L;
	String VALUE_FIND_BY_NAME = "Matematica";
	String VALUE_FIND_BY_NAME_QUESTION = "Derivadas";

	@BeforeEach 
	void beforeEach() {	
		//MockitoAnnotations.openMocks(this);
	}

	@Tag("quiz")
	@Nested
	@DisplayName("Clase prueba nombre del quiz.")
	class QuizServiceTestFindByName {
		
		@BeforeEach 
		void beforeEach() {	
			when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ);
		}
		
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
		
		@BeforeEach 
		void beforeEach() {	
			when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ);
			when(iQuestionRepository.findQuestionByQuizId(VALUE_FIND_BY_ID)).thenReturn(DATA_LIST_QUESTION);
		}
		
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
	
	@Tag("quiz")
	@Tag("question")
	@Nested
	@DisplayName("Clase prueba el registro del quiz y preguntas del mismo.")
	class QuizServiceTestSave{
		
		@BeforeEach 
		void beforeEach() {	
			when(iQuizRepository.save(any(Quiz.class))).thenReturn(DATA_QUIZ);
		}
		
		@Test
		@DisplayName("Registra un quiz")
		void save(){
			Quiz quiz = iQuizRepository.save(DATA_QUIZ);
			
			assertNotNull(quiz);
			assertEquals(DATA_QUIZ.getId(), quiz.getId());
			
			verify(iQuizRepository).save(any(Quiz.class));
			verify(iQuestionRepository).save(anyList());
		}
	}
}
