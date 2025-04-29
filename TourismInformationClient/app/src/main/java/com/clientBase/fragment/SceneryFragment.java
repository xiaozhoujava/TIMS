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
import com.clientBase.adapter.SceneryAdapter;
import com.clientBase.base.BaseFragment;
import com.clientBase.config.Consts;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.ShopModel;
import com.clientBase.util.ToastUtil;
import com.clientUI.CreatActivity;
import com.clientUI.SceneryMessageActivity;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class SceneryFragment extends BaseFragment {
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
        mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
        mivCreateMessage = (Button) rootView.findViewById(R.id.mivCreateMessage);
        mivCreateMessage.setOnClickListener(this);
        mivCreateMessage.setVisibility(View.VISIBLE);
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
                listMessage(true,"");
            }


            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //上拉加载更多...
                // 结束上拉刷新...
                materialRefreshLayout.finishRefreshLoadMore();
            }
        });

        mivCreateMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatActivity.class);
                getActivity().startActivity(intent);


//                List<HotCity> hotCities = new ArrayList<>();
//                hotCities.add(new HotCity("北京", "北京", "101010100"));
//                hotCities.add(new HotCity("上海", "上海", "101020100"));
//                hotCities.add(new HotCity("西安", "陕西", "101280101"));
//                hotCities.add(new HotCity("成都", "四川", "101280601"));
//                CityPicker.getInstance()
//                        .setFragmentManager(getActivity().getSupportFragmentManager())  //此方法必须调用
//                        .enableAnimation(true)  //启用动画效果
//                        .setLocatedCity(new LocatedCity("西安", "陕西", "101210101"))
//                        .setHotCities(hotCities)  //指定热门城市
//                        .setOnPickListener(new OnPickListener() {
//                            @Override
//                            public void onPick(int position, City data) {
//
//                                if(data!=null){
//                                    Toast.makeText(getActivity(), data.getName(), Toast.LENGTH_SHORT).show();
//                                    listMessage(true,data.getName());
//                                }
//
//                            }
//
//                            @Override
//                            public void onLocate() {
//                                //开始定位，这里模拟一下定位
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //定位完成之后更新数据
//                                        CityPicker.getInstance()
//                                                .locateComplete(new LocatedCity("西安", "陕西", "101280601"), LocateState.SUCCESS);
//                                    }
//                                }, 2000);
//                            }
//                        })
//                        .show();
            }
        });

    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void initData() {



        listMessage(true,"");
    }

    private void listMessage(boolean isShow,String city) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listUserRecommendPhone");
        params.put("city", city);
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
                        SceneryAdapter lookListAdapter = new SceneryAdapter(getActivity(), listMsg);
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
