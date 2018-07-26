package com.prwss.mis.common.exception;

public class MISSessionTimeOutException extends Exception {
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -9061327267737044206L;
	private String code;
	private String message;
	
	public MISSessionTimeOutException() {
		super();
	}
	
	public MISSessionTimeOutException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MISSessionTimeOutException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MISSessionTimeOutException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param code
	 * @param message
	 */
	public MISSessionTimeOutException(String exceptionCode, String message) {
		this.code = exceptionCode;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
