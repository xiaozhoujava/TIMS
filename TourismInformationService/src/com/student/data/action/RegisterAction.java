package com.student.data.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.student.data.dao.RegisterDao;
import com.student.im.RongCloud;
import com.student.im.models.TokenReslut;
import com.student.jdbc.Consts;
import com.student.util.GsonTools;

public class RegisterAction extends HttpServlet {

	private RegisterDao registerDao;
	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;
	private GsonTools gsonTools;
	public RegisterAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("addUser")) {
			registerMessage(request, response);
		} else if (action_flag.equals("listUser")) {
			listUser(request, response);
		} else if (action_flag.equals("login")) {
			loginMessage(request, response);
		} else if (action_flag.equals("deleteUser")) {
			deleteUserPc(request, response);
		} else if (action_flag.equals("updateName")) {
			updateName(request, response);
		} else if (action_flag.equals("updatePhone")) {
			updatePhone(request, response);
		} else if (action_flag.equals("loginPc")) {
			login(request, response);
		} else if (action_flag.equals("updateAddress")) {
			updateAddress(request, response);
		} else if (action_flag.equals("updatePswd")) {
			updatePswd(request, response);
		} else if (action_flag.equals("listSearchPhone")) {
			listSearchPhone(request, response);
		} else if (action_flag.equals("addFriend")) {
			addFriend(request, response);
		} else if (action_flag.equals("listMyFriendMessagePhone")) {
			listMyFriendMessagePhone(request, response);
		} else if (action_flag.equals("listMyNearMessagePhone")) {
			listMyNearMessagePhone(request, response);
		}
		if (action_flag.equals("addUserPc")) {
			addUserPc(request, response);
		} else if (action_flag.equals("updatePcQuery")) {
			updatePcQuery(request, response);
		} else if (action_flag.equals("updateStudent")) {
			updateStudent(request, response);
		} else if (action_flag.equals("deleteFriend")) {
			deleteFriend(request, response);
		} else if (action_flag.equals("updateUserImage")) {
			updateUserImage(request, response);
		} else if (action_flag.equals("updateUser")) {
			updateUser(request, response);
		} else if (action_flag.equals("listUserPhone")) {
			listUserPhone(request, response);
		} else if (action_flag.equals("listUserPiPeiPhone")) {
			listUserPiPeiPhone(request, response);
		} else if (action_flag.equals("listUserAllPhone")) {
			listUserAllPhone(request, response);
		}else if (action_flag.equals("updateTag")) {
			updateTag(request, response);
		}else if (action_flag.equals("updateImage")) {
			updateImage(request, response);
		}else if (action_flag.equals("uploadImage")) {
			uploadImage(request, response);
		}else if (action_flag.equals("updateTag")) {
			updateTag(request, response);
		}



	}
	
	
	private void updateImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uImg = request.getParameter("uImg");
		String uid = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(uImg);
		params.add(uid);
		boolean flag = registerDao.updateImage(params);
		if (flag) {
			
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);
			
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);
		}

	}
	
	private void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imagePath = null;
		try {
			List<FileItem> items = this.upload.parseRequest(request);
			if (items != null && !items.isEmpty()) {
				for (FileItem fileItem : items) {
					String filename = fileItem.getName();

					File real_path = new File(Consts.imgPath + "/" + filename);
					InputStream inputSteam = fileItem.getInputStream();
					BufferedInputStream fis = new BufferedInputStream(inputSteam);
					FileOutputStream fos = new FileOutputStream(real_path);
					int f;
					while ((f = fis.read()) != -1) {
						fos.write(f);
					}
					fos.flush();
					fos.close();
					fis.close();
					inputSteam.close();
					System.out.println("文件：" + filename + "上传成功!");
					imagePath = filename;
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void listUserAllPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listUserAll();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}
	private void updateTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String utag = request.getParameter("utag");
		String uid = request.getParameter("uid");

		
		
		

		
		
	
		
		boolean flagCheck = registerDao.userTag(utag);
		
		if(flagCheck){
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}else{
			
			Map<String, Object> mapInfor = registerDao.usreQuery(uid);
			
			List<Object> params = new ArrayList<Object>();
			String inforMsg = utag+","+mapInfor.get("userTag").toString();
			params.add(inforMsg.substring(0, inforMsg.length()-1));
			params.add(uid);
			boolean flag = registerDao.updateTag(params);
			if (flag) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "更新成功");
				jsonmsg.put("repCode", "666");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端

			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "更新失败");
				jsonmsg.put("repCode", "111");
				System.out.println(jsonmsg);
			}
		}
		
	

	}

	private void listUserPiPeiPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String likeName = request.getParameter("likeName");
		String cityMsg = request.getParameter("cityMsg");
		
		String[] likeArr = likeName.split(",");
		String sqkAppend = "";
		for (int i = 0; i < likeArr.length; i++) {
			sqkAppend=sqkAppend+sqlMsg(likeArr[i]);
		}
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listUserPiPeiOrPhone(sqkAppend.substring(0, sqkAppend.length()-2));
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}
	
	private String sqlMsg(String sqlmessage){
		return  " uLikeName like '%" + sqlmessage + "%' ||";
	}

	private void listUserPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String likeName = request.getParameter("likeName");

		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listUserPhoneMsg(likeName);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uphone = request.getParameter("uphone");
		String uname = request.getParameter("uname");
		String uid = request.getParameter("uid");

		List<Object> params = new ArrayList<Object>();
		params.add(uphone);
		params.add(uname);
		params.add(uid);
		
		boolean flag = registerDao.updateUser(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void updateUserImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imagePath = null;
		try {
			List<FileItem> items = this.upload.parseRequest(request);
			if (items != null && !items.isEmpty()) {
				for (FileItem fileItem : items) {
					String filename = fileItem.getName();

					File real_path = new File(Consts.imgPath + "/" + filename);
					InputStream inputSteam = fileItem.getInputStream();
					BufferedInputStream fis = new BufferedInputStream(inputSteam);
					FileOutputStream fos = new FileOutputStream(real_path);
					int f;
					while ((f = fis.read()) != -1) {
						fos.write(f);
					}
					fos.flush();
					fos.close();
					fis.close();
					inputSteam.close();
					System.out.println("文件：" + filename + "上传成功!");
					imagePath = filename;
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	private void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String addFriendId = request.getParameter("addFriendId");
		List<Object> params = new ArrayList<Object>();
		params.add(addFriendId);
		boolean flag = registerDao.deleteFriend(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		String upswd = request.getParameter("upswd");
		String uphone = request.getParameter("uphone");
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		params.add(uphone);
		params.add(upswd);
		params.add(uid);
		boolean flag = registerDao.updateStudnet(params);
		if (flag) {
			listUser(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updatePcQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		Map<String, Object> mapmsg = registerDao.queryStudnet(params);
		request.setAttribute("mapmsg", mapmsg);
		request.getRequestDispatcher("../formUpdateStudent.jsp").forward(request, response);

	}

	private void addUserPc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String upswd = request.getParameter("upswd");
		String uphone = request.getParameter("uphone");
		System.out.println(uname);
		System.out.println(upswd);
		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(uname);
		params_check_login.add(upswd);

		boolean flag = registerDao.resgisterCheck(params_check_login);
		if (flag == true) {
			listUser(request, response);
		} else {
			List<Object> params = new ArrayList<Object>();
			params.add(uname);
			params.add(uphone);
			params.add(upswd);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			params.add(df.format(new Date()));
			JSONObject jsonmsg = new JSONObject();
			// 数据的注册
			boolean flag_zhuce = registerDao.resgisterPhone(params);

			// 注册成功处理
			if (flag_zhuce) {
				listUser(request, response);
			} else {
				jsonmsg.put("repMsg", "注册失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
				System.out.println(jsonmsg);
			}

			System.out.println(flag_zhuce);

		}

	}

	private void listMyNearMessagePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String addFriendUserId = request.getParameter("addFriendUserId");
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listMyNearMessagePhone(addFriendUserId);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void listMyFriendMessagePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String addFriendUserId = request.getParameter("addFriendUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(addFriendUserId);
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listMyFriendMessagePhone(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void addFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();

		String addFriendFRId = request.getParameter("addFriendFRId");
		String addFriendFRName = request.getParameter("addFriendFRName");
		String addFriendFRPhone = request.getParameter("addFriendFRPhone");
		String addFriendUserId = request.getParameter("addFriendUserId");

		List<Object> params = new ArrayList<Object>();
		params.add(addFriendFRId);
		params.add(addFriendFRName);
		params.add(addFriendFRPhone);
		params.add(addFriendUserId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));

		boolean flag = registerDao.addFriend(params);
		JSONObject jsonmsg = new JSONObject();
		if (flag) {
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void listSearchPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String searchMsg = request.getParameter("searchMsg");
		List<Map<String, Object>> list = registerDao.listSearchMessage(searchMsg);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void updatePswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userPswd = request.getParameter("userPswd");
		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userPswd);
		params.add(userId);
		boolean flag = registerDao.updatePswd(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			// response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uaddresslon = request.getParameter("uaddresslon");
		String uaddresslat = request.getParameter("uaddresslat");
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uaddresslon);
		params.add(uaddresslat);
		params.add(uid);
		boolean flag = registerDao.updateAddress(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String name = request.getParameter("name");
		String pswd = request.getParameter("pswd");
		System.out.println("-------name----" + name);
		System.out.println("-------pswd----" + pswd);
		// List<Object> params = new ArrayList<Object>();
		// params.add(name);
		// params.add(pswd);
		// boolean flag = registerDao.Login(params);
		if (name.equals("admin") && pswd.equals("123456")) {
			response.sendRedirect(path + "/mainWeb/main.jsp");
		} else {
			request.setAttribute("message", "用户名或密码错误");
			// request.getRequestDispatcher("/login.jsp").forward(request,
			// response);
		}
	}

	private void updateName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uname = request.getParameter("uname");
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		params.add(uid);
		boolean flag = registerDao.updateName(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updatePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uphone = request.getParameter("uphone");
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uphone);
		params.add(uid);
		boolean flag = registerDao.updatePhone(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteUserPc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uid = request.getParameter("uid");
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		boolean flag = registerDao.deleteUser(params);
		if (flag) {
			System.out.println("成功了");
			response.sendRedirect(path + "/servlet/RegisterAction?action_flag=listUser&uid=" + uid);
		} else {
			System.out.println("失败了");
		}

	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void registerMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String upswd = request.getParameter("upswd");
		String uphone = request.getParameter("uphone");
		System.out.println(uname);
		System.out.println(upswd);
		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(uphone);

		boolean flag = registerDao.resgisterCheck(params_check_login);
		if (flag == true) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "此用户已经注册");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			
			List<Object> params = new ArrayList<Object>();
			params.add(uname);
			params.add(uphone);
			params.add(upswd);
		
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			params.add(df.format(new Date()));
			params.add("icon_user_logo.png");
			
			JSONObject jsonmsg = new JSONObject();
			//数据的注册
			boolean flag_zhuce = registerDao.resgisterPhone(params);
			
			//注册成功处理
			if(flag_zhuce){
			
				try {
					//获取注册用户的信息
					Map<String, Object> user_model = registerDao.queryUser(params_check_login);
					//融云初始化
					RongCloud rongCloud = RongCloud.getInstance(Consts.appKey, Consts.appSecret);
					//给融云服务端提交数据,生成token
					
					TokenReslut userGetTokenResult = rongCloud.user.getToken(user_model.get("uid")+"", user_model.get("uname")+"", user_model.get("uImg")+"");
					//把生成token的数据提交给本地的服务器
					List<Object> paramsToken = new ArrayList<Object>();
					paramsToken.add(user_model.get("uid")+"");
					paramsToken.add(userGetTokenResult.getToken());
					boolean regToken = registerDao.resgisterToken(paramsToken);
					
					if(regToken){
						jsonmsg.put("repMsg", "注册成功");
						jsonmsg.put("repCode", "666");
						response.getWriter().print(jsonmsg);// 将路径返回给客户端
						System.out.println(jsonmsg);
					}else{
						jsonmsg.put("repMsg", "IM注册失败");
						jsonmsg.put("repCode", "111");
						response.getWriter().print(jsonmsg);// 将路径返回给客户端
					}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				jsonmsg.put("repMsg", "注册失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
				System.out.println(jsonmsg);
			}
			
			System.out.println(flag_zhuce);

		}

	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = registerDao.listUserMsg();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../userMessage.jsp").forward(request, response);
	}

	private void loginMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_phone = request.getParameter("uphone");
		String user_pswd = request.getParameter("pswd");

		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(user_phone);
		params_check_login.add(user_pswd);
		boolean flag = registerDao.Login(params_check_login);
		if (flag) {
			Map<String, Object> map = registerDao.usreLogin(params_check_login);
			
			List<Object> token = new ArrayList<Object>();
			token.add(map.get("uid"));
			Map<String, Object> tokenMessage = registerDao.queryToken(token);
			
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "登录成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", gsonTools.createGsonString(tokenMessage));
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
			

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "用户名或密码不正确");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
	}

	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory
		this.upload = new ServletFileUpload(factory);// Create a new file upload
		this.upload.setSizeMax(this.MAXSize);// Set overall request size
		filedir = config.getServletContext().getRealPath("upload");
		System.out.println("filedir=" + filedir);
		registerDao = new RegisterDao();
		gsonTools = new GsonTools();
	}

}
