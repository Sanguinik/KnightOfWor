package com.wordpress.marleneknoche.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

	private Field emptyField;

	@Before
	public void setUp() {
		emptyField = new Field(0, 0);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(emptyField.getFieldState() == FieldState.EMPTY);
	}

}
