package com.clientUI;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.client.R;

import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.TypeModel;
import com.clientBase.time.JudgeDate;
import com.clientBase.time.MyAlertDialog;
import com.clientBase.time.ScreenInfo;
import com.clientBase.time.WheelMain;
import com.clientBase.util.CustomToast;
import com.clientBase.util.LoadingDialog;
import com.clientBase.util.ToastUtil;
import com.clientBase.view.DialogListMsg;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class RegisterActivity extends BaseActivity {
	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private Button mSubmit;

	private EditText metName;
	private EditText metPhone;
	private EditText metPswd;

	private int choiceType = 1;
	private RadioGroup mrgChoice;
	private RadioButton mrbTea = null;
	private RadioButton mrbParents = null;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_register);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;
			case R.id.mSubmit:
				createTopicPost(true);
				break;


		}
	}

	@Override
	public void initWidget() {


		mrgChoice = (RadioGroup) findViewById(R.id.mrgChoice);
		mrbTea = (RadioButton) findViewById(R.id.mrbTea);
		mrbParents = (RadioButton) findViewById(R.id.mrbParents);
		metName = (EditText) findViewById(R.id.metName);
		metPhone = (EditText) findViewById(R.id.metPhone);
		metPswd = (EditText) findViewById(R.id.metPswd);

		mSubmit = (Button) findViewById(R.id.mSubmit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("注册");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mSubmit.setOnClickListener(this);


	}

	@Override
	public void initData() {
		mrgChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == mrbTea.getId()) {
					choiceType = 1;
				} else if (checkedId == mrbParents.getId()) {
					choiceType = 2;
				}
			}
		});
	}

	String imgMsg;
	private void createTopicPost(boolean isShow) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addUser");
		params.put("uname", metName.getText().toString());
		params.put("uphone", metPhone.getText().toString());
		params.put("upswd", metPswd.getText().toString());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);


		switch (actionId) {
			case Consts.actionId.resultCode:
				CustomToast.showToast(this, entry.getRepMsg());
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						finish();
					}
				}, 2000);
				break;




		}
	}



}
