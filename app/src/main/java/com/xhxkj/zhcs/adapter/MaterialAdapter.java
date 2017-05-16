package com.xhxkj.zhcs.adapter;

import android.view.View;
import android.widget.EditText;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.MaterialEntity;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 菜谱材料适配器
 * <p/>
 * Created by r3lish on 2016/2/29.
 */
public class MaterialAdapter extends BaseHolderAdapter<MaterialAdapter.ViewHolder, MaterialEntity> {


    public MaterialAdapter(BaseAty context, ArrayList<MaterialEntity> mData) {
        super(context, mData);
    }

    @Override
    protected int itemLayoutId() {
        return R.layout.lv_item_material;
    }

    @Override
    protected void setItemData(ViewHolder holder, MaterialEntity material, int position) {
        holder.etName.setText(material.getName());
        holder.etWeight.setText(material.getWeight());
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder {
        @Bind(R.id.etName)
        EditText etName;
        @Bind(R.id.etWeight)
        EditText etWeight;

        public ViewHolder(View view) {
            super(view);
        }
    }
}