package org.cap.testmgt.service;

import java.math.BigInteger;

import org.cap.testmgt.entities.Question;

public interface IQuestionService {
	Question addQuestions(Question question);

	Question updateQuestions(BigInteger questionId, Question question);

	Question deleteQuestions(BigInteger questionId);

	Question findById(BigInteger questionId);
}
