package com.heppner.crypto.interfaces;

/**
 * Simple Ceasar Algorithm
 * Focusing on pattern [a-z]
 * This algorithm replace a letter with the following one (encoding)
 * and with the previous (decoding)
 * @author Jonathan Perucca
 *
 */
public interface ICryptoOne extends ICrypto{

	/**
	 * Replace a letter with the previous one in pattern [a-z]
	 * @param c - Character to decode
	 * @return decoded character
	 */
	public char decodeChar(char c);
	
	/**
	 * Replace a letter with the following one in pattern [a-z]
	 * @param c - Character to encode
	 * @return encoded character
	 */
	public char encodeChar(char c);
	
	/**
	 * Verify if <tt>ch</tt> is a character from pattern [a-z]
	 * @param ch - Character to check
	 * @return true if the character follows pattern [a-z], false otherwise
	 */
	public boolean validateCharacter(char ch);
}
