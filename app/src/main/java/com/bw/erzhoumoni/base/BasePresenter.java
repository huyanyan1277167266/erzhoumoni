package com.bw.erzhoumoni.base;

import java.lang.ref.WeakReference;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:9:58
 *@Description:
 * */public abstract class BasePresenter <V extends IBaseView> {

    private WeakReference<V> mWeakReference;

    public BasePresenter(V v) {
        mWeakReference = new WeakReference<>(v);
        initmodel();
    }
    public V getView(){
        if (mWeakReference!=null){
            return mWeakReference.get();
        }
        return null;
    }
    protected abstract void initmodel();
    public void datachView(){
        if (mWeakReference!=null){
            mWeakReference.clear();
            mWeakReference=null;
        }
    }
}
