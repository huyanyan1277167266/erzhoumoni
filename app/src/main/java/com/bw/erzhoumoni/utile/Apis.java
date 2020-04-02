package com.bw.erzhoumoni.utile;

import com.bw.erzhoumoni.bean.ConfirmBean;
import com.bw.erzhoumoni.bean.CreateOrderBean;
import com.bw.erzhoumoni.bean.LogBean;
import com.bw.erzhoumoni.bean.OrderListByBean;
import com.bw.erzhoumoni.bean.PayBean;
import com.bw.erzhoumoni.bean.ReceiveBean;
import com.bw.erzhoumoni.bean.ShopBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:10:10
 *@Description:
 * */
public interface Apis {
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<LogBean>doLog(@Field("phone")String phone,@Field("pwd")String pwd);

    @GET("order/verify/v1/findShoppingCart")
    Observable<ShopBean>doShop(@Header("userId")int userId,@Header("sessionId")String sessionId);
    //创建订单
    @POST("order/verify/v1/createOrder")
    @FormUrlEncoded
    Observable<CreateOrderBean>doOrder(@Header("userId")int userId, @Header("sessionId")String sessionId,
                                       @Field("orderInfo")String orderInfo, @Field("totalPrice")double totalPrice, @Field("addressId") int addressId );
    //获取地址
    @GET("user/verify/v1/receiveAddressList")
    Observable<ReceiveBean>doReceive(@Header("userId")int userId,@Header("sessionId")String sessionId);
//2、根据订单状态查询订单信息
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<OrderListByBean>doOrderBy(@Header("userId")int userId, @Header("sessionId")String sessionId,
                                         @Query("status")int status,@Query("page")int page,@Query("count")int count);
//支付
    @POST("order/verify/v1/pay")
    Observable<PayBean>doPay(@Header("userId")int userId,@Header("sessionId")String sessionId,
                             @Field("orderId")String orderId,@Field("payType")int payType);
    //收货
    @PUT("order/verify/v1/confirmReceipt")
    Observable<ConfirmBean>doConfirm(@Header("userId")int userId,@Header("sessionId")String sessionId,@Field("orderId")String orderId);

}
