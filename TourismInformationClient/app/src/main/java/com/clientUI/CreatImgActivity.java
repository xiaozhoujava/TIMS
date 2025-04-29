package com.clientUI;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.adapter.SelectedImageAdapter;
import com.clientBase.base.BaseActivity;
import com.clientBase.config.Consts;
import com.clientBase.db.MemberUserUtils;
import com.clientBase.model.ResponseEntry;
import com.clientBase.model.SelectImageItem;
import com.clientBase.observable.ImgObservable;
import com.clientBase.photo.ui.SelectImagesActivity;
import com.clientBase.photo.ui.ShowCreatePicturesActivity;
import com.clientBase.util.LoadingDialog;
import com.clientBase.util.ToastUtil;
import com.clientBase.util.UploadUtils;
import com.clientBase.view.GridLayout;
import com.clientBase.view.ImageItemClickListner;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreatImgActivity extends BaseActivity implements ImageItemClickListner {
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
	private EditText imgMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_img);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;

			case R.id.mSubmit:

//			if (TextUtils.isEmpty(diaryName.getText().toString())) {
//				ToastUtil.ShowCentre(this, "请输入标题");
//				return;
//			}
//
//
//			if (TextUtils.isEmpty(diaryMessage.getText().toString())) {
//				ToastUtil.ShowCentre(this, "请输入内容");
//				return;
//			}
//

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

		imgMessage = (EditText) findViewById(R.id.imgMessage);
		mdialog = new LoadingDialog(this, "上传图片...");
		mSubmit = (Button) findViewById(R.id.mSubmit);
		grid_instructor = (GridLayout) findViewById(R.id.grid_instructor);
		horizontalscrollview = (HorizontalScrollView) findViewById(R.id.horizontalscrollview);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("添加图片信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mSubmit.setOnClickListener(this);

	}

	@Override
	public void initData() {
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
		initSelectedGridView();
		imgMessage.setText("");
	}

	private void initSelectedGridView() {
		selectedImageAdapter = new SelectedImageAdapter(this, imageItems);
		SelectImageItem addItem = new SelectImageItem();
		addItem.setSid(100);// 添加的图标
		imageItems.add(addItem);
		selectedImageAdapter.notifyDataSetChanged();
		grid_instructor.setGridAdapter(selectedImageAdapter, CreatImgActivity.this);

	}


	@Override
	public void imageItemClick(int position, SelectImageItem imageItem) {
		if (imageItem != null) {
			int sid = imageItem.getSid();
			if (sid == 100) {

				if (CreatImgActivity.this.getIntent().getIntExtra("photo_message", 0) == 1) {
					goCameraActivity();
				} else {
					// 添加图片
					Intent intentImages = new Intent(CreatImgActivity.this, SelectImagesActivity.class);
					intentImages.putExtra("image_count", imageItems.size());
					intentImages.putExtra("max_count", "1");
					startActivityForResult(intentImages, 1);
				}
			} else {
				Intent intentPicture = new Intent(CreatImgActivity.this, ShowCreatePicturesActivity.class);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);


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
				@SuppressWarnings("unchecked")
				ArrayList<SelectImageItem> imgUrls = (ArrayList<SelectImageItem>) data.getSerializableExtra("data");
				if (imgUrls != null && imgUrls.size() > 0) {
					imageItems.clear();
					imageItems.addAll(imgUrls);
					selectedImageAdapter.notifyDataSetChanged();
					scrollgridView();
				}
			}
		}
		grid_instructor.setGridAdapter(selectedImageAdapter, CreatImgActivity.this);
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
		params.put("action_flag", "addImage");

		if (mListImage.size() != 0) {
			params.put("imgMsg", imagePath.substring(0, imagePath.length() - 1));
		}
		params.put("imgUserId", MemberUserUtils.getUid(this));
		params.put("imgUserName", MemberUserUtils.getName(this));
		httpPost(Consts.URL + Consts.APP.MessageAction, params, Consts.actionId.resultCode, isShow, "正在上传...");

	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultCode:
				ImgObservable.getInstance().notifyStepChange("ok");
				ToastUtil.ShowToast(CreatImgActivity.this, entry.getRepMsg());
				new Handler().postDelayed(new Runnable() {
					//
					@Override
					public void run() {
						finish();
					}
				}, 2000);
				break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		mdialog.dismiss();
		ToastUtil.show(CreatImgActivity.this, strMsg);

	}

	public class UploadFileTask extends AsyncTask<String, Void, String> {

		/**
		 * 可变长的输入参数，与AsyncTask.exucute()对应
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
					UploadFileTask uploadFileTask = new UploadFileTask(CreatImgActivity.this);
					uploadFileTask.execute(mListImage.get(imageFlagNumber));
				} else if (imageFlagNumber == mListImage.size()) {

					createTopicPost(true);
					// 返回HTML页面的内容

				}
			} else {
				ToastUtil.show(CreatImgActivity.this, " 图片上传失败");
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
			return UploadUtils.uploadFile(file, Consts.URL + Consts.APP.MessageAction + "?action_flag=addImage");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

	}

}
