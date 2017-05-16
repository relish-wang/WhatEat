package wang.relish.baseadapterhelper.holder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * <h3>通用ViewHolder</h3>
 *
 * @author wangxina wangxina@servyou.com.cn v1.1
 * @author HuangBing huangb@servyou.com.cn v1.0
 * @version 1.1 2016-8-27
 * @since 1.0 2015-7-30
 */
public class CommonHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;

    private View convertView;

    public CommonHolder(View itemView) {
        super(itemView);
        convertView = itemView;
        views = new SparseArray<>();
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param txt    The text to put in the text view.
     * @return The CommonHolder for chaining.
     */
    public CommonHolder setText(int viewId, CharSequence txt) {
        TextView tv = getView(viewId);
        tv.setText(txt);
        return this;
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param strId  The id of text to put in the text view.
     * @return The CommonHolder for chaining.
     */
    public CommonHolder setText(int viewId, int strId) {
        TextView tv = getView(viewId);
        tv.setText(strId);
        return this;
    }

    /**
     * Will set the image of an ImageView from a bitmap.
     *
     * @param viewId The view id.
     * @param bm     The image bitmap.
     * @return The CommonHolder for chaining.
     */
    public CommonHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }

    /**
     * Will set the image of an ImageView from resId.
     *
     * @param viewId The view id.
     * @param resId  The id of image
     * @return The CommonHolder for chaining.
     */
    public CommonHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * Will set the {@link android.view.View.OnClickListener} of a View from resId.
     *
     * @param viewId   The view id
     * @param listener onClickListener
     * @return The CommonHolder for chaining.
     */
    public CommonHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        ImageView iv = getView(viewId);
        iv.setOnClickListener(listener);
        return this;
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId R.id.viewId
     * @return view
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public CommonHolder setBackgroundColor(int viewId, int color) {
        View v = getView(viewId);
        v.setBackgroundColor(color);
        return this;
    }


    public View getConvertView() {
        return convertView;
    }
}
