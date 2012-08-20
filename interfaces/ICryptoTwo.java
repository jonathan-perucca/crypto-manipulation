package com.heppner.crypto.interfaces;

/**
 * Polybe Algorithm Extension
 * This algorithm is based on a bi-dimentionnal character table ( char[][] ) with each dimention has a maxSize of 6
 * This table is populated with values from pattern [0-9a-z] in this order
 * Encoding a given character is made by swapping it with i,j coords from the table (eg. following examples)
 * Decoding a given character is the step backward
 * @author Jonathan Perucca
 *
 */
public interface ICryptoTwo extends ICrypto{

	/**
	 * 
	 * Populate a private bi-dimentionnal character table
	 * Table must be a 6x6 array and must be populated with the pattern [0-9a-z]
	 * Will result in :
	 * 		[0][1][2][3][4][5]
	 * [0] : 0, 1, 2, 3, 4, 5
	 * [1] : 6, 7, 8, 9, a, b 
	 * [2] : c, d, e, f, g, h
	 * [3] : i, j, k, l, m, n
	 * [4] : o, p, q, r, s, t
	 * [5] : u, v, w, x, y, z
	 */
	public void populateTab();
	
	/**
	 * Replace <tt>ch</tt> character with its corresponding i,j coords 
	 * from the private bi-dimentionnal character table
	 * Examples :
	 * 0 is replaced by : 00
	 * 2 is replaced by : 02
	 * d is replaced by : 21
	 * r is replaced by : 43
	 * u is replaced by : 50
	 * @param ch - Character to encode
	 * @return corresponding encoded character
	 */
	public String encodeChar(char ch);
	
	/**
	 * Replace <tt>ch</tt> string (length of 2) with its corresponding i,j coords value
	 * from the private bi-dimentionnal character table
	 * Examples : 
	 * 00 is replaced by : 0
	 * 02 is replaced by : 2
	 * 21 is replaced by : d
	 * 43 is replaced by : r
	 * 50 is replaced by : u
	 * @param ch - Character to decode
	 * @return corresponding decoded character
	 */
	public String decodeChar(String ch);
	
	/**
	 * Verify if <tt>ch</tt> is a character from pattern [0-9a-z]
	 * @param ch - Character to check
	 * @return true if the character follows pattern [0-9a-z], false otherwise
	 */
	public boolean validateCharacter(char ch);
	
}
