package org.serbuitrago.repository;

import java.util.List;

import org.serbuitrago.model.Quiz;

public interface IQuizRepository {
	
	List<Quiz> findAll();
	
	Quiz save(Quiz quiz);
}
