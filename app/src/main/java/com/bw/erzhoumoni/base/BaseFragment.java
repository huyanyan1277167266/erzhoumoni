package com.bw.erzhoumoni.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment  {
    P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getlayoutId(), null);
        bind = ButterKnife.bind(getActivity());
        mPresenter=initpresenter();

        initView(view);
        getData();
        return view;
    }
    public P getmPresenter(){
        return mPresenter;
    }
    protected abstract P initpresenter();
    protected abstract int getlayoutId();
    protected abstract void initView(View view);
    protected abstract void getData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.datachView();
            mPresenter=null;

        }
        bind.unbind();
    }
}
