package com.clientBase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.client.R;
import com.clientBase.adapter.TopicAdapter;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.listener.TopicListner;
import com.clientBase.model.ImgModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.TopicModel;
import com.clientBase.observable.TopicObservable;
import com.clientBase.refresh.JellyRefreshLayout;
import com.clientBase.refresh.PullToRefreshLayout;
import com.clientBase.util.CustomToast;
import com.clientBase.util.ToastUtil;
import com.clientUI.CreatTopicActivity;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class TopicFragment extends BaseFragment implements  Observer,TopicListner {
    // 获取view
    private View rootView;
    // 获取控件
    private ListView mListMessage;
    View convertView;
    private List<TopicModel> listMsg = new ArrayList<TopicModel>();
    private ImageView mivCreateMessage;

    // 预加载标志
    private boolean isPrepared;
    private JellyRefreshLayout mJellyLayout;
    private EditText metName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_refresh_main, null);
        isPrepared = true;
        setlazyLoad();
        return rootView;
    }


    /**
     * 加载数据的方法，只要保证isPrepared和isVisible都为true的时候才往下执行开始加载数据
     */
    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();

        if (!isPrepared || !isVisible) {
            return;
        }
        if (listMsg.size() == 0) {
            initWidget();
            initData();
        }
    }

    @Override
    public void initWidget() {
        metName = (EditText) rootView.findViewById(R.id.metName);
        mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
        mivCreateMessage = (ImageView) rootView.findViewById(R.id.mivCreateMessage);
        mivCreateMessage.setOnClickListener(this);
        mivCreateMessage.setVisibility(View.VISIBLE);
        mListMessage.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


            }
        });

        mJellyLayout = (JellyRefreshLayout) rootView.findViewById(R.id.jelly_refresh);
        mJellyLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mJellyLayout.setRefreshing(false);
                        listMessage(false);

                    }
                }, 1000);
            }
        });
        View loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.view_loading, null);
        mJellyLayout.setLoadingView(loadingView);

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void initData() {


        metName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


//
//                if (editable.toString().length() > 0) {
//                    listSearchMessage(false,editable.toString());
//                } else {
//                    listMessage(false);
//                }

            }
        });


        mivCreateMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatTopicActivity.class);
                getActivity().startActivity(intent);
            }
        });
        listMessage(true);
    }




    private void listMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listMessageTopic");
        params.put("userId", MemberUserUtils.getUid(getActivity()));
        params.put("topicFlag", "1");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
    }



    private void addPraise(boolean isShow, TopicModel imgModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addPraise");
        params.put("praiseMessageId", imgModel.getTopicId() + "");
        params.put("praiseUserId", MemberUserUtils.getUid(getActivity()));

        params.put("praiseUserName", MemberUserUtils.getName(getActivity()));
        params.put("praiseMessageName", imgModel.getTopicInfor() + "");
        params.put("praiseSendUserId", imgModel.getTopicUserId() + "");

        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultPraiseOk, isShow, "正在加载...");
    }

    private void deletePraise(boolean isShow, TopicModel imgModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "deletePraise");
        params.put("praiseMessageId", imgModel.getTopicId() + "");
        params.put("praiseUserId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultPraiseNo, isShow, "正在加载...");
    }

    private void addCollectMessage(boolean isShow, TopicModel imgModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addCollectMessage");
        params.put("collectMessageId", imgModel.getTopicId() + "");
        params.put("collectUserId", MemberUserUtils.getUid(getActivity()));

        params.put("collectUserName", MemberUserUtils.getName(getActivity()));
        params.put("collectMessageName", imgModel.getTopicInfor() + "");
        params.put("collectSendUserId", imgModel.getTopicUserId() + "");


        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCollectOk, isShow, "正在加载...");
    }


    private void deleteCollectMessage(boolean isShow, TopicModel imgModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "deleteCollectMessage");
        params.put("collectMessageId", imgModel.getTopicId() + "");
        params.put("collectUserId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCollectNo, isShow, "正在加载...");
    }



    TopicAdapter planAdapter;

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultPraiseOk:

                TopicModel praiseModelOk = listMsg.get(posIndex);
                praiseModelOk.setPraiseNumber(praiseModelOk.getPraiseNumber()+1);
                praiseModelOk.setPraiseState(true);
                listMsg.set(posIndex, praiseModelOk);
                planAdapter.notifyDataSetChanged();
                CustomToast.showToast(getActivity(),entry.getRepMsg());
                break;

            case Consts.actionId.resultPraiseNo:

                TopicModel praiseModelNo = listMsg.get(posIndex);
                praiseModelNo.setPraiseNumber(praiseModelNo.getPraiseNumber()-1);
                praiseModelNo.setPraiseState(false);
                listMsg.set(posIndex, praiseModelNo);
                planAdapter.notifyDataSetChanged();
                CustomToast.showToast(getActivity(),entry.getRepMsg());
                break;
            case Consts.actionId.resultCollectOk:

                TopicModel imgModelOk = listMsg.get(posIndex);
                imgModelOk.setCollectState(true);
                imgModelOk.setPraiseNumber(imgModelOk.getCollectNumber()+1);
                listMsg.set(posIndex, imgModelOk);
                planAdapter.notifyDataSetChanged();
                CustomToast.showToast(getActivity(),entry.getRepMsg());
                break;

            case Consts.actionId.resultCollectNo:

                TopicModel imgModelNo = listMsg.get(posIndex);
                imgModelNo.setCollectState(false);
                imgModelNo.setPraiseNumber(imgModelNo.getCollectNumber()-1);
                listMsg.set(posIndex, imgModelNo);
                planAdapter.notifyDataSetChanged();
                CustomToast.showToast(getActivity(),entry.getRepMsg());
                break;

            case Consts.actionId.resultCode:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        listMsg.clear();
                        listMsg = mGson.fromJson(entry.getData(), new TypeToken<List<TopicModel>>() {
                        }.getType());
                        planAdapter = new TopicAdapter(getActivity(), listMsg,this);
                        mListMessage.setAdapter(planAdapter);
                    }
                }
                break;



        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(getActivity(), strMsg);

    }




    @Override
    public void onResume() {
        super.onResume();

        TopicObservable.getInstance().addObserver(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TopicObservable.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable arg0, Object object) {
        listMessage(false);
    }

    private int posIndex;
    @Override
    public void setPraise(int pos, TopicModel topicModel) {
        posIndex = pos;

        if (topicModel.isCollectState()) {
            deletePraise(false, topicModel);
        } else {
            addPraise(false, topicModel);
        }

    }

    @Override
    public void setCollect(int pos, TopicModel topicModel) {
        posIndex = pos;
        if (topicModel.isCollectState()) {
            deleteCollectMessage(false, topicModel);
        } else {
            addCollectMessage(false, topicModel);
        }

    }
}
