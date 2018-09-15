package com.revisao.dh.android.aplicacaorevisao.controler.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    List<String> listFragmentsTitle;

    public FragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> listFragmentsTitle) {
        super(fm);
        this.fragmentList = fragmentList;
        this.listFragmentsTitle = listFragmentsTitle;

    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //Método específico pra usar o tabLayout
    @Override
    public CharSequence getPageTitle(int position){
        return  listFragmentsTitle.get(position);
    }

}
