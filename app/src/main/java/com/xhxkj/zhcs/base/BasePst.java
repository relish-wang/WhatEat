package com.xhxkj.zhcs.base;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class BasePst<V extends BaseView> {

    private WeakReference<V> mViewReference;

    /**
     * 获得当前Presenter通过{@link #attachView(BaseView)}所关联的View实例，
     * 当所关联的View被回收时返回null（例如activity退栈），所以在使用时务必对其返回值进行判空
     * @return 当前presenter所关联的View实例
     */
    @Nullable
    public V getView() {
        if (mViewReference != null) {
            return mViewReference.get();
        }
        return null;
    }

    public void attachView(V view) {
        mViewReference = new WeakReference<>(view);
    }

    public void detachView() {
        mViewReference.clear();
    }
}
