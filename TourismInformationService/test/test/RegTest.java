package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.RegisterDao;

public class RegTest {

	private RegisterDao orderDao;

	@Test
	public void Reg() {
		orderDao = new RegisterDao();

		List<Object> params = new ArrayList<Object>();
		params.add("37");
		params.add("1");

		String likeName = "摄影,茶艺,旅游";

		String[] likeArr = likeName.split(",");

//		String sql = "SELECT * from user where uLikeName like '%" + msg + "%'";

		String sqkAppend = "";
		for (int i = 0; i < likeArr.length; i++) {
			sqkAppend=sqkAppend+sqlMsg(likeArr[i]);
		}
		
		System.out.println(sqkAppend.substring(0, sqkAppend.length()-2));

		List<Map<String, Object>> myCourse = orderDao.listUserPiPeiOrPhone(sqkAppend.substring(0, sqkAppend.length()-2));
//
//		JSONObject jsonmsg = new JSONObject();
//		jsonmsg.put("repMsg", "请求成功");
//		jsonmsg.put("repCode", "666");
//		jsonmsg.put("data", myCourse);
//		System.out.println(jsonmsg);
	}
	
	private String sqlMsg(String sqlmessage){
		return  " utag like '%" + sqlmessage + "%' ||";
	}
}
