package com.bw.erzhoumoni.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{
    P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        bind = ButterKnife.bind(this);
        mPresenter=initPresenter();
        initView();
        getData();
    }
    public P getmPresenter(){
        return mPresenter;
    }
    protected abstract P initPresenter();
    protected abstract int getlayoutId();
    protected abstract void initView();
    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.datachView();
            mPresenter=null;
        }
        bind.unbind();
    }
}
