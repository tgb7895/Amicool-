package com.example.a37046.zyfypt_707_zt.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.fragments.videofragment.NewVideoFragment;
import com.example.a37046.zyfypt_707_zt.fragments.videofragment.PagerSlidingTabStrip;
import com.example.a37046.zyfypt_707_zt.fragments.videofragment.SpecialVideoFragment;

public class VideoFragment extends BaseFragment {
    //获取当前屏幕的密度
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagersliding;
    private ViewPager viewpager;
    private NewVideoFragment newVideoFragment = null;
    private SpecialVideoFragment specialVideoFragment = null;

    public VideoFragment() {
    }
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f3--" + getSessionId());
        dm = getResources().getDisplayMetrics();//获取屏幕密度
        viewpager = (ViewPager) view.findViewById(R.id.fragment_video_view_pager);
        viewpager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        pagersliding = (PagerSlidingTabStrip) view.findViewById(R.id.fragment_video_strip);
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
        private String[] titles = {"最新", "专题"};//显示在二级导航上的标题文字
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
                    if (newVideoFragment == null) {
                        newVideoFragment = new NewVideoFragment();
                    }
                    return newVideoFragment;
                case 1:
                    if (specialVideoFragment == null) {
                        specialVideoFragment = new SpecialVideoFragment();
                    }
                    return specialVideoFragment;
                default:
                    return null;
            }
        }
    }

}
