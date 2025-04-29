package com.clientUI;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.model.NewsModel;

public class NewsMessageActivity extends BaseActivity {
	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private TextView mtvtitle;
	private TextView jobMessage;
	NewsModel noticeModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsmsg);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {


		mtvtitle = (TextView) findViewById(R.id.mtvtitle);
		jobMessage = (TextView) findViewById(R.id.jobMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("艺展详情信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.mIvBack:
				NewsMessageActivity.this.finish();
				break;


		}
	}


	@Override
	public void initData() {

		noticeModel = (NewsModel) this.getIntent().getSerializableExtra("msg");

		mtvtitle.setText(noticeModel.getNewsTitle());
		// 书名，出版社，种类
		jobMessage.setText("        " + noticeModel.getNewsMessage());


	}

	
}
