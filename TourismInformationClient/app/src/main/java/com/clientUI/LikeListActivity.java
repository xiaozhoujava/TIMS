package com.clientUI;

import android.content.Intent;
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
import com.clientBase.adapter.LikeAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.model.CityModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.TypeModel;
import com.clientBase.util.ToastUtil;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class LikeListActivity extends BaseActivity {

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private ListView mListMessage;
    private String state;
    private LinearLayout mllNomessage;
    private List<TypeModel> listType = new ArrayList<TypeModel>();


    private TextView mIvStu;


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
            case R.id.mIvStu:

                String likeId = "";
                String likeName = "";

                for (int i = 0; i < listType.size(); i++) {

                    if (listType.get(i).isChoice()) {
                        likeId = likeId + listType.get(i).getTypeId() + ",";
                        likeName = likeName + listType.get(i).getTypeName() + ",";
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("likeId", likeId.substring(0,likeId.length()-1));
                intent.putExtra("likeName",likeName.substring(0,likeName.length()-1));
                setResult(10, intent);
                finish();
                break;
        }
    }

    @Override
    public void initWidget() {

        mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
        mListMessage = (ListView) findViewById(R.id.mListMessage);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);

        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);


        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mIvStu.setOnClickListener(this);
        mIvStu.setVisibility(View.VISIBLE);
        mIvStu.setText("确定");
    }

    @Override
    public void initData() {

        mTvTitle.setText("选择爱好");
        listPhoneTypeMessage(true);

        mListMessage.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TypeModel typeModel = listType.get(position);
                if (typeModel.isChoice()) {
                    typeModel.setChoice(false);
                } else {
                    typeModel.setChoice(true);
                }
                listType.set(position, typeModel);
                likeAdapter.notifyDataSetChanged();


            }
        });
    }


    private void listPhoneTypeMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listPhoneTypeMessage");
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
                        listType = mGson.fromJson(entry.getData(), new TypeToken<List<TypeModel>>() {
                        }.getType());

                        likeAdapter = new LikeAdapter(this, listType);
                        mListMessage.setAdapter(likeAdapter);
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
        ToastUtil.show(LikeListActivity.this, strMsg);

    }

}
