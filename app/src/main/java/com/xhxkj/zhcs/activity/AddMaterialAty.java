package com.xhxkj.zhcs.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 主界面-首页-私房菜单-添加菜单-添加原料种类-添加原料
 *
 * @author 魏一凡
 */
public class AddMaterialAty extends BaseAty {


    @Bind(R.id.lvMaterials)
    ListView lvMaterials;

    @Override
    protected int layoutResId() {
        return R.layout.aty_add_material;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.add_material));//TODO the name is intent carried in
    }

    @Override
    protected void initViews() {
        MaterialAdapter adapter = new MaterialAdapter(TempData.getFoods());
        lvMaterials.setAdapter(adapter);
    }

    class MaterialAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> materials;

        public MaterialAdapter(ArrayList<String> materials) {
            this.inflater = getLayoutInflater();
            this.materials = materials;
        }

        @Override
        public int getCount() {
            return materials.size();
        }

        @Override
        public Object getItem(int i) {
            return materials.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.lv_item_material, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            return view;
        }

        class ViewHolder extends BaseViewHolder {


            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
