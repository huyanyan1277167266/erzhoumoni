package com.bw.erzhoumoni.presenter;

import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.CreateOrderBean;
import com.bw.erzhoumoni.bean.ReceiveBean;
import com.bw.erzhoumoni.contract.CreateOrderContract;
import com.bw.erzhoumoni.model.CreateOrderModel;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:16:44
 *@Description:
 * */public class CreateOrderPresenter extends BasePresenter implements CreateOrderContract.OrderPresenter {

    private CreateOrderModel mModel;

    public CreateOrderPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initmodel() {
        mModel = new CreateOrderModel();
    }

    @Override
    public void doCreateOrder(int userId, String sessionId, String orderInfo, double totalPrice, int addressId) {
        mModel.doCreateOrder(userId, sessionId, orderInfo, totalPrice, addressId, new CreateOrderContract.OrderModel.OrderICallBack() {
            @Override
            public void onOrderSuccess(CreateOrderBean createOrderBean) {
                IBaseView view = getView();
                if (view instanceof CreateOrderContract.OrderView){
                    ((CreateOrderContract.OrderView) view).onOrderSuccess(createOrderBean);
                }
            }

            @Override
            public void onError(String str) {
                IBaseView view = getView();
                if (view instanceof CreateOrderContract.OrderView){
                   ((CreateOrderContract.OrderView) view).onError(str);
                }
            }
        });
    }

    @Override
    public void doReceive(int userId, String sessionId) {
        mModel.doReceive(userId, sessionId, new CreateOrderContract.OrderModel.ReceiveICallBack() {
            @Override
            public void onReceiveSuccess(ReceiveBean resultBean) {
                IBaseView view = getView();
                if (view instanceof CreateOrderContract.OrderView){
                    ((CreateOrderContract.OrderView) view).onReceiveSuccess(resultBean);
                }
            }

            @Override
            public void onReceiveError(String str) {
                IBaseView view = getView();
                if (view instanceof CreateOrderContract.OrderView){
                    ((CreateOrderContract.OrderView) view).onReceiveError(str);
                }
            }
        });
    }

}
