package com.bw.erzhoumoni.contract;

import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.LogBean;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:10:33
 *@Description:
 * */public interface LogContract {
     interface LogView extends IBaseView{
         void onLogSuccess(LogBean logBean);
         void onError(String str);
     }
     interface LogPresenter{
         void doLog(String phone,String pwd);
     }
     interface LogModel{
         void doLog(String phone,String pwd,LogICallBack iCallBack);
         interface LogICallBack{
             void onLogSuccess(LogBean logBean);
             void onError(String str);
         }
     }
}
