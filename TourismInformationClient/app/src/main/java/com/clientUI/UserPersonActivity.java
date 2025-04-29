package com.clientUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.client.R;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.UserModel;
import com.clientBase.util.CustomToast;
import com.clientBase.view.CircleImageView;
import com.squareup.picasso.Picasso;

import net.tsz.afinal.http.AjaxParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wangxuan 用户信息的展示
 */
public class UserPersonActivity extends BaseActivity {

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;


    private CircleImageView mivShop;
    private LinearLayout mllUserMessage;
    private TextView mIvStu;

    private TextView userLike;
    private TextView userMoney;
    private TextView userStudy;
    private TextView userCity;
    private TextView userName;
    private ImageView yulequan_attch_index;

    private ImageView mivZan;
    private TextView zanNumber;
    private RelativeLayout mrePhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

    }


    @Override
    protected void onResume() {
        super.onResume();
        initWidget();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                UserPersonActivity.this.finish();
                break;
            case R.id.mivZan:
                addPrise(false);
                break;
            case R.id.mIvStu:
                Intent mIvStu = new Intent(this, UserMessageUpdateActivity.class);
                startActivity(mIvStu);
                break;
            case R.id.mrePhoto:
                Intent intent = new Intent(this, PhotoListActivity.class);
                intent.putExtra("msg","2");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initWidget() {

        mrePhoto = (RelativeLayout) findViewById(R.id.mrePhoto);
        mrePhoto.setOnClickListener(this);



        mivZan = (ImageView) findViewById(R.id.mivZan);
        zanNumber = (TextView) findViewById(R.id.zanNumber);
        mivZan.setOnClickListener(this);

        yulequan_attch_index = (ImageView) findViewById(R.id.yulequan_attch_index);
        userLike = (TextView) findViewById(R.id.userLike);
        userMoney = (TextView) findViewById(R.id.userMoney);
        userStudy = (TextView) findViewById(R.id.userStudy);
        userCity = (TextView) findViewById(R.id.userCity);
        userName = (TextView) findViewById(R.id.userName);
        mivShop = (CircleImageView) findViewById(R.id.mivShop);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("我的资料");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);


        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mIvStu.setOnClickListener(this);
        mIvStu.setVisibility(View.VISIBLE);
        mIvStu.setText("更改");


    }
    UserModel     userModel;
    @Override
    public void initData() {
        userModel= (UserModel) MemberUserUtils.getBean(this, "user_messgae");
        userName.setText(userModel.getURealName()+"  "+userModel.getUSex()+"  "+userModel.getUphone()+"  "+userModel.getUAge()+"岁");
        userLike.setText("爱好："+userModel.getULikeName());
        userMoney.setText("薪资："+userModel.getUMoney());
        userStudy.setText("学历："+userModel.getUStudy());
        userCity.setText("城市："+userModel.getUCity());
        zanNumber.setText(userModel.getuPrise()+"");


        if (!TextUtils.isEmpty(userModel.getUImg())) {
            Picasso.with(this).load(Consts.URL_IMAGE + userModel.getUImg()).placeholder(R.drawable.default_drawable_show_pictrue).into(mivShop);
        }


        bitmap= returnBitMap(Consts.URL_IMAGE + userModel.getUImg());
        mHandler.sendEmptyMessageDelayed(1, 1000);





    }


    public static Bitmap blurBitmap(Context context, Bitmap bitmap) {
        //用需要创建高斯模糊bitmap创建一个空的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 初始化Renderscript，该类提供了RenderScript context，创建其他RS类之前必须先创建这个类，其控制RenderScript的初始化，资源管理及释放
        RenderScript rs = RenderScript.create(context);
        // 创建高斯模糊对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 创建Allocations，此类是将数据传递给RenderScript内核的主要方 法，并制定一个后备类型存储给定类型
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //设定模糊度(注：Radius最大只能设置25.f)
        blurScript.setRadius(15.f);
        // Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        // Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        // recycle the original bitmap
        // bitmap.recycle();
        // After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;
    }

    Bitmap bitmap;
    public Bitmap returnBitMap(final String url){

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    yulequan_attch_index.setImageBitmap(blurBitmap(UserPersonActivity.this,bitmap));
                    break;
            }
        }
    };
    private void addPrise(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addPrise");
        params.put("uid",userModel.getUid()+"");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        CustomToast.showToast(this,"点赞成功");
        zanNumber.setText(entry.getRepMsg()+"");
    }

}
