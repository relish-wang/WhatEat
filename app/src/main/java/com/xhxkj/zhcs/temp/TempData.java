package com.xhxkj.zhcs.temp;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.entity.FoodEntity;
import com.xhxkj.zhcs.entity.MarketEntity;
import com.xhxkj.zhcs.entity.MaterialEntity;
import com.xhxkj.zhcs.entity.OrderEntity;
import com.xhxkj.zhcs.entity.RecipeEntity;
import com.xhxkj.zhcs.util.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 临时数据，之后再删
 *
 * @author 王鑫
 *         Created by 王鑫 on 2015/10/9.
 */
public class TempData {

    public static ArrayList<MessageBeanDe> messageEntityArrayList = new ArrayList<>();

    public static ArrayList<HashMap<String, Object>> addMenuList = new ArrayList<>();


    public TempData() {
        messageEntityArrayList.add(new MessageBeanDe.Builder().setIsMe(false).setName("A").setMessage("我要吃番茄牛肉").build());
        messageEntityArrayList.add(new MessageBeanDe.Builder().setIsMe(false).setName("B").setMessage("我要吃红烧茄子").build());
        messageEntityArrayList.add(new MessageBeanDe.Builder().setIsMe(true).setName("C").setMessage("今天晚上吃的清淡一点").build());
        messageEntityArrayList.add(new MessageBeanDe.Builder().setIsMe(false).setName("A").setMessage("好的").build());
    }

    /**
     * 私房菜单
     *
     * @return ArrayList<RecipeEntity> 菜单
     */
    public static ArrayList<RecipeEntity> getPrivateMenus() {
        return getRecipes();
    }

    public static ArrayList<RecipeEntity> setPrivateMenus(String name) {
        //// TODO: 2016/2/27  
        return getRecipes();
    }


    /**
     * 我要点菜
     *
     * @return ArrayList<MessageBean> 聊天记录
     */
    public static ArrayList<MessageBeanDe> getMessages() {
//        return new ArrayList<MessageBeanDe>() {
//            {
//                //A：我要吃番茄牛肉
//                add(new MessageBeanDe.Builder().setIsMe(false).setName("A").setMessage("我要吃番茄牛肉").build());
//                //B：我要吃红烧茄子
//                add(new MessageBeanDe.Builder().setIsMe(false).setName("B").setMessage("我要吃红烧茄子").build());
//                //C：今天晚上吃的清淡一点
//                add(new MessageBeanDe.Builder().setIsMe(true).setName("C").setMessage("今天晚上吃的清淡一点").build());
//                //A：好的
//                add(new MessageBeanDe.Builder().setIsMe(false).setName("A").setMessage("好的").build());
//            }
//        };
        return messageEntityArrayList;
    }

    /**
     * 我要点菜
     *
     * @return ArrayList<MessageBean> 聊天记录 插入信息
     */
    public static ArrayList<MessageBeanDe> setMessages(String msg) {

        messageEntityArrayList.add(new MessageBeanDe.Builder().setIsMe(true).setName("C").setMessage(msg).build());

        return messageEntityArrayList;
    }

    /**
     * 添加菜单 魏一凡
     *
     * @return ArrayList HashMap String, Object 菜单
     */
    public static ArrayList<HashMap<String, Object>> getAddMenu() {
        return addMenuList;
    }

    public static ArrayList<HashMap<String, Object>> setAddMenu(String name, String weight) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("good_name", name);
        map.put("good_weight", weight);
        addMenuList.add(map);
        return addMenuList;
    }

    /**
     * 购物车界面（fragment）
     *
     * @return ArrayList 菜名
     */
    public static ArrayList<HashMap<String, Object>> getShoppingCart() {
        String[] s = {"白菜", "芹菜", "萝卜", "小青菜", "茄子"};
        ArrayList<HashMap<String, Object>> mData = new ArrayList<>();
        for (String s1 : s) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("good_name", s1);
            map.put("good_weight", "500g");
            mData.add(map);
        }
        return mData;
    }

    /**
     * 附近菜场
     *
     * @return ArrayList 菜场相关信息
     */
    public static ArrayList<ShopBeanDe> getStores() {
        return new ArrayList<ShopBeanDe>() {
            {
                add(new ShopBeanDe("海天菜市", 500, 5, 4.5));
                for (int i = 0; i < 4; i++) {
                    add(new ShopBeanDe("星湾蔬菜店", i + 500, 5, 4.5));
                }
            }
        };
    }


    /**
     * Market
     *
     * @return ArrayList 相关信息
     */
    public static ArrayList<MarketEntity> getMarkets() {
        return new ArrayList<MarketEntity>() {
            {
                add(new MarketEntity(R.mipmap.temp_market_icon1, "昇果园", 500.0, 5, 4.5));
                add(new MarketEntity(R.mipmap.temp_market_icon2, "鲜蔬园", 1500.0, 5, 4.5));
            }
        };
    }

    /**
     * 菜谱材料
     *
     * @return ArrayList<MaterialEntity> 菜谱材料
     */
    public static ArrayList<MaterialEntity> getMaterials() {
        return new ArrayList<MaterialEntity>() {
            {
                add(new MaterialEntity("猪肉", "500g"));
                add(new MaterialEntity("鸡蛋", "2个"));
                add(new MaterialEntity("生姜", "2片"));
                add(new MaterialEntity("大蒜", "少许"));
            }
        };
    }

    /**
     * 家庭组
     *
     * @return ArrayList<MemberBean> 家庭组成员
     */
    public static ArrayList<MemberBeanDe> getMembers() {
        return new ArrayList<MemberBeanDe>() {
            {
                add(new MemberBeanDe(R.mipmap.ic_launcher, "二姑"));
                add(new MemberBeanDe(R.mipmap.ic_launcher, "表姨"));
                add(new MemberBeanDe(R.mipmap.ic_launcher, "大舅"));
                add(new MemberBeanDe(R.mipmap.ic_launcher, "隔壁老王"));
            }
        };
    }

    /**
     * 获得买手信息
     *
     * @return buyers ArrayList<>
     */
    public static ArrayList<BuyerBean> getBuyers() {
        return new ArrayList<BuyerBean>() {
            {//String name, String tel, String address, Integer buyCount,
                //     Integer commentCount
                Random r = new Random();
                add(new BuyerBean("张三", "13512341234", "白杨街道334号", 3, 4, r.nextBoolean(), 3.5));
                add(new BuyerBean("李四", "13512341233", "青松街道432号", 3, 4, r.nextBoolean(), 3.5));
                add(new BuyerBean("王五", "13512341234", "白杨街道334号", 3, 4, r.nextBoolean(), 3.5));
                add(new BuyerBean("景一", "13512341233", "青松街道432号", 3, 4, r.nextBoolean(), 3.5));
                add(new BuyerBean("阿鹏", "13512341234", "白杨街道334号", 3, 4, r.nextBoolean(), 3.5));
                add(new BuyerBean("小刘", "13512341233", "青松街道432号", 3, 4, r.nextBoolean(), 3.5));
            }
        };
    }

    /**
     * 获得收藏买手信息
     *
     * @return buyers ArrayList<>
     */
    public static ArrayList<BuyerBean> getStaredBuyers() {
        return new ArrayList<BuyerBean>() {
            {
                add(new BuyerBean("阿鹏", "13512341234", "白杨街道334号", 3, 4, Boolean.TRUE, 3.5));
                add(new BuyerBean("小刘", "13512341233", "青松街道432号", 3, 4, Boolean.TRUE, 3.5));
            }
        };
    }


    /**
     * 获得摊位信息
     *
     * @return booths ArrayList<HashMap<String, Object>>
     */
    public static ArrayList<HashMap<String, Object>> getBooths() {
        ArrayList<HashMap<String, Object>> booths = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("booth_name", "C1" + i + "摊位");
            map.put("price", "2.50元/500g");
            map.put("statistics", "总销售52单，平均评分4.2分，购买过3次");
            booths.add(map);
        }
        return booths;
    }

    /**
     * 家庭组
     *
     * @return ArrayList<MemberBean> 家庭组成员
     */
    public static ArrayList<MemberBeanDe> getFamilyMembers() {
        return new ArrayList<MemberBeanDe>() {
            {
                add(new MemberBeanDe("老婆", "13100001111"));
                add(new MemberBeanDe("儿子", "13185051234"));
                add(new MemberBeanDe("老妈", "13185051234"));
            }
        };
    }

    /**
     * 订单
     *
     * @return
     */
    public static ArrayList<OrderEntity> getOrders() {
        return new ArrayList<OrderEntity>() {
            {
                add(new OrderEntity(TimeUtils.longToTimeFormat(System.currentTimeMillis()), "3.5", "3", "1", "海天蔬菜水果店", "白菜、牛肉等"));
                add(new OrderEntity(TimeUtils.longToTimeFormat(System.currentTimeMillis() - 1000 * 60 * 59 * 24 - 78), "3.5", "3", "1", "海天麻辣龙虾店", "小龙虾等"));
                add(new OrderEntity(TimeUtils.longToTimeFormat(System.currentTimeMillis() - 1000 * 60 * 59 * 24 - 78), "3.5", "3", "1", "西门菜市海鲜店", "大闸蟹、大白鲨等"));

            }
        };
    }

    /**
     * 商品
     *
     * @return
     */
    public static ArrayList<GoodBean> getGoods() {
        return new ArrayList<GoodBean>() {
            {
                //shop name unit value
                add(new GoodBean(new ShopBean("海天蔬菜水果店", 0), "癞蛤蟆", "10.00", 15.00));
                add(new GoodBean(new ShopBean("海天蔬菜水果店", 1), "大蛤蟆", "10.00", 15.00));
                add(new GoodBean(new ShopBean("海天蔬菜水果店", 0), "小蛤蟆", "10.00", 15.00));
                add(new GoodBean(new ShopBean("海天蔬菜水果店", 1), "癞蛤蟆", "10.00", 15.00));
                add(new GoodBean(new ShopBean("海天蔬菜水果店", 0), "癞蛤蟆", "10.00", 15.00));
            }
        };
    }

    /**
     * 收货地址
     *
     * @return
     */
    public static ArrayList<String> getAddresses() {
        return new ArrayList<String>() {
            {
                add("浙江省杭州市海天城某某小区");
                add("浙江省杭州市海天城某某小区");
                add("浙江省杭州市海天城某某小区");
            }
        };
    }

    public static ArrayList<String> getFoods() {
        return new ArrayList<String>() {
            {
                add("白菜");
                add("芹菜");
                add("萝卜");
                add("小青菜");
                add("茄子");
            }
        };
    }

    public static ArrayList<String> getFoodDetails() {
        return new ArrayList<String>() {
            {
                add("白菜");
                add("芹菜");
                add("萝卜");
                add("小青菜");
                add("茄子");
            }
        };
    }

    public static ArrayList<FoodEntity> getDishes() {
        return new ArrayList<FoodEntity>() {
            {
                add(new FoodEntity(R.mipmap.ic_launcher, "白菜"));
                add(new FoodEntity(R.mipmap.ic_launcher, "芹菜"));
                add(new FoodEntity(R.mipmap.ic_launcher, "萝卜"));
                add(new FoodEntity(R.mipmap.ic_launcher, "小青菜"));
                add(new FoodEntity(R.mipmap.ic_launcher, "茄子"));
            }
        };
    }


    public static ArrayList<RecipeEntity> getDrafts() {
        return new ArrayList<RecipeEntity>() {
            {
                add(new RecipeEntity(R.mipmap.temp_dish5,
                        "番茄虾仁意面",
                        "快手菜系列~现在做吃的追求的是用尽量少的食材做出一样的美味！\n" +
                                "简单好吃是我的信条！(●°v°●)\u200B 」",
                        new ArrayList<MaterialEntity>() {{
                            add(new MaterialEntity("番茄", "2个"));
                            add(new MaterialEntity("虾仁", "半碗"));
                            add(new MaterialEntity("意面", "一盘"));
                            add(new MaterialEntity("蒜", "适量"));
                            add(new MaterialEntity("橄榄油", "一勺"));
                            add(new MaterialEntity("盐", "适量"));
                            add(new MaterialEntity("胡椒", "适量"));
                            add(new MaterialEntity("欧芹", "3根"));
                        }},
                        new ArrayList<String>() {
                            {
                                add("烧一锅水，煮沸后放入少许盐，下意面炖煮");
                                add("煮意面的时候可以准备其他食材：蒜切碎、虾仁开县焯熟，番茄切小块");
                                add("意面一般煮10-15分钟，口感从有韧性到比较绵软。祝好的意面捞起来仿在一边备用");
                                add("热锅，倒一点油，炒香蒜末后下番茄。边炒边用锅铲压番茄，把汁水压出来~然后下虾仁，翻炒一阵后，下意面");
                                add("尽量让意面过上番茄~出锅前撒上盐、呼叫和欧芹碎调味即可~");
                            }
                        }, 0.0, 0, "yujing", "2016-02-11"));
            }
        };
    }

    /**
     * 发现餐桌
     *
     * @return ArrayList<String> 菜名
     */
    public static ArrayList<RecipeEntity> getRecipes() {
        return new ArrayList<RecipeEntity>() {{
            add(new RecipeEntity(R.mipmap.temp_dish1,
                    "根本停不下来的糖醋排骨",
                    "糖醋排骨，一道既家常，又可以在小伙伴们的聚会上登得了台面的菜~因为我每次做这道菜给小伙伴们吃，他们吃完都只有一句话“根本停不下来！” \n" +
                            "\n" +
                            "为什么会有停不下来的感觉？因为使用了黄金比例的糖和醋，包裹着充盈的油脂，肉的口感软硬兼施恰到好处，爱不释手甜而不腻，就像保持一点点距离的爱情。\n" +
                            "\n" +
                            "而且这道菜，很简单！既不用先炒糖色，也不用炸，直接上手( ¯ᒡ̱¯ )و",
                    new ArrayList<MaterialEntity>() {{
                        add(new MaterialEntity("猪肋排", "500g"));
                        add(new MaterialEntity("盐", "3g"));
                        add(new MaterialEntity("料酒", "1勺（15ml的勺）"));
                        add(new MaterialEntity("酱油", "2勺（30ml）"));
                        add(new MaterialEntity("冰糖", "3勺（45ml）"));
                        add(new MaterialEntity("白醋", "4勺（60ml）"));
                        add(new MaterialEntity("八角", "1个"));
                    }},
                    new ArrayList<String>() {
                        {
                            add("首先！排骨要买得好，买得妙，买得呱呱叫！肥瘦均匀招人喜欢，就像一个身材匀称的女孩人人爱~食材买得不好菜就逊了一半…要是你用炖汤的大排骨来做这道菜…就像让个高大壮硕威猛的女人去当平面模特…\n" +
                                    "\n" +
                                    "所以，买一字排来做糖醋排骨就很很适合了，轻松无压力，自在不塞牙~");
                            add("李安的《饮食男女》里有句台词“人生不像做菜，要等所有材料备齐才下锅。”  \n" +
                                    "\n" +
                                    "做菜就是要把所有材料备齐才开火啊~不然手忙脚乱锅铲掉地酱油打翻，整个没眼看。");
                            add("烧一锅水，沸腾之后把排骨放进去滚一滚，当做给它洗礼有没有，以此去掉血水和肉腥。");
                            add("捞起滤干水，蓄势待炒！");
                            add("锅里倒点点点儿油，把排骨倒进去翻炒一下，别小手一抖就油倒多，因为本身排骨油脂已经够丰富，你给点阳光它就灿烂了的，炒到颜色金黄就OK了~\n" +
                                    "\n" +
                                    "注意不要炒太久，不然口感会很老~");
                            add("现在，到了团结就是力量的时刻！前面所提到的黄金比例，就是按照比例把之前准备好的调料一起加入炒好的排骨里。\n" +
                                    "\n" +
                                    "模特身材的黄金比例是九头身，那糖醋排骨调料的黄金比例是多少呢？来，喊口号！一，二，三，四，一二三四！排排队站好！1：2：3：4，也就是1勺料酒，2勺酱油，3勺糖，4勺醋。");

                            add("先放1勺料酒，再放2勺酱油，3勺糖，4勺醋。勺是普通的汤匙，大概一勺是15ml，如果你要用大大的汤瓢，那……那我也没办法，口味可以随个人风格变化。");
                            add("我习惯是用冰糖，因为冰糖的味道比普通白糖更立体一点。\n" +
                                    "而且炒出来的糖色晶晶亮，心飞扬~调料都放完之后，就翻拌均匀。");
                            add("随后加入开水，加到差不多没过排骨的高度。为什么加开水？因为死猪不怕开水烫啊 | ू•ૅω•́)ᵎᵎᵎ（呸 是因为加冷水会让肉立刻收缩变硬！！！！");
                            add("再加一小颗八角进去一起焖，气味会更香~\n" +
                                    "这道菜需要一小点的耐心，加了水之后，要开小火焖大概20多分钟，盖上锅盖，心急吃不了好排骨。\n" +
                                    "\n" +
                                    "焖的时候火千万别太大，不然水一下就干了，烧成一盆黑炭的时候，恭喜你成为了黑暗料理的主角。");
                            add("水焖到还剩三分之一，这时候就可以开大火收汁了。收汁的时候要不停翻拌以免粘锅~排骨颜色好不好看，就看这一刻了！大概翻拌个一两分钟，再加点点盐进去。排骨就可以出锅了~如果喜欢颜色再深点，可以加点老抽，排骨天乐~");
                            add("不需要任何ps，颜色就很美了啦~");
                            add("搞掂出碟上桌！配上一碗洁白饱满的米饭，好开胃哒~");
                        }
                    },
                    9.1, 243, "笑货吃货", "2016-01-31"));
            add(new RecipeEntity(R.mipmap.temp_dish2,
                    "蒜香蒸茄子",
                    "简单，蒸米饭的时候顺便就把茄子给蒸了，饭好茄子也好了，期间可以准备其他菜，茄子放在最后，泼上料汁就可以了。",
                    new ArrayList<MaterialEntity>() {{
                        add(new MaterialEntity("茄子", "4-5根"));
                        add(new MaterialEntity("大蒜", "适量"));
                        add(new MaterialEntity("生姜", "适量"));
                        add(new MaterialEntity("青椒", "适量"));
                        add(new MaterialEntity("生抽", "小半碗"));
                        add(new MaterialEntity("料酒", "一勺(吃饭用的主餐勺)"));
                        add(new MaterialEntity("鱼露", "十来滴"));
                        add(new MaterialEntity("白砂糖", "半勺"));
                    }},
                    new ArrayList<String>() {
                        {
                            add("茄子切5至6厘米的长段，隔水蒸熟，取出放凉一些(手能承受的温度)，撕成条状备用，准备料汁。");
                            add("大蒜，生姜，青椒切成碎末，尽量碎一点。");
                            add("准备一个小碗，倒小半碗生抽，一勺料酒(吃饭用的主餐勺)，十来滴鱼露，半勺白砂糖(我调的程度是只有一丁点甜味)");
                            add("热锅倒油，油热入生姜末翻炒出香味，倒入刚才调好的料汁，料汁冒泡关火，倒入大蒜末青椒末拌匀，浇在茄子上就好了。");
                            add("简单吧，满屋子都是香味。");
                        }
                    }, 8.6, 16, "味儿蕾", "2016-01-25"));
            add(new RecipeEntity(R.mipmap.temp_dish3,
                    "超级好吃的家庭自制【香卤牛肉】",
                    "口味一流的家庭自制【香卤牛肉】 \n" +
                            "卤牛肉最好用毽子肉，也叫“黄瓜条”，即牛前后腿上的肌肉，有肉膜包裹的，藏筋，硬度适中，纹路规则，吃起来特别筋道耐嚼、横着切开有漂亮的花纹，最适合卤味。",
                    new ArrayList<MaterialEntity>() {{
                        add(new MaterialEntity("牛腱肉", "3斤"));
                        add(new MaterialEntity("桂皮", "3段"));
                        add(new MaterialEntity("草寇", "3个"));
                        add(new MaterialEntity("老姜", "一大片"));
                        add(new MaterialEntity("白芷", "3片"));
                        add(new MaterialEntity("干辣椒", "若干"));
                        add(new MaterialEntity("小茴香", "一小把"));
                        add(new MaterialEntity("花椒", "一小把"));
                        add(new MaterialEntity("八角", "4颗"));
                        add(new MaterialEntity("草果", "3颗"));
                        add(new MaterialEntity("香叶", "3片"));
                        add(new MaterialEntity("葱白", "一块"));
                        add(new MaterialEntity("生姜", "一块"));
                        add(new MaterialEntity("蒜头", "半个"));
                        add(new MaterialEntity("冰糖", "7粒"));
                        add(new MaterialEntity("绍兴料酒", "200ml"));
                        add(new MaterialEntity("黄豆酱油", "100ml"));
                        add(new MaterialEntity("老抽", "50ml"));
                        add(new MaterialEntity("盐", "2大勺"));
                        add(new MaterialEntity("味精", "少量"));
                    }},
                    new ArrayList<String>() {
                        {
                            add("牛腱肉三斤。");
                            add("桂皮3段、草寇3个、老姜一大片、白芷3片、干辣椒若干、小茴香和花椒一小把、八角4颗、草果3颗、香叶3片");
                            add("葱白、生姜一块、半头蒜、冰糖7粒");
                            add("绍兴料酒200ml、黄豆酱油100ml、老抽50ml、盐两大勺、味精");
                            add("牛毽肉冷水下锅。");
                            add("烧开后7、8分钟，去除表面的杂质和血沫。");
                            add("捞出，用清水冲洗干净。");
                            add("香料用纱布包成小包，或装入调料盒。");
                            add("重新换一锅清水，把葱、姜、蒜和香料包放入。");
                            add("调入200ml料酒、100ml生抽、50ml老抽、冰糖，放入牛腱肉。");
                            add("大火滚煮30分钟。");
                            add("然后将牛肉转入电炖锅中。煲1个小时，牛肉八分熟时，放入适量盐。（也可在炖煮过程中放几片干山楂片，即可去腥，也可使牛肉熟的更快。");
                            add("卤好的牛肉，在卤水汁中浸泡1个小时以上，牛肉更加入味好吃。");
                            add("放凉切片即食。上桌时，可根据个人喜好调配蘸料，醋加生抽，再来点儿蒜末，我喜欢在调料里少加点白糖就是天下至味啦，当然放点辣椒油就更更……美味了。");
                            add("卤过牛肉的卤汁用笊篱蓖去杂质、油脂和香料，放凉后，盖严放冰箱冷冻室保存即可。");
                        }
                    }, 8.6, 10, "璐稔", "2015-12-21"));
            add(new RecipeEntity(R.mipmap.temp_dish4,
                    "卤三鸡【鸡腿/鸡翅/鸡蛋】",
                    "三鸡其实是鸡腿、鸡翅和鸡蛋的统称！\n" +
                            "\n" +
                            "有那么一段时间，\n" +
                            "特别馋各种三鸡的吃法，\n" +
                            "从卤、烤、蒸、炸、煎…等等，\n" +
                            "在三鸡的路上，\n" +
                            "我也算是资深吃客了...",
                    new ArrayList<MaterialEntity>() {{
                        add(new MaterialEntity("鸡翅", "8只"));
                        add(new MaterialEntity("葱段", "6段"));
                        add(new MaterialEntity("八角", "3个"));
                        add(new MaterialEntity("花椒", "6g"));
                        add(new MaterialEntity("辣椒", "1个"));
                        add(new MaterialEntity("姜片", "2片"));
                        add(new MaterialEntity("生抽", "6汤勺"));
                        add(new MaterialEntity("老抽", "3汤勺"));
                        add(new MaterialEntity("陈醋", "2汤勺"));
                        add(new MaterialEntity("蚝油", "1汤勺"));
                        add(new MaterialEntity("冰糖", "1汤勺"));
                        add(new MaterialEntity("盐（调整用）", "半汤勺"));
                    }},
                    new ArrayList<String>() {
                        {
                            add("鸡翅洗干净灼水，起锅用急自来水冲30秒，沥水备用；鸡蛋水煮至熟，剥壳备用");
                            add("准备卤水配料，辣椒需要切断");
                            add("鸡翅、鸡蛋以及卤水配料放小奶锅，先加淹没鸡翅鸡蛋一半的清水");
                            add("加生抽6汤勺(由于锅具大小和生抽品牌不同，建议先添加4汤勺)、老抽3汤勺(由于锅具大小和生抽品牌不同，建议先添加2汤勺)、陈醋2汤勺、耗油1汤勺、冰糖1汤勺、盐半汤勺(可不加)，加完所有调料应该刚好覆盖了鸡翅鸡蛋，如果还未覆盖，请添加清水至完全覆盖");
                            add("大火煮10分钟转中小火煮20分钟，开盖大火收汁就可以了。留点汁料在卤三鸡下面更赞哦（鉴于大家使用的锅具大小不同，建议在出锅前，试下咸度，试下咸度，试下咸度，便于做些调整。）\n" +
                                    "\n" +
                                    "PS：请一定细心看心得，尤其是出锅前试下咸度非常必要！");
                            add("最后简单摆盘就可以吃大餐啦~");
                        }
                    }, 8.9, 10, "康姑娘", "2015-11-26"));
        }};
    }
}
//new ArrayList<String>() {
//{
//        add("烧一锅水，煮沸后放入少许盐，下意面炖煮");
//        add("煮意面的时候可以准备其他食材：蒜切碎、虾仁开县焯熟，番茄切小块");
//        add("意面一般煮10-15分钟，口感从有韧性到比较绵软。祝好的意面捞起来仿在一边备用");
//        add("热锅，倒一点油，炒香蒜末后下番茄。边炒边用锅铲压番茄，把汁水压出来~然后下虾仁，翻炒一阵后，下意面");
//        add("尽量让意面过上番茄~出锅前撒上盐、呼叫和欧芹碎调味即可~");
//        }
//        }