package org.exammodule.service;

import java.util.List;

import org.exammodule.exception.StudentNotFoundException;
import org.exammodule.form.AddStudentFormBean;
import org.exammodule.form.AdditionalQuestionsRecordFormBean;
import org.exammodule.form.RegularQuestionsRecordFormBean;
import org.exammodule.form.StudentsRecordFormBean;
public interface AdminService {

	List<StudentsRecordFormBean> getStudentsRecord() throws Exception;

	String getStudentName(String userName) throws Exception;

	List<AdditionalQuestionsRecordFormBean> getAdditionalQuestionsRecord(
			String userName) throws Exception;

	List<RegularQuestionsRecordFormBean> getRegularQuestionsRecord(
			String userName) throws Exception;

	public void resetUserAccount(String userName, String fullName) throws StudentNotFoundException, Exception;

	String getAdminName(String attribute) throws Exception;

	boolean addStudent(AddStudentFormBean student) throws Exception;

	}
