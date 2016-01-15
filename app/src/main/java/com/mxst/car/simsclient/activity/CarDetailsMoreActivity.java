package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.entity.ParaList;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/7.
 * version:  V1.0
 * Description:
 */
public class CarDetailsMoreActivity extends CommonHeadPanelActivity {
    private List<ParaList.ConfigInfoEntity> configinfoList;
    LinearLayout layut;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_more);
        super.onCreate(savedInstanceState);
        mContext = this;
        init();

    }

    private void init() {
        showBackBtn();
        setHeadTitle("资源配置详情");
        configinfoList = (List<ParaList.ConfigInfoEntity>) getIntent().getSerializableExtra("configinfoList");
        layut = (LinearLayout) findViewById(R.id.res_config);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (ParaList.ConfigInfoEntity entity : configinfoList) {
            LinearLayout rowLayout = (LinearLayout) inflater.inflate(R.layout.item_carconfig, null);
            TextView tv = (TextView) rowLayout.findViewById(R.id.titleName);
            TableLayout configLayout = (TableLayout) rowLayout.findViewById(R.id.config_layout);
            tv.setText(entity.getConfigName());
            List<ParaList.ConfigInfoEntity.DataEntity> dataList = entity.getData();

            for (int i = 0; i < dataList.size(); i++) {
                ParaList.ConfigInfoEntity.DataEntity entity1 = dataList.get(i);
                TableLayout row = (TableLayout) inflater.inflate(R.layout.table_row_peizhi_item, null);
                ((TextView) row.findViewById(R.id.peizhi_name)).setText(entity1.getParaName() + ":");
                ((TextView) row.findViewById(R.id.peizhi_value)).setText(entity1.getContent());
                configLayout.addView(row);
            }
            layut.addView(rowLayout);
        }

    }
}
