package org.serbuitrago.service;

import java.util.Optional;

import org.serbuitrago.model.Quiz;

public interface IQuizService {
	
	Optional<Quiz> findByName(String name);
	
	Quiz findQuizByName(String name);
	
	Quiz save(Quiz quiz);
}
