package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.MessageDao;

public class CourseTest {

	private MessageDao orderDao;

	@Test
	public void Reg() {
		orderDao = new MessageDao();

//		List<Object> params = new ArrayList<Object>();
//		params.add("52");
		List<Map<String, Object>> list = orderDao.listShopOrderPcMessage();
//		
//		
		
		
		
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
//		
		
	
		
		
	}
	
	private String sqlMsg(String sqlmessage){
		return  " shopTypeName like '%" + sqlmessage + "%' ||";
	}

}
