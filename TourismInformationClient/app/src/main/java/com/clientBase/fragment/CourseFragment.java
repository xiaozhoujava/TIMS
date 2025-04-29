package com.clientBase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.client.R;
import com.clientBase.adapter.ImgLeftAdapter;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.listener.ChoiceCourseListner;
import com.clientBase.model.CourseModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.util.ToastUtil;
import com.clientUI.CourseMessageActivity;
import com.clientUI.LoginActivity;
import com.google.gson.reflect.TypeToken;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class CourseFragment extends BaseFragment implements Observer, ChoiceCourseListner {
    // 获取view
    private View rootView;

    private List<CourseModel> list_result = new ArrayList<CourseModel>();
    private ListView mListMessage;
    private EditText metName;
    // 预加载标志
    private boolean isPrepared;
    private TextView searchInfor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_message_course, null);
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

        searchInfor = (TextView)rootView.findViewById(R.id.searchInfor);
        searchInfor.setOnClickListener(this);
        metName = (EditText) rootView.findViewById(R.id.metName);
        mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
//        if (MemberUserUtils.getLoginFlag(getActivity()).equals("2")) {
//            listMessageTeacherLook(false);
//        } else {
//            listCoursePhone(false);
//        }

        listCoursePhone(false);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData() {





        mListMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

                    Intent intent = new Intent(getActivity(), CourseMessageActivity.class);
                    intent.putExtra("msg", list_result.get(pos));
                    startActivity(intent);
            }
        });


    }



    private void listCoursePhone(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listCoursePhone");
        params.put("choiceStuId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }



    private void addChoice(boolean isShow, CourseModel videoTopicModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addChoice");
        params.put("choiceCourseId", videoTopicModel.getCourseId()+"");
        params.put("choiceStuId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
    }


    private void deleteChoice(boolean isShow, CourseModel videoTopicModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "deleteChoice");
        params.put("choiceCourseId", videoTopicModel.getCourseId()+"");
        params.put("choiceStuId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
    }


    ImgLeftAdapter noticeAdapter;

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result.clear();
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<CourseModel>>() {
                        }.getType());
                        noticeAdapter = new ImgLeftAdapter(getActivity(), list_result, this);
                        mListMessage.setAdapter(noticeAdapter);
                        mListMessage.setVisibility(View.VISIBLE);
                    } else {
                        mListMessage.setVisibility(View.GONE);
                    }
                }
                break;
            case Consts.actionId.resultCode:
                CourseModel videoTopicModel = list_result.get(posIndex);
                if (videoTopicModel.isChoiceState()) {
                    videoTopicModel.setChoiceState(false);
                } else {
                    videoTopicModel.setChoiceState(true);
                }
                list_result.set(posIndex, videoTopicModel);
                noticeAdapter.notifyDataSetChanged();

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object data) {
        listCoursePhone(false);
    }

    private int posIndex;

    @Override
    public void setChoiceCourse(int pos, CourseModel videoTopicModel) {
        posIndex = pos;

        if (videoTopicModel.isChoiceState()) {
            deleteChoice(false, videoTopicModel);
        } else {
            addChoice(false, videoTopicModel);
        }


    }
}
