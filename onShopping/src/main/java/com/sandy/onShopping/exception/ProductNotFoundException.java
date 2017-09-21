package com.sandy.onShopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public ProductNotFoundException()
	{
		this("Product not available!!!");
	}
	
	public ProductNotFoundException(String msg)
	{
		this.msg=System.currentTimeMillis() +": " +msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
	
}
