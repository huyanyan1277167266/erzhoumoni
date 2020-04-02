package com.bw.erzhoumoni.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:cln
 *@Date: 2020/3/29
 *@Time:12:33
 *@Description:商家
 * */
@Entity
public class ResultBean {
     //设一个id字段当做数据库的主键（主键自增唯一）
    @Id(autoincrement = true)
    private Long id;

    //把商家名字作为唯一字段 也就是值唯一
    @Unique
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    //GreenDao默认不支持集合，所以我们的 List 在编译的时候会报错,
    //解决方式是：在创建表的时候，使用 @Transient 这个注解
    //作用是：被标记的字段不会被添加到表中
    @Transient
    private List<ShoppingCartListBean> shoppingCartList;

    public List<ShoppingCartListBean> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    //给商家设置默认未点击,是否被点击，通常情况下我们也不需要存入数据库，这个没什么用，所以也添加 @Transient
    @Transient
    private boolean isShop=false;


    @Generated(hash = 1003647683)
    public ResultBean(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    @Generated(hash = 2137771703)
    public ResultBean() {
    }


    public boolean getShop() {
        return isShop;
    }

    public void setShop(boolean shop) {
        isShop = shop;
    }





    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
