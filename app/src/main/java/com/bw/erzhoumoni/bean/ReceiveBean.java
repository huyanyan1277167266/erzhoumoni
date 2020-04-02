package com.bw.erzhoumoni.bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/4/1
 *@Time:17:14
 *@Description:
 * */public class ReceiveBean {


    /**
     * result : [{"address":"上海市松江区嘉兴小区","createTime":1585711383000,"id":6301,"phone":"18864411238","realName":"会飞的猪","userId":33330,"whetherDefault":1,"zipCode":"000000"},{"address":"上海市松江区嘉兴小区","createTime":1585844764000,"id":6404,"phone":"18864411238","realName":"会飞的猪","userId":33330,"whetherDefault":2,"zipCode":"000000"},{"address":"上海市松江区嘉兴小区","createTime":1585844943000,"id":6405,"phone":"18864411238","realName":"会飞的猪","userId":33330,"whetherDefault":2,"zipCode":"000000"}]
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

    public static class ResultBean {
        /**
         * address : 上海市松江区嘉兴小区
         * createTime : 1585711383000
         * id : 6301
         * phone : 18864411238
         * realName : 会飞的猪
         * userId : 33330
         * whetherDefault : 1
         * zipCode : 000000
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
