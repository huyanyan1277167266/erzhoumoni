package com.bw.erzhoumoni.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.base.BaseActivity;
import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.bean.LogBean;
import com.bw.erzhoumoni.contract.LogContract;
import com.bw.erzhoumoni.presenter.LogIPresenter;
import com.bw.erzhoumoni.utile.SpUtile;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements LogContract.LogView {
@BindView(R.id.phone)
    EditText phone;
@BindView(R.id.pwd)
EditText pwd;
@BindView(R.id.bt)
    Button bt;
    @Override
    protected BasePresenter initPresenter() {
        return new LogIPresenter(this);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

    }
//点击事件
    @OnClick(R.id.bt)
    public void setOnClickbt(View view){
        String phones = phone.getText().toString();
        String pwds = pwd.getText().toString();
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof LogIPresenter){
            ((LogIPresenter) basePresenter).doLog(phones,pwds);
        }
    }
    @Override
    public void onLogSuccess(LogBean logBean) {
        //解析
        LogBean.ResultBean result = logBean.getResult();
        int userId = result.getUserId();
        String sessionId = result.getSessionId();
        SpUtile.putString(this,SpUtile.USERINFO,SpUtile.USERID,userId+"");
        SpUtile.putString(this,SpUtile.USERINFO,SpUtile.USERSESSIONID,sessionId);

        //跳转
        Intent intent = new Intent(MainActivity.this, ShopActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String str) {

    }

}
