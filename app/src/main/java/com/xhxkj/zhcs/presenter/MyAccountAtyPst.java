package com.xhxkj.zhcs.presenter;

import com.xhxkj.zhcs.base.BasePst;
import com.xhxkj.zhcs.network.LogoutRequest;
import com.xhxkj.zhcs.util.AppLog;
import com.xhxkj.zhcs.vm.MyAccountAtyView;

/**
 * 我的账号
 *
 * @author 王鑫 on 2015/12/3.
 */
public class MyAccountAtyPst extends BasePst<MyAccountAtyView> {

    /**
     * 退出登录，清除缓存
     */
    public void logout(String sessionId) {

        LogoutRequest request = new LogoutRequest(sessionId);
        request.request();

        MyAccountAtyView view = getView();
        if (view != null) {
            AppLog.e("MyAccountAtyPst", "logout", "onLogoutSuccess");
            view.onLogoutSuccess();
        }
    }
}
