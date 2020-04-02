package com.bw.erzhoumoni.model;

import com.bw.erzhoumoni.bean.OrderListByBean;
import com.bw.erzhoumoni.contract.OrderListByContract;
import com.bw.erzhoumoni.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/4/2
 *@Time:14:46
 *@Description:
 * */public class ByIModel implements OrderListByContract.ByModel {
    @Override
    public void doBy(int userId, String sessionId, int status, int page, int count, ByICallBack iCallBack) {
        NetUtile.getInstance().getMapis().doOrderBy(userId,sessionId,status,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderListByBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderListByBean orderListByBean) {
                        if (iCallBack!=null){
                            iCallBack.onBySuccess(orderListByBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                           iCallBack.onByError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
