package com.bw.erzhoumoni.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.erzhoumoni.R;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:18:07
 *@Description:
 * */public class CreateOrderView extends LinearLayout implements View.OnClickListener {

    private TextView orderview_jian;
    private TextView orderview_count;
    private TextView orderview_jia;
    private String orderview_counts;

    public CreateOrderView(Context context) {
        super(context);
        initView(context);
    }

    public CreateOrderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
//定义一个传值的方法
    public void setOrderView(int b){
        if (b<1){
            return;
        }
        orderview_count.setText(String.valueOf(b));
    }
    private void initView(Context context) {
        View view = View.inflate(context, R.layout.createorder_view, null);
        orderview_jian = view.findViewById(R.id.orderview_jian);
        orderview_count = view.findViewById(R.id.orderview_count);
        orderview_jia = view.findViewById(R.id.orderview_jia);

        //设置加减点击事件
        orderview_jian.setOnClickListener(this);
        orderview_jia.setOnClickListener(this);
        //添加布局
        addView(view);
    }

    @Override
    public void onClick(View v) {
        //定义数量为0
        int count=0;

        orderview_counts = this.orderview_count.getText().toString();
        try {
            //转型并赋值
            count=Integer.valueOf(orderview_counts);
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (v.getId()){
            case R.id.jian:
                if (count<=1){
                    return;
                }
                --count;
                orderview_jian.setText(String.valueOf(count));
                //接口回调
                monCreateOrderView.onCreateOrderView(count);
                break;
            case R.id.jia:
                ++count;
                orderview_jia.setText(String.valueOf(count));
                monCreateOrderView.onCreateOrderView(count);
                break;
                default:
        }

    }
    private OnCreateOrderView monCreateOrderView;
    public void OnCreateOrderView(OnCreateOrderView onCreateOrderView){
        monCreateOrderView=onCreateOrderView;
    }
    //创建接口
    public interface OnCreateOrderView{
        void onCreateOrderView(int orderviewcount);
    }
}
