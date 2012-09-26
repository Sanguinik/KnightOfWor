package com.wordpress.marleneknoche.model;

import javafx.scene.shape.Rectangle;

/**
 * 
 * @author marlene
 * 
 *         A field is one element of the play field.
 * 
 */
public class Field {

	private int x;
	private int y;
	private FieldState fieldState = FieldState.EMPTY;
	private int borderLength = 5;
	private final Rectangle rect;

	public Field(int x, int y) {
		this.x = x;
		this.y = y;
		rect = new Rectangle(x, y, borderLength, borderLength);
	}

	public FieldState getFieldState() {
		return fieldState;
	}

	public void setFieldState(FieldState fieldState) {
		this.fieldState = fieldState;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	};

	public void setY(int y) {
		this.y = y;
	}

	public int getBorderLength() {
		return borderLength;
	}

	public void setBorderLength(int borderLength) {
		this.borderLength = borderLength;
	}

}
