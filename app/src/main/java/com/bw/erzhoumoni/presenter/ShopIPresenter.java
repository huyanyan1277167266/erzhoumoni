package com.bw.erzhoumoni.presenter;

import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.ShopBean;
import com.bw.erzhoumoni.contract.ShopContract;
import com.bw.erzhoumoni.model.ShopIModel;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:11:29
 *@Description:
 * */public class ShopIPresenter extends BasePresenter implements ShopContract.ShopPresenter {

    private ShopIModel mModel;

    public ShopIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initmodel() {
        mModel = new ShopIModel();
    }

    @Override
    public void doShop(int userId, String sessionId) {
        mModel.doShop(userId, sessionId, new ShopContract.ShopModel.ShopICallBack() {
            @Override
            public void onShopSuccess(ShopBean shopBean) {
                IBaseView view = getView();
                if (view instanceof ShopContract.ShopView){
                    ((ShopContract.ShopView) view).onShopSuccess(shopBean);
                }
            }

            @Override
            public void onError(String str) {
                IBaseView view = getView();
                if (view instanceof ShopContract.ShopView){
                    ((ShopContract.ShopView) view).onError(str);
                }
            }
        });
    }
}
