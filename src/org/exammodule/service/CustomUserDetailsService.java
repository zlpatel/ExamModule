package org.exammodule.service;

import org.exammodule.dao.UserDAO;
import org.exammodule.exception.StudentNotFoundException;


public interface CustomUserDetailsService {

	UserDAO getUserDao() throws Exception;
	void setUserDao(UserDAO userDao) throws Exception;
	void blockUserAccount(String userName) throws Exception;
	String getUserFullName(String attribute) throws StudentNotFoundException, Exception;

}