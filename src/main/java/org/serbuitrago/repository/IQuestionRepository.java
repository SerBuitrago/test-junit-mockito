package org.serbuitrago.repository;

import java.util.List;

public interface IQuestionRepository {
	
	List<String> findQuestionByQuizId(Long idQuiz);
	
	void save(List<String> questions);
}
