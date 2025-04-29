package com.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.ShopOrderAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ShopModel;
import com.clientBase.util.CustomToast;
import com.clientBase.view.ListviewForScrollView;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class PayShopMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private Button mPay;

	private List<ShopModel> listChoice = new ArrayList<ShopModel>();
	private ListviewForScrollView mListMessage;

	private TextView orderMoney;

	private	EditText mtvAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paycar_message);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;
			case R.id.mPay:
				addOrder(true);
				break;
		}
	}

	@Override
	public void initWidget() {
		orderMoney = (TextView) findViewById(R.id.orderMoney);
		mtvAddress = (EditText) findViewById(R.id.mtvAddress);
		mtvAddress.setOnClickListener(this);
		mListMessage = (ListviewForScrollView) findViewById(R.id.mListMessage);
		mPay = (Button) findViewById(R.id.mPay);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("支付确认");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mPay.setOnClickListener(this);
	}
	String payMoney;
	@Override
	public void initData() {
		listChoice = (List<ShopModel>) this.getIntent().getSerializableExtra("msg");
		payMoney = this.getIntent().getStringExtra("payMoney");
		ShopOrderAdapter carListAdapter = new ShopOrderAdapter(this, listChoice);
		mListMessage.setAdapter(carListAdapter);

		orderMoney.setText(payMoney+"元");

	}


	private void addOrder(boolean isShow) {

		String ids="";
		for(int i=0;i<listChoice.size();i++){
			ids = ids+listChoice.get(i).getShopId()+",";
		}
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addOrder");
		params.put("orderMessageId", ids.substring(0,ids.length()-1));
		params.put("orderMessageMoney",payMoney);
		params.put("orderAddress",mtvAddress.getText().toString());
		params.put("orderUserId", MemberUserUtils.getUid(this));
		params.put("orderUserName",MemberUserUtils.getName(this));
		httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		CustomToast.showToast(this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
			}
		}, 2000);

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		CustomToast.showToast(this, strMsg);

	}

}
