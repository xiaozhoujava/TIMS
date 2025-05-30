package com.clientUI;

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
import com.clientBase.adapter.PraiseAdapter;
import com.clientBase.adapter.ReviewAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.PraiseModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ReviewNoticeModel;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class ReviewNoticeActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private ListView mListMessage;
	private List<ReviewNoticeModel> list_result = new ArrayList<ReviewNoticeModel>();
	private String state;
	private LinearLayout mllNomessage;


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
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvStu.setVisibility(View.GONE);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("评论信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
	}

	@Override
	public void initData() {
		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listReviewNotice");
		params.put("userId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}
	ReviewAdapter orderListAdapter;
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultFlag:
				if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

					String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
					if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
						list_result = mGson.fromJson(entry.getData(), new TypeToken<List<ReviewNoticeModel>>() {
						}.getType());
						orderListAdapter = new ReviewAdapter(ReviewNoticeActivity.this, list_result);
						mListMessage.setAdapter(orderListAdapter);
					} else {
						mllNomessage.setVisibility(View.VISIBLE);
					}
				}
				break;
			default:
				break;
		}

	}

}
