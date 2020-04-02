package com.bw.erzhoumoni.presenter;

import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.OrderListByBean;
import com.bw.erzhoumoni.contract.OrderListByContract;
import com.bw.erzhoumoni.model.ByIModel;

/*
 *@Auther:cln
 *@Date: 2020/4/2
 *@Time:14:49
 *@Description:
 * */public class ByIPresenter extends BasePresenter implements OrderListByContract.ByPresenter {

    private ByIModel mModel;

    public ByIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initmodel() {
        mModel = new ByIModel();
    }

    @Override
    public void doBy(int userId, String sessionId, int status, int page, int count) {
        mModel.doBy(userId, sessionId, status, page, count, new OrderListByContract.ByModel.ByICallBack() {
            @Override
            public void onBySuccess(OrderListByBean orderListByBean) {
                IBaseView view = getView();
                if (view instanceof OrderListByContract.ByView){
                    ((OrderListByContract.ByView) view).onBySuccess(orderListByBean);
                }
            }

            @Override
            public void onByError(String str) {
                IBaseView view = getView();
                if (view instanceof OrderListByContract.ByView){
                   ((OrderListByContract.ByView) view).onByError(str);
                }
            }
        });
    }
}
