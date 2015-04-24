package org.exammodule.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.exammodule.dao.UserDAO;
import org.exammodule.dto.UserDTO;
import org.exammodule.exception.StudentNotFoundException;
import org.exammodule.form.AddStudentFormBean;
import org.exammodule.handler.HashCode;
import org.exammodule.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService,CustomUserDetailsService
{
	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private UserDAO userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		org.exammodule.dto.UserDTO user;
		try {
			user = userDao.fetchUserByUserName(username);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getAccess());
		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(org.exammodule.dto.UserDTO user, 
			List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), 
				user.getPassword(), true, 
				true, true, user.isAccountNotBlocked() , authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(String access) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		String userRole="";
		if(access.compareTo("1")==0){
			userRole="ROLE_ADMIN";
		}
		else if(access.compareTo("2")==0){
			userRole="ROLE_USER_VIDEO";
		}
		else if(access.compareTo("3")==0){
			userRole="ROLE_USER_IMAGE";
		}
		else if(access.compareTo("4")==0){
			userRole="ROLE_USER_NOTHING";
		}
		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority(userRole));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	

	@Transactional
	@Override
	public void blockUserAccount(String userName) throws Exception{
		userDao.setAccountNonBlockedStatus(userName,false);
	}

	@Transactional
	@Override
	public UserDAO getUserDao() throws Exception{
		return userDao;
	}

	@Transactional
	@Override
	public void setUserDao(UserDAO userDao) throws Exception{
		this.userDao = userDao;
	}

	@Override
	public String getUserFullName(String username) throws StudentNotFoundException, Exception {
		return userDao.fetchUserByUserName(username).getName();
	}

}




