package com.example.admin.appmarket.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.admin.appmarket.R;
import com.example.admin.appmarket.base.BaseActivity;
import com.example.admin.appmarket.base.BaseFragment;
import com.example.admin.appmarket.factory.FragmentFactory;
import com.example.admin.appmarket.util.UIUtils;
import com.example.admin.appmarket.widget.PagerTab;

public class MainActivity extends BaseActivity {

    private PagerTab mTab;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTab = (PagerTab) findViewById(R.id.tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mTab.setViewPager(mViewPager);
        mTab.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //选中Fragment界面的请求网络操作,Fragment工厂类获取Fragment对象
            BaseFragment fragment = FragmentFactory.createFragment(position);
            //做当前选中界面的请求网络操作
            fragment.baseShow();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        private String[] tabNames;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            tabNames = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public Fragment getItem(int position) {
            // 根据不同的索引生成不同的Fragment,Fragment的工厂类,用于缓存以及生成Fragment对象
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        /**
         * 指定指针说明文字方法
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }
}
