package com.bw.erzhoumoni.contract;

import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.OrderListByBean;

/*
 *@Auther:cln
 *@Date: 2020/4/2
 *@Time:14:42
 *@Description:
 * */public interface OrderListByContract {
     interface ByView extends IBaseView{
         void onBySuccess(OrderListByBean orderListByBean);
         void onByError(String str);
     }
     interface ByPresenter{
         void doBy(int userId,String sessionId,int status,int page,int count);
     }
     interface ByModel{
         void doBy(int userId,String sessionId,int status,int page,int count,ByICallBack iCallBack);
         interface ByICallBack{
             void onBySuccess(OrderListByBean orderListByBean);
             void onByError(String str);
         }
     }
}
