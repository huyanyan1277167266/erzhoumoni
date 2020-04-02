package com.bw.erzhoumoni.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.adapter.CreateorderAdapter;
import com.bw.erzhoumoni.base.BaseActivity;
import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.bean.CreateOrderBean;
import com.bw.erzhoumoni.bean.ReceiveBean;
import com.bw.erzhoumoni.bean.ShoppingCartListBean;
import com.bw.erzhoumoni.contract.CreateOrderContract;
import com.bw.erzhoumoni.presenter.CreateOrderPresenter;
import com.bw.erzhoumoni.presenter.ReceiveIPresenter;
import com.bw.erzhoumoni.utile.SpUtile;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateOrderActivity extends BaseActivity implements CreateOrderContract.OrderView {
    ArrayList<ShoppingCartListBean> result = new ArrayList<>();
    @BindView(R.id.name)
    TextView name;
   @BindView(R.id.phonee)
    TextView phonee;
    @BindView(R.id.dz)
    TextView dz;
    @BindView(R.id.order_rlv)
    RecyclerView order_rlv;
    @BindView(R.id.order_count)
    TextView order_count;
    @BindView(R.id.order_price)
    TextView order_price;
    @BindView(R.id.order_bt)
    Button order_bt;
    double mone=0;
    ArrayList<Map<String, String>> maps = new ArrayList<>();
    private ArrayList<ShoppingCartListBean> selected;
    private CreateorderAdapter createorderAdapter;
    private String orderId;


    @Override
    protected BasePresenter initPresenter() {
        return new CreateOrderPresenter(this);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void initView() {



    }
//注册EventBus
    @Override
    protected void onResume() {
        super.onResume();


    }
    //反注册EventBus
    @Override
    protected void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void getData() {

        String userId = SpUtile.getString(this, SpUtile.USERINFO, SpUtile.USERID);
        String sessionId = SpUtile.getString(this, SpUtile.USERINFO, SpUtile.USERSESSIONID);
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof ReceiveIPresenter){
            ((ReceiveIPresenter) basePresenter).doReceive(Integer.valueOf(userId),sessionId);
        }
        Intent intent = getIntent();
        selected = intent.getParcelableArrayListExtra("selected");
        Log.i("selected", selected.size()+"");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        order_rlv.setLayoutManager(linearLayoutManager);
        createorderAdapter = new CreateorderAdapter(this, selected);
        createorderAdapter.setData(selected);
        order_rlv.setAdapter(createorderAdapter);

    }
    @OnClick(R.id.order_bt)
    public void getbt(View view){
        Gson gson = new Gson();
        String info = gson.toJson(maps);
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof CreateOrderPresenter){
            String userId = SpUtile.getString(this, SpUtile.USERINFO, SpUtile.USERID);
            String sessionId = SpUtile.getString(this, SpUtile.USERINFO, SpUtile.USERINFO);

            ((CreateOrderPresenter) basePresenter).doCreateOrder(Integer.valueOf(userId),sessionId,info,selected.get(0).getPrice(),4601);
        }

        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }



    @Override
    public void onOrderSuccess(CreateOrderBean createOrderBean) {
        orderId = createOrderBean.getOrderId();
    }

    @Override
    public void onError(String str) {

    }

    @Override
    public void onReceiveSuccess(ReceiveBean receiveBean) {

        name.setText(receiveBean.getResult().get(0).getRealName()+"");
        phonee.setText(receiveBean.getResult().get(0).getPhone()+"");
        dz.setText(receiveBean.getResult().get(0).getAddress()+"");
        List<ReceiveBean.ResultBean> result = receiveBean.getResult();
        int id = result.get(0).getId();
        orderId=String.valueOf(id);
    }

    @Override
    public void onReceiveError(String str) {

    }
@Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getisorder(double d){
        maps.clear();
    int totalcount = 0;
    double totalprice = 0;
    for(int i=0;i<selected.size();i++){
        HashMap<String, String> map = new HashMap<>();
        totalcount+=selected.get(i).getCount();
        totalprice+=selected.get(i).getCount()*selected.get(i).getPrice();
        map.put("commodityId",selected.get(i).getCommodityId()+"");
        map.put("amount",selected.get(i).getCount()+"");
        maps.add(map);
    }
    createorderAdapter.notifyDataSetChanged();
    order_count.setText("共计"+totalcount+"件");
    order_price.setText("总价￥"+totalprice);
    mone=totalprice;
}

}
