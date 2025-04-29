package com.clientUI;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.PractitionersAdapter;
import com.clientBase.adapter.SelectedImageAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.SelectImageItem;
import com.clientBase.model.TypeModel;
import com.clientBase.photo.ui.SelectImagesActivity;
import com.clientBase.photo.ui.ShowCreatePicturesActivity;
import com.clientBase.util.LoadingDialog;
import com.clientBase.util.ToastUtil;
import com.clientBase.util.UploadUtils;
import com.clientBase.view.DialogListMsg;
import com.clientBase.view.GridLayout;
import com.clientBase.view.ImageItemClickListner;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CreatNiMingActivity extends BaseActivity implements ImageItemClickListner {
    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;

    GridLayout grid_instructor;
    HorizontalScrollView horizontalscrollview;
    private ArrayList<SelectImageItem> imageItems = new ArrayList<SelectImageItem>();
    private SelectedImageAdapter selectedImageAdapter;
    private Button mSubmit;
    private File imgPath;
    public LoadingDialog mdialog;
    private List<String> mListImage = new ArrayList<String>();
    private int imgPosFlag = 0;
    private String picPath = null;
    // 图片上传表示位
    private int imageFlagNumber = 0;

    private EditText topicAdvantages;
    private EditText topicDisadvantages;
    private EditText topicConclusion;


    private List<TypeModel> mlistData = new ArrayList<TypeModel>();
    private DialogListMsg dialogListMsg;
    private PractitionersAdapter listaAdapter;
    private TextView mtvStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_niming);
        initWidget();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.mtvStart:


                break;
            case R.id.mSubmit:

                Log.e("pony_log", imageFlagNumber + "");
                imageFlagNumber = 0;
                if (mListImage.size() == 0) {
                    createTopicPost(true);
                } else {
                    mdialog.show();
                    UploadFileTask uploadFileTaskSend = new UploadFileTask(this);
                    uploadFileTaskSend.execute(mListImage.get(imageFlagNumber));
                }
                break;

        }
    }


    @Override
    public void initWidget() {

        dialogListMsg = new DialogListMsg(this);

        listaAdapter = new PractitionersAdapter(this);
        mtvStart = (TextView) findViewById(R.id.mtvStart);
        mtvStart.setOnClickListener(this);


        topicAdvantages = (EditText) findViewById(R.id.topicAdvantages);
        topicDisadvantages = (EditText) findViewById(R.id.topicDisadvantages);
        topicConclusion = (EditText) findViewById(R.id.topicConclusion);

        mdialog = new LoadingDialog(this, "上传图片...");
        mSubmit = (Button) findViewById(R.id.mSubmit);
        grid_instructor = (GridLayout) findViewById(R.id.grid_instructor);
        horizontalscrollview = (HorizontalScrollView) findViewById(R.id.horizontalscrollview);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("添加匿名信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

//		listPay(false);
    }

    @Override
    public void initData() {


        listHuaTiType(false);
        dialogListMsg.show_listview().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                dialogListMsg.Close();
                posIndex = pos;
                mtvStart.setText(mlistData.get(pos).getTypeName());
            }
        });

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        initSelectedGridView();

    }

    private int posIndex = 0;

    private void initSelectedGridView() {
        selectedImageAdapter = new SelectedImageAdapter(this, imageItems);
        SelectImageItem addItem = new SelectImageItem();
        addItem.setSid(100);// 添加的图标
        imageItems.add(addItem);
        selectedImageAdapter.notifyDataSetChanged();
        grid_instructor.setGridAdapter(selectedImageAdapter, CreatNiMingActivity.this);

    }


    @Override
    public void imageItemClick(int position, SelectImageItem imageItem) {
        if (imageItem != null) {
            int sid = imageItem.getSid();
            if (sid == 100) {

                if (CreatNiMingActivity.this.getIntent().getIntExtra("photo_message", 0) == 1) {
                    goCameraActivity();
                } else {
                    // 添加图片
                    Intent intentImages = new Intent(CreatNiMingActivity.this, SelectImagesActivity.class);
                    intentImages.putExtra("image_count", imageItems.size());
                    intentImages.putExtra("max_count", "1");
                    startActivityForResult(intentImages, 1);
                }
            } else {
                Intent intentPicture = new Intent(CreatNiMingActivity.this, ShowCreatePicturesActivity.class);
                intentPicture.putExtra("position", position);
                intentPicture.putExtra("piction_path", imageItems);
                startActivityForResult(intentPicture, 3);
            }
        }
    }

    private void addNewItemWithPre(String cameraPath) {

        int count = selectedImageAdapter.getCount();
        if (count > 0) {
            int selectCount = count - 1;
            SelectImageItem item = new SelectImageItem();
            item.setUrl(cameraPath);
            imageItems.add(selectCount, item);
        }
    }

    private void scrollgridView() {
        final int count = selectedImageAdapter.getCount();
        if (count > 1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    horizontalscrollview.smoothScrollTo(2000, 0);
                }
            }, 500);
        }
    }

    String tagInfor;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            if (data != null) {
                tagInfor = data.getStringExtra("msg");
                mtvStart.setText(tagInfor);
            }

        } else {
            if (requestCode == 5) {
                Log.d("camera", "" + (data == null));
                if (mCameraFile == null || !mCameraFile.exists()) {
                    return;
                }
                imgPath = mCameraFile;
                mListImage.add(mCameraFile.getAbsolutePath());
                addNewItemWithPre(mCameraFile.getAbsolutePath());
            }

            if (requestCode == 1) {
                if (data != null) {

                    if (0 == data.getIntExtra("action", -1)) {
                        String cameraPath = data.getStringExtra("camera_path");
                        addNewItemWithPre(cameraPath);

                    } else if (1 == data.getIntExtra("action", -1)) {
                        ArrayList<CharSequence> charSequences = data.getCharSequenceArrayListExtra("images");
                        for (CharSequence ss : charSequences) {
                            Log.e("pony_log", "image:" + ss.toString());
                            picPath = ss.toString();
                            mListImage.add(ss.toString());
                            addNewItemWithPre(ss.toString());
                        }
                    }
                    selectedImageAdapter.notifyDataSetChanged();
                    scrollgridView();
                }
            }

            if (requestCode == 3) {
                if (data != null) {
                    @SuppressWarnings( "unchecked" )
                    ArrayList<SelectImageItem> imgUrls = (ArrayList<SelectImageItem>) data.getSerializableExtra("data");
                    if (imgUrls != null && imgUrls.size() > 0) {
                        imageItems.clear();
                        imageItems.addAll(imgUrls);
                        selectedImageAdapter.notifyDataSetChanged();
                        scrollgridView();
                    }
                }
            }
            grid_instructor.setGridAdapter(selectedImageAdapter, CreatNiMingActivity.this);
        }

    }

    private Uri mOutPutFileUri;
    private File mCameraFile;

    // 去拍照
    private void goCameraActivity() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // dailyyoga_camera文件夹
        File parentFile = new File(Environment.getExternalStorageDirectory().toString() + "/dailyyoga_camera");
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        mCameraFile = new File(parentFile + "/" + System.currentTimeMillis() + ".jpg");
        mOutPutFileUri = Uri.fromFile(mCameraFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        startActivityForResult(intent, 5);
    }

    private void createTopicPost(boolean isShow) {

        // 对图片路径的处理
        String imagePath = "";
        for (int i = 0; i < mListImage.size(); i++) {
            String[] arrPath = mListImage.get(i).split("\\/");
            imagePath = arrPath[arrPath.length - 1] + "," + imagePath;
        }

        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addNiMing");

        params.put("topicTypeInfor", "");
        params.put("topicInfor", topicConclusion.getText().toString());
        params.put("topicImg", "");
        params.put("topicUserId", MemberUserUtils.getUid(this));
        params.put("topicUserName", MemberUserUtils.getName(this));
        params.put("topicFlag", "1");
        params.put("topicState", "2");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在上传...");

    }

    private void listHuaTiType(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listHuaTiType");
        httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
    }


    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultCode:
//				TopicObservable.getInstance().notifyStepChange("ok");
                ToastUtil.ShowToast(CreatNiMingActivity.this, entry.getRepMsg());
                new Handler().postDelayed(new Runnable() {
                    //
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
                break;
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);


                }

                break;
        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        mdialog.dismiss();
        ToastUtil.show(CreatNiMingActivity.this, strMsg);

    }

    public class UploadFileTask extends AsyncTask<String, Void, String> {

        /**
         */
        private Activity context = null;

        public UploadFileTask(Activity ctx) {
            this.context = ctx;

        }

        @Override
        protected void onPostExecute(String result) {
            imageFlagNumber++;
            if (UploadUtils.SUCCESS.equalsIgnoreCase(result)) {
                if (imageFlagNumber < mListImage.size()) {
                    UploadFileTask uploadFileTask = new UploadFileTask(CreatNiMingActivity.this);
                    uploadFileTask.execute(mListImage.get(imageFlagNumber));
                } else if (imageFlagNumber == mListImage.size()) {

                    createTopicPost(true);
                    // 返回HTML页面的内容

                }
            } else {
                ToastUtil.show(CreatNiMingActivity.this, " 图片上传失败");
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... params) {
            File file = new File(params[0]);
            return UploadUtils.uploadFile(file, Consts.URL + Consts.APP.MessageAction + "?action_flag=addTopic");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}
