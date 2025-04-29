package com.clientBase.model;

public class TypeModel {

	private String typeId;
	private String typeName;
	private String typeTime;
	private boolean isChoice=false;


	public boolean isChoice() {
		return isChoice;
	}

	public void setChoice(boolean choice) {
		isChoice = choice;
	}

	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeTime() {
		return typeTime;
	}
	public void setTypeTime(String typeTime) {
		this.typeTime = typeTime;
	}
	
}
