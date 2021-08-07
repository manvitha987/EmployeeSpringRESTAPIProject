/**
 * 
 */
package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Admin
 *
 */

@Entity
@Table(name = "emp")
public class Employee {

	// Employee Properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", length = 12, nullable = false)
	@NotBlank()
	private String name;

	@Column(name = "marks", nullable = false)
	@Min(value = 35, message = "Marks should not be less than 35")
	@Max(value = 100, message = "Marks should not be greater than 100")
	private double marks;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

}
