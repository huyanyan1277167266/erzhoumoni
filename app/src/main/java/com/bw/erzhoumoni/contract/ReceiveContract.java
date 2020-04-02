package com.bw.erzhoumoni.contract;

import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.ReceiveBean;
import com.bw.erzhoumoni.bean.ResultBean;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:17:16
 *@Description:
 * */public interface ReceiveContract {
     interface ReceiveView extends IBaseView{
         void onReceiveSuccess(ReceiveBean resultBean);
         void onReceiveError(String str);
     }
     interface ReceivePresenter{
         void doReceive(int userId,String sessionId);
     }
     interface ReceiveModel{
         void doReceive(int userId,String sessionId,ReceiveICallBack iCallBack);
         interface ReceiveICallBack{
             void onReceiveSuccess(ReceiveBean resultBean);
             void onReceiveError(String str);
         }
     }


}
