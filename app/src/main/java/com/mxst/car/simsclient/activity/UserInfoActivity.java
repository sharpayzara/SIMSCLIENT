package com.mxst.car.simsclient.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.PersonInfo;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.FileUtil;
import com.mxst.car.simsclient.utils.SelectHeadUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static com.mxst.car.simsclient.utils.FileUtil.DeleteFile;
import static com.mxst.car.simsclient.utils.FileUtil.Foutput;
import static com.mxst.car.simsclient.utils.FileUtil.Ispath;

public class UserInfoActivity extends CommonHeadPanelActivity implements View.OnClickListener {
    LinearLayout headImg_layout;
    EditText name, nickName, email, address;
    ImageButton submit_btn, cancel_btn;
    RadioButton sex_man, sex_woman;
    TextView birthday;
    Context mContext;
    private Calendar calendar;
    private Uri photoUri = null;
    private ImageView headImg;
    private File myfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_info);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        getPersonInfo();
    }

    private void getPersonInfo() {

        new BaseTask<JsonResult<PersonInfo>, String>(mContext, R.string.upload_notice) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<PersonInfo>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    PersonInfo.VipInfoEntity bean = result.getRecord().getVipInfo();
                    name.setText(bean.getName());
                    nickName.setText(bean.getNickName());
                    email.setText(bean.getEmail());
                    birthday.setText(bean.getBirthday());
                    address.setText(bean.getAddress());
                    BitmapUtils bitmapUtils = new BitmapUtils(mContext);
                    bitmapUtils.display(headImg, bean.getHeadImg());
                    if (bean.getGender().isEmpty() && bean.getGender().equals("女")) {
                        sex_woman.setChecked(true);
                    } else {
                        sex_man.setChecked(true);
                    }
                } else {
                    CommonUtil.showToastToShort(mContext, result.getMsg());
                }
            }
        }.requestByPost(Constant.URL.PERSONINFO, null);

    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人中心");
        name = (EditText) findViewById(R.id.name);
        sex_man = (RadioButton) findViewById(R.id.sex_man);
        sex_woman = (RadioButton) findViewById(R.id.sex_woman);
        nickName = (EditText) findViewById(R.id.nickName);
        email = (EditText) findViewById(R.id.email);
        birthday = (TextView) findViewById(R.id.birthday);
        address = (EditText) findViewById(R.id.address);
        submit_btn = (ImageButton) findViewById(R.id.submit_btn);
        cancel_btn = (ImageButton) findViewById(R.id.cancel_btn);
        headImg_layout = (LinearLayout) findViewById(R.id.headImg_layout);
        headImg = (ImageView) findViewById(R.id.headImg);
        submit_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        sex_man.setChecked(true);
        calendar = Calendar.getInstance();
        headImg_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FileUtil.hasSdcard()) {
                    Toast.makeText(mContext, "没有找到SD卡，请检查SD卡是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    photoUri = FileUtil.getUriByFileDirAndFileName(Constant.SAVE_DIRECTORY, Constant.SAVE_PIC_NAME);
                } catch (IOException e) {
                    Toast.makeText(mContext, "创建文件失败。", Toast.LENGTH_SHORT).show();
                    return;
                }
                SelectHeadUtil.openDialog(UserInfoActivity.this, photoUri);

            }
        });


        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {

                        //更新EditText控件日期 小于10加0
                        birthday.setText(new StringBuilder().append(mYear).append("-")
                                .append((mMonth + 1) < 10 ? 0 + (mMonth + 1) : (mMonth + 1))
                                .append("-")
                                .append((mDay < 10) ? 0 + mDay : mDay));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (submit_btn == v) {
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("name", name.getText().toString());
            params.addQueryStringParameter("nickName", nickName.getText().toString());
            if (sex_woman.isChecked()) {
                params.addQueryStringParameter("sex", "女");
            } else {
                params.addQueryStringParameter("sex", "男");
            }
            params.addQueryStringParameter("email", email.getText().toString());
            params.addQueryStringParameter("birthday", birthday.getText().toString());
            params.addQueryStringParameter("address", address.getText().toString());
            if (Ispath()) {
                params.addBodyParameter("file", new File(Environment.getExternalStorageDirectory() + Constant.SAVE_DIRECTORY + "/" + Constant.SAVE_PIC_NAME));
            }
            new BaseTask<JsonResult<JSONObject>, String>(mContext, R.string.upload_notice) {

                @Override
                public TypeToken setTypeToken() {
                    return new TypeToken<JSONObject>() {
                    };
                }

                @Override
                public void onSuccess() {
                    if (result.isSuccess()) {
                        CommonUtil.showToastToShort(mContext, "修改成功");
                        DeleteFile();
                        Intent intent = new Intent();
                        if (result.getRecord().length() != 0) {
                            intent.putExtra("img", result.getRecord().optString("newImg"));
                        }
                        intent.putExtra("name", nickName.getText().toString());
                        setResult(Constant.REQUESTCODE.NICKNAME, intent);
                        finish();
                    } else {
                        CommonUtil.showToastToShort(mContext, result.getMsg());
                    }
                }
            }.requestByPost(Constant.URL.UPDATEPERSON, params);
        } else if (v == cancel_btn) {
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.REQUESTCODE.PHOTO_REQUEST_TAKEPHOTO: // 拍照
                SelectHeadUtil.startPhotoZoom(this, photoUri, 300);
                break;
            case Constant.REQUESTCODE.PHOTO_REQUEST_GALLERY://相册获取
                if (data == null)
                    return;
                SelectHeadUtil.startPhotoZoom(this, data.getData(), 300);
                break;
            case Constant.REQUESTCODE.PHOTO_REQUEST_CUT: //接收处理返回的图片结果
                if (data != null) {
                    Bitmap bit = data.getExtras().getParcelable("data");
                    headImg.setImageBitmap(bit);
                    myfile = FileUtil.getFileByUri(this, photoUri);
                    Foutput(bit, myfile);
                    break;

                }
        }
    }

}

