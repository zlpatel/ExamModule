package org.exammodule.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.exammodule.dao.UserDAO;
import org.exammodule.dto.UserDTO;
import org.exammodule.exception.StudentNotFoundException;
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
	public UserDTO fetchUserByUserName(String username) throws StudentNotFoundException, Exception{

		Session session = null;
		UserDTO user = null;
		session = sessionFactory.getCurrentSession();
		user = (UserDTO)session.get(UserDTO.class, username);
		if(user==null)
			throw new StudentNotFoundException("Student not found!");
		return user;
	}
	@Override
	public boolean addStudent(UserDTO user) throws Exception{
			sessionFactory.getCurrentSession().save(user);
			return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> getAllStudents() throws Exception{

		List<UserDTO> students = null;
		logger.debug("Request to get all students");
		students=sessionFactory.getCurrentSession().createCriteria(UserDTO.class).add(Restrictions.between("access","2","4")).list();
		return students;
	}

	@Override
	public UserDTO getThisStudent(String userName) throws StudentNotFoundException, Exception{
		logger.debug("Request to find a given student in UserDAO");
		UserDTO userDTO=new UserDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO u WHERE u.userName = :userName");
		query.setString("userName", userName);
		userDTO = (UserDTO) query.uniqueResult();
		if(userDTO!=null)
			return userDTO;
		logger.error("Student does not exist!");
		throw new StudentNotFoundException("Student does not exist!");
	}
	
	@Override
	public void resetBlockedAccount(String userName,String fullName,boolean status) throws StudentNotFoundException,Exception{
		logger.debug("Request to change account blocked status");
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO u WHERE u.userName = :userName and u.name = :fullName");
		query.setString("userName", userName);
		query.setString("fullName", fullName);
		UserDTO user = (UserDTO) query.uniqueResult();
		if(user==null){
			throw new StudentNotFoundException("Student does not exist!");
		}else if(user!=null){
			Query query2 = sessionFactory.getCurrentSession().createQuery("delete FROM AttemptsDTO a WHERE a.user.userName = :userName");
			query2.setString("userName",user.getUserName());
			query2.executeUpdate();
			user.setAccountNotBlocked(status);
			sessionFactory.getCurrentSession().saveOrUpdate(user);
		}
	}

	@Override
	public void setAccountNonBlockedStatus(String userName, boolean status) throws Exception {
		logger.debug("Request to change account blocked status");
		UserDTO user = getThisStudent(userName);
		user.setAccountNotBlocked(status);
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
}
