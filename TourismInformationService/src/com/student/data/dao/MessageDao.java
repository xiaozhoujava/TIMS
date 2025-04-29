package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.BaseService;
import com.student.jdbc.JdbcUtils;

public class MessageDao {
	private JdbcUtils jdbcUtils;

	public MessageDao() {
		jdbcUtils = new JdbcUtils();
	}

	
	
	public boolean addShop(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into shoptb (shopName,shopAddress,shopTypeId,shopTypeName,shopMoney,shopMessage,shopImg,shopUserId,shopUserName) values  (?,?,?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listShopOrderPcMessage() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from ordertb ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

		
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);

				String[] ids = list.get(i).get("orderMessageId").toString().split(",");
				String shopInfor = "";
				List<Map<String, Object>> listResultShop = new ArrayList<Map<String, Object>>();
				for (int a = 0; a < ids.length; a++) {
					List<Object> paramsCheck = new ArrayList<Object>();
					paramsCheck.clear();
					paramsCheck.add(ids[a] + "");
					Map<String, Object> queryShop = queryShop(paramsCheck);
					listResultShop.add(queryShop);
					shopInfor = shopInfor + queryShop.get("shopName").toString() + ",";
				}
				mapResult.put("shopLists", shopInfor.substring(0, shopInfor.length() - 1));

				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean deleteShop(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from shoptb where shopId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMyMessageTopic(String userId) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from topictb where topicUserId = " + userId + "   order by topicId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(userId);
				paramsFocus.add(list.get(i).get("topicId"));

				mapResult.put("collectState", queryCollect(paramsFocus));
				mapResult.put("praiseState", queryPraiseState(paramsFocus));

				mapResult.put("collectNumber", getItemCountCollect(list.get(i).get("topicId").toString()));
				mapResult.put("praiseNumber", getItemCountPraise(list.get(i).get("topicId").toString()));

				listResult.add(mapResult);
			}

		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;

	}

	public List<Map<String, Object>> listRankPhone() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = " select * from shoptb LEFT JOIN (select count(*) as totalNumber ,ordertb.* from ordertb GROUP BY ordertb.orderMessageId) ordermsg on shoptb.shopId = ordermsg.orderMessageId order by totalNumber desc  ";
		System.out.println(sql);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listReviewNotice(List<Object> params) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from reviewtb where reviewSendUserId =?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listCollectNotice(List<Object> params) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from collectmsg where collectSendUserId =?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listPraiseNotice(List<Object> params) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from praisetb where praiseSendUserId =?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean deletePraise(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from praisetb where praiseUserId=? and praiseMessageId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addPraise(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into praisetb (praiseUserId,praiseMessageId,praiseUserName,praiseMessageName,praiseSendUserId) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteCollect(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from collectmsg where collectUserId=? and collectMessageId = ? ";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addCollectMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into collectmsg (collectUserId,collectMessageId,collectUserName,collectMessageName,collectSendUserId) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listShopCityAllPhone(String cityinfor) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from shoptb where shopAddress like '%" + cityinfor + "%' order by shopId desc ";
		System.out.println(sql);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listShopAllPhone() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * from shoptb order by shopId desc ";
		System.out.println(sql);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	
	
	public List<Map<String, Object>> listUserRecommendPhone(String msg) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * from shoptb where" + msg;
		System.out.println(sql);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> ListShopPCMessage() {
		// TODO Auto-generated method stub

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from shoptb ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addShopPC(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into shoptb (shopName,shopTypeId,shopTypeName,shopFZName,shopMoney,shopMessage,shopImg) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listorderPhoneMesage(List<Object> params) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from ordertb where orderUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);

				String[] ids = list.get(i).get("orderMessageId").toString().split(",");

				List<Map<String, Object>> listResultShop = new ArrayList<Map<String, Object>>();
				for (int a = 0; a < ids.length; a++) {
					List<Object> paramsCheck = new ArrayList<Object>();
					paramsCheck.clear();
					paramsCheck.add(ids[a] + "");
					Map<String, Object> queryShop = queryShop(paramsCheck);
					listResultShop.add(queryShop);
				}
				mapResult.put("shopLists", listResultShop);

				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addOrder(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into ordertb (orderMessageId,orderMessageMoney,orderUserId,orderUserName,orderAddress,orderCreatime,orderNo) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMyOrderPhoneMessage(List<Object> params) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from ordertb where orderUserId= ? ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("orderMessageId") + "");
				mapResult.put("shopMessage", queryShop(paramsFocus));
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean updateShop(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  shoptb set shopName=?,shopMoney=?,shopMessage=?,shopImg=? where shopId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public Map<String, Object> queryShop(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from shoptb where shopId=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}

	public List<Map<String, Object>> listShopmMainPhoneMessage() {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from shoptb order by shopId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean updateGreensStateMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  topictb set topicFlag =? where topicId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMessageTopicAll(String userId) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from topictb where topicFlag = 2 order by topicId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(userId);
				paramsFocus.add(list.get(i).get("topicId"));

				mapResult.put("collectState", queryCollect(paramsFocus));
				mapResult.put("praiseState", queryPraiseState(paramsFocus));

				mapResult.put("collectNumber", getItemCountCollect(list.get(i).get("topicId").toString()));
				mapResult.put("praiseNumber", getItemCountPraise(list.get(i).get("topicId").toString()));

				listResult.add(mapResult);
			}

		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;

	}

	public int getItemCountCollect(String collectMessageId) {
		int result = 0;
		Map<String, Object> map = null;

		String sql = " select COUNT(*) as mycount from collectmsg  where collectMessageId = " + collectMessageId + " group by  collectMessageId";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, null);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return result;
	}

	public int getItemCountPraise(String praiseMessageId) {
		int result = 0;
		Map<String, Object> map = null;

		String sql = " select COUNT(*) as mycount from praisetb  where praiseMessageId = " + praiseMessageId + " group by  praiseMessageId";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, null);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return result;
	}

	public boolean queryPraiseState(List<Object> params) {
		boolean flag = false;
		String sql = "select * from praisetb where praiseUserId=? and praiseMessageId=?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean queryCollect(List<Object> params) {
		boolean flag = false;
		String sql = "select * from collectmsg where collectUserId=? and collectMessageId=?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMyCourseMessage(List<Object> params) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from choicetb LEFT JOIN coursetb on choicetb.choiceCourseId = coursetb.courseId where choiceStuId = ? ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listMessageCourseAll(String topicvideoUserId) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from coursetb ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("courseId"));
				paramsFocus.add(topicvideoUserId);

				mapResult.put("choiceState", queryCollect(paramsFocus));
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addChoice(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into choicetb (choiceCourseId,choiceStuId,choiceTime) values  (?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deletChoiceCourse(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from choicetb where choiceCourseId = ? and   choiceStuId= ?  ";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteCourse(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from coursetb where courseId = ?  ";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMessageCourse() {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from coursetb ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addCourse(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into coursetb (courseName,courseType,courseInfor,courseTime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	// params.add(topicTypeInfor + "");
	// params.add(topicInfor + "");
	public boolean addTopic(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into topictb (topicTypeInfor,topicInfor,topicImg,topicUserId,topicUserName,topicTime,topicFlag,topicState) values  (?,?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMessageNiMing() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from topictb  where  topicState  = 2 order by topicId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;

	}

	public List<Map<String, Object>> listMessageTopic() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from topictb  where  topicState  = 1 order by topicId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;

	}

	public List<Map<String, Object>> queryImg(List<Object> params) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from imgdb where imgUserId=? order by imgId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addImage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into imgdb (imgMsg,imgUserId,imgUserName,imgTime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean updatePrise(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uPrise =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public Map<String, Object> queryUser(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from user where uid=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}

	public List<Map<String, Object>> reviewListTopicZhuMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from reviewtb where  reviewMessageId = ? and reviewSendUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> reviewListMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from reviewmsg where reviewMessageId = ? order by reviewId desc  ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addReview(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into reviewmsg (reviewMessageId,reviewContent,reviewUserId,reviewUserName,reviewTime) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}


	public List<Map<String, Object>> listShareMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from sharetb";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addShare(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into sharetb (shareMessage,shareImg,shareUserId,shareUserName,shareTime) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteShangXiMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from appreciationtb where appreciationId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listPcShangXi() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from appreciationtb";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addShangXiMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into appreciationtb (appreciationTitle,appreciationMessage,appreciationTime,appreciationImg) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteMusicMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from musicmsg where musicId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from musicmsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addMusic(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into musicmsg (musicTypeId,musicTypeName,musicTitle,musicMessage,musicCreatTime,musicImage,musicFile) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from typemsg where typeId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteNews(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from newstb where newsId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listTypeMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typemsg ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into typemsg (typeName) values  (?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> ListNewsPc() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from newstb ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addNews(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into newstb (newsTitle,newsMessage,newsTime) values  (?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> queryReply(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from replytb where replyMessgaeId = ? order by replyId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addReply(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into replytb (replyMessgaeId,replyMessage,replyUserId,replyUserName,replyCreatime) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> ListShopOffer(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from offertb where offerMessageId = ? and offerType = ? order by offerId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> ListOffer(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from offertb where offerUserId = ? and offerType = ? order by offerId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addOffer(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into offertb (offerMessageId,offerMessageName,offerUserId,offerUserName,offerMoney,offerType,offerTime) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addSceneryMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into jewelrytb (jewelryTitle,jewelryMoney,jewelryMessage,jewelryTime,jewelryImage) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deletescenerymsgType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from jewelrytb where jewelryId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listscenerymsgTypeMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from jewelrytb";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

}
