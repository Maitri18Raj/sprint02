package org.cap.testmgt.service;

import java.math.BigInteger;
import java.util.Optional;

import org.cap.testmgt.dao.IQuestionDao;
import org.cap.testmgt.entities.Question;
import org.cap.testmgt.exceptions.QuestionNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {
	private IQuestionDao questionDao;

	public IQuestionDao getQuestionDao() {
		return questionDao;
	}

	@Autowired
	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public Question addQuestions(Question question) {
		question = questionDao.save(question);
		return null;
	}

	@Override
	public Question updateQuestions(BigInteger questionId, Question question) {
		Boolean exists = questionDao.existsById(questionId);
		if (exists) {
			question = questionDao.save(question);
			return question;
		}
		throw new QuestionNotFoundException("Question not found for id=" + questionId);
	}

	@Override
	public Question deleteQuestions(BigInteger questionId) {
		Question question = findById(questionId);
		questionDao.delete(question);
		return question;
	}

	@Override
	public Question findById(BigInteger questionId) {
		Optional<Question> optional = questionDao.findById(questionId);
		if (optional.isPresent()) {
			Question question = optional.get();
			return question;
		}
		throw new QuestionNotFoundException("Question not found for id=" + questionId);
	}

}
