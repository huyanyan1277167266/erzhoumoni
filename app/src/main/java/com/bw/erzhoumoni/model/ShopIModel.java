package com.bw.erzhoumoni.model;

import com.bw.erzhoumoni.bean.ShopBean;
import com.bw.erzhoumoni.contract.ShopContract;
import com.bw.erzhoumoni.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:11:26
 *@Description:
 * */public class ShopIModel implements ShopContract.ShopModel {
    @Override
    public void doShop(int userId, String sessionId, ShopICallBack iCallBack) {
        NetUtile.getInstance().getMapis().doShop(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopBean shopBean) {
                        if (iCallBack!=null){
                            iCallBack.onShopSuccess(shopBean);
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
