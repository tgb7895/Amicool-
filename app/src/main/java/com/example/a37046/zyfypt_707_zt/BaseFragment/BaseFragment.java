package com.example.a37046.zyfypt_707_zt.BaseFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
    /**
     * 与sharedpreferences保存的关键字一致
     */
    private final String KEY_SESSION_ID = "sessionID";
    /**
     * 与sharedpreferences保存的关键字一致
     */
    private final String KEY_USERNAME = "name";
    /**
     * 与sharedpreferences的文件名一致
     */
    private final String FILE = "login";
    private final int MODE = Context.MODE_PRIVATE;


    private SharedPreferences sharedPreferences;
    protected Context context;

    /**
     * Fragment生命周期方法
     * @param context 上下文
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE, MODE);
    }

    /**
     * @return 返回sessionid
     */
    protected String getSessionId(){
        return sharedPreferences.getString(KEY_SESSION_ID, "");
    }
    /**
     *
     * @return 返回username
     */
    protected String getUserName(){
        return sharedPreferences.getString(KEY_USERNAME, "");
    }
}
