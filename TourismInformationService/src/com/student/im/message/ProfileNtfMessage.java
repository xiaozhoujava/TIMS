package com.student.im.message;

import com.student.im.util.GsonUtil;

/**
 *
 * 资料通知消息。此类型消息没有 Push 通知。
 *
 */
public class ProfileNtfMessage extends BaseMessage {
	private String operation = "";
	private String data = "";
	private String extra = "";
	private transient static final String TYPE = "RC:ProfileNtf";
	
	public ProfileNtfMessage(String operation, String data, String extra) {
		this.operation = operation;
		this.data = data;
		this.extra = extra;
	}
	
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取为资料通知操作，可以自行定义。
	 *
	 * @returnString
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * 设置为资料通知操作，可以自行定义。
	 *
	 * @return
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}  
	
	/**
	 * 获取操作的数据。
	 *
	 * @returnString
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * 设置操作的数据。
	 *
	 * @return
	 */
	public void setData(String data) {
		this.data = data;
	}  
	
	/**
	 * 获取附加内容(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @returnString
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * 设置附加内容(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ProfileNtfMessage.class);
	}
}