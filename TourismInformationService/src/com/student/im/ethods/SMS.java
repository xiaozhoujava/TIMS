package com.student.im.ethods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.student.im.models.CodeSuccessReslut;
import com.student.im.models.SMSImageCodeReslut;
import com.student.im.models.SMSSendCodeReslut;
import com.student.im.util.GsonUtil;
import com.student.im.util.HostType;
import com.student.im.util.HttpUtil;

public class SMS {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public SMS(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 获取图片验证码方法 
	 * 
	 * @param  appKey:应用Id
	 *
	 * @return SMSImageCodeReslut
	 **/
	public SMSImageCodeReslut getImageCode(String appKey) throws Exception {
		if (appKey == null) {
			throw new IllegalArgumentException("Paramer 'appKey' is required");
		}
		
	    StringBuilder sb = new StringBuilder(HostType.SMS.getStrType()+"/getImgCode.json");
		sb.append("?appKey=").append(URLEncoder.encode(appKey, UTF8));
		
		HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(sb.toString());
	    
	    return (SMSImageCodeReslut) GsonUtil.fromJson(HttpUtil.returnResult(conn), SMSImageCodeReslut.class);
	}
	
	/**
	 * 发送短信验证码方法。 
	 * 
	 * @param  mobile:接收短信验证码的目标手机号，每分钟同一手机号只能发送一次短信验证码，同一手机号 1 小时内最多发送 3 次。（必传）
	 * @param  templateId:短信模板 Id，在开发者后台->短信服务->服务设置->短信模版中获取。（必传）
	 * @param  region:手机号码所属国家区号，目前只支持中图区号 86）
	 * @param  verifyId:图片验证标识 Id ，开启图片验证功能后此参数必传，否则可以不传。在获取图片验证码方法返回值中获取。
	 * @param  verifyCode:图片验证码，开启图片验证功能后此参数必传，否则可以不传。
	 *
	 * @return SMSSendCodeReslut
	 **/
	public SMSSendCodeReslut sendCode(String mobile, String templateId, String region, String verifyId, String verifyCode) throws Exception {
		if (mobile == null) {
			throw new IllegalArgumentException("Paramer 'mobile' is required");
		}
		
		if (templateId == null) {
			throw new IllegalArgumentException("Paramer 'templateId' is required");
		}
		
		if (region == null) {
			throw new IllegalArgumentException("Paramer 'region' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&mobile=").append(URLEncoder.encode(mobile.toString(), UTF8));
	    sb.append("&templateId=").append(URLEncoder.encode(templateId.toString(), UTF8));
	    sb.append("&region=").append(URLEncoder.encode(region.toString(), UTF8));
	    
	    if (verifyId != null) {
	    	sb.append("&verifyId=").append(URLEncoder.encode(verifyId.toString(), UTF8));
	    }
	    
	    if (verifyCode != null) {
	    	sb.append("&verifyCode=").append(URLEncoder.encode(verifyCode.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.SMS, appKey, appSecret, "/sendCode.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (SMSSendCodeReslut) GsonUtil.fromJson(HttpUtil.returnResult(conn), SMSSendCodeReslut.class);
	}
	
	/**
	 * 验证码验证方法 
	 * 
	 * @param  sessionId:短信验证码唯一标识，在发送短信验证码方法，返回值中获取。（必传）
	 * @param  code:短信验证码内容。（必传）
	 *
	 * @return CodeSuccessReslut
	 **/
	public CodeSuccessReslut verifyCode(String sessionId, String code) throws Exception {
		if (sessionId == null) {
			throw new IllegalArgumentException("Paramer 'sessionId' is required");
		}
		
		if (code == null) {
			throw new IllegalArgumentException("Paramer 'code' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&sessionId=").append(URLEncoder.encode(sessionId.toString(), UTF8));
	    sb.append("&code=").append(URLEncoder.encode(code.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.SMS, appKey, appSecret, "/verifyCode.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessReslut) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessReslut.class);
	}

	 
}