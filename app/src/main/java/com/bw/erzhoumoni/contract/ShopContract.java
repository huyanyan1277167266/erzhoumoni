package com.bw.erzhoumoni.contract;

import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.ShopBean;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:11:21
 *@Description:
 * */public interface ShopContract {
     interface ShopView extends IBaseView {
         void onShopSuccess(ShopBean shopBean);
         void onError(String str);
     }
     interface ShopPresenter{
         void doShop(int userId,String sessionId);
     }
     interface ShopModel{
         void doShop(int userId,String sessionId,ShopICallBack iCallBack);
         interface ShopICallBack{
             void onShopSuccess(ShopBean shopBean);
             void onError(String str);
         }
     }
}
