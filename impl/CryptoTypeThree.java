package com.heppner.crypto.impl;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.heppner.crypto.interfaces.ICryptoThree;
import com.heppner.crypto.util.FileHelper;

/**
 * Homophonic Substitution
 * @author Jonathan Perucca
 */
public class CryptoTypeThree implements ICryptoThree{

	private Map<String, List<String>> substitute;
	private File fileSubstitute;
	
	public CryptoTypeThree(File file){
		fileSubstitute = file;
		populateSubstitute();
	}
	
	private CryptoTypeThree(){}
	
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

	private void replaceDecodedLine(StringBuffer buffer, String line) {
		String value;
		for(int i = 0; i < line.length();){
			if((line.length() - i) == 1){
				buffer.append(line.charAt(i));
				i++;
				continue;
			}
			value = String.valueOf(line.charAt(i)) + String.valueOf(line.charAt(i+1));
			buffer.append(matchingKey(value));
			i += 2;
		}
		buffer.append(System.getProperty("line.separator"));
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
		for(int i = 0; i < line.length(); i++){
			c = line.charAt(i);
			buffer.append(randomValue(c));
		}
		buffer.append(System.getProperty("line.separator"));
	}
	
	public String randomValue(char key){
		List<String> listValue = new ArrayList<String>();
		listValue = substitute.get(String.valueOf(key));
		if(null == listValue)
			return String.valueOf(key);
		if(listValue.size() == 1) 
			return listValue.get(0);
		return listValue.get(new Random().nextInt(listValue.size()));
	}
	
	public String matchingKey(String value){
		List<String> listValue;
		for (String key : substitute.keySet()) {
			listValue = substitute.get(key);
			if(listValue.contains(value))
				return key;
		}
		return value;
	}
	
	public void populateSubstitute(){
		if(!FileHelper.validate(fileSubstitute)) return;
		try{
			BufferedReader br = FileHelper.getBufferRead(fileSubstitute);
			String line;
			String[] words = null;
			List<String> listValue;
			substitute = new HashMap<String, List<String>>();
			while ((line=br.readLine())!=null){
				words = line.split(";");
				
				listValue = new ArrayList<String>(); 
				for(int i = 1; i < words.length; i++)
					listValue.add(words[i]);
				
				substitute.put(words[0], listValue);
			}
			br.close(); 
		}		
		catch (Exception e){ 
			System.out.println(e.toString()); 
		}
	}
}
