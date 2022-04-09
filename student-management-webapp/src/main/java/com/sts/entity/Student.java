package com.sts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@JsonProperty("class")
	private String clasS;
	private String sex;
	private float maths;
	private float science;
	private float language;
	private float percentage;
	
	
	public String getClasS() {
		return clasS;
	}
	public void setClasS(String clasS) {
		this.clasS = clasS;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public float getMaths() {
		return maths;
	}
	public void setMaths(float maths) {
		this.maths = maths;
	}
	public float getScience() {
		return science;
	}
	public void setScience(float science) {
		this.science = science;
	}
	public float getLanguage() {
		return language;
	}
	public void setLanguage(float language) {
		this.language = language;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", maths=" + maths + ", science=" + science
				+ ", language=" + language + ", percentage=" + percentage + "]";
	}

	public Student(int id, String name, String clasS, String sex, float maths, float science, float language,
			float percentage) {
		super();
		this.id = id;
		this.name = name;
		this.clasS = clasS;
		this.sex = sex;
		this.maths = maths;
		this.science = science;
		this.language = language;
		this.percentage = percentage;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
