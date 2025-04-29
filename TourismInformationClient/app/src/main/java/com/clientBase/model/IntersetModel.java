package com.clientBase.model;

import java.io.Serializable;

public class IntersetModel implements Serializable{

	private String interestImage;
	private String interestUserId;
	private String interestId;
	private String interestUserName;
	private String interestMessage;
	private String interestTime;
	private String interestHobby;
	private boolean collectState =false;
	private String interestAddress;
	private String interestTitle;
	
	private String interestLatLon;
	
	private String interestType;
	private String interestTypeId;
	
	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}

	public String getInterestTypeId() {
		return interestTypeId;
	}

	public void setInterestTypeId(String interestTypeId) {
		this.interestTypeId = interestTypeId;
	}

	public String getInterestLatLon() {
		return interestLatLon;
	}

	public void setInterestLatLon(String interestLatLon) {
		this.interestLatLon = interestLatLon;
	}

	public String getInterestTitle() {
		return interestTitle;
	}

	public void setInterestTitle(String interestTitle) {
		this.interestTitle = interestTitle;
	}

	public String getInterestAddress() {
		return interestAddress;
	}

	public void setInterestAddress(String interestAddress) {
		this.interestAddress = interestAddress;
	}

	public boolean isCollectState() {
		return collectState;
	}

	public void setCollectState(boolean collectState) {
		this.collectState = collectState;
	}

	public String getInterestImage() {
		return interestImage;
	}

	public void setInterestImage(String interestImage) {
		this.interestImage = interestImage;
	}

	public String getInterestUserId() {
		return interestUserId;
	}

	public void setInterestUserId(String interestUserId) {
		this.interestUserId = interestUserId;
	}

	public String getInterestId() {
		return interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}

	public String getInterestUserName() {
		return interestUserName;
	}

	public void setInterestUserName(String interestUserName) {
		this.interestUserName = interestUserName;
	}

	public String getInterestMessage() {
		return interestMessage;
	}

	public void setInterestMessage(String interestMessage) {
		this.interestMessage = interestMessage;
	}

	public String getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(String interestTime) {
		this.interestTime = interestTime;
	}

	public String getInterestHobby() {
		return interestHobby;
	}

	public void setInterestHobby(String interestHobby) {
		this.interestHobby = interestHobby;
	}

}
