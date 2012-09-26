package com.wordpress.marleneknoche.model;

import java.util.List;

/**
 * 
 * @author marlene
 * 
 *         The grid contains all fields of the playing field.
 * 
 */
public class Grid {

	private List<Field> fields;

	public Grid() {

	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
