package com.clientBase.fragment;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.client.R;
import com.clientBase.adapter.NewsListAdapter;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.model.NewsModel;
import com.clientBase.model.ResponseEntry;
import com.clientBase.util.ToastUtil;
import com.clientUI.NewsMessageActivity;
import com.google.gson.reflect.TypeToken;


public class NewsFragment extends BaseFragment {
	// 获取view
	private View rootView;
	
	
	// 获取控件
	private ListView mListMessage;
	View convertView;
	
	private List<NewsModel> list_result = new ArrayList<NewsModel>();
	NewsListAdapter noticeAdapter;
	// 预加载标志
	private boolean isPrepared;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news, null);
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

		mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
	}

	@Override
	public void onClick(View v) {
	
	}

	@Override
	public void initData() {
		CourseAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				Intent intent = new Intent(getActivity(), NewsMessageActivity.class);
				intent.putExtra("msg", list_result.get(pos));
				startActivity(intent);
			}
		});
	}

	private void CourseAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listPhoneNewsMessage");
		httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
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
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<NewsModel>>() {
					}.getType());
					noticeAdapter = new NewsListAdapter(getActivity(), list_result);
					mListMessage.setAdapter(noticeAdapter);
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
		ToastUtil.show(getActivity(), strMsg);

	}



}
