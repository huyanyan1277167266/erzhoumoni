package com.bw.erzhoumoni.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.View.GoodsView;
import com.bw.erzhoumoni.bean.ShopBean;
import com.bw.erzhoumoni.bean.ShoppingCartListBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:12:02
 *@Description:
 * */
public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ShoppingCartListBean> list;

    public GoodsAdapter(Context context, List<ShoppingCartListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.goods_itme, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).goodscb.setChecked(list.get(position).getIscheckd());
        Glide.with(context).load(list.get(position).getPic()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).name.setText(list.get(position).getCommodityName());
        ((ViewHolder)holder).goodsprice.setText("￥"+list.get(position).getPrice());
        //自定义view
        ((ViewHolder)holder).gv.setConut(list.get(position).getCount());
        //接收从view回调过来的加减数据
        ((ViewHolder)holder).gv.Onconuts(new GoodsView.Onconuts() {
            @Override
            public void onconuts(int conut) {
              //将集合里的conut字段改变
                list.get(position).setCount(conut);
                //通知
                EventBus.getDefault().post(0);
            }

        });
        //给 checkbox写监听事件
        ((ViewHolder)holder).goodscb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //改变集合
                list.get(position).setIscheckd(isChecked);
                //更新ui 通知
                EventBus.getDefault().post(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox goodscb;
        private ImageView iv;
        private TextView name;
        private TextView goodsprice;
        private GoodsView gv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodscb=itemView.findViewById(R.id.goodscb);
            iv=itemView.findViewById(R.id.iv);
            name=itemView.findViewById(R.id.name);
            goodsprice=itemView.findViewById(R.id.goodsprice);
            gv=itemView.findViewById(R.id.gv);
        }
    }

}
