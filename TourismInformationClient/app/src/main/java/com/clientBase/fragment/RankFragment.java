package com.clientBase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.client.R;
import com.clientBase.adapter.RankAdapter;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ShopModel;
import com.clientBase.util.ToastUtil;
import com.clientUI.SceneryMessageActivity;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class RankFragment extends BaseFragment {
    // 获取view
    private View rootView;
    // 获取控件
    private ListView mListMessage;
    private List<ShopModel> listMsg = new ArrayList<ShopModel>();
    private Button mivCreateMessage;

    // 预加载标志
    private boolean isPrepared;
    MaterialRefreshLayout materialRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news, null);
        initWidget();
        initData();
        return rootView;
    }



    @Override
    public void initWidget() {
        mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
        mivCreateMessage = (Button) rootView.findViewById(R.id.mivCreateMessage);
        mivCreateMessage.setOnClickListener(this);
        mivCreateMessage.setVisibility(View.GONE);
        mListMessage.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(getActivity(), SceneryMessageActivity.class);
                intent.putExtra("msg",listMsg.get(position));
                getActivity().startActivity(intent);

            }
        });


        materialRefreshLayout = (MaterialRefreshLayout) rootView.findViewById(R.id.refresh);

        /**
         * 设置是否上拉加载更多，默认是false，要手动改为true，要不然不会出现上拉加载
         */
        materialRefreshLayout.setLoadMore(false);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                listMessage(false);
            }


            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //上拉加载更多...
                // 结束上拉刷新...
                materialRefreshLayout.finishRefreshLoadMore();
            }
        });

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void initData() {

        mivCreateMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        listMessage(true);
    }

    private void listMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listRecommendPhone");
        params.put("userTag", MemberUserUtils.getTag(getActivity()));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }


    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                // 结束下拉刷新...
                materialRefreshLayout.finishRefresh();
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        listMsg.clear();
                        listMsg = mGson.fromJson(entry.getData(), new TypeToken<List<ShopModel>>() {
                        }.getType());
                        RankAdapter lookListAdapter = new RankAdapter(getActivity(), listMsg);
                        mListMessage.setAdapter(lookListAdapter);
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


}
