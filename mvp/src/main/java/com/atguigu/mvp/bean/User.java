package com.atguigu.mvp.bean;

/**
 * Created by 李金桐 on 2017/4/1.
 * QQ: 474297694
 * 功能: xxxx
 */

import java.io.Serializable;

/**
 * 用户数据
 */
public class User implements Serializable{

    private int status;
    private String message;
    private BodyEntity body;
    private long timestamp;
    private Object exception;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BodyEntity getBody() {
        return body;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public static class BodyEntity implements Serializable{
        /**
         * user : {"id":12,"createTime":null,"updateTime":null,"enableFlag":0,"username":"afu","password":"123456","phone":"18601042258","imgurl":null,"createTimeString":"","updateTimeString":""}
         */

        private UserEntity user;

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public static class UserEntity implements Serializable{
            /**
             * id : 12
             * createTime : null
             * updateTime : null
             * enableFlag : 0
             * username : afu
             * password : 123456
             * phone : 18601042258
             * imgurl : null
             * createTimeString :
             * updateTimeString :
             */

            private int id;
            private Object createTime;
            private Object updateTime;
            private int enableFlag;
            private String username;
            private String password;
            private String phone;
            private Object imgurl;
            private String createTimeString;
            private String updateTimeString;

            @Override
            public String toString() {
                return "UserEntity{" +
                        "id=" + id +
                        ", createTime=" + createTime +
                        ", updateTime=" + updateTime +
                        ", enableFlag=" + enableFlag +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", phone='" + phone + '\'' +
                        ", imgurl=" + imgurl +
                        ", createTimeString='" + createTimeString + '\'' +
                        ", updateTimeString='" + updateTimeString + '\'' +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getEnableFlag() {
                return enableFlag;
            }

            public void setEnableFlag(int enableFlag) {
                this.enableFlag = enableFlag;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getImgurl() {
                return imgurl;
            }

            public void setImgurl(Object imgurl) {
                this.imgurl = imgurl;
            }

            public String getCreateTimeString() {
                return createTimeString;
            }

            public void setCreateTimeString(String createTimeString) {
                this.createTimeString = createTimeString;
            }

            public String getUpdateTimeString() {
                return updateTimeString;
            }

            public void setUpdateTimeString(String updateTimeString) {
                this.updateTimeString = updateTimeString;
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", body=" + body +
                ", timestamp=" + timestamp +
                ", exception=" + exception +
                '}';
    }
}
