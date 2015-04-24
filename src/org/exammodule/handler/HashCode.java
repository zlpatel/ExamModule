package org.exammodule.handler;

import org.springframework.security.authentication.encoding.*;

public class HashCode {

	public static String getHashPassword(String password) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String hashedPassword = passwordEncoder.encodePassword(password,null);

		System.out.println(hashedPassword);
		return hashedPassword;
	}

}