package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

import java.io.File;
import java.io.FileInputStream;

public class UserSetUpActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    Context mContext;
    File cacheDir;
    private TextView cacheSize_tv;
    RelativeLayout clear_rlt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setup);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人设置");
        cacheSize_tv = (TextView) findViewById(R.id.cacheSize_tv);
        cacheSize_tv.setText(getCatchSize());
        clear_rlt = (RelativeLayout) findViewById(R.id.clear_rlt);
        clear_rlt.setOnClickListener(this);
    }

    public String getCatchSize(){
        if("mounted".equals(Environment.getExternalStorageState())) {
            java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
            cacheDir = mContext.getExternalCacheDir();
            if(getFileSizes(cacheDir)/1024 < 1024){
                return getFileSizes(cacheDir)/1024 + "KB";
            }
            return new java.text.DecimalFormat("#.00").format(((double)getFileSizes(cacheDir)/1024/1024)) +" M";
        }
        return 0+" M";
    }

    private static long getFileSizes(File f)
    {
        long size = 0;
        try{
            File flist[] = f.listFiles();
            for (int i = 0; i < flist.length; i++){
                if (flist[i].isDirectory()){
                    size = size + getFileSizes(flist[i]);
                }
                else{
                    size = size + getFileSize(flist[i]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }

    private static long getFileSize(File file) throws Exception
    {
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        else{
            file.createNewFile();
        }
        return size;
    }

    public void deleteFile(File file){
        if (file.isFile()) {
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                deleteFile(f);
            }
            file.delete();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == clear_rlt){
            if(cacheDir != null){
                deleteFile(cacheDir);
                cacheSize_tv.setText(getCatchSize());
            }
        }
    }
}
