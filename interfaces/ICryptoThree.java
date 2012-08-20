package com.heppner.crypto.interfaces;

/**
 * Homophonic Substitution
 * This algorithm gives for a key many different values
 * A key can get 1 to N value(s) but a value is declared for only one key
 * Encoding will result in picking up a random value for a specified key
 * Decoding will result in finding the corresponding key for a specified value
 * @author Jonathan Perucca
 *
 */
public interface ICryptoThree extends ICrypto{

	/**
	 * 
	 * This method populates a private map
	 * Key will be letters in pattern [a-z]
	 * Values will be numbers on the same line which are seperated with ';'
	 */
	public void populateSubstitute();
	
	/**
	 * This method randomly pick a value corresponding to the specified <tt>key</tt>
	 * @param key - Key on which a value will be picked up
	 * @return Random value corresponding to the key
	 */
	public String randomValue(char key);
	
	/**
	 * Verifying if <tt>value</tt> is corresponding a key from private map
	 * @param value - Value to check
	 * @return Corresponding key if found, <tt>value</tt> not changed otherwise
	 */
	public String matchingKey(String value);
	
}
