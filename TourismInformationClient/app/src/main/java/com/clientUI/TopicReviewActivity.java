package com.clientUI;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.ReplyAdapter;
import com.clientBase.adapter.TopicImgAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ReviewModel;
import com.clientBase.model.TopicModel;
import com.clientBase.util.ToastUtil;
import com.clientBase.view.GridviewForScrollView;
import com.clientBase.view.ListviewForScrollView;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.List;


public class TopicReviewActivity extends BaseActivity {
    // title
    private TextView mTvTitle, mIvStu;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private TopicModel noticeModel;

    private TextView mtvtime;

    private RelativeLayout mllbottom;

    private TextView topicInfor;
    private EditText replyMessage;
    private LinearLayout mllimg;
    private Button mbtnSend;
    private TextView notipifnor;
    private ListviewForScrollView mListReviewMessage;

    private TextView mtvSendName;
    private GridviewForScrollView gvImgShow;
    private ImageView message_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_activity_reviewinfor);
        initWidget();
        initData();

    }



    @Override
    public void initWidget() {

        message_image = (ImageView) findViewById(R.id.message_image);
        gvImgShow = (GridviewForScrollView) findViewById(R.id.gvImgShow);
        mtvSendName = (TextView) findViewById(R.id.mtvSendName);

        mListReviewMessage = (ListviewForScrollView) findViewById(R.id.mListReviewMessage);
        notipifnor = (TextView) findViewById(R.id.notipifnor);
        mbtnSend = (Button) findViewById(R.id.mbtnSend);
        mbtnSend.setOnClickListener(this);
        mllimg = (LinearLayout) findViewById(R.id.mllimg);
        replyMessage = (EditText) findViewById(R.id.replyMessage);
        topicInfor = (TextView) findViewById(R.id.topicInfor);
        mllbottom = (RelativeLayout) findViewById(R.id.mllbottom);
        mtvtime = (TextView) findViewById(R.id.mtvtime);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("信息详情");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                TopicReviewActivity.this.finish();
                break;
            case R.id.mbtnSend:
                if (TextUtils.isEmpty(replyMessage.getText().toString())) {
                    ToastUtil.ShowCentre(this, "请输入信息");
                    return;
                }
                addReviewMessage(true);
                break;

        }
    }



    @Override
    public void initData() {

        noticeModel = (TopicModel) this.getIntent().getSerializableExtra("msg");


        TopicImgAdapter topicGridAdminAdapter = new TopicImgAdapter(this, noticeModel.getTopicImg().split(","));
        gvImgShow.setAdapter(topicGridAdminAdapter);




        message_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        mtvSendName.setText(noticeModel.getTopicUserName());
        topicInfor.setText(noticeModel.getTopicInfor());
        mtvtime.setText(noticeModel.getTopicTime());


        replyMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    mllimg.setVisibility(View.GONE);
                    mbtnSend.setVisibility(View.VISIBLE);

                } else {
                    mllimg.setVisibility(View.VISIBLE);
                    mbtnSend.setVisibility(View.GONE);
                }
            }
        });
        ListNoticesMessage(false);
    }



    private void addReviewMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addReview");
        params.put("reviewUserId", MemberUserUtils.getUid(this));
        params.put("reviewUserName", MemberUserUtils.getName(this));
        params.put("reviewMessageId", noticeModel.getTopicId() + "");
        params.put("reviewContent", replyMessage.getText().toString() + "");
        params.put("reviewSendUserId",noticeModel.getTopicUserId());
        params.put("reviewMessageName",  noticeModel.getTopicInfor());
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在提交...");
    }

    private void ListNoticesMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "reviewListMessage");
        params.put("reviewMessageId", noticeModel.getTopicId() + "");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在提交...");
    }

    List<ReviewModel> list_result;
    ReplyAdapter replyAdapter;

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        switch (actionId) {

            case Consts.actionId.resultCode:
                ListNoticesMessage(false);
                break;
            case Consts.actionId.resultFlag:

                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<ReviewModel>>() {
                        }.getType());

                        notipifnor.setVisibility(View.GONE);
                    } else {
                        notipifnor.setVisibility(View.VISIBLE);
                    }
                }

                break;
        }


    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
    }



}
