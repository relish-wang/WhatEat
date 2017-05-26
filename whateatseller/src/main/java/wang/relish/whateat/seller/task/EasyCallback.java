package wang.relish.whateat.seller.task;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface EasyCallback<T> {

    void onBefore();

    void onSuccess(T t);

    void onFailed(String message);
}
