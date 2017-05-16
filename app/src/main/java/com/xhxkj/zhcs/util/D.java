package com.xhxkj.zhcs.util;

/**
 * 数据库字段表
 *
 * @author 王鑫
 */
public final class D {
    /**
     * 用户信息表
     */
    public static final class user_info {
        /**
         * 主键 不为空 由服务器传入
         */
        public static final String id = "id";
        /**
         * 不为空
         */
        public static final String name = "name";
        /**
         * 不为空
         */
        public static final String pwd = "pwd";
        /**
         * 不为空
         */
        public static final String tel = "tel";
    }

    /**
     * 地址表
     */
    public static final class address {
        /**
         * 主键 自增长 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 收货人姓名
         */
        public static final String name = "name";
        /**
         * 不为空 收货人电话
         */
        public static final String tel = "tel";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
        /**
         * 不为空 收货地址
         */
        public static final String address = "address";
    }

    public static final class join {

        /**
         * 主键 自增长 不为空 表示用户加入的家庭组
         */
        public static final String id = "id";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
        /**
         * 允许空 在家庭组里的昵称
         */
        public static final String nickname = "nickname";
        /**
         * 外键 不为空 连接family表
         */
        public static final String family_id = "family_id";
    }

    public static final class family {
        /**
         * 主键 自增长 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 家庭组名称
         */
        public static final String name = "name";
    }

    public static final class msg {
        /**
         * 主键 自增长 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 聊天内容
         */
        public static final String chat_content = "chat_content";
        /**
         * 不为空 发送日期
         */
        public static final String date = "date";
        /**
         * 外键 不为空 发送人ID
         */
        public static final String user_id = "user_id";
        /**
         * 外键 不为空 家庭组ID
         */
        public static final String family_id = "family_id";
    }

    public static final class shop {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 商铺名
         */
        public static final String name = "name";
        /**
         * 不为空 商铺类型（0是杂货铺，1是超市）
         */
        public static final String type = "type";
        /**
         * 允许空 销售总量
         */
        public static final String sale_count = "sale_count";
        /**
         * 允许空 平均评价分
         */
        public static final String comment = "comment";
        /**
         * 允许空 评价总数
         */
        public static final String comment_count = "comment_count";
        /**
         * 允许空 店铺位置的经度
         */
        public static final String lng = "lng";
        /**
         * 允许空 店铺位置的纬度
         */
        public static final String lat = "lat";
    }

    public static final class good {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 货物名
         */
        public static final String name = "name";
        /**
         * 不为空 单位
         */
        public static final String unit = "unit";
        /**
         * 不为空 价格
         */
        public static final String value = "value";
        /**
         * 允许空 平均评价
         */
        public static final String comment = "comment";
        /**
         * 允许空 评价总数
         */
        public static final String comment_count = "comment_count";
    }

    public static final class cart_item {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 购买数量
         */
        public static final String num = "num";
        /**
         * 外键 不为空 连接good表
         */
        public static final String good_id = "good_id";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
    }

    public static final class order {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 不为空 货物名
         */
        public static final String name = "name";
        /**
         * 不为空 价格
         */
        public static final String value = "value";
        /**
         * 允许空 平均评价
         */
        public static final String comment = "comment";
        /**
         * 外键 允许空 连接buyer表
         */
        public static final String buyer_id = "buyer_id";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
        /**
         * 不为空 订单状态
         */
        public static final String status = "status";
    }

    public static final class good_details {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 外键 不为空 连接good表
         */
        public static final String good_id = "good_id";
        /**
         * 外键 不为空 连接order表
         */
        public static final String order_id = "order_id";
        /**
         * 不为空 购买时的菜品单价
         */
        public static final String buyer_value = "buyer_value";
    }

    public static final class buy_count {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 外键 不为空 连接good表
         */
        public static final String good_id = "good_id";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
        /**
         * 不为空
         */
        public static final String count = "count";
    }

    public static final class buyer {
        /**
         * 主键 不为空 买手id
         */
        public static final String id = "id";
        /**
         * 不为空 买手姓名
         */
        public static final String name = "name";
        /**
         * 不为空
         */
        public static final String tel = "tel";
        /**
         * 不为空
         */
        public static final String address = "address";
        /**
         * 默认值0 不为空
         */
        public static final String buy_count = "buy_count";
        /**
         * 允许空 平均评价
         */
        public static final String comment = "comment";
        /**
         * 允许空 评价总数
         */
        public static final String comment_count = "comment_count";
    }

    public static final class collect_buyer {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 外键 不为空 连接buyer表
         */
        public static final String buyer_id = "buyer_id";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
    }

    public static final class collect_shop {
        /**
         * 主键 不为空
         */
        public static final String id = "id";
        /**
         * 外键 不为空 连接shop表
         */
        public static final String shop_id = "shop_id";
        /**
         * 外键 不为空 连接user_info表
         */
        public static final String user_id = "user_id";
    }
}
