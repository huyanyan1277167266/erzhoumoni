package com.bw.erzhoumoni.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.activity.ShopActivity;
import com.bw.erzhoumoni.bean.ResultBean;
import com.bw.erzhoumoni.bean.ShopBean;
import com.bw.erzhoumoni.bean.ShoppingCartListBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:11:49
 *@Description:
 * */public class ShopAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
//    ArrayList<ShoppingCartListBean> listBeans = new ArrayList<>();
    Context context;
    List<ResultBean> list;
    private GoodsAdapter goodsAdapter;

    public ShopAdapter(Context context, List<ResultBean> list) {
        this.context = context;
        this.list = list;
    }
//    public void setData(ArrayList<ShoppingCartListBean> shoppingCartListBeanList){
//        listBeans=shoppingCartListBeanList;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shop_itme, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).shopcb.setChecked(list.get(position).getShop());
        ((ViewHolder)holder).shopname.setText(list.get(position).getCategoryName());
      //添加布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        ((ViewHolder)holder).shoprcv.setLayoutManager(linearLayoutManager);
        //设置适配器
        goodsAdapter = new GoodsAdapter(context, list.get(position).getShoppingCartList());
        ((ViewHolder)holder).shoprcv.setAdapter(goodsAdapter);
        //设置商家的点击事件，改变商品的多选框状态
        ((ViewHolder)holder).shopcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShoppingCartListBean> shoppingCartList = list.get(position).getShoppingCartList();
                //遍历商品的长度
                for (int i=0;i<shoppingCartList.size();i++){
                    //改变商品值的状态
                    shoppingCartList.get(i).setIscheckd(((ViewHolder)holder).shopcb.isChecked());

                }
                //通知
                EventBus.getDefault().post(0);
                //刷新适配器
                goodsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox shopcb;
        private TextView shopname;
        private RecyclerView shoprcv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopcb=itemView.findViewById(R.id.shopcb);
            shopname=itemView.findViewById(R.id.shopname);
            shoprcv=itemView.findViewById(R.id.shoprcv);
        }
    }
}
