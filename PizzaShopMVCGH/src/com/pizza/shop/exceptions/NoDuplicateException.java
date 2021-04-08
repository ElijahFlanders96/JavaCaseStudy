package com.pizza.shop.exceptions;

//This class extends the Exception class, and is used to prohibit a user from entering a duplicate ID
public class NoDuplicateException extends Exception {
	public NoDuplicateException(String msg) {
		super(msg);
	}
}
