package org.exammodule.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.exammodule.dao.CategoryDAO;
import org.exammodule.dto.CategoryDTO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	protected static Logger logger = Logger.getLogger("category dao");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryDTO> getAllCategories() throws Exception{
		logger.debug("Request to get list of all categories in CategoryDAO");
		List<CategoryDTO> categories = null;
		categories=sessionFactory.getCurrentSession().createCriteria(CategoryDTO.class).list();
		return categories;
	}

	@Override
	public long getRightAttemptCount(String userName, Integer categoryId) throws Exception{
		logger.debug("Request to get right attempt count for a given category and student in CategoryDAO");
		Query query1 = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM RightAttemptsDTO r WHERE r.user.userName = :userName and r.questionRegular.category.categoryId=:categoryId");
		Query query2 = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM RightAttemptsDTO r WHERE r.user.userName = :userName and r.questionAdditional.category.categoryId=:categoryId");
		query1.setString("userName", userName);
		query1.setInteger("categoryId", categoryId);
		query2.setString("userName", userName);
		query2.setInteger("categoryId", categoryId);
		long count = (Long) query1.uniqueResult()+(Long)query2.uniqueResult();
		return count;
	}

	@Override
	public long getWrongAttemptCount(String userName, Integer categoryId) throws Exception{
		logger.debug("Request to get right attempt count for a given category and student in CategoryDAO");
		Query query1 = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM WrongAttemptsDTO w WHERE w.user.userName = :userName and w.questionRegular.category.categoryId=:categoryId");
		Query query2 = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM WrongAttemptsDTO w WHERE w.user.userName = :userName and w.questionAdditional.category.categoryId=:categoryId");
		query1.setString("userName", userName);
		query1.setInteger("categoryId", categoryId);
		query2.setString("userName", userName);
		query2.setInteger("categoryId", categoryId);
		long count = (Long) query1.uniqueResult()+(Long)query2.uniqueResult();
		return count;	
	}
}