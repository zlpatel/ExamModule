package org.exammodule.dao;

import java.util.List;

import org.exammodule.dto.UserDTO;

public interface UserDAO {

	public UserDTO fetchUserByUserName(String username) throws Exception;
	public List<UserDTO> getAllStudents() throws Exception;
	public UserDTO getThisStudent(String userName) throws Exception;

}