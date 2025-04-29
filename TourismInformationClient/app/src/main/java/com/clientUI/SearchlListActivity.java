package com.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.HostoryListAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.CityModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.UserModel;
import com.clientBase.util.ToastUtil;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class SearchlListActivity extends BaseActivity  {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private ListView mListMessage;
	private List<UserModel> list_result = new ArrayList<UserModel>();
	private String state;
	private LinearLayout mllNomessage;

	private CityModel cityModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;

		}
	}

	@Override
	public void initWidget() {

		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		state = this.getIntent().getStringExtra("state");

		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
	}

	@Override
	public void initData() {

		cityModel = (CityModel) this.getIntent().getSerializableExtra("msg");
		mTvTitle.setText("人员匹配");
		MessageAction(true);

		mListMessage.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(SearchlListActivity.this, UserIndexActivity.class);
				intent.putExtra("msg", list_result.get(position));
				SearchlListActivity.this.startActivity(intent);
			}
		});
	}



	private void MessageAction(boolean isShow) {
		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listUserPiPeiPhone");
		params.put("likeName",userModel.getULikeName());
		params.put("cityMsg",userModel.getUCity());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultFlag:
				if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
					String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);

					if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
						list_result.clear();
						list_result = mGson.fromJson(entry.getData(), new TypeToken<List<UserModel>>() {
						}.getType());
						HostoryListAdapter listAdapter= new HostoryListAdapter(SearchlListActivity.this, list_result);
						mListMessage.setAdapter(listAdapter);
						mllNomessage.setVisibility(View.GONE);
					} else {
						mllNomessage.setVisibility(View.VISIBLE);
					}
				}
				break;

			default:
				break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(SearchlListActivity.this, strMsg);

	}

}
