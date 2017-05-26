package wang.relish.whateat.seller.task;

import android.os.AsyncTask;

import java.util.List;

import wang.relish.whateat.seller.bean.Good;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GetGoodsTask extends AsyncTask<Void,Void,List<Good>>{

    EasyCallback<List<Good>> callback;

    public GetGoodsTask(EasyCallback<List<Good>> callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.onBefore();
    }

    @Override
    protected List<Good> doInBackground(Void... params) {
        return Good.findAll(Good.class);
    }

    @Override
    protected void onPostExecute(List<Good> goods) {
        super.onPostExecute(goods);
        if(goods==null||goods.size()==0){
            callback.onFailed("暂无数据");
        }else{
            callback.onSuccess(goods);
        }
    }
}
