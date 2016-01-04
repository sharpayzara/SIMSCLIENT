package com.mxst.car.simsclient.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.layout.HeadControlPanel;

/**
 * author   Joy
 * Date:  2016/1/4.
 * version:  V1.0
 * Description:
 */
public class CommonHeadPanelActivity extends Activity {
    LinearLayout backBtn;
    HeadControlPanel headControlPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headControlPanel = (HeadControlPanel) findViewById(R.id.head_layout);
        backBtn = (LinearLayout) findViewById(R.id.left_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setHeadTitle(String msg) {
        headControlPanel.setMiddleTitle(msg);
    }

    public void showBackBtn() {
        backBtn.setVisibility(View.VISIBLE);
    }

}
