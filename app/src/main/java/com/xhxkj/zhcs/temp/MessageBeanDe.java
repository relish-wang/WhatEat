package com.xhxkj.zhcs.temp;

/**
 * 一条聊天记录实体
 *
 * Created by 鑫 on 2015/10/10.
 * @deprecated
 */
public class MessageBeanDe {
    private boolean isMe;
    private String message;
    private String name;

    public MessageBeanDe(Builder builder){
        this.isMe = builder.isMe;
        this.message = builder.message;
        this.name = builder.name;
    }

    public static class Builder{

        private boolean isMe;
        private String message;
        private String name;

        public Builder setIsMe(boolean isMe) {
            this.isMe = isMe;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public MessageBeanDe build(){
            return new MessageBeanDe(this);
        }

    }

    public boolean isMe() {
        return isMe;
    }

    public void setIsMe(boolean isMe) {
        this.isMe = isMe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
