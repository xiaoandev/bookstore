package example.com.bookstorepractice.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.bookstorepractice.R;
import butterknife.ButterKnife;
import example.com.bookstorepractice.adapter.NormalFragmentAdapter;

public class NormalActivity extends AppCompatActivity {

    //菜单标题
    private final int[] MENU_TITLE = new int[]{R.string.menu_msg, R.string.menu_contact,
                                    R.string.menu_find, R.string.menu_me};
    //菜单图标
    private final int[] MENU_ICON = new int[]{R.drawable.menu_main_msg_selector, R.drawable.menu_main_contact_selector,
                                    R.drawable.menu_main_find_selector, R.drawable.menu_main_me_selector};

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PagerAdapter adapter; //页卡适配器
    private long exitTime; //退出时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initView();
        ButterKnife.bind(this);

        // 初始化页卡
        initPager();
        setTabs(tabLayout, getLayoutInflater(), MENU_TITLE, MENU_ICON);
    }

    /**
     * 设置页卡显示效果
     * @param tabLayout
     * @param inflater
     * @param tabTitles
     * @param tabIcon
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitles,
                         int[] tabIcon) {
        for (int i = 0; i < tabIcon.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            //使用自定义视图，目的是为了便于修改，也可以使用自带的视图
            tab.setCustomView(view);
            ImageView imageTab = (ImageView) view.findViewById(R.id.img_tab);
            imageTab.setImageResource(tabIcon[i]);
            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitles[i]);
            tabLayout.addTab(tab);
        }
    }

    private void initPager() {
        adapter = new NormalFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                    && event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
            //重写键盘事件分发，onKeyDown方法在某些情况下捕获不到，只能在这里等
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar snackbar = Snackbar.make(viewPager, "再按一下退出程序", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundResource(R.color.colorPrimary);
                snackbar.show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}