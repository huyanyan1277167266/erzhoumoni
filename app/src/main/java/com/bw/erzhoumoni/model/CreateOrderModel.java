package com.bw.erzhoumoni.model;

import com.bw.erzhoumoni.bean.CreateOrderBean;
import com.bw.erzhoumoni.bean.ReceiveBean;
import com.bw.erzhoumoni.contract.CreateOrderContract;
import com.bw.erzhoumoni.utile.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:16:42
 *@Description:
 * */public class CreateOrderModel implements CreateOrderContract.OrderModel {
    @Override
    public void doCreateOrder(int userId, String sessionId, String orderInfo, double totalPrice, int addressId, OrderICallBack iCallBack) {
        NetUtile.getInstance().getMapis().doOrder(userId,sessionId,orderInfo,totalPrice,addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CreateOrderBean createOrderBean) {
                        if (iCallBack!=null){
                            iCallBack.onOrderSuccess(createOrderBean);
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
    @Override
    public void doReceive(int userId, String sessionId, ReceiveICallBack iCallBack) {
        NetUtile.getInstance().getMapis().doReceive(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReceiveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReceiveBean receiveBean) {
                        if (iCallBack!=null){
                            iCallBack.onReceiveSuccess(receiveBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onReceiveError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
