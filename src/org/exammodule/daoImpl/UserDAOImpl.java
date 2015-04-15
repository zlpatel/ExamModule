package org.exammodule.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.exammodule.dao.UserDAO;
import org.exammodule.dto.UserDTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

	protected static Logger logger = Logger.getLogger("user dao");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//Method to retrieve user by a username
	@Override
	public UserDTO fetchUserByUserName(String username) throws Exception{

		Session session = null;
		UserDTO user = null;
		session = sessionFactory.getCurrentSession();
		user = (UserDTO)session.get(UserDTO.class, username);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> getAllStudents() throws Exception{

		List<UserDTO> students = null;
		logger.debug("Request to get all students");
		students=sessionFactory.getCurrentSession().createCriteria(UserDTO.class).add(Restrictions.eq("access","2")).list();
		return students;
	}

	@Override
	public UserDTO getThisStudent(String userName) throws Exception{
		logger.debug("Request to find a given student in UserDAO");
		UserDTO userDTO=new UserDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO u WHERE u.userName = :userName");
		query.setString("userName", userName);
		userDTO = (UserDTO) query.uniqueResult();
		if(userDTO!=null)
			return userDTO;
		logger.error("Student does not exist!");
		throw new Exception("Student does not exist!");
	}
}
