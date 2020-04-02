package com.bw.erzhoumoni.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.bw.erzhoumoni.R;
import com.bw.erzhoumoni.base.BaseActivity;
import com.bw.erzhoumoni.base.BasePresenter;
import com.bw.erzhoumoni.fragment.FragmentFive;
import com.bw.erzhoumoni.fragment.FragmentFor;
import com.bw.erzhoumoni.fragment.FragmentThree;
import com.bw.erzhoumoni.fragment.FragmentTwo;
import com.bw.erzhoumoni.fragment.Fragmentshop;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class FragmentActivity extends BaseActivity {
@BindView(R.id.tab)
    TabLayout tab;
@BindView(R.id.vp)
    ViewPager vp;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_fragment2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
       tabs.add("全部");
        tabs.add("待付款");
        tabs.add("待发货");
        tabs.add("待收货");
        tabs.add("待评价");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));
        tab.addTab(tab.newTab().setText(tabs.get(4)));

        Fragmentshop fragmentshop = new Fragmentshop();
        FragmentTwo fragmentTwo = new FragmentTwo();
        FragmentThree fragmentThree = new FragmentThree();
        FragmentFor fragmentFor = new FragmentFor();
        FragmentFive fragmentFive = new FragmentFive();
        list.add(fragmentshop);
        list.add(fragmentTwo);
        list.add(fragmentThree);
        list.add(fragmentFor);
        list.add(fragmentFive);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        tab.setupWithViewPager(vp);

    }
    public class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
