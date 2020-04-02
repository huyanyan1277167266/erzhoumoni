package com.bw.erzhoumoni.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:cln
 *@Date: 2020/3/29
 *@Time:12:36
 *@Description:商品
 * */
@Entity
public class ShoppingCartListBean implements Parcelable {
    @Id
    private int commodityId;
    private String commodityName;
    private int count;
    private String pic;
    private double price;
    private String shopName;

    protected ShoppingCartListBean(Parcel in) {
        commodityId = in.readInt();
        commodityName = in.readString();
        count = in.readInt();
        pic = in.readString();
        price = in.readDouble();
        shopName = in.readString();
        ischeckd = in.readByte() != 0;
    }

    public static final Creator<ShoppingCartListBean> CREATOR = new Creator<ShoppingCartListBean>() {
        @Override
        public ShoppingCartListBean createFromParcel(Parcel in) {
            return new ShoppingCartListBean(in);
        }

        @Override
        public ShoppingCartListBean[] newArray(int size) {
            return new ShoppingCartListBean[size];
        }
    };

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    //给商品设置默认未点击
    @Transient
    private boolean ischeckd=false;
    @Generated(hash = 314782951)
    public ShoppingCartListBean(int commodityId, String commodityName, int count,
            String pic, double price, String shopName) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.count = count;
        this.pic = pic;
        this.price = price;
        this.shopName = shopName;
    }

    @Generated(hash = 248192423)
    public ShoppingCartListBean() {
    }

    public boolean getIscheckd() {
        return ischeckd;
    }

    public void setIscheckd(boolean ischeckd) {
        this.ischeckd = ischeckd;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(commodityId);
        dest.writeString(commodityName);
        dest.writeInt(count);
        dest.writeString(pic);
        dest.writeDouble(price);
        dest.writeString(shopName);
        dest.writeByte((byte) (ischeckd ? 1 : 0));
    }
}
