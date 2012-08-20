package com.heppner.crypto.impl;

import java.io.BufferedReader;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.heppner.crypto.interfaces.ICrypto;
import com.heppner.crypto.util.FileHelper;

/**
 * This class encode entire text/ascii file with md5 hash algorithm
 * @author Jonathan Perucca
 *
 */
public class CryptoTypeFour implements ICrypto{

	private String breakRow = "\n";
	
	/**
	 * No decoding method for MD5 Algo ( one-way hashing )
	 */
	public void decode(File source, File dest) {
		return;
	}

	public void encode(File source, File dest) {
		if(!FileHelper.validate(source) || !FileHelper.validate(dest)) return;
		String hash = "";
		try{
			BufferedReader br = FileHelper.getBufferRead(source);
			String line;
			
			while ((line=br.readLine())!=null)
				hash += line + breakRow;
				
			hash = _encode(hash);
			
			br.close(); 
		}		
		catch (Exception e){ 
			System.out.println(e.toString()); 
		}
		
		FileHelper.writeFile(hash, dest);
	}
	
	private String _encode(String hash) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(hash.getBytes());
		BigInteger bigInt = new BigInteger(1, digest);
		hash = bigInt.toString(1 << 4);
		hash = leadingZeros(hash);
		return hash;
	}

	private String leadingZeros(String hash) {
		while(hash.length() < ( 1 << 5 ))
			hash += '0'+hash;
		return hash;
	}
}
