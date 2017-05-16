package wang.relish.baseadapterhelper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wang.relish.baseadapterhelper.holder.CommonHolder;

/**
 * <h3>封装{@link RecyclerView.Adapter}</h3>
 *
 * @param <T> 数据实体类
 * @author wangxian wangxina@servyou@com.cn
 * @version 1.1 2016-08-29 Adapter功能分离
 * @since 1.0 2016-08-27
 */
public abstract class BaseQuickAdapter<T> extends RecyclerView.Adapter<CommonHolder> {

    protected static final int TYPE_HEADER = -0x4;
    protected static final int TYPE_FOOTER = -0x3;
    protected static final int TYPE_LOADING = -0x2;
    protected static final int TYPE_EMPTY = -0x1;
    protected static final int TYPE_NORMAL = 0x0;


    private OnLoadMoreListener mLoadMoreListener;
    /**
     * 点击事件
     */
    private OnItemClickLister<T> mClickListener;
    /**
     * 长按事件
     */
    private OnItemLongClickListener<T> mLongListener;
    private SpanSizeLookup mSpanSizeLookup;

    /**
     * 被加载数据
     */
    protected List<T> mData;


    private View mHeaderView;
    private View mLoadingView;
    private View mFooterView;
    private View mEmptyView;
    private View mContentView;

    protected boolean hasHeader = false;
    protected boolean hasFooter = false;
    protected boolean mNextLoadEnable = false;

    protected Context mContext;

    protected LayoutInflater mInflater;

    protected int mLayoutResId;

    /**
     * 用于将data中的数据显示在view上
     * <p>
     * <li>1) 从ViewHolder中获取view对象 {@link CommonHolder#getConvertView()}<br>
     * <li>2) 从数据集中获取数据 {@link T}<br>
     * <li>3) 将数据绑定到view上 <br>
     *
     * @param holder   ViewHolder
     * @param data     数据实体
     * @param position data的索引
     */
    public abstract void convert(CommonHolder holder, T data, int position, int itemType);


    /**
     * 构造方法
     *
     * @param context  上下文
     * @param data     数据
     * @param layoutId {@link BaseQuickAdapter#TYPE_NORMAL}的布局文件id
     */
    public BaseQuickAdapter(Context context, List<T> data, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mData = data;
        mLayoutResId = layoutId;
        mNextLoadEnable = false;
    }

    public BaseQuickAdapter(Context context, View contentView, List<T> data) {
        this(context, data, 0);
        mContentView = contentView;
        mNextLoadEnable = false;
    }

    /**
     * @param context 上下文
     * @param data    数据
     * @see BaseMultiItemAdapter#BaseMultiItemAdapter(Context, List)
     */
    protected BaseQuickAdapter(Context context, List<T> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mData = data;
        mNextLoadEnable = false;
    }


    /**
     * 更新数据，适用于下拉刷新
     *
     * @param data 待加载数据
     */
    public void updateData(List<T> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        } else {
            mData.clear();
        }
        addData(data);
    }

    /**
     * 移除单条数据
     *
     * @param d 单条数据
     */
    public void removeData(T d) {
        mData.remove(d);
    }

    /**
     * 移除单条数据
     *
     * @param i 数据索引
     */
    public void removeData(int i) {
        mData.remove(i);
    }

    /**
     * 添加数据（用于分页加载）
     *
     * @param data 待加载数据
     */
    public void addData(List<T> data) {
        if (data == null || data.size() == 0) {
            mData = new ArrayList<>();
        } else {
            mData.addAll(data);
        }
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonHolder holder;
        switch (viewType) {
            case TYPE_EMPTY:
                holder = new CommonHolder(mEmptyView);
                break;
            case TYPE_HEADER:
                holder = new CommonHolder(mHeaderView);
                break;
            case TYPE_LOADING:
                if (mLoadingView == null) {
                    if (mFooterView == null) {
                        holder = onCreateDefaultViewHolder(parent, viewType);
                    } else {
                        holder = new CommonHolder(mFooterView);
                    }
                } else {
                    holder = new CommonHolder(mLoadingView);
                }
                break;
            case TYPE_FOOTER:
                holder = new CommonHolder(mFooterView);
                break;
            default://NORMAL or other type
                holder = onCreateDefaultViewHolder(parent, viewType);
        }
        return holder;
    }

    protected CommonHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType) {
        return createCommonHolder(parent, mLayoutResId);
    }

    protected CommonHolder createCommonHolder(ViewGroup parent, int layoutResId) {
        if (mContentView == null) {
            return new CommonHolder(getItemView(layoutResId, parent));
        }
        return new CommonHolder(mContentView);
    }

    private View getItemView(int layoutResId, ViewGroup parent) {
        return mInflater.inflate(layoutResId, parent, false);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Override it in {@link BaseMultiItemAdapter#getDefaultViewType(int)}
     *
     * @param position 位置索引
     * @return TYPE
     */
    protected int getDefaultViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, final int position) {
        int viewType = holder.getItemViewType();//getItemViewType(position);
        switch (viewType) {
            case TYPE_LOADING:
                addLoadMore(holder);
            case TYPE_HEADER:
                break;
            case TYPE_FOOTER:
                break;
            case TYPE_EMPTY:
                break;
            default:
                convert(holder,
                        mData.get(position - (hasHeader ? 1 : 0)),
                        position - (hasHeader ? 1 : 0),
                        getItemViewType(position));
                final T data = mData.get(position - (hasHeader ? 1 : 0));
                //点击事件
                holder.getConvertView().setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (mClickListener != null)
                                    mClickListener.onClick(BaseQuickAdapter.this, view, data, position);
                            }
                        });
                //长按事件
                holder.getConvertView().setOnLongClickListener(
                        new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                return mLongListener != null &&
                                        mLongListener.onLongClick(BaseQuickAdapter.this, view, data, position);
                            }
                        });
                break;
        }
    }

    private void addLoadMore(@SuppressWarnings("unused") CommonHolder holder) {
        if (mLoadMoreListener == null) return;
        mLoadMoreListener.onLoadMore(!mNextLoadEnable);
    }

    public void loadComplete() {
        mNextLoadEnable = false;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData == null || mData.size() == 0) {
            return TYPE_EMPTY;
        }
        if (position == 0) {//大坑啊
            return hasHeader ? TYPE_HEADER : getDefaultViewType(position);
        } else if (position == mData.size()) {
            if (hasHeader()) {
                return getDefaultViewType(position);
            } else {
                return mNextLoadEnable ? TYPE_LOADING : TYPE_FOOTER;
            }
        } else if (position == mData.size() + 1) {
            return mNextLoadEnable ? TYPE_LOADING : TYPE_FOOTER;
        } else {
            return getDefaultViewType(position);
        }
    }


    @Override
    public int getItemCount() {
        if (mData == null || mData.size() == 0) {
            return 1;
        }
        return mData.size() + (hasHeader() ? 1 : 0) + (hasFooter() ? 1 : 0);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if (mSpanSizeLookup == null)
                        return (type == TYPE_EMPTY || type == TYPE_HEADER || type == TYPE_FOOTER ||
                                type == TYPE_LOADING) ? gridManager.getSpanCount() : 1;
                    else
                        return (type == TYPE_EMPTY || type == TYPE_HEADER || type == TYPE_FOOTER ||
                                type == TYPE_LOADING) ? gridManager.getSpanCount() :
                                mSpanSizeLookup.getSpanSize(gridManager, position - getHeaderLayoutCount());
                }
            });
        }
//        recyclerView.post(() -> {
//            if (mLoadMoreListener != null) {
//                openLoadMore();
//            }
//        });

    }

    public void openLoadMore() {
        mNextLoadEnable = true;
    }

    private int getHeaderLayoutCount() {
        return hasHeader ? 1 : 0;
    }


    /**
     * 用于getHeaderView.findViewById(int resId);
     *
     * @return {@link BaseQuickAdapter#mHeaderView}
     */
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        hasHeader = true;
        mHeaderView = headerView;
    }

    /**
     * 用于getFooterView.findViewById(int resId);
     *
     * @return {@link BaseQuickAdapter#mFooterView}
     */
    public View getFooterView() {
        return mFooterView;
    }

    public View getLoaingView() {
        return mLoadingView;
    }

    public void setLoadingView(View loadingView) {
        this.mLoadingView = loadingView;
    }

    public void setFooterView(View footerView) {
        hasFooter = true;
        mFooterView = footerView;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    public List<T> getData() {
        return mData;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(boolean isCompleted);
    }

    /**
     * 点击事件
     *
     * @param <T> 数据
     */
    public interface OnItemClickLister<T> {
        /**
         * 点击事件
         *
         * @param v    view
         * @param data 数据
         */
        void onClick(BaseQuickAdapter<T> adapter, View v, T data, int position);
    }

    /**
     * 长按事件
     *
     * @param <T> 数据
     */
    public interface OnItemLongClickListener<T> {

        /**
         * 长按事件
         *
         * @param v    view
         * @param data 数据
         * @return 是否点击
         */
        boolean onLongClick(BaseQuickAdapter<T> adapter, View v, T data, int position);
    }

    public void setOnItemClickListener(OnItemClickLister<T> listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        mLongListener = listener;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int position);
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }

    public void setSpanSizeLookup(SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }
}
