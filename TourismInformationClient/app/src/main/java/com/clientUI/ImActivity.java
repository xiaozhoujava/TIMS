package com.clientUI;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imlib.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.config.Consts;
import com.clientBase.model.Friend;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ImActivity extends FragmentActivity implements OnClickListener,UserInfoProvider {
	/** AllCategoryPracticeActivity的Tag标识 */
	private static final String TAG = "SearchMessageActivity";
	/** Fragment对象 */
	private Fragment searchFragment;
	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private List<Friend> userIdList;
	/** onCreate回调方法 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imlist);
		setWindowStatus();
		initWidget();
		initData();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			ImActivity.this.finish();
			break;
		}
	}

	public void initWidget() {
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("我的消息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
	}

	public void initData() {
		// 初始化Fragment,显示Fragment
		FragmentManager mFragmentManager = this.getSupportFragmentManager();
		FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
		Fragment searchFragment = mFragmentManager.findFragmentById(R.id.fragment_imlist);
		mFragmentTransaction.show(searchFragment);
		mFragmentTransaction.commitAllowingStateLoss();
		
		postData(false);
	}

	// 设置状态栏
	private void setWindowStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// 设置状态栏颜色
			getWindow().setBackgroundDrawableResource(R.color.main_color);
		}
	}

	/**
	 * 取消关注
	 * 
	 * @param isShow
	 */
	private void postData(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listPhoneUser");
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode);
	}

	/**
	 * FinalHttp
	 */
	public FinalHttp fh = new FinalHttp();
	public Gson mGson = new Gson();

	public void httpPost(String url, AjaxParams params, final int actionId) {
		fh.configTimeout(Consts.TIME_OUT);
		fh.post(url, params, new AjaxCallBack<String>() {
			@Override
			public void onSuccess(String response) {
				// TODO 网络访问成功
				super.onSuccess(response);
				Log.i("pony_log", "返回结果是：" + response);
				try {
					JSONObject jo = new JSONObject(response);
					ResponseEntry entry = new ResponseEntry();
					entry.setRepCode(jo.optString("repCode"));
					entry.setRepMsg(jo.optString("repMsg"));
					if (jo.optString("repCode").equals("666")) {
						entry.setData(jo.optString("data"));
						callBackSuccessed(entry, actionId);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		});
	}

	protected void callBackSuccessed(ResponseEntry entry, int actionId) {

		if (null != entry.getRepMsg() && !TextUtils.isEmpty(entry.getRepMsg())) {

			userIdList = new ArrayList<Friend>();
			List<UserModel> list_result = mGson.fromJson(entry.getData(), new TypeToken<List<UserModel>>() {
			}.getType());

			for (int i = 0; i < list_result.size(); i++) {

//
//				if(list_result.get(i).getUtype().equals("1")){
//					userIdList.add(new Friend(list_result.get(i).getUid(), list_result.get(i).getUname(), "http://www.easyicon.net/api/resizeApi.php?id=1096629&size=128"));
//				}else{
//					userIdList.add(new Friend(list_result.get(i).getUid(), list_result.get(i).getUname(), "http://www.easyicon.net/api/resizeApi.php?id=1208234&size=128"));
//				}
//

			}

			RongIM.setUserInfoProvider(this, true);

			/**
			 * 刷新用户缓存数据。
			 * 
			 * @param userInfo
			 *            需要更新的用户缓存数据。
			 */

			for (int i = 0; i < list_result.size(); i++) {

//				if(list_result.get(i).getUtype().equals("1")){
//
//						RongIM.getInstance().refreshUserInfoCache(
//								new UserInfo(list_result.get(i).getUid(), list_result.get(i).getUname(), Uri.parse("http://www.easyicon.net/api/resizeApi.php?id=1096629&size=128")));
//				} else {
//					RongIM.getInstance().refreshUserInfoCache(
//							new UserInfo(list_result.get(i).getUid(), list_result.get(i).getUname(), Uri.parse("http://www.easyicon.net/api/resizeApi.php?id=1208234&size=128")));
//				}

			}
			//

		}
	}

	@Override
	public UserInfo getUserInfo(String userId) {
		for (Friend i : userIdList) {
			if (i.getUserId().equals(userId)) {
				Log.i("pony_log", i.getPortraitUri());
				return new UserInfo(i.getUserId(), i.getUserName(), Uri.parse(i.getPortraitUri()));
			}
		}
		return null;
	}
}
