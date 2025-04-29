package com.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.client.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.UserModel;
import com.clientBase.view.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * @author wangxuan 用户信息的展示
 */
public class UserActivity extends BaseActivity {

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private RelativeLayout mtvUserName;
    private RelativeLayout mrlPhone;
    private RelativeLayout mrlAddress;
    private TextView mIvStu;
    private TextView mbirthday;
    private TextView mZoom;
    private TextView userName;
    private TextView userPhone;
    private CircleImageView circleImageView;
    private LinearLayout mllUserMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                UserActivity.this.finish();
                break;
            case R.id.mrlAddress:
                Intent intent = new Intent(this, UpdatePswdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mllUserMessage:
                Intent mIvStu = new Intent(this, UserPersonActivity.class);
                mIvStu.putExtra("msg",userModel);
                startActivity(mIvStu);
                break;

        }
    }

    @Override
    public void initWidget() {

        mllUserMessage  =  (LinearLayout) findViewById(R.id.mllUserMessage);
        mllUserMessage.setOnClickListener(this);
        circleImageView = (CircleImageView) findViewById(R.id.musicImage);
        userName = (TextView) findViewById(R.id.userName);
        userPhone = (TextView) findViewById(R.id.userPhone);
        mrlAddress = (RelativeLayout) findViewById(R.id.mrlAddress);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mTvTitle.setText("我的资料");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mrlAddress.setOnClickListener(this);
        mIvStu.setOnClickListener(this);
        mIvStu.setVisibility(View.GONE);
        mIvStu.setText("更改");
    }
    UserModel userModel;
    @Override
    public void initData() {
        userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
            userName.setText(userModel.getUname());
            userPhone.setText( userModel.getUphone());
        if (!TextUtils.isEmpty(userModel.getUImg())) {
            Picasso.with(this).load(Consts.URL_IMAGE + userModel.getUImg()).placeholder(R.drawable.default_drawable_show_pictrue).into(circleImageView);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        initWidget();
        initData();
    }
}
