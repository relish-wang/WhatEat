package wang.relish.baseadapterhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import wang.relish.baseadapterhelper.holder.CommonHolder;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 多头部多尾部基础适配器
 * Created by Relish on 2016/8/31.
 */
public abstract class BaseMultiHeaderAndFooterAdapter<T> extends BaseQuickAdapter<T> {

    /**
     * 多头部容器
     */
    LinearLayout mHeaderLayout;
    /**
     * 多尾部容器
     */
    LinearLayout mFooterLayout;

    LinearLayout mCopyHeaderLayout = null;
    LinearLayout mCopyFooterLayout = null;


    @SuppressWarnings("unchecked")
    public BaseMultiHeaderAndFooterAdapter(Context context, List data, int layoutId) {
        super(context, data, layoutId);
    }

    @SuppressWarnings("unchecked")
    public BaseMultiHeaderAndFooterAdapter(Context context, View contentView, List data) {
        super(context, contentView, data);
    }

    public LinearLayout getHeaderLayout() {
        return mHeaderLayout;
    }

    public LinearLayout getFooterLayout() {
        return mFooterLayout;
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new CommonHolder(mHeaderLayout);
        } else if (viewType == TYPE_FOOTER) {
            return new CommonHolder(mFooterLayout);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    /**
     * 默认添加在最下方
     *
     * @param header 待添加的HeaderView
     */
    public void addHeaderView(View header) {
        hasHeader = true;
        mHeaderLayout.addView(header, mHeaderLayout.getChildCount() - 1);
    }

    /**
     * 添加当前HeaderView至{@link #mHeaderLayout},且放置在{@link #mHeaderLayout}指定位置
     * 若不设置index或index大于等于child count或index小于0，
     * 默认添加在{@link #mHeaderLayout}最上方 {@link #addHeaderView(View)}.
     *
     * @param header 待添加的HeaderView
     * @param index  当前添加的HeaderView在{@link #mHeaderLayout}的位置
     */
    public void addHeaderView(View header, int index) {
        hasHeader = true;
        if (mHeaderLayout == null) {
            if (mCopyHeaderLayout == null) {
                mHeaderLayout = new LinearLayout(header.getContext());
                mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                mCopyHeaderLayout = mHeaderLayout;
            } else {
                mHeaderLayout = mCopyHeaderLayout;
            }
        }
        index = index >= mHeaderLayout.getChildCount() || index <= 0 ?
                /*mHeaderLayout.getChildCount()*/ -1 : index;
        mHeaderLayout.addView(header, index);
        notifyDataSetChanged();

    }

    /**
     * 添加当前FooterView至{@link #mFooterLayout},且放置在{@link #mFooterLayout}指定位置
     * 若不设置index或index大于等于child count或index小于0，
     * 默认添加在{@link #mFooterLayout}最上方 {@link #addFooterView(View)}.
     *
     * @param footer 待添加的FooterView
     * @param index  当前添加的footer在{@link #mFooterLayout}的位置
     */
    public void addFooterView(View footer, int index) {
        hasFooter = true;
        mNextLoadEnable = false;
        if (mFooterLayout == null) {
            if (mCopyFooterLayout == null) {
                mFooterLayout = new LinearLayout(footer.getContext());
                mFooterLayout.setOrientation(LinearLayout.VERTICAL);
                mFooterLayout.setLayoutParams(
                        new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                mCopyFooterLayout = mFooterLayout;
            } else {
                mFooterLayout = mCopyFooterLayout;
            }
        }
        index = index >= mFooterLayout.getChildCount() || index < 0 ?
                /*mFooterLayout.getChildCount()*/ -1 : index;
        mFooterLayout.addView(footer, index);
        notifyDataSetChanged();
    }

    /**
     * 默认添加在最上方
     *
     * @param footer 待添加的FooterView
     */
    public void addFooterView(View footer) {
        hasFooter = true;
        mFooterLayout.addView(footer, mFooterLayout.getChildCount() - 1);
    }

    /**
     * 从{@link #mHeaderLayout}移除HeaderView.
     * 当{@link #mHeaderLayout}调用{@link LinearLayout#getChildCount() getChildCount()}方法时，
     * 若返回值为0，则将{@link #mHeaderLayout}设为null
     *
     * @param header headerView
     */
    public void removeHeaderView(View header) {
        if (mHeaderLayout == null) return;

        mHeaderLayout.removeView(header);
        if (mHeaderLayout.getChildCount() == 0) {
            mHeaderLayout = null;
        }
        notifyDataSetChanged();
    }

    /**
     * 移除FooterView
     * 当{@link #mFooterLayout}的child count为0时，将{@link #mFooterLayout}设置为null
     *
     * @param footer 待移除的FooterView
     */
    public void removeFooterView(View footer) {
        if (mFooterLayout == null) return;

        mFooterLayout.removeView(footer);
        if (mFooterLayout.getChildCount() == 0) {
            mFooterLayout = null;
        }
        this.notifyDataSetChanged();
    }

    /**
     * 移除所有的HeaderView，并将{@link #mHeaderLayout}设置为null
     */
    public void removeAllHeaderView() {
        if (mHeaderLayout == null) return;

        mHeaderLayout.removeAllViews();
        mHeaderLayout = null;
    }

    /**
     * 移除所有的FooterView，并将{@link #mFooterLayout}设置为null
     */
    public void removeAllFooterView() {
        if (mFooterLayout == null) return;

        mFooterLayout.removeAllViews();
        mFooterLayout = null;
    }


    public void setHeaderLayout(LinearLayout mHeaderLayout) {
        this.mHeaderLayout = mHeaderLayout;
    }


    public void setFooterLayout(LinearLayout mFooterLayout) {
        this.mFooterLayout = mFooterLayout;
    }

    public int getHeaderCount() {
        if (mHeaderLayout == null) {
            return 0;
        } else {
            return mHeaderLayout.getChildCount();
        }
    }

    public int getFooterCount() {
        if (mFooterLayout == null) {
            return 0;
        }else {
            return mFooterLayout.getChildCount();
        }
    }
}
