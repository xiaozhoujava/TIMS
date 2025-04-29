package com.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.GridImgAdapter;
import com.clientBase.adapter.LikeAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ImgModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.TypeModel;
import com.clientBase.observable.ImgObservable;
import com.clientBase.photo.ui.ShowPictureActivity;
import com.clientBase.util.ToastUtil;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class PhotoListActivity extends BaseActivity implements Observer{

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private GridView idphoto_gridView;
    private String state;
    private LinearLayout mllNomessage;
    private List<ImgModel> listType = new ArrayList<ImgModel>();


    private ImageView mviCreateMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        initWidget();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.mviCreateMessage:
                Intent intent = new Intent(PhotoListActivity.this, CreatImgActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initWidget() {

        mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
        idphoto_gridView = (GridView) findViewById(R.id.idphoto_gridView);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);

        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);


        mviCreateMessage = (ImageView) findViewById(R.id.mviCreateMessage);
        mviCreateMessage.setOnClickListener(this);

        if(this.getIntent().getStringExtra("msg").equals("1")){
            mviCreateMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {

        mTvTitle.setText("相册信息");
        listPhoneTypeMessage(true);

        idphoto_gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String imgInfor="";
                for(int i=0;i<listType.size();i++){
                    imgInfor = imgInfor+listType.get(i).getImgMsg()+",";
                }

                Intent intent = new Intent(PhotoListActivity.this, ShowPictureActivity.class);
                intent.putExtra("piction_path", Consts.URL_IMAGE + imgInfor.substring(0,imgInfor.length()-1).split(",")[position]);
                startActivity(intent);
            }
        });
    }


    private void listPhoneTypeMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listMessageImage");
        params.put("imgUserId", MemberUserUtils.getUid(this));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
    }

    LikeAdapter likeAdapter;

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);

                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        listType.clear();
                        listType = mGson.fromJson(entry.getData(), new TypeToken<List<ImgModel>>() {
                        }.getType());

                        String imgInfor="";
                        for(int i=0;i<listType.size();i++){
                            imgInfor = imgInfor+listType.get(i).getImgMsg()+",";
                        }

                        GridImgAdapter  gridImgAdapter = new GridImgAdapter(this,imgInfor.substring(0,imgInfor.length()-1).split(","));
                        idphoto_gridView.setAdapter(gridImgAdapter);
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
        ToastUtil.show(PhotoListActivity.this, strMsg);

    }
    @Override
    public void onResume() {
        super.onResume();
        ImgObservable.getInstance().addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImgObservable.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        listPhoneTypeMessage(false);
    }


}
