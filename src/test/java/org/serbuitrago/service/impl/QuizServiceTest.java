package org.serbuitrago.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
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
	
	@Captor
	ArgumentCaptor<Long> captor;

	Long VALUE_FIND_BY_ID = 1L;
	String VALUE_FIND_BY_NAME = "Matematica";
	String VALUE_FIND_BY_NAME_QUESTION = "Derivadas";

	@BeforeEach
	void beforeEach() {
		// MockitoAnnotations.openMocks(this);
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
	class QuizServiceTestSave {

		@BeforeEach
		void beforeEach() {
			// when(iQuizRepository.save(any(Quiz.class))).thenReturn(DATA_QUIZ);
			when(iQuizRepository.save(any(Quiz.class))).then(new Answer<Quiz>() {

				Long next = DATA_LIST_QUIZ.get(DATA_LIST_QUIZ.size() - 1).getId();

				@Override
				public Quiz answer(InvocationOnMock invocation) throws Throwable {
					Quiz quiz = invocation.getArgument(0);
					quiz.setId(next++);
					return quiz;
				}
			});
		}

		@Test
		@DisplayName("Registra un quiz")
		void save() {
			Quiz quiz = iQuizService.save(DATA_QUIZ);

			assertNotNull(quiz);
			assertEquals(DATA_LIST_QUIZ.get(DATA_LIST_QUIZ.size() - 1).getId(), quiz.getId());

			verify(iQuizRepository).save(any(Quiz.class));
			verify(iQuestionRepository).save(anyList());
		}
	}

	@Tag("question")
	@Nested
	@DisplayName("Clase prueba las preguntas del quiz.")
	class QuizServiceTestQuestionId {
		@Test
		@DisplayName("Probando excepcion.")
		void exception() {
			when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ_ID_NULL);
			when(iQuestionRepository.findQuestionByQuizId(null)).thenThrow(IllegalArgumentException.class);

			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				iQuizService.findQuizByName(VALUE_FIND_BY_NAME);
			});

			assertEquals(IllegalArgumentException.class, exception.getClass());

			verify(iQuizRepository).findAll();
			verify(iQuestionRepository).findQuestionByQuizId(null);
		}
	}

	@Tag("matchers")
	@Nested
	@DisplayName("Clase prueba los argumentos matchers.")
	class QuizServiceTestMatchers {

		@Test
		@DisplayName("Probando matchers.")
		void matchers() {
			when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ);
			when(iQuestionRepository.findQuestionByQuizId(anyLong())).thenReturn(DATA_LIST_QUESTION);

			iQuizService.findQuizByName(VALUE_FIND_BY_NAME);

			verify(iQuizRepository).findAll();
			verify(iQuestionRepository).findQuestionByQuizId(argThat(arg -> arg.equals(VALUE_FIND_BY_ID)));
		}

		@Test
		@DisplayName("Probando matchers con ArgumentMatcher.")
		void matchersII() {
			when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ);
			when(iQuestionRepository.findQuestionByQuizId(anyLong())).thenReturn(DATA_LIST_QUESTION);

			iQuizService.findQuizByName(VALUE_FIND_BY_NAME);

			verify(iQuizRepository).findAll();
			verify(iQuestionRepository).findQuestionByQuizId(argThat(new ArgsMatchersTest()));
		}

	}

	@Tag("capture")
	@Nested
	@DisplayName("Clase prueba la captura de argumentos.")
	class QuizServiceTestCapture {
		
		@Test
		@DisplayName("Probando la captura.")
		void capture() {
			when(iQuizRepository.findAll()).thenReturn(DATA_LIST_QUIZ);
			when(iQuestionRepository.findQuestionByQuizId(anyLong())).thenReturn(DATA_LIST_QUESTION);
			
			iQuizService.findQuizByName(VALUE_FIND_BY_NAME);
			
			//ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

			verify(iQuizRepository).findAll();
			verify(iQuestionRepository).findQuestionByQuizId(captor.capture());
			
			assertEquals(VALUE_FIND_BY_ID, captor.getValue());
		}
	}

	static class ArgsMatchersTest implements ArgumentMatcher<Long> {

		private Long argument;

		@Override
		public boolean matches(Long argument) {
			this.argument = argument;
			return argument != null && argument > 0;
		}

		@Override
		public String toString() {
			return "Es para un mensaje personalizando de error que imprime mockito en caso de que falle el test del id "
					+ argument + ".";
		}
	}
}
