package com.heppner.crypto;

import java.io.File;

import com.heppner.crypto.impl.CryptoTypeFour;
import com.heppner.crypto.impl.CryptoTypeOne;
import com.heppner.crypto.impl.CryptoTypeThree;
import com.heppner.crypto.impl.CryptoTypeTwo;
import com.heppner.crypto.interfaces.ICrypto;

public class Main {
	
	private static ICrypto crypto;
	private static String defaultPath = "resources/crypto.txt";
	private static String encodePath = "resources/crypto-encode.txt";
	private static String decodePath = "resources/crypto-decode.txt";
	private static String substitutePath = "resources/substitute.txt";
	
	public static void main(String[] args) {
		File source = new File(defaultPath);
		File dest = new File(encodePath);
		crypto = new CryptoTypeOne();
		System.out.println("Encoding with Simple Ceasar Algorithm");
		crypto.encode(source, dest);
		source = dest;
		dest = new File(decodePath);
		System.out.println("Decoding with Simple Ceasar Algorithm");
		crypto.decode(source, dest);
		
		source = new File(defaultPath);
		dest = new File(encodePath);
		crypto = new CryptoTypeTwo();
		System.out.println("Encoding with Polybe Algorithm Extension");
		crypto.encode(source, dest);
		source = dest;
		dest = new File(decodePath);
		System.out.println("Decoding with Polybe Algorithm Extension");
		crypto.decode(source, dest);

		source = new File(defaultPath);
		dest = new File(encodePath);
		crypto = new CryptoTypeThree(new File(substitutePath));
		System.out.println("Encoding with Homophonic Substitution");
		crypto.encode(source, dest);
		source = dest;
		dest = new File(decodePath);
		System.out.println("Decoding with Homophonic Substitution");
		crypto.decode(source, dest);
		
		source = new File(defaultPath);
		dest = new File(encodePath);
		crypto = new CryptoTypeFour();
		System.out.println("Encoding with MD5 Hashing Algorithm");
		crypto.encode(source, dest);
	}
}
