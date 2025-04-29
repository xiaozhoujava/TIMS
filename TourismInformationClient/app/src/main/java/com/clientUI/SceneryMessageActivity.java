package com.clientUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ShopModel;
import com.clientBase.photo.ui.ShowPictureActivity;
import com.squareup.picasso.Picasso;

import net.tsz.afinal.http.AjaxParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;


public class SceneryMessageActivity extends BaseActivity {
    // title
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private TextView mtvtitle;
    private TextView mtvcontent;
    ShopModel noticeModel;

    private TextView Name;
    private TextView phone;

    private Button mbtnPay, mbtnChat;
    private TextView mtvShopPrice;

    private int choiceTime = 1;
    private TextView mIvStu;


    private ImageView mivShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopmsg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {
        mivShop = (ImageView) findViewById(R.id.mivShop);


        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mIvStu.setVisibility(View.VISIBLE);
        mIvStu.setOnClickListener(this);
        mIvStu.setText("评论");

        mbtnChat = (Button) findViewById(R.id.mbtnChat);
        mbtnChat.setOnClickListener(this);

        mbtnPay = (Button) findViewById(R.id.mbtnPay);
        mbtnPay.setOnClickListener(this);
        mtvShopPrice = (TextView) findViewById(R.id.mtvShopPrice);

        Name = (TextView) findViewById(R.id.Name);
        phone = (TextView) findViewById(R.id.phone);

        mtvtitle = (TextView) findViewById(R.id.mtvtitle);
        mtvcontent = (TextView) findViewById(R.id.mtvcontent);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("景点详情");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mbtnChat:
                RongIM.getInstance().startPrivateChat(this, noticeModel.getShopUserId()+"", noticeModel.getShopUserName());
                break;

            case R.id.mIvStu:
                Intent mIvStu = new Intent(SceneryMessageActivity.this, ReviewShareMessageActivity.class);
                mIvStu.putExtra("msg",noticeModel);
                startActivity(mIvStu);
                break;

            case R.id.mIvBack:
                SceneryMessageActivity.this.finish();
                break;
            case R.id.mbtnPay:
                list_result_choice.add(noticeModel);

                Intent mbtnPay = new Intent(SceneryMessageActivity.this, PayShopMessageActivity.class);
                mbtnPay.putExtra("msg", (Serializable) list_result_choice);
                mbtnPay.putExtra("payMoney", noticeModel.getShopMoney() + "");
                startActivity(mbtnPay);
                break;


            case R.id.guide_image:

                Intent intent = new Intent(this, ShowPictureActivity.class);
                intent.putExtra("piction_path", Consts.URL_IMAGE + noticeModel.getShopImg());
                startActivity(intent);

                break;

        }
    }
    private List<ShopModel> list_result_choice = new ArrayList<ShopModel>();
    @Override
    public void initData() {

        noticeModel = (ShopModel) this.getIntent().getSerializableExtra("msg");
        mtvtitle.setText(noticeModel.getShopName());
        Name.setText("地址："+noticeModel.getShopAddress());
        phone.setText("类型："+noticeModel.getShopTypeName());
        mtvcontent.setText("        " + noticeModel.getShopMessage());
        mtvShopPrice.setText(noticeModel.getShopMoney() + "元");
        if (!TextUtils.isEmpty(noticeModel.getShopImg())) {
            Picasso.with(this).load(Consts.URL_IMAGE + noticeModel.getShopImg()).placeholder(R.drawable.default_drawable_show_pictrue)
                    .into(mivShop);
        }
        RegisterAction(false);

    }

    private void RegisterAction(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "updateTag");
        params.put("utag", noticeModel.getShopTypeName());
        params.put("uid", MemberUserUtils.getUid(this));
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        Log.d("pony_log",entry.getRepMsg());
    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        Log.d("pony_log",strMsg);

    }

}
