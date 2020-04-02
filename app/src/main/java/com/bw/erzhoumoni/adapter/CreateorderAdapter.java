package com.bw.erzhoumoni.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.View.CreateOrderView;
import com.bw.erzhoumoni.activity.CreateOrderActivity;
import com.bw.erzhoumoni.bean.ShoppingCartListBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:17:55
 *@Description:
 * */public class CreateorderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ShoppingCartListBean> listBeans = new ArrayList<>();
    Context context;
    ArrayList<ShoppingCartListBean> list;

    public CreateorderAdapter(Context context, ArrayList<ShoppingCartListBean> list) {
        this.context = context;
        this.list = list;
    }



    public void setData(ArrayList<ShoppingCartListBean> shoppingCartListBeanList){
        listBeans=shoppingCartListBeanList;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.createorder_itme, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getPic()).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(((ViewHolder)holder).order_iv);
        ((ViewHolder)holder).order_name_adapter.setText(list.get(position).getCommodityName());
        ((ViewHolder)holder).order_price_adapter.setText("￥"+list.get(position).getPrice());
        ((ViewHolder)holder).v.setOrderView(list.get(position).getCount());
        ((ViewHolder)holder).v.OnCreateOrderView(new CreateOrderView.OnCreateOrderView() {
            @Override
            public void onCreateOrderView(int orderviewcount) {
                list.get(position).setCount(orderviewcount);
                EventBus.getDefault().post(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView order_iv;
        private TextView order_name_adapter;
        private TextView order_price_adapter;
        private CreateOrderView v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_iv=itemView.findViewById(R.id.order_iv);
            order_name_adapter=itemView.findViewById(R.id.order_name_adapter);
            order_price_adapter=itemView.findViewById(R.id.order_price_adapter);
            v=itemView.findViewById(R.id.v);
        }
    }
}
