package org.exammodule.service;

import org.exammodule.dao.UserDAO;


public interface CustomUserDetailsService {

	UserDAO getUserDao() throws Exception;
	void setUserDao(UserDAO userDao) throws Exception;

}