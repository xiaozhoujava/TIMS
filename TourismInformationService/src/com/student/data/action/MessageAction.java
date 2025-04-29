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
import java.util.HashMap;
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

import com.student.data.dao.MessageDao;
import com.student.jdbc.Consts;
import com.student.util.PingYinUtil;

public class MessageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;
	private MessageDao hobbyDao;

	public MessageAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("addScenery")) {
			addScenery(request, response);
		} else if (action_flag.equals("listSceneryMessage")) {
			listSceneryMessage(request, response);
		} else if (action_flag.equals("deleteScenery")) {
			deleteScenery(request, response);
		} else if (action_flag.equals("listSceneryPhoneMessage")) {
			listSceneryPhoneMessage(request, response);
		} else if (action_flag.equals("addOffer")) {
			addOffer(request, response);
		} else if (action_flag.equals("ListOffer")) {
			ListOffer(request, response);
		} else if (action_flag.equals("ListShopOffer")) {
			ListShopOffer(request, response);
		}

		else if (action_flag.equals("addReply")) {
			addReply(request, response);
		} else if (action_flag.equals("queryUserReply")) {
			queryUserReply(request, response);
		}

		else if (action_flag.equals("ListPcOffer")) {
			ListPcOffer(request, response);
		} else if (action_flag.equals("addNews")) {
			addNews(request, response);
		} else if (action_flag.equals("ListNewsPc")) {
			listNewsMessage(request, response);
		} else if (action_flag.equals("listPhoneNewsMessage")) {
			listPhoneNewsMessage(request, response);
		}

		else if (action_flag.equals("addType")) {
			addType(request, response);
		} else if (action_flag.equals("listTypeMessage")) {
			listTypeMessage(request, response);
		} else if (action_flag.equals("listTypeChoiceMessage")) {
			listTypeChoiceMessage(request, response);
		} else if (action_flag.equals("deleteType")) {
			deleteType(request, response);
		}

		else if (action_flag.equals("addMusic")) {
			addMusic(request, response);
		} else if (action_flag.equals("listMessage")) {
			listMessage(request, response);
		} else if (action_flag.equals("deleteMessage")) {
			deleteMessage(request, response);
		} else if (action_flag.equals("listPhoneMusicMessage")) {
			listPhoneMusicMessage(request, response);
		} else if (action_flag.equals("addShangXi")) {
			addShangXi(request, response);
		} else if (action_flag.equals("listPcShangXi")) {
			listPcShangXi(request, response);
		} else if (action_flag.equals("deleteShangXiMessage")) {
			deleteShangXiMessage(request, response);
		} else if (action_flag.equals("listPhoneShangXi")) {
			listPhoneShangXi(request, response);
		} else if (action_flag.equals("addShare")) {
			addShare(request, response);
		} else if (action_flag.equals("listPhoneShare")) {
			listPhoneShare(request, response);
		} else if (action_flag.equals("addReview")) {
			addReview(request, response);
		} else if (action_flag.equals("reviewListMessage")) {
			reviewListMessage(request, response);
		} else if (action_flag.equals("deleteNews")) {
			deleteNews(request, response);
		} else if (action_flag.equals("listPhoneTypeMessage")) {
			listPhoneTypeMessage(request, response);
		} else if (action_flag.equals("addImage")) {
			addImage(request, response);
		} else if (action_flag.equals("listMessageImage")) {
			listMessageImage(request, response);
		} else if (action_flag.equals("listMessageTopic")) {
			listMessageTopic(request, response);
		} else if (action_flag.equals("addTopic")) {
			addTopic(request, response);
		} else if (action_flag.equals("listMessageNiMing")) {
			listMessageNiMing(request, response);
		} else if (action_flag.equals("addNiMing")) {
			addNiMing(request, response);
		} else if (action_flag.equals("reviewListTopicZhuMessage")) {
			reviewListTopicZhuMessage(request, response);
		} else if (action_flag.equals("addCourse")) {
			addCourse(request, response);
		} else if (action_flag.equals("listCourseChoicePc")) {
			listCourseChoicePc(request, response);
		} else if (action_flag.equals("addChoice")) {
			addChoice(request, response);
		} else if (action_flag.equals("deleteChoice")) {
			deleteChoice(request, response);
		} else if (action_flag.equals("listCoursePhone")) {
			listCoursePhone(request, response);
		} else if (action_flag.equals("deleteCourse")) {
			deleteCourse(request, response);
		} else if (action_flag.equals("listMyCourseMessage")) {
			listMyCourseMessage(request, response);
		} else if (action_flag.equals("listTopicPCMessage")) {
			listTopicPCMessage(request, response);
		} else if (action_flag.equals("updateState")) {
			updateState(request, response);
		} else if (action_flag.equals("listShopmMainPhoneMessage")) {
			listShopmMainPhoneMessage(request, response);
		} else if (action_flag.equals("addOrder")) {
			addOrder(request, response);
		} else if (action_flag.equals("listOrderPhoneMessage")) {
			listOrderPhoneMessage(request, response);
		} else if (action_flag.equals("addShopPC")) {
			addShopPC(request, response);
		} else if (action_flag.equals("ListShopPCMessage")) {
			ListShopPCMessage(request, response);
		} else if (action_flag.equals("listUserRecommendPhone")) {
			listUserRecommendPhone(request, response);
		} else if (action_flag.equals("addCollectMessage")) {
			addCollectMessage(request, response);
		} else if (action_flag.equals("deleteCollectMessage")) {
			deleteCollectMessage(request, response);
		} else if (action_flag.equals("addPraise")) {
			addPraise(request, response);
		} else if (action_flag.equals("deletePraise")) {
			deletePraise(request, response);
		} else if (action_flag.equals("listPraiseNotice")) {
			listPraiseNotice(request, response);
		} else if (action_flag.equals("listCollectNotice")) {
			listCollectNotice(request, response);
		} else if (action_flag.equals("listReviewNotice")) {
			listReviewNotice(request, response);
		} else if (action_flag.equals("listRankPhone")) {
			listRankPhone(request, response);
		} else if (action_flag.equals("listMyMessageTopic")) {
			listMyMessageTopic(request, response);
		} else if (action_flag.equals("queryShop")) {
			queryShop(request, response);
		} else if (action_flag.equals("updateShop")) {
			updateShop(request, response);
		} else if (action_flag.equals("deleteShop")) {
			deleteShop(request, response);
		} else if (action_flag.equals("listShopOrderPcMessage")) {
			listShopOrderPcMessage(request, response);
		}else if (action_flag.equals("addShopPhone")) {
			addShopPhone(request, response);
		}else if (action_flag.equals("listTypePhoneMessage")) {
			listTypePhoneMessage(request, response);
		}else if (action_flag.equals("listRecommendPhone")) {
			listRecommendPhone(request, response);
		}

	}

	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory
		this.upload = new ServletFileUpload(factory);// Create a new file upload
		this.upload.setSizeMax(this.MAXSize);// Set overall request size
		filedir = config.getServletContext().getRealPath("upload");
		System.out.println("filedir=" + filedir);
		hobbyDao = new MessageDao();
	}
	
	
	private void listRecommendPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userTag = request.getParameter("userTag");
		
		
		if(userTag==null){
			List<Map<String, Object>> list = hobbyDao.listRankPhone();
			
			
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "请求成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", list);
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}else{
			String[] likeArr = userTag.split(",");
			String sqkAppend = "";
			for (int i = 0; i < likeArr.length; i++) {
				sqkAppend=sqkAppend+sqlMsg(likeArr[i]);
			}
			// 已经进行分页之后的数据集合
			List<Map<String, Object>> list = hobbyDao.listUserRecommendPhone(sqkAppend.substring(0, sqkAppend.length()-2));
			
			
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "请求成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", list);
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
		
	
	}
	
	private void listTypePhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listTypeMessage();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}
	
	
	private void addShopPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shopName = request.getParameter("shopName");
		String shopAddress = request.getParameter("shopAddress");
		
		String shopTypeId = request.getParameter("shopTypeId");
		String shopTypeName = request.getParameter("shopTypeName");
		
		String shopMoney = request.getParameter("shopMoney");
		String shopMessage = request.getParameter("shopMessage");
		String shopImg = request.getParameter("shopImg");
		String shopUserId = request.getParameter("shopUserId");
		String shopUserName = request.getParameter("shopUserName");

		String imagePath = null;
		if (shopName == null) {
			try {
				List<FileItem> items = this.upload.parseRequest(request);
				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						String filename = fileItem.getName();

						System.out.println("文件保存路径为:" + Consts.imgPath + "/" + filename);
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
		} else {

			List<Object> params = new ArrayList<Object>();
			params.add(shopName + "");
			params.add(shopAddress);
			
			params.add(shopTypeId + "");
			params.add(shopTypeName + "");
			
			params.add(shopMoney + "");
			params.add(shopMessage + "");

			params.add(shopImg + "");
			params.add(shopUserId + "");
			params.add(shopUserName + "");
			boolean flag = hobbyDao.addShop(params);
			System.out.println(flag + "");
			if (flag) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "提交成功");
				jsonmsg.put("repCode", "666");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "上传文件失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}
		}

	}
	private void listShopOrderPcMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		List<Map<String, Object>> list = hobbyDao.listShopOrderPcMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../orderMessage.jsp").forward(request, response);

	}
	private void deleteShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String shopId = request.getParameter("shopId");
		List<Object> params = new ArrayList<Object>();
		params.add(shopId);
		boolean flag = hobbyDao.deleteShop(params);

		if (flag) {
			ListShopPCMessage(request, response);

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String shopId = request.getParameter("shopId");

		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("shopName")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("shopMoney")) {
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("shopMessage")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));
					}

				} else {
					try {

						String image = fileItem.getName();
						String imageload = PingYinUtil.getPingYin(image);
						System.out.println("image111--->>" + imageload);
						params.add(imageload);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = Consts.imgPath;
						File real_path = new File(imgPath + "/" + imageload);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

			System.out.println("shopId:" + shopId);
			params.add(shopId);
			boolean flag = hobbyDao.updateShop(params);

			if (flag) {
				ListShopPCMessage(request, response);
			} else {
				System.out.println("flag:no");
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void queryShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String shopId = request.getParameter("shopId");
		List<Object> params = new ArrayList<Object>();
		params.add(shopId);
		Map<String, Object> map = hobbyDao.queryShop(params);
		request.setAttribute("map", map);
		request.getRequestDispatcher("../updateShop.jsp").forward(request, response);
	}

	private void listMyMessageTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String userId = request.getParameter("userId");
		List<Map<String, Object>> list = hobbyDao.listMyMessageTopic(userId);
		// 生成json字符串s
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listRankPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", hobbyDao.listRankPhone());
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void listReviewNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> flagFood = hobbyDao.listReviewNotice(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listCollectNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> flagFood = hobbyDao.listCollectNotice(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listPraiseNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> flagFood = hobbyDao.listPraiseNotice(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deletePraise(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String praiseUserId = request.getParameter("praiseUserId");
		String praiseMessageId = request.getParameter("praiseMessageId");

		List<Object> params = new ArrayList<Object>();
		params.add(praiseUserId + "");
		params.add(praiseMessageId + "");
		boolean flag = hobbyDao.deletePraise(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "点赞取消成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "点赞取消失败");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
	}

	private void addPraise(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String praiseUserId = request.getParameter("praiseUserId");
		String praiseMessageId = request.getParameter("praiseMessageId");

		String praiseUserName = request.getParameter("praiseUserName");
		String praiseMessageName = request.getParameter("praiseMessageName");
		String praiseSendUserId = request.getParameter("praiseSendUserId");

		List<Object> params = new ArrayList<Object>();
		params.add(praiseUserId + "");
		params.add(praiseMessageId + "");
		params.add(praiseUserName + "");
		params.add(praiseMessageName + "");
		params.add(praiseSendUserId + "");
		boolean flag = hobbyDao.addPraise(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "点赞成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "点赞失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
	}

	private void deleteCollectMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String collectUserId = request.getParameter("collectUserId");
		String collectMessageId = request.getParameter("collectMessageId");
		List<Object> params = new ArrayList<Object>();
		params.add(collectUserId);
		params.add(collectMessageId);
		boolean flag = hobbyDao.deleteCollect(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "取消成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "取消失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void addCollectMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String collectUserId = request.getParameter("collectUserId");
		String collectMessageId = request.getParameter("collectMessageId");

		String collectUserName = request.getParameter("collectUserName");
		String collectMessageName = request.getParameter("collectMessageName");
		String collectSendUserId = request.getParameter("collectSendUserId");

		List<Object> params = new ArrayList<Object>();
		params.add(collectUserId);
		params.add(collectMessageId);
		params.add(collectUserName);
		params.add(collectMessageName);
		params.add(collectSendUserId);

		boolean flag = hobbyDao.addCollectMessage(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "收藏成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "收藏失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listUserRecommendPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String city = request.getParameter("city");
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", hobbyDao.listShopCityAllPhone(city));
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private String sqlMsg(String sqlmessage) {
		return " shopTypeName like '%" + sqlmessage + "%' ||";
	}

	private void addShopPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("shopName")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					/*
					 * if (fileItem.getFieldName().equals("typeMessage")) {
					 * 
					 * params.add(fileItem.getString("utf-8").split(",")[0]);
					 * params.add(fileItem.getString("utf-8").split(",")[1]);
					 * 
					 * }
					 */
					if (fileItem.getFieldName().equals("shopFZName")) {
						params.add("-1");
						params.add("-1");
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("shopMoney")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("shopNumber")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("shopMessage")) {

						params.add(fileItem.getString("utf-8"));

					}

				} else {
					try {

						String image = fileItem.getName();
						String imageload = PingYinUtil.getPingYin(image);
						System.out.println("image111--->>" + imageload);
						params.add(imageload);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = Consts.imgPath;
						File real_path = new File(imgPath + "/" + imageload);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = hobbyDao.addShopPC(params);

					if (flag) {
						ListShopPCMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ListShopPCMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = hobbyDao.ListShopPCMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../shopMessage.jsp").forward(request, response);

	}

	private void listOrderPhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		List<Map<String, Object>> flagFood = hobbyDao.listorderPhoneMesage(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderMessageId = request.getParameter("orderMessageId");
		String orderMessageMoney = request.getParameter("orderMessageMoney");
		String orderAddress = request.getParameter("orderAddress");
		String orderUserId = request.getParameter("orderUserId");
		String orderUserName = request.getParameter("orderUserName");
		List<Object> params = new ArrayList<Object>();
		params.add(orderMessageId);
		params.add(orderMessageMoney);
		params.add(orderUserId);
		params.add(orderUserName);
		params.add(orderAddress);

		System.out.println(orderMessageId);
		System.out.println(orderMessageMoney);
		System.out.println(orderUserId);
		System.out.println(orderUserName);
		System.out.println(orderAddress);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));

		SimpleDateFormat dfInfor = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		params.add("NO" + dfInfor.format(new Date()));
		boolean flag = hobbyDao.addOrder(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "购买成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void listShopmMainPhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		List<Map<String, Object>> listShop = hobbyDao.listShopmMainPhoneMessage();
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", listShop);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String topicFlag = request.getParameter("topicFlag");
		String topicId = request.getParameter("topicId");
		List<Object> params = new ArrayList<Object>();
		params.add(topicFlag);
		params.add(topicId);
		boolean flag = hobbyDao.updateGreensStateMessage(params);

		if (flag) {
			listTopicPCMessage(request, response);

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listTopicPCMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = hobbyDao.listMessageTopic();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../topicMessage.jsp").forward(request, response);
	}

	private void listMyCourseMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);

		List<Map<String, Object>> list = hobbyDao.listMyCourseMessage(params);
		// 生成json字符串s
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String courseId = request.getParameter("courseId");
		List<Object> params = new ArrayList<Object>();
		params.add(courseId);
		boolean flag = hobbyDao.deleteCourse(params);
		if (flag) {
			listCourseChoicePc(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void listCoursePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String topicvideoUserId = request.getParameter("choiceStuId");
		List<Map<String, Object>> list = hobbyDao.listMessageCourseAll(topicvideoUserId);
		// 生成json字符串s
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String choiceCourseId = request.getParameter("choiceCourseId");
		String choiceStuId = request.getParameter("choiceStuId");

		List<Object> params = new ArrayList<Object>();
		params.add(choiceCourseId);
		params.add(choiceStuId);
		boolean flag = hobbyDao.deletChoiceCourse(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "取消成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void addChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String choiceCourseId = request.getParameter("choiceCourseId");
		String choiceStuId = request.getParameter("choiceStuId");

		List<Object> params = new ArrayList<Object>();
		params.add(choiceCourseId);
		params.add(choiceStuId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = hobbyDao.addChoice(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void listCourseChoicePc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = hobbyDao.listMessageCourse();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../courseMessage.jsp").forward(request, response);
	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName = request.getParameter("courseName");
		String courseType = request.getParameter("courseType");
		String courseInfor = request.getParameter("courseInfor");

		List<Object> params = new ArrayList<Object>();
		params.add(courseName);
		params.add(courseType);
		params.add(courseInfor);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = hobbyDao.addCourse(params);
		System.out.println(flag + "");
		if (flag) {
			listCourseChoicePc(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "上传文件失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void reviewListTopicZhuMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reviewMessageId = request.getParameter("reviewMessageId");
		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(reviewMessageId);
		params.add(userId);

		List<Map<String, Object>> flagFood = hobbyDao.reviewListTopicZhuMessage(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMessageNiMing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		List<Map<String, Object>> list = hobbyDao.listMessageNiMing();
		// 生成json字符串s
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addNiMing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String topicTypeInfor = request.getParameter("topicTypeInfor");
		String topicInfor = request.getParameter("topicInfor");
		String topicImg = request.getParameter("topicImg");
		String topicUserId = request.getParameter("topicUserId");
		String topicUserName = request.getParameter("topicUserName");
		String topicFlag = request.getParameter("topicFlag");
		List<Object> params = new ArrayList<Object>();

		params.add(topicTypeInfor + "");
		params.add(topicInfor + "");
		params.add(topicImg);
		params.add(topicUserId);
		params.add(topicUserName);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		params.add(topicFlag);
		params.add("2");
		boolean flag = hobbyDao.addTopic(params);
		System.out.println(flag + "");
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "发布成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "上传文件失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void addTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String topicTypeInfor = request.getParameter("topicTypeInfor");
		String topicInfor = request.getParameter("topicInfor");

		String topicImg = request.getParameter("topicImg");
		String topicUserId = request.getParameter("topicUserId");
		String topicUserName = request.getParameter("topicUserName");
		String topicFlag = request.getParameter("topicFlag");
		String imagePath = null;
		if (topicTypeInfor == null) {
			try {
				List<FileItem> items = this.upload.parseRequest(request);
				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						String filename = fileItem.getName();
						System.out.println("文件保存路径为:" + Consts.imgPath + "/" + filename);
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
		} else {
			// topicInfor,topicImg,topicUserId,topicUserName,topicTime
			List<Object> params = new ArrayList<Object>();

			params.add(topicTypeInfor + "");
			params.add(topicInfor + "");
			params.add(topicImg);
			params.add(topicUserId);
			params.add(topicUserName);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			params.add(df.format(new Date()));
			params.add(topicFlag);
			params.add("1");
			boolean flag = hobbyDao.addTopic(params);
			System.out.println(flag + "");
			if (flag) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "发布成功");
				jsonmsg.put("repCode", "666");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "上传文件失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

		}

	}

	private void listMessageTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String userId = request.getParameter("userId");
		List<Map<String, Object>> list = hobbyDao.listMessageTopicAll(userId);
		// 生成json字符串s
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMessageImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String imgUserId = request.getParameter("imgUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(imgUserId + "");
		List<Map<String, Object>> list = hobbyDao.queryImg(params);
		// 生成json字符串s
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String imgMsg = request.getParameter("imgMsg");
		String imgUserId = request.getParameter("imgUserId");
		String imgUserName = request.getParameter("imgUserName");

		String imagePath = null;
		if (imgUserId == null) {
			try {
				List<FileItem> items = this.upload.parseRequest(request);
				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						String filename = fileItem.getName();
						System.out.println("文件保存路径为:" + Consts.imgPath + "/" + filename);
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
		} else {

			List<Object> params = new ArrayList<Object>();
			params.add(imgMsg + "");
			params.add(imgUserId + "");
			params.add(imgUserName + "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			params.add(df.format(new Date()));

			boolean flag = hobbyDao.addImage(params);
			System.out.println(flag + "");
			if (flag) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "上传成功");
				jsonmsg.put("repCode", "666");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "上传文件失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

		}

	}

	private void addPrise(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uid = request.getParameter("uid");

		List<Object> paramsQuery = new ArrayList<Object>();
		paramsQuery.add(uid);

		Map<String, Object> userMessage = hobbyDao.queryUser(paramsQuery);

		int priseNumber = Integer.valueOf(userMessage.get("uPrise").toString());

		List<Object> params = new ArrayList<Object>();
		params.add((priseNumber + 1) + "");
		params.add(uid);

		boolean flag = hobbyDao.updatePrise(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", (priseNumber + 1) + "");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评论失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void listPhoneTypeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listTypeMessage();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void reviewListMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reviewMessageId = request.getParameter("reviewMessageId");
		List<Object> params = new ArrayList<Object>();
		params.add(reviewMessageId);
		List<Map<String, Object>> flagFood = hobbyDao.reviewListMessage(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String reviewMessageId = request.getParameter("reviewMessageId");
		String reviewContent = request.getParameter("reviewContent");
		String reviewUserId = request.getParameter("reviewUserId");
		String reviewUserName = request.getParameter("reviewUserName");

		System.out.println(reviewMessageId);

		List<Object> params = new ArrayList<Object>();
		params.add(reviewMessageId);
		params.add(reviewContent);
		params.add(reviewUserId);
		params.add(reviewUserName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = hobbyDao.addReview(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评论成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评论失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}

	private void listPhoneShare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> flagFood = hobbyDao.listShareMessage();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addShare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String shareMessage = request.getParameter("shareMessage");
		String shareImg = request.getParameter("shareImg");
		String shareUserId = request.getParameter("shareUserId");
		String shareUserName = request.getParameter("shareUserName");

		String imagePath = null;
		if (shareMessage == null) {
			try {
				List<FileItem> items = this.upload.parseRequest(request);
				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						String filename = fileItem.getName();

						System.out.println("文件保存路径为:" + Consts.imgPath + "/" + filename);
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
		} else {

			List<Object> params = new ArrayList<Object>();
			params.add(shareMessage + "");
			params.add(shareImg + "");
			params.add(shareUserId + "");
			params.add(shareUserName + "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");// 设置日期格式
			params.add(df.format(new Date()));

			boolean flag = hobbyDao.addShare(params);
			System.out.println(flag + "");
			if (flag) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "提交成功");
				jsonmsg.put("repCode", "666");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "上传文件失败");
				jsonmsg.put("repCode", "111");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}
		}

	}

	private void listPhoneShangXi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listPcShangXi();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteShangXiMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String appreciationId = request.getParameter("appreciationId");
		List<Object> params = new ArrayList<Object>();
		params.add(appreciationId);
		boolean flag = hobbyDao.deleteShangXiMessage(params);
		if (flag) {
			listPcShangXi(request, response);
		} else {
			System.out.println("失败了");
		}
	}

	private void listPcShangXi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listPcShangXi();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../appMessage.jsp").forward(request, response);

	}

	private void addShangXi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("appreciationTitle")) {
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));
					}

					if (fileItem.getFieldName().equals("appreciationMessage")) {
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
						params.add(df.format(new Date()));
						System.out.println(df.format(new Date()));

					}

				} else {
					try {
						String image = fileItem.getName();
						System.out.println("image111--->>" + image);
						params.add(image);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = Consts.imgPath;
						File real_path = new File(imgPath + "/" + image);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = hobbyDao.addShangXiMessage(params);
					System.out.println(flag);
					if (flag) {
						listPcShangXi(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listPhoneMusicMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listMessage();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String musicId = request.getParameter("musicId");
		List<Object> params = new ArrayList<Object>();
		params.add(musicId);
		boolean flag = hobbyDao.deleteMusicMessage(params);
		if (flag) {
			listMessage(request, response);
		} else {
			System.out.println("失败了");
		}
	}

	private void addMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {

					if (fileItem.getFieldName().equals("musicType")) {
						params.add(fileItem.getString("utf-8").split(",")[0]);
						params.add(fileItem.getString("utf-8").split(",")[1]);
					}

					if (fileItem.getFieldName().equals("musicTitle")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("musicMessage")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
						params.add(df.format(new Date()));
						System.out.println(df.format(new Date()));

					}

				} else {
					try {
						String image = fileItem.getName();
						String imageload = PingYinUtil.getPingYin(image);
						System.out.println("image111--->>" + imageload);
						params.add(imageload);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = Consts.imgPath;
						File real_path = new File(imgPath + "/" + imageload);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = hobbyDao.addMusic(params);
					System.out.println(flag);
					if (flag) {
						listMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../musicMessage.jsp").forward(request, response);

	}

	private void listTypeChoiceMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listTypeMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../formMusic.jsp").forward(request, response);

	}

	private void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typeId = request.getParameter("typeId");
		List<Object> params = new ArrayList<Object>();
		params.add(typeId);
		boolean flag = hobbyDao.deleteType(params);
		if (flag) {
			listTypeMessage(request, response);
		}

	}

	private void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsId = request.getParameter("newsId");
		List<Object> params = new ArrayList<Object>();
		params.add(newsId);
		boolean flag = hobbyDao.deleteNews(params);
		if (flag) {
			listNewsMessage(request, response);
		}

	}

	private void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typeName = request.getParameter("typeName");

		List<Object> params = new ArrayList<Object>();
		params.add(typeName);
		boolean flag = hobbyDao.addType(params);
		if (flag) {
			listTypeMessage(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listTypeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listTypeMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../typeMessage.jsp").forward(request, response);

	}

	private void listPhoneNewsMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.ListNewsPc();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String newsTitle = request.getParameter("newsTitle");
		String newsMessage = request.getParameter("newsMessage");

		List<Object> params = new ArrayList<Object>();
		params.add(newsTitle);
		params.add(newsMessage);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));

		boolean flag = hobbyDao.addNews(params);
		if (flag) {
			listNewsMessage(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listNewsMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.ListNewsPc();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../newsMessage.jsp").forward(request, response);

	}

	private void ListPcOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String offerMessageId = request.getParameter("offerMessageId");
		String offerType = request.getParameter("offerType");
		List<Object> params = new ArrayList<Object>();
		params.add(offerMessageId);
		params.add(offerType);

		List<Map<String, Object>> list = hobbyDao.ListShopOffer(params);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../moneyMessage.jsp").forward(request, response);

	}

	private void queryUserReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String replyMessgaeId = request.getParameter("replyMessgaeId");

		System.out.println(replyMessgaeId);

		List<Object> params = new ArrayList<Object>();
		params.add(replyMessgaeId);
		List<Map<String, Object>> list = hobbyDao.queryReply(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String replyMessgaeId = request.getParameter("replyMessgaeId");
		String replyMessage = request.getParameter("replyMessage");
		String replyUserId = request.getParameter("replyUserId");
		String replyUserName = request.getParameter("replyUserName");
		List<Object> params = new ArrayList<Object>();
		params.add(replyMessgaeId);
		params.add(replyMessage);
		params.add(replyUserId);
		params.add(replyUserName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = hobbyDao.addReply(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "发布成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void ListShopOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String offerMessageId = request.getParameter("offerMessageId");
		String offerType = request.getParameter("offerType");
		List<Object> params = new ArrayList<Object>();
		params.add(offerMessageId);
		params.add(offerType);

		List<Map<String, Object>> flagFood = hobbyDao.ListShopOffer(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void ListOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String offerUserId = request.getParameter("offerUserId");
		String offerType = request.getParameter("offerType");
		List<Object> params = new ArrayList<Object>();
		params.add(offerUserId);
		params.add(offerType);

		List<Map<String, Object>> flagFood = hobbyDao.ListOffer(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String offerMessageId = request.getParameter("offerMessageId");
		String offerMessageName = request.getParameter("offerMessageName");

		String offerUserId = request.getParameter("offerUserId");
		String offerUserName = request.getParameter("offerUserName");
		String offerMoney = request.getParameter("offerMoney");
		String offerType = request.getParameter("offerType");

		List<Object> params = new ArrayList<Object>();
		params.add(offerMessageId);
		params.add(offerMessageName);
		params.add(offerUserId);
		params.add(offerUserName);
		params.add(offerMoney);
		params.add(offerType);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));

		boolean flag = hobbyDao.addOffer(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交成功");
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

	private void deleteScenery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jewelryId = request.getParameter("jewelryId");
		List<Object> params = new ArrayList<Object>();
		params.add(jewelryId);
		boolean flag = hobbyDao.deletescenerymsgType(params);
		if (flag) {
			listSceneryMessage(request, response);
		}

	}

	private void listSceneryPhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> flagFood = hobbyDao.listscenerymsgTypeMessage();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listSceneryMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = hobbyDao.listscenerymsgTypeMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../jewelryMessage.jsp").forward(request, response);

	}

	private void addScenery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("jewelryTitle")) {
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));
					}

					if (fileItem.getFieldName().equals("jewelryMoney")) {
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));
					}

					if (fileItem.getFieldName().equals("jewelryMessage")) {
						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
						params.add(df.format(new Date()));
						System.out.println(df.format(new Date()));

					}

				} else {
					try {
						String image = fileItem.getName();
						System.out.println("image111--->>" + image);
						params.add(image);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = Consts.imgPath;
						File real_path = new File(imgPath + "/" + image);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = hobbyDao.addSceneryMessage(params);
					System.out.println(flag);
					if (flag) {
						listSceneryMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
