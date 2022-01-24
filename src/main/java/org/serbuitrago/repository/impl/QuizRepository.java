package org.serbuitrago.repository.impl;

import java.util.Arrays;
import java.util.List;

import org.serbuitrago.model.Quiz;
import org.serbuitrago.repository.IQuizRepository;

public class QuizRepository implements IQuizRepository {

	@Override
	public List<Quiz> findAll() {
		return Arrays.asList(new Quiz(1L, "Matematica"), new Quiz(2L, "Religion"), new Quiz(3L, "Etica"),
				new Quiz(4L, "Social"), new Quiz(5L, "Ingles"), new Quiz(6L, "Informatica"),
				new Quiz(7L, "Estadistica"), new Quiz(8L, "Filosofia"), new Quiz(9L, "Tecnologia"),
				new Quiz(10L, "Lengua Castellena"), new Quiz(11L, "Frances"), new Quiz(12L, "Italiano"));
	}
}
