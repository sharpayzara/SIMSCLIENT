package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.entity.TradeList;

public class TradeDetailActivity extends CommonHeadPanelActivity{
    Context mContext;
    TextView name,phone,effective,tjDate,brand,store,xinghao,buyDate,chengjiaojf;
    TradeList.Trade trade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_trade_detail);
        super.onCreate(savedInstanceState);
        mContext = this;
        trade = (TradeList.Trade) getIntent().getSerializableExtra("trade");
        initUI();
        initData();
    }

    private void initData() {
        name.setText(trade.getName());
        phone.setText(trade.getPhone());
        effective.setText(trade.getEffective());
        tjDate.setText(trade.getTjDate());
        brand.setText(trade.getBrand());
        store.setText(trade.getStore());
        xinghao.setText(trade.getXinghao());
        buyDate.setText(trade.getBuyDate());
        chengjiaojf.setText(trade.getChengjiaojf());
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("成交客户详情");
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        effective = (TextView) findViewById(R.id.effective);
        tjDate = (TextView) findViewById(R.id.tjDate);
        brand = (TextView) findViewById(R.id.brand);
        store = (TextView) findViewById(R.id.store);
        xinghao = (TextView) findViewById(R.id.xinghao);
        buyDate = (TextView) findViewById(R.id.buyDate);
        chengjiaojf = (TextView) findViewById(R.id.chengjiaojf);

    }

}
