package com.bw.erzhoumoni.bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:11:20
 *@Description:
 * */public class ShopBean {

    /**
     * result : [{"categoryName":"美妆护肤","shoppingCartList":[{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/4/1.jpg","price":39}]},{"categoryName":"女鞋","shoppingCartList":[{"commodityId":20,"commodityName":"环球 冬季休闲加绒保暖棉靴 韩版百搭学生小白鞋女士高帮棉鞋","count":15,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/3/1.jpg","price":78},{"commodityId":18,"commodityName":"白色经典 秋季新款简约百搭轻便休闲女鞋板鞋小白鞋","count":10,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/1/1.jpg","price":79}]}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


}
