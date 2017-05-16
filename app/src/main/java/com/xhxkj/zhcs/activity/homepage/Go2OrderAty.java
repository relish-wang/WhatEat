package com.xhxkj.zhcs.activity.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.temp.MessageBeanDe;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *主界面-主页-我要点菜
 *
 * @author 魏一凡
 */
public class Go2OrderAty extends BaseAty {

    @Bind(R.id.lvChat)
    ListView lvChat;

    @Bind(R.id.etMessage)
    EditText etMessage;

    ChatAdapter adapter;

    @Override
    protected int layoutResId() {
        return R.layout.aty_go2_order;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.go_2_order));
    }

    @Override
    protected void initViews() {
        adapter = new ChatAdapter(this, TempData.getMessages());
        lvChat.setAdapter(adapter);
    }


    @OnClick(R.id.btnSend)
    public void btnSend() {
        String message = etMessage.getText().toString();
        TempData.setMessages(message);
        //initViews();
        adapter.notifyDataSetChanged();
        etMessage.setText(null);
    }

    /**
     * 聊天记录适配器
     */
    class ChatAdapter extends BaseAdapter {

        ArrayList<MessageBeanDe> mData;
        Context context;
        LayoutInflater inflater;

        public ChatAdapter(Context context, ArrayList<MessageBeanDe> mData) {
            this.mData = mData;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.lv_item_chat, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tvPersonName.setText(mData.get(position).getName() + ":");
            holder.tvMessage.setText(mData.get(position).getMessage());
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.layoutMsg.getLayoutParams();
            params.addRule(mData.get(position).isMe() ? RelativeLayout.ALIGN_PARENT_RIGHT : RelativeLayout.ALIGN_PARENT_LEFT);
            holder.layoutMsg.setLayoutParams(params);
            return view;
        }

        class ViewHolder {
            @Bind(R.id.layoutMessage)
            LinearLayout layoutMsg;
            @Bind(R.id.tvPersonName)
            TextView tvPersonName;
            @Bind(R.id.tvMessage)
            TextView tvMessage;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
