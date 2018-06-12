package com.example.a37046.zyfypt_707_zt.activities;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a37046.zyfypt_707_zt.BaseFragment.BaseFragment;
import com.example.a37046.zyfypt_707_zt.BaseFragment.BottomNavigationViewHelper;
import com.example.a37046.zyfypt_707_zt.R;
import com.example.a37046.zyfypt_707_zt.fragments.BlogFragment;
import com.example.a37046.zyfypt_707_zt.fragments.KeyNoteFragment;
import com.example.a37046.zyfypt_707_zt.fragments.MyDataFragment;
import com.example.a37046.zyfypt_707_zt.fragments.SampleFragment;
import com.example.a37046.zyfypt_707_zt.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//  ┃　　　┃   神兽保佑　　　　　　　　
//  ┃　　　┃   代码无BUG！
//  ┃　　　┗━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationViewb;
    ImageView menu;



    private SharedPreferences sp;
    private String sessionID = "";

    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private BottomNavigationView navigationView;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitc();//侧滑
        initFragmentList();//初始化FragmentList
        initViewPager();//初始化ViewPager
        initBottomNV();//初始化BottomNavigationView
    }

    private void intitc() {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationViewb = (NavigationView) findViewById(R.id.nav);
        menu= (ImageView) findViewById(R.id.main_menu);
        View headerView = navigationViewb.getHeaderView(0);//获取头布局
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationViewb)){
                    drawerLayout.closeDrawer(navigationViewb);
                }else{
                    drawerLayout.openDrawer(navigationViewb);
                }
            }
        });
        navigationViewb.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationViewb);
                return true;
            }
        });
    }


    private void initFragmentList() {
        // 将fragment加载到list中
        fragmentList = new ArrayList<>();
        fragmentList.add(new BlogFragment());
        fragmentList.add(new KeyNoteFragment());
        fragmentList.add(new VideoFragment());
        fragmentList.add(new SampleFragment());
        fragmentList.add(new MyDataFragment());

    }

    private void initViewPager() {
        //实例化viewpager
        viewPager = findViewById(R.id.viewpager);
        //实例化FragmentPagerAdapter
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        //设置viewpager的适配器
        viewPager.setAdapter(fragmentPagerAdapter);
        //设置viewpager的页面切换事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                System.out.println("---position=" + position);
                switch (position) {
                    case 0:
                        navigationView.setSelectedItemId(R.id.blog);
                        break;
                    case 1:
                        navigationView.setSelectedItemId(R.id.keynote);
                        break;
                    case 2:
                        navigationView.setSelectedItemId(R.id.video);
                        break;
                    case 3:
                        navigationView.setSelectedItemId(R.id.sample);
                        break;
                    case 4:
                        navigationView.setSelectedItemId(R.id.mydata);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initBottomNV() {
        //实例化BottomNavigationView
        navigationView = findViewById(R.id.bottomnv);
        //设置BottomNavigationView切换事件
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = 0;
                switch (item.getItemId()) {
                    case R.id.blog:
                        id = 0;
                        break;
                    case R.id.keynote:
                        id = 1;
                        break;
                    case R.id.video:
                        id = 2;
                        break;
                    case R.id.sample:
                        id = 3;
                        break;
                    case R.id.mydata:
                        id = 4;
                        break;
                    default:
                        break;
                }
//                System.out.println("---id=" + id);
                viewPager.setCurrentItem(id);
                return true;
            }
        });
        //设置底部导航栏显示效果
        BottomNavigationViewHelper.disableShiftMode(navigationView);
    }
}
