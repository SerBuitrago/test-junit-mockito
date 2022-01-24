package org.serbuitrago.service;

import org.serbuitrago.model.Quiz;

public interface IQuizService {
	
	Quiz findByName(String name);
}
