package org.exammodule.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "test", schema = "", name="question_type")
@XmlRootElement
public class QuestionTypeDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="type_id", nullable = false)
	private int typeId;
	
	@Column(name="type_name", nullable = false)
	private String typeName;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
