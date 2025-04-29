package com.clientUI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.client.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;

import com.clientBase.model.ResponseEntry;
import com.clientBase.model.UserModel;
import com.clientBase.util.CustomToast;
import com.clientBase.util.LoadingDialog;
import com.clientBase.util.ToastUtil;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import net.tsz.afinal.http.AjaxParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * 登录页面
 *
 * @author WangXuan
 */

public class LoginActivity extends BaseActivity {
    // title
    private TextView mTvTitle;
    // 登录用户名称
    private EditText mLoginNumber;
    // 登录密码
    private EditText mLoginPswd;
    // 登录按钮
    private Button mLogin;
    private Button mEnterpriseQuery;
    private LinearLayout mllTop;
    private UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//一个权限没有，就一次申请所有所需的权限，这样可以在打开应用的时候获得所有权限


        initWidget();
    }

    /**
     * 控件初始化
     */
    @Override
    public void initWidget() {

        mdialog = new LoadingDialog(this, "正在登录");
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("登录");
        mLoginNumber = (EditText) findViewById(R.id.mLoginNumber);
        mLoginPswd = (EditText) findViewById(R.id.mLoginPswd);
        mLogin = (Button) findViewById(R.id.mLogin);
        mEnterpriseQuery = (Button) findViewById(R.id.mEnterpriseQuery);
        // mLoginNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        // 事件的监听
        mLogin.setOnClickListener(this);
        mEnterpriseQuery.setOnClickListener(this);
        // 给输入框设置默认的测试数据
        mLoginNumber.setSelection(mLoginNumber.getText().length());


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mLogin:
                if (TextUtils.isEmpty(mLoginNumber.getText().toString())) {

                    CustomToast.showToast(this, "请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(mLoginPswd.getText().toString())) {
                    CustomToast.showToast(this, "请输入登录密码");
                    return;
                }
                LoginUserPost(true);
                break;

            case R.id.mEnterpriseQuery:
                Intent mEnterpriseQuery = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mEnterpriseQuery);
            default:
                break;
        }
    }

    @Override
    public void initData() {



    }

    /**
     * 用户的登录
     *
     * @param isShow
     */
    private void LoginUserPost(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "login");
        params.put("uphone", mLoginNumber.getText().toString());
        params.put("pswd", mLoginPswd.getText().toString());
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在登录...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:

                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
                    userModel = mGson.fromJson(entry.getData(), UserModel.class);
                    MemberUserUtils.setTag(LoginActivity.this, userModel.getUserTag() + "");
                    MemberUserUtils.setUid(LoginActivity.this, userModel.getUid() + "");
                    MemberUserUtils.setName(LoginActivity.this, userModel.getUname());
                    MemberUserUtils.putBean(LoginActivity.this, "user_messgae", userModel);

                    connect(userModel.getUtoken());

                }
                break;

        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(LoginActivity.this, strMsg);

    }


    /**
     * 建立与融云服务器
     *
     * @param token
     */
    private void connect(String token) {


        Log.e("Pony_log", "--onTokenIncorrect");
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                //消息数据库打开，可以进入到主页面
            }

            @Override
            public void onSuccess(String s) {
                //连接成功
                    Intent intent = new Intent(LoginActivity.this, FrameworkActivity.class);
                    startActivity(intent);
                    finish();
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                if (errorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)) {
                    //从 APP 服务获取新 token，并重连
                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                }
            }
        });


//        Log.e("Pony_log", "--onTokenIncorrect");
//        RongIM.connect(token, new RongIMClient.ConnectCallback() {
//
//            @Override
//            public void onTokenIncorrect() {
//
//                Log.i("Pony_log", "--onTokenIncorrect");
//            }
//
//            @Override
//            public void onSuccess(String userid) {
//                mdialog.dismiss();
//                Log.i("Pony_log", "--onSuccess" + userid);
//
//
//                if (TextUtils.isEmpty(userModel.getUAge())) {
//                    Intent intent = new Intent(LoginActivity.this, CreateMessageUpdateActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {

//                }
//
//
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                mdialog.dismiss();
//                Log.i("Pony_log", "--onError" + errorCode);
//            }
//        });
    }


}