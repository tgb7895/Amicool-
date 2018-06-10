package com.example.a37046.zyfypt_707_zt.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.fragments.CollectFragment;
import com.example.a37046.zyfypt_707_zt.fragments.ConcernFragment;
import com.example.a37046.zyfypt_707_zt.fragments.videofragment.PagerSlidingTabStrip;

public class ConcernActivity extends AppCompatActivity {
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagersliding;
    private ViewPager viewpager;

    private int userId;

    ConcernFragment video=null;
    ConcernFragment keyNote=null;
    ConcernFragment blog=null;
    ConcernFragment cases=null;
    ConcernFragment items=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern);
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        userId=Integer.parseInt(user_id);
        dm = getResources().getDisplayMetrics();//获取屏幕密度
        viewpager = findViewById(R.id.fragment_concern_view_pager);
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pagersliding = findViewById(R.id.fragment_concern_strip);
        pagersliding.setViewPager(viewpager);
        setpagerstyle();//设置PagerSlidingTabStrip显示效果
    }


    private void setpagerstyle() {
        pagersliding.setShouldExpand(true); // 设置Tab是自动填充满屏幕的
        pagersliding.setDividerColor(Color.TRANSPARENT); // 设置Tab的分割线是透明的
        // 设置Tab底部线的高度
        pagersliding.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        pagersliding.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        pagersliding.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        pagersliding.setIndicatorColor(Color.parseColor("#45c01a"));// 设置Tab Indicator的颜色
        // 设置选中Tab文字的颜色 (这是自定义的一个方法)
        pagersliding.setSelectedTextColor(Color.parseColor("#45c01a"));
        pagersliding.setTabBackground(0); // 取消点击Tab时的背景色
    }

    //自定义ViewPagerAdapter子类
    private class MyPagerAdapter extends FragmentPagerAdapter {
        private String[] titles = {"文章", "课件","视频","案例","项目"};//显示在二级导航上的标题文字
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];//确定当页导航上文字
        }
        @Override
        public int getCount() {
            return titles.length;//二级导航个数
        }
        @Override
        public Fragment getItem(int position) { //根据位置返回具体某个导航上对应的Fragment
            switch (position) {
                case 0:
                    if (blog == null) {
                        blog = new ConcernFragment();
                        blog.setFlag(0);
                        blog.setUserId(userId);
                    }
                    return blog;
                case 1:
                    if (keyNote == null) {
                        keyNote = new ConcernFragment();
                        keyNote.setFlag(1);
                        keyNote.setUserId(userId);
                    }
                    return keyNote;
                case 2:
                if (video == null) {
                    video = new ConcernFragment();
                    video.setFlag(2);
                    video.setUserId(userId);
                }
                return video;
                case 3:
                    if (cases == null) {
                        cases = new ConcernFragment();
                        cases.setFlag(3);
                        cases.setUserId(userId);
                    }
                    return cases;
                case 4:
                    if (items == null) {
                        items = new ConcernFragment();
                        items.setFlag(4);
                        items.setUserId(userId);
                    }
                    return items;
                default:
                    return null;
            }
        }
    }
}
