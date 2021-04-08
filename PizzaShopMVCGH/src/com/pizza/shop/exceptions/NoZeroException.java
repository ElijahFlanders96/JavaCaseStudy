package com.pizza.shop.exceptions;

// This class extends the Exception class, and is used to prohibit a user from entering "0" for an ID
public class NoZeroException extends Exception {
	public NoZeroException(String msg) {
		super(msg);
	}
}
