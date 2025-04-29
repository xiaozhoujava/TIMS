package com.clientUI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.client.R;

import com.clientBase.adapter.ChoiceTypeAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.TypeModel;
import com.clientBase.model.UserModel;
import com.clientBase.util.CustomToast;
import com.clientBase.util.FaceUtil;
import com.clientBase.util.ToastUtil;
import com.clientBase.view.CircleImageView;
import com.clientBase.view.DialogListMsg;
import com.clientBase.view.DialogMsg;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;


public class UserMessageUpdateActivity extends BaseActivity {
    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private Button mSubmit;

    private EditText uphone;
    private EditText uname;

    private RadioGroup mrgChoice;
    private RadioButton mrb1 = null;
    private RadioButton mrb2 = null;
    private String choiceSex = "男";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {


        mrb1 = (RadioButton) findViewById(R.id.mrb1);
        mrb2 = (RadioButton) findViewById(R.id.mrb2);
        mrgChoice = (RadioGroup) findViewById(R.id.mrgChoice);




        uphone = (EditText) findViewById(R.id.uphone);
        uname = (EditText) findViewById(R.id.uname);

        mSubmit = (Button) findViewById(R.id.mSubmit);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("更改用户信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

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
    public void initData() {


        mrgChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mrb1.getId()) {
                    choiceSex = "男";
                } else if (checkedId == mrb2.getId()) {
                    choiceSex = "女";
                }
            }
        });


        UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");

        uphone.setText(userModel.getUphone());
        uname.setText(userModel.getUname());

    }


    private void createTopicPost(boolean isShow) {


        AjaxParams params = new AjaxParams();
        params.put("action_flag", "updateUser");
        params.put("uphone", uphone.getText().toString());
        params.put("uname", uname.getText().toString());
        params.put("uid", MemberUserUtils.getUid(this) + "");
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
    }



    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        switch (actionId) {
            case Consts.actionId.resultCode:
                ToastUtil.show(this, entry.getRepMsg());
                UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
                userModel.setUphone( uphone.getText().toString());
                userModel.setUname( uname.getText().toString());
                MemberUserUtils.putBean(this, "user_messgae", userModel);
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
