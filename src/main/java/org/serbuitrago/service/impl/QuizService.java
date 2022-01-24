package org.serbuitrago.service.impl;

import org.serbuitrago.model.Quiz;
import org.serbuitrago.repository.IQuizRepository;
import org.serbuitrago.service.IQuizService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuizService implements IQuizService{
	
	private final IQuizRepository iQuizRepository;
	
	@Override
	public Quiz findByName(String name) {
		return iQuizRepository.findAll().stream().filter(quiz -> quiz.getName().contains(name)).findFirst()
				.orElseThrow();
	}
}
