package org.cap.testmgt.controller;

import java.math.BigInteger;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cap.testmgt.dto.QuestionDto;
import org.cap.testmgt.entities.Question;
import org.cap.testmgt.exceptions.QuestionNotFoundException;
import org.cap.testmgt.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionRestController {

	private static final Logger Log = LoggerFactory.getLogger(QuestionRestController.class);
	@Autowired
	private IQuestionService service;

	@GetMapping("/get/{id}")
	public ResponseEntity<Question> getQuestion(@PathVariable("id") BigInteger questionId) {
		Question question = service.findById(questionId);
		ResponseEntity<Question> response = new ResponseEntity<>(question, HttpStatus.OK);
		return response;

	}

	@PostMapping("/add")
	public ResponseEntity<Question> createQuestion(@RequestBody @Valid QuestionDto questionDto) {
		Question question = convertFromDto(questionDto);
		question = service.addQuestions(question);
		ResponseEntity<Question> response = new ResponseEntity<>(question, HttpStatus.OK);
		return response;
	}

	@GetMapping("/remove/{id}")
	public ResponseEntity<Boolean> deleteQuestion(@PathVariable("id") BigInteger questionId) {
		Question result = service.deleteQuestions(questionId);
		ResponseEntity<Boolean> response = new ResponseEntity<>(true, HttpStatus.OK);
		return response;
	}

	@PutMapping("/update//{testId}")
	public ResponseEntity<Question> updateTest(@PathVariable("testId") BigInteger questionId,
			@RequestBody @Valid QuestionDto questionDto) {
		Question question = convertFromDto(questionDto);
		question.setQuestionId(questionId);
		question = service.updateQuestions(questionId, question);
		ResponseEntity<Question> response = new ResponseEntity<>(question, HttpStatus.OK);
		return response;
	}

	public Question convertFromDto(QuestionDto dto) {
		Question question = new Question();
		question.setChosenAnswer(dto.getChosenAnswer());
		question.setMarksScored(dto.getMarksScored());
		question.setQuestionAnswer(dto.getQuestionAnswer());
		question.setQuestionMarks(dto.getQuestionMarks());
		question.setQuestionOptions(dto.getQuestionOptions());
		question.setQuestionTitle(dto.getQuestionTitle());
		question.setTestId(dto.getTestId());
		return question;
	}

	@ExceptionHandler(QuestionNotFoundException.class)
	public ResponseEntity<String> handleTestNotFound(QuestionNotFoundException ex) {
		Log.error("test not found exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
		Log.error("constraint violation", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable ex) {
		Log.error("exception caught", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

}
