package com.bw.erzhoumoni.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.erzhoumoni.R;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:12:26
 *@Description:
 * */public class GoodsView extends LinearLayout implements View.OnClickListener {

    private TextView jia;
    private TextView conutview;
    private TextView jian;
    private String conut;

    public GoodsView(Context context) {
        super(context);
        initView(context);
    }

    public GoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    //定义一个传值的方法
    public void setConut(int c){
        conutview.setText(c+"");
    }
        //自定义布局方法
    private void initView(Context context) {
        //找布局
        View view = View.inflate(context, R.layout.view_item, null);
        jia = view.findViewById(R.id.jia);
        conutview = view.findViewById(R.id.conutview);
        jian = view.findViewById(R.id.jian);
        //给加减设置点击事件
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        //添加布局
      addView(view);
    }

    @Override
    public void onClick(View v) {
        //先拿到数量
        conut = conutview.getText().toString();
        //因为数量返回是string类型所以要给他转型成为int行
        Integer conuts = Integer.valueOf(conut);
        //判断 如果是加那么，就让数量自增  如果是减那么就让数量自减
        switch (v.getId()){
            case R.id.jia:
                conuts++;
                //给控件赋值
                jia.setText(conuts+"");
                //接口回调
                mOnconuts.onconuts(conuts);
                break;
            case R.id.jian:
                conuts--;
                //给控件赋值
                jian.setText(conuts+"");
                //接口回调
                mOnconuts.onconuts(conuts);
                break;
                default:
        }

    }

    private Onconuts mOnconuts;
    public void Onconuts(Onconuts onconuts){
        mOnconuts=onconuts;
    }
    //设置接口回调
    public interface Onconuts{
        void onconuts(int conut);
    }
}
