package com.bw.erzhoumoni.model;

import com.bw.erzhoumoni.bean.LogBean;
import com.bw.erzhoumoni.contract.LogContract;
import com.bw.erzhoumoni.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:10:37
 *@Description:
 * */public class LogIModel implements LogContract.LogModel {
    @Override
    public void doLog(String phone, String pwd, LogICallBack iCallBack) {
        NetUtile.getInstance().getMapis().doLog(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogBean logBean) {
                        if (iCallBack!=null){
                            iCallBack.onLogSuccess(logBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
