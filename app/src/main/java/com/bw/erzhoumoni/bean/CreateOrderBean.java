package com.bw.erzhoumoni.bean;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:16:37
 *@Description:
 * */public class CreateOrderBean {


    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
