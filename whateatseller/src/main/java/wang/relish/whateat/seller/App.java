package wang.relish.whateat.seller;

import org.litepal.LitePal;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class App extends wang.relish.ugo.App{

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
