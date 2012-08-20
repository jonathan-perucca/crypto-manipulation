package com.heppner.crypto.interfaces;

import java.io.File;

/**
 * Default cryptography interface
 * @author Jonathan Perucca
 *
 */
public interface ICrypto {

	/**
	 * Encode source file following a specific algorithm
	 * @param source - File read from which we apply an algorithm
	 * @param dest - File resulting the source manipulation
	 */
	public void encode(File source, File dest);
	
	/**
	 * Decode source file following a specific algorithm
	 * @param source - File read from which we apply an algorithm
	 * @param dest - File resulting the source manipulation
	 */
	public void decode(File source, File dest);
	
}
