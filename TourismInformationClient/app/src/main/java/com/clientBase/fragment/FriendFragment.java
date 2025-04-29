package com.clientBase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.client.R;
import com.clientBase.adapter.HostoryListAdapter;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.MusicModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.UserModel;
import com.clientUI.SearchlListActivity;
import com.clientUI.UserIndexActivity;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;


public class FriendFragment extends BaseFragment {

    public static Fragment newInstance(){
        FriendFragment fragment = new FriendFragment();
        return fragment;
    }

    // 获取view
    private View rootView;
    // 获取控件
    private ListView mListMessage;
    private List<UserModel> list_result = new ArrayList<UserModel>();
    private EditText metName;
    private Button mviTongJi;
    private boolean isPrepared;// 预加载标志


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_message_shop, null);
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
        if (list_result.size() == 0) {
            initWidget();
            initData();
        }
    }

    @Override
    public void initWidget() {

        metName = (EditText) rootView.findViewById(R.id.metName);
        mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);

        mListMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RongIM.getInstance().startPrivateChat(getActivity(), list_result.get(position).getUid()+"", list_result.get(position).getUname());
            }
        });


        mviTongJi = (Button) rootView.findViewById(R.id.mviTongJi);
        mviTongJi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SearchlListActivity.class);
        startActivity(intent);

    }

    @Override
    public void initData() {
        MessageAction(false);

        metName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().length()>0){
                    listSearchPhone(false,editable.toString());
                }else{
                    MessageAction(false);
                }
            }
        });
    }




    private void MessageAction(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listUserPhone");
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }


    private void listSearchPhone(boolean isShow,String searchMsg) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listSearchPhone");
        params.put("searchMsg",searchMsg);
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }



    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result.clear();
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<UserModel>>() {
                        }.getType());
                        HostoryListAdapter listAdapter= new HostoryListAdapter(getActivity(), list_result);
                        mListMessage.setAdapter(listAdapter);
                    }
                }
                break;


        }

    }




}

