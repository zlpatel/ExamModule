package org.exammodule.exception;

public class NoAssignedQuestionException extends Exception
{
	private static final long serialVersionUID = 1L;
	public NoAssignedQuestionException(String message) {
		super(message);
	}
}
