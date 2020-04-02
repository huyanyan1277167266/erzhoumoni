package com.bw.erzhoumoni.presenter;

import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.base.IBaseView;
import com.bw.erzhoumoni.bean.LogBean;
import com.bw.erzhoumoni.contract.LogContract;
import com.bw.erzhoumoni.model.LogIModel;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:10:42
 *@Description:
 * */public class LogIPresenter extends BasePresenter implements LogContract.LogPresenter {

    private LogIModel mModel;

    public LogIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initmodel() {
        mModel = new LogIModel();
    }

    @Override
    public void doLog(String phone, String pwd) {
        mModel.doLog(phone, pwd, new LogContract.LogModel.LogICallBack() {
            @Override
            public void onLogSuccess(LogBean logBean) {
                IBaseView view = getView();
                if (view instanceof LogContract.LogView){
                    ((LogContract.LogView) view).onLogSuccess(logBean);
                }
            }

            @Override
            public void onError(String str) {
                IBaseView view = getView();
                if (view instanceof LogContract.LogView){
                    ((LogContract.LogView) view).onError(str);
                }
            }
        });
    }
}
