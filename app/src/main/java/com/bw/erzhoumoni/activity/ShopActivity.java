
package com.bw.erzhoumoni.activity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.adapter.ShopAdapter;
import com.bw.erzhoumoni.base.BaseActivity;
import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.bean.ResultBean;
import com.bw.erzhoumoni.bean.ShopBean;
import com.bw.erzhoumoni.bean.ShoppingCartListBean;
import com.bw.erzhoumoni.contract.ShopContract;
import com.bw.erzhoumoni.presenter.ShopIPresenter;
import com.bw.erzhoumoni.utile.NetUtile;
import com.bw.erzhoumoni.utile.SpUtile;
import com.dj.greendao.dao.DaoMaster;
import com.dj.greendao.dao.DaoSession;
import com.dj.greendao.dao.ResultBeanDao;
import com.dj.greendao.dao.ShoppingCartListBeanDao;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopActivity extends BaseActivity implements ShopContract.ShopView {
    //找控件
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.conut)
    TextView conut;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.bt)
    Button bt;
    private ShopAdapter shopAdapter;

    private List<ResultBean> result;
    private ResultBeanDao mresultBeanDao;
    private ShoppingCartListBeanDao mshoppingCartListBeanDao;

    @Override
    protected BasePresenter initPresenter() {
        return new ShopIPresenter(this);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        //注册eventbus
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        //数据库
//        DaoSession daoSession= DaoMaster.newDevSession(this, "shoppingCar");
//        mresultBeanDao = daoSession.getResultBeanDao();
//        mshoppingCartListBeanDao = daoSession.getShoppingCartListBeanDao();
//        boolean iswork = !NetUtile.getInstance().iswork(this);
//        if (!iswork){
//          //  List<ShoppingCartListBean> list = mshoppingCartListBeanDao.queryBuilder().list();
//            List<ResultBean> list = mresultBeanDao.queryBuilder().list();
//            for (ResultBean resultBean:list){
//
//                //把商品集合添加到商品的bean类中
//                List<ShoppingCartListBean> cartListBeans = mshoppingCartListBeanDao.queryBuilder()
//                        .where(ShoppingCartListBeanDao.Properties.ShopName.eq(ResultBean.class.getCanonicalName()))
//                        .list();
//                resultBean.setShoppingCartList(cartListBeans);
//                result.add(resultBean);
//            }
//
//        }
        //接收数据
        String userId = SpUtile.getString(this, SpUtile.USERINFO, SpUtile.USERID);
        String sessionId = SpUtile.getString(this, SpUtile.USERINFO, SpUtile.USERSESSIONID);

        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof ShopIPresenter){
            ((ShopIPresenter) basePresenter).doShop(Integer.valueOf(userId),sessionId);

        }
        //全选全不选点击事件
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变集合中checkbox的值
                //遍历集合
                for (int i=0;i<result.size();i++){

                    List<ShoppingCartListBean> list = result.get(i).getShoppingCartList();
                    //遍历每个商家的商品
                    for (int j=0;j<list.size();j++){
                        //改变数据集合里的值
                        list.get(j).setIscheckd(cb.isChecked());
                    }
                }
                //调用更新方法重新计算
                getisshop(0);
                //刷新适配器
                shopAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onShopSuccess(ShopBean shopBean) {
        //解析拿到商家集合

        result = shopBean.getResult();
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        //创建适配器
        shopAdapter = new ShopAdapter(this,result);
        rcv.setAdapter(shopAdapter);
        //往表里添加数据
//        for (ResultBean resultBean:result){
//            mresultBeanDao.insertOrReplace(resultBean);
//            List<ShoppingCartListBean> cartList = resultBean.getShoppingCartList();
//            for (ShoppingCartListBean bean:cartList){
//                bean.setShopName(resultBean.getCategoryName());
//                mshoppingCartListBeanDao.insertOrReplace(bean);
//            }
//
//        }
    }

    @Override
    public void onError(String str) {

    }
    //接收通知更新
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getisshop(Integer bb){
        //初始化价格
        double prices=0;
        int conuts=0;
        //定义全选全不选的变量值并赋值
        boolean q=true;
        //遍历商家
        for (int i=0;i<result.size();i++){
            //给商家定义默认为true
            boolean s=true;
            //循环商品
            List<ShoppingCartListBean> shoppingCartList = result.get(i).getShoppingCartList();
            for (int j=0;j<shoppingCartList.size();j++){
                //判断是否被选中
                if (!shoppingCartList.get(j).getIscheckd()){
                    q=false;
                    s=false;
                }else{
                    //如果选中数量就累加
                    conuts+=shoppingCartList.get(j).getCount();
                    //如果险种价格=价格*数量
                    prices+=shoppingCartList.get(j).getCount()*shoppingCartList.get(j).getPrice();
                }
            }
            //给商家赋值a
            result.get(i).setShop(s);
        }
        //给全选按钮赋值
        cb.setChecked(q);
        //刷新适配器
        shopAdapter.notifyDataSetChanged();
        //给控件赋值
        conut.setText("总计："+conuts+"件");
        price.setText("￥"+String.format("%.2f",prices));
    }
    @OnClick(R.id.bt)
    public void btClick(){
        ArrayList<ShoppingCartListBean> selectedBean = new ArrayList<>();
        for (int i=0;i<result.size();i++){
            List<ShoppingCartListBean> shoppingCartList = result.get(i).getShoppingCartList();
            for (int j=0;j<shoppingCartList.size();j++){
                if (shoppingCartList.get(j).getIscheckd()){
                    selectedBean.add(shoppingCartList.get(j));
                }
            }
        }
//        EventBus.getDefault().postSticky(selectedBean);
        Intent intent = new Intent(ShopActivity.this, CreateOrderActivity.class);
        intent.putParcelableArrayListExtra("selected",selectedBean);

       startActivity(intent);
    }

}
