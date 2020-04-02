package com.bw.erzhoumoni.presenter;

import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.ReceiveBean;
import com.bw.erzhoumoni.contract.ReceiveContract;
import com.bw.erzhoumoni.model.ReceiveIModel;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:17:23
 *@Description:
 * */public class ReceiveIPresenter extends BasePresenter implements ReceiveContract.ReceivePresenter {

    private ReceiveIModel mModel;

    public ReceiveIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initmodel() {
        mModel = new ReceiveIModel();
    }

    @Override
    public void doReceive(int userId, String sessionId) {
        mModel.doReceive(userId, sessionId, new ReceiveContract.ReceiveModel.ReceiveICallBack() {
            @Override
            public void onReceiveSuccess(ReceiveBean resultBean) {
                IBaseView view = getView();
                if (view instanceof ReceiveContract.ReceiveView){
                    ((ReceiveContract.ReceiveView) view).onReceiveSuccess(resultBean);
                }
            }

            @Override
            public void onReceiveError(String str) {
                IBaseView view = getView();
                if (view instanceof ReceiveContract.ReceiveView){
                   ((ReceiveContract.ReceiveView) view).onReceiveError(str);
                }
            }
        });
    }
}
