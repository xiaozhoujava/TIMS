/**
 * create by kitName
 * create datetime : 2016-10-20 
 * 
 * v2.0.1
 */
package com.student.im;

import java.util.concurrent.ConcurrentHashMap;

import com.student.im.ethods.Chatroom;
import com.student.im.ethods.Group;
import com.student.im.ethods.Message;
import com.student.im.ethods.Push;
import com.student.im.ethods.SMS;
import com.student.im.ethods.User;
import com.student.im.ethods.Wordfilter;

public class RongCloud {

	private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String,RongCloud>();
	
	public User user;
	public Message message;
	public Wordfilter wordfilter;
	public Group group;
	public Chatroom chatroom;
	public Push push;
	public SMS sms;

	private RongCloud(String appKey, String appSecret) {
		user = new User(appKey, appSecret);
		message = new Message(appKey, appSecret);
		wordfilter = new Wordfilter(appKey, appSecret);
		group = new Group(appKey, appSecret);
		chatroom = new Chatroom(appKey, appSecret);
		push = new Push(appKey, appSecret);
		sms = new SMS(appKey, appSecret);

	}

	public static RongCloud getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
		}
		return rongCloud.get(appKey);
	}
	 
}