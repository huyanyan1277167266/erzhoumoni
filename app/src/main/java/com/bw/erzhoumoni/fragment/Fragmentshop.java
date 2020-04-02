package com.bw.erzhoumoni.fragment;

import android.view.View;

import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.base.BaseFragment;
import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.bean.OrderListByBean;
import com.bw.erzhoumoni.contract.OrderListByContract;

import org.greenrobot.eventbus.EventBus;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:23:03
 *@Description:
 * */
public class Fragmentshop extends BaseFragment implements OrderListByContract.ByView {

     @Override
    protected BasePresenter initpresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View view) {
        if (!EventBus.getDefault().isRegistered(getActivity())){
            EventBus.getDefault().register(getActivity());
        }
    }

    @Override
    protected void getData() {

//        selected = intent.getParcelableArrayListExtra("selected");
//        Log.i("selected", selected.size()+"");
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        order_rlv.setLayoutManager(linearLayoutManager);
//        createorderAdapter = new CreateorderAdapter(this, selected);
//        createorderAdapter.setData(selected);
//        order_rlv.setAdapter(createorderAdapter);
    }

    @Override
    public void onBySuccess(OrderListByBean orderListByBean) {

    }

    @Override
    public void onByError(String str) {

    }
}
