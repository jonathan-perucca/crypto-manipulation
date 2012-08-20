package com.heppner.crypto.impl;

import java.io.BufferedReader;
import java.io.File;

import com.heppner.crypto.interfaces.ICryptoOne;
import com.heppner.crypto.util.FileHelper;

/**
 * Simple Ceasar Algorithm
 * @author Jonathan Perucca
 */
public class CryptoTypeOne implements ICryptoOne{

	private int MIN_CHAR = 0x61;
	private int MAX_CHAR = 0x7A;
	
	public void decode(File source, File dest) {
		if(!FileHelper.validate(source) || !FileHelper.validate(dest)) return;
		StringBuffer buffer = new StringBuffer();
		
		try{
			BufferedReader br = FileHelper.getBufferRead(source);
			String line;

			while ((line=br.readLine())!=null)
				replaceDecodedLine(buffer, line);
			
			br.close(); 
		}		
		catch (Exception e){ 
			System.out.println(e.toString()); 
		}
		
		FileHelper.writeFile(buffer.toString(), dest);
	}

	public void encode(File source, File dest){
		if(!FileHelper.validate(source) || !FileHelper.validate(dest)) return;
		StringBuffer buffer = new StringBuffer();
		
		try{
			BufferedReader br = FileHelper.getBufferRead(source);
			String line;
			
			while ((line=br.readLine())!=null)
				replaceEncodedLine(buffer, line);
			
			br.close(); 
		}		
		catch (Exception e){ 
			System.out.println(e.toString()); 
		}
		
		FileHelper.writeFile(buffer.toString(), dest);
	}

	private void replaceEncodedLine(StringBuffer buffer, String line) {
		char c;
		for(int i = 0; i < line.length(); i++){
			c = line.charAt(i);
			if(validateCharacter(c))
				c = encodeChar(c);
			buffer.append(c);
		}
		buffer.append(System.getProperty("line.separator"));
	}
	
	private void replaceDecodedLine(StringBuffer buffer, String line) {
		char c;
		for(int i = 0; i < line.length(); i++){
			c = line.charAt(i);
			if(validateCharacter(c))
				c = decodeChar(c);
			buffer.append(c);
		}
		buffer.append(System.getProperty("line.separator"));
	}
	
	public char decodeChar(char c){
		return (c == MIN_CHAR) ? (char)(MAX_CHAR) : --c;
	}
	
	public char encodeChar(char c){
		return (c == MAX_CHAR) ? (char)(MIN_CHAR) : ++c;
	}
	
	public boolean validateCharacter(char ch){
		return ((int)ch >= MIN_CHAR && (int)ch <= MAX_CHAR);
	}
}
