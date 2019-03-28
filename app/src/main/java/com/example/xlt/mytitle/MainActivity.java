package com.example.xlt.mytitle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private SearchView mSearchView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private String[] titles = new String[]{"本地音乐", "网络曲库", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment_1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(),
                fragments);
        //设定适配器
        ViewPager vp =  findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("本地音乐"));
        tabLayout.addTab(tabLayout.newTab().setText("网络曲库"));
        tabLayout.addTab(tabLayout.newTab().setText("我的"));
        tabLayout.setupWithViewPager(vp);
    }

    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //通过MenuItem得到SearchView
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        tabLayout =  findViewById(R.id.tablayout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "您点击了搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "你点击了关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "你点击了分享", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

}