package com.clientUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.ReplyAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ReviewBean;
import com.clientBase.model.ShopModel;
import com.clientBase.model.UserModel;
import com.clientBase.util.ToastUtil;
import com.clientBase.view.ListviewForScrollView;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.List;


public class ReviewShareMessageActivity extends BaseActivity {

    // title
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private Button mbtnUpdate;
    private ListviewForScrollView mListReviewMessage;
    private EditText replyMessage;

    ShopModel shopBean;

    private TextView mBookTitle;
    private TextView mTvMoney;
    private TextView mtvTime;
    private TextView mtvState;
    private ImageView mivShop;

    private RelativeLayout mllbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_user_look_msg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {
        mllbottom = (RelativeLayout) findViewById(R.id.mllbottom);

        mBookTitle = (TextView) findViewById(R.id.mBookTitle);
        mTvMoney = (TextView) findViewById(R.id.mTvMoney);
        mtvTime = (TextView) findViewById(R.id.mtvTime);
        mivShop = (ImageView) findViewById(R.id.mivShop);
        

        replyMessage = (EditText) findViewById(R.id.replyMessage);
        mListReviewMessage = (ListviewForScrollView) findViewById(R.id.mListReviewMessage);

        mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("查看评论");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mbtnUpdate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                ReviewShareMessageActivity.this.finish();
                break;
            case R.id.mbtnUpdate:

                if (TextUtils.isEmpty(replyMessage.getText().toString())) {
                    ToastUtil.ShowCentre(this, "请添加评论内容");
                    return;
                }

                ReviewAction(false,replyMessage.getText().toString());
                break;
        }
    }

    private UserModel userModel;

    @Override
    public void initData() {


        shopBean = (ShopModel) this.getIntent().getSerializableExtra("msg");
        mBookTitle.setText(shopBean.getShopName());

        ListNoticesMessage(false);
    }


    private void ReviewAction(boolean isShow, String sendMessage) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addReview");
        params.put("reviewUserId", MemberUserUtils.getUid(this));
        params.put("reviewUserName", MemberUserUtils.getName(this));
        params.put("reviewMessageId",shopBean.getShopId() + "");
        params.put("reviewContent", sendMessage);
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在提交...");
    }


    private void ListNoticesMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "reviewListMessage");
        params.put("reviewMessageId",shopBean.getShopId() + "");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在提交...");
    }


    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {

            case Consts.actionId.resultCode:
                ListNoticesMessage(false);
                break;
            case Consts.actionId.resultFlag:
                List<ReviewBean> list_result = mGson.fromJson(entry.getData(), new TypeToken<List<ReviewBean>>() {
                }.getType());
                ReplyAdapter replyAdapter = new ReplyAdapter(this, list_result);
                mListReviewMessage.setAdapter(replyAdapter);
                break;

        }

    }

}
