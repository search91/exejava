/**
 * 
 */
package com.java.learn.md5;

/**
 * @author hzliuzhujie
 *
 */
public class GarbageException extends Exception {
	private static final long serialVersionUID = 1L;

	public GarbageException(String message) {
		super(message);
	}

	public GarbageException(String message, Throwable cause) {
		super(message, cause);
	}

	public GarbageException(Exception e) {
		super(e);
	}
}