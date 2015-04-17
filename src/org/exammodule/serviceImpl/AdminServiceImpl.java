package org.exammodule.serviceImpl;


import org.apache.log4j.Logger;
import org.exammodule.dao.UserDAO;
import org.exammodule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	protected static Logger logger = Logger.getLogger("admin service");
	@Autowired
	private UserDAO userDAO;
	
}