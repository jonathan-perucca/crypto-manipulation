package com.heppner.crypto.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class makes standards operations on files
 * @author Jonathan Perucca
 * @version 0.1
 */
public class FileHelper{

	/**
	 * Writes content string into destination file <tt>dest</tt>
	 * @param content - Text to insert into <tt>dest</tt> file
	 * @param dest - File into which we will write
	 */
	public static void writeFile(String content, File dest){
		System.out.println(content);
		BufferedWriter output;
		try{
			FileWriter fw = new FileWriter(dest, false);
			output = new BufferedWriter(fw);
			output.write(content);
			output.flush();
			output.close();
		}catch (Exception e) { e.printStackTrace();	}
	}
	
	/**
	 * Routine for reading file, this method give us a reader for a given file <tt>source</tt>
	 * @param source - File we want to read
	 * @return new BufferReader on <tt>source</tt> file
	 * @throws FileNotFoundException - <tt>source</tt> file doesn't physicaly exist
	 */
	public static BufferedReader getBufferRead(File source) throws FileNotFoundException {
		InputStream ips=new FileInputStream(source); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		return br;
	}
	
	/**
	 * Verifies if the given <tt>file</tt> isn't <b>null</b> nor a <b>directory</b>
	 * @param file - File to validate
	 * @return true is <tt>file</tt> is what we expected, false otherwise
	 */
	public static boolean validate(File file) {
		return !(null == file || file.isDirectory());
	}
}
