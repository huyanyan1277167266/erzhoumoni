package com.bw.erzhoumoni.contract;

import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.CreateOrderBean;
import com.bw.erzhoumoni.bean.ReceiveBean;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:16:38
 *@Description:
 * */public interface CreateOrderContract {
     interface OrderView extends IBaseView{
         void onOrderSuccess(CreateOrderBean createOrderBean);
         void onError(String str);

         void onReceiveSuccess(ReceiveBean receiveBean);
         void onReceiveError(String str);
     }
     interface OrderPresenter{
         void doCreateOrder(int userId,String sessionId,String orderInfo,double totalPrice,int addressId);
         void doReceive(int userId,String sessionId);
     }
     interface OrderModel{
         void doCreateOrder(int userId,String sessionId,String orderInfo,double totalPrice,int addressId,OrderICallBack iCallBack);
         interface OrderICallBack{
             void onOrderSuccess(CreateOrderBean createOrderBean);
             void onError(String str);
         }
         void doReceive(int userId,String sessionId,ReceiveICallBack iCallBack);
         interface ReceiveICallBack{
             void onReceiveSuccess(ReceiveBean resultBean);
             void onReceiveError(String str);
         }
     }
}
