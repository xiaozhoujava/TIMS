package com.clientUI;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.PractitionersAdapter;
import com.clientBase.adapter.SelectedImageAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.SelectImageItem;
import com.clientBase.model.TopicModel;
import com.clientBase.model.TypeModel;
import com.clientBase.photo.ui.SelectImagesActivity;
import com.clientBase.photo.ui.ShowCreatePicturesActivity;
import com.clientBase.util.LoadingDialog;
import com.clientBase.util.ToastUtil;
import com.clientBase.util.UploadUtils;
import com.clientBase.view.DialogListMsg;
import com.clientBase.view.GridLayout;
import com.clientBase.view.ImageItemClickListner;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class NiMingReviewActivity extends BaseActivity  {
    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;

    private EditText topicConclusion;
    private Button mSubmit;

    private TopicModel topicModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_niming);
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
                addReview(false);
                break;

        }
    }


    @Override
    public void initWidget() {

        topicConclusion = (EditText) findViewById(R.id.topicConclusion);

        mSubmit = (Button) findViewById(R.id.mSubmit);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("匿名信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

//		listPay(false);
    }

    @Override
    public void initData() {
        topicModel = (TopicModel) this.getIntent().getSerializableExtra("msg");

    }


    private void addReview(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addReview");
        params.put("reviewMessageId", topicModel.getTopicId()+"");
        params.put("reviewContent", topicConclusion.getText().toString());
        params.put("reviewUserId", MemberUserUtils.getUid(this));
        params.put("reviewUserName", MemberUserUtils.getName(this));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在上传...");

    }


    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultCode:
                ToastUtil.ShowToast(NiMingReviewActivity.this, entry.getRepMsg());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
                break;

        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(NiMingReviewActivity.this, strMsg);

    }



}
