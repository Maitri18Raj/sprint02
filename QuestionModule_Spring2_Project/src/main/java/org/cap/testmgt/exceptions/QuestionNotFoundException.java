package org.cap.testmgt.exceptions;

public class QuestionNotFoundException extends RuntimeException{
	public QuestionNotFoundException(String msg) {
		super(msg);
	}
}
