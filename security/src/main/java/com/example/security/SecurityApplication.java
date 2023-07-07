package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(SecurityApplication.class, args);
		String encode = new BCryptPasswordEncoder().encode("123");
		System.out.println(encode);
	}

}
