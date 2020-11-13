package example.com.bookstorepractice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import example.com.bookstorepractice.fragment.ContactFragment;
import example.com.bookstorepractice.fragment.FindFragment;
import example.com.bookstorepractice.fragment.MeFragment;
import example.com.bookstorepractice.fragment.MsgFragment;

public class NormalFragmentAdapter extends FragmentPagerAdapter {
    public NormalFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new MsgFragment();
                break;
            case 1:
                fragment = new ContactFragment();
                break;
            case 2:
                fragment = new FindFragment();
                break;
            case 3:
                fragment = new MeFragment();
                break;
            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
