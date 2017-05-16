package wang.relish.baseadapterhelper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import wang.relish.baseadapterhelper.entity.IViewType;
import wang.relish.baseadapterhelper.holder.CommonHolder;

/**
 * 多viewType
 */
@SuppressWarnings("unchecked")
public abstract class BaseMultiItemAdapter<T extends IViewType> extends BaseQuickAdapter {

    /**
     * itemType与layoutId的映射
     */
    protected HashMap<Integer, Integer> layouts;

    public BaseMultiItemAdapter(Context context, List<T> data) {
        super(context, data);
    }

    /**
     * 构造方法
     *
     * @param context  上下文
     * @param data     数据
     * @param layoutId {@link BaseQuickAdapter#TYPE_NORMAL}的布局文件id
     */
    private BaseMultiItemAdapter(Context context, List<T> data, int layoutId) {
        super(context, data, layoutId);
    }

    public void setDefaultViewTypeLayout(@LayoutRes int layoutId) {
        addLayout(BaseQuickAdapter.TYPE_NORMAL, layoutId);
    }

    @Override
    protected int getDefaultViewType(int position) {
        Object data = mData.get(position);
        Log.d("viewType", "viewType = " + data.getClass());
        if (data instanceof IViewType) {
            return ((IViewType) data).getViewType();
        }
        return BaseQuickAdapter.TYPE_NORMAL;
    }

    /**
     * 添加新的Item类型，及布局ID
     * <p>
     * <font color=red><strong>注：itemType应大于0</strong></font>
     * <li>1) {@link BaseQuickAdapter#TYPE_HEADER} = -0x4<br></li>
     * <li>2) {@link BaseQuickAdapter#TYPE_FOOTER} = -0x3<br></li>
     * <li>2) {@link BaseQuickAdapter#TYPE_LOADING} = -0x2<br></li>
     * <li>3) {@link BaseQuickAdapter#TYPE_EMPTY} = -0x1<br></li>
     * <li>5) {@link BaseQuickAdapter#TYPE_NORMAL} = 0x0<br></li>
     *
     * @param viewType viewType
     * @param layoutId layoutId
     * @see BaseQuickAdapter#TYPE_HEADER
     * @see BaseQuickAdapter#TYPE_FOOTER
     * @see BaseQuickAdapter#TYPE_LOADING
     * @see BaseQuickAdapter#TYPE_EMPTY
     * @see BaseQuickAdapter#TYPE_NORMAL
     */
    public void addLayout(int viewType, int layoutId) {
        if (layouts == null) {
            layouts = new HashMap();
        }
        layouts.put(viewType, layoutId);
    }

    @Override
    protected CommonHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType) {
        return createCommonHolder(parent, layouts.get(viewType));
    }

    @Override
    public void convert(CommonHolder holder, Object data, int position, int itemType) {
        convert(holder, (T) data, position, itemType);
    }

    protected abstract void convert(CommonHolder holder, T data, int position, int itemType);
}
