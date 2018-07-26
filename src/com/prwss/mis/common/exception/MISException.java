/**
 * 
 */
package com.prwss.mis.common.exception;

/**
 * @author vgadiraju
 *
 */
public class MISException extends Exception {
	
	private String code;
	private String message;

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -8129602989509113841L;

	/**
	 * 
	 */
	public MISException() {
		super();
	}

	/**
	 * @param message
	 */
	public MISException(String message) {
		this.message = message;
	}

	/**
	 * @param cause
	 */
	public MISException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MISException(String message, Throwable cause) {
		super(message, cause);
	}

	public MISException(String code, String message) {
		super(message);
		this.code = code;
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
