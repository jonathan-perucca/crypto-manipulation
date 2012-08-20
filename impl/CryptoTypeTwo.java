package com.heppner.crypto.impl;

import java.io.BufferedReader;
import java.io.File;

import com.heppner.crypto.interfaces.ICryptoTwo;
import com.heppner.crypto.util.FileHelper;

/**
 * Polybe Algorithm Extension
 * @author Jonathan Perucca
 */
public class CryptoTypeTwo implements ICryptoTwo{

	private int MIN_CHAR_NUMERIC = 0x30; //48
	private int MAX_CHAR_NUMERIC = 0x39; //57
	private int MIN_CHAR_ALPHA = 0x61; //97
	private int MAX_CHAR_ALPHA = 0x7A; //122
	
	private int ENTRIES = 6;
	
	private char[][] tab = new char[ENTRIES][ENTRIES];
	
	public CryptoTypeTwo(){
		populateTab();
	}
	
	public void decode(File source, File dest) {
		if(!FileHelper.validate(source) || !FileHelper.validate(dest)) return;
		StringBuffer buffer = new StringBuffer();
		
		try{
			BufferedReader br = FileHelper.getBufferRead(source);
			String line;
			
			while ((line=br.readLine())!=null)
				replaceDecodedLine(line, buffer);
			
			br.close(); 
		}		
		catch (Exception e){ 
			System.out.println(e.toString()); 
		}
		
		FileHelper.writeFile(buffer.toString(), dest);
	}
	
	public void encode(File source, File dest) {
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
		String newChar = "";
		
		for(int i = 0; i < line.length(); i++){
			c = line.charAt(i);
			if(validateCharacter(c))
				newChar = encodeChar(c);
			if(null != newChar)
				buffer.append(newChar);
			else
				buffer.append(c);
			newChar = null;
		}
		buffer.append(System.getProperty("line.separator"));
	}
	
	public void replaceDecodedLine(String line, StringBuffer buffer){
		String c = "";
		String newChar = "";
		for(int i = 0; i < line.length();){
			if((line.length() - i) == 1){
				buffer.append(line.charAt(i));
				i++;
				continue;
			}
			c = String.valueOf(line.charAt(i)) + String.valueOf(line.charAt(i+1));
			newChar = decodeChar(c);
			buffer.append(newChar);
			i += 2;
		}
		buffer.append(System.getProperty("line.separator"));
	}
	
	public void populateTab(){
		char c = (char)MIN_CHAR_NUMERIC;
		for(int i = 0; i < ENTRIES; i++){
			for(int j = 0; j < ENTRIES; j++){
				if(!validateCharacter(c))
					c = (char)MIN_CHAR_ALPHA;
				tab[i][j] = c++;
			}
		}
	}
	
	public String encodeChar(char ch){
		String result = String.valueOf(ch);
		mainLoop:
		for(int i = 0; i < ENTRIES; i++){
			for(int j = 0; j < ENTRIES; j++){
				if(tab[i][j] == ch){
					result = String.valueOf(i) + String.valueOf(j);
					break mainLoop;
				}
			}
		}
		return result;
	}
	
	public String decodeChar(String ch){
		String result = ch;
		String tab_representation = "";
		mainLoop:
		for(int i = 0; i < ENTRIES; i++){
			for(int j = 0; j < ENTRIES; j++){
				tab_representation = String.valueOf(i) + String.valueOf(j);
				if(tab_representation.equalsIgnoreCase(ch)){
					result = String.valueOf(tab[i][j]);
					break mainLoop;
				}
			}
		}
		return result;
	}
	
	public boolean validateCharacter(char ch){
		return ((int)ch >= MIN_CHAR_NUMERIC && (int)ch <= MAX_CHAR_NUMERIC) 
				|| ((int)ch >= MIN_CHAR_ALPHA && (int)ch <= MAX_CHAR_ALPHA);
	}
	
}
