package org.exammodule.dao;

import java.util.List;

import org.exammodule.dto.UserDTO;

public interface UserDAO {

	public UserDTO fetchUserByUserName(String username) throws Exception;
	public List<UserDTO> getAllStudents() throws Exception;
	public UserDTO getThisStudent(String userName) throws Exception;
	public void resetBlockedAccount(String userName, String fullName, boolean status)
			throws Exception;
	public void setAccountNonBlockedStatus(String userName, boolean b) throws Exception;
	public boolean addStudent(UserDTO user) throws Exception;
	

}