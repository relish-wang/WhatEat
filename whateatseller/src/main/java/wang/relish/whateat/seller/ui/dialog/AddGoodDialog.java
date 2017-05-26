package wang.relish.whateat.seller.ui.dialog;

import android.support.constraint.solver.Goal;

import wang.relish.ugo.base.BaseDialog;
import wang.relish.whateat.seller.R;
import wang.relish.whateat.seller.bean.Good;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AddGoodDialog extends BaseDialog<Good>{

    public static AddGoodDialog newInstance(){
        return new AddGoodDialog();
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog_add_good;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected Good generateT() {
        return null;
    }
}
