package org.serbuitrago.service.impl;

import java.util.List;
import java.util.Optional;

import org.serbuitrago.model.Quiz;
import org.serbuitrago.repository.IQuestionRepository;
import org.serbuitrago.repository.IQuizRepository;
import org.serbuitrago.service.IQuizService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuizService implements IQuizService {

	private final IQuizRepository iQuizRepository;
	private final IQuestionRepository iQuestionRepository;

	@Override
	public Optional<Quiz> findByName(String name) {
		return iQuizRepository.findAll().stream().filter(quiz -> quiz.getName().contains(name)).findFirst();
	}

	@Override
	public Quiz findQuizByName(String name) {
		Optional<Quiz> optional = findByName(name);
		Quiz quiz = null;
		if (optional.isPresent()) {
			quiz = optional.orElseThrow();
			List<String> questions = iQuestionRepository.findQuestionByQuizId(quiz.getId());
			quiz.setQuiestions(questions);
		}
		return quiz;
	}

	@Override
	public Quiz save(Quiz quiz) {
		if(!quiz.getQuiestions().isEmpty()) {
			iQuestionRepository.save(quiz.getQuiestions());
		}
		return iQuizRepository.save(quiz);
	}
}
