package de.masternoir.pw_gen;

public class PwTooShortException extends Exception{

	public PwTooShortException(String message){
		super(message);
	}
	
	public PwTooShortException(String message, Throwable throwable){
		super(message, throwable);
	}
}
