package com.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.model.CourseModel;
import com.clientBase.util.CustomToast;

public class CourseMessageActivity extends BaseActivity {
    // title
    private TextView mTvTitle, mIvStu;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private TextView mtvtitle;
    private CourseModel courseModel;

    private TextView infor1;
    private TextView infor2;
    private TextView infor3;


    private Button mbtnPay,mbtnExame;
    private TextView mtvShopPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_msg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {


        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mIvStu.setText("");
        mIvStu.setVisibility(View.GONE);
        mIvStu.setOnClickListener(this);

        infor1 = (TextView) findViewById(R.id.infor1);
        infor2 = (TextView) findViewById(R.id.infor2);
        infor3 = (TextView) findViewById(R.id.infor3);
        mbtnPay = (Button) findViewById(R.id.mbtnPay);


        mtvtitle = (TextView) findViewById(R.id.mtvtitle);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("课程详情");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mbtnPay.setOnClickListener(this);

        mtvShopPrice = (TextView) findViewById(R.id.mtvShopPrice);

        mbtnExame = (Button) findViewById(R.id.mbtnExame);
        mbtnExame.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                CourseMessageActivity.this.finish();
                break;
            case R.id.mIvStu:
                break;
            case R.id.mbtnPay:


                break;
            case R.id.mbtnExame:




                break;
        }
    }

    @Override
    public void initData() {

        courseModel = (CourseModel) this.getIntent().getSerializableExtra("msg");
        mtvtitle.setText(courseModel.getCourseName());
        infor2.setText("        "+courseModel.getCourseInfor());
        mtvShopPrice.setText(courseModel.getCourseUserName());

    }





}
