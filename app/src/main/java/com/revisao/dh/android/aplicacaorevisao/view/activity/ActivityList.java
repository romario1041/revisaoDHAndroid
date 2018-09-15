package com.revisao.dh.android.aplicacaorevisao.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.webkit.WebView;

import com.revisao.dh.android.aplicacaorevisao.R;
import com.revisao.dh.android.aplicacaorevisao.view.activity.Fragments.Fragment_top_rated;
import com.revisao.dh.android.aplicacaorevisao.view.activity.Fragments.Fragment_upcoming;
import com.revisao.dh.android.aplicacaorevisao.view.activity.Fragments.Fragment_popular;
import com.revisao.dh.android.aplicacaorevisao.view.activity.Fragments.Fragment_now_playing;
import com.revisao.dh.android.aplicacaorevisao.controler.adapter.FragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        WebView webView;

        //WebView
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.themoviedb.org/");

        //Lista de fragmentos
        List<Fragment> fragmentList = new ArrayList<>();
        //Lista de titulos
        List<String> fragmentTitle = new ArrayList<>();

        //Titulos
        String fragmentAction = getResources().getString(R.string.top_rated);
        String fragmentFight = getResources().getString(R.string.upcoming);
        String fragmentHorror = getResources().getString(R.string.popular);
        String fragmentParty = getResources().getString(R.string.now_playing);

        //Fragmentos
        Fragment_top_rated fragment_toprated = new Fragment_top_rated();
        Fragment_upcoming fragment_upcoming = new Fragment_upcoming();
        Fragment_popular fragment_popular = new Fragment_popular();
        Fragment_now_playing fragment_nowplaying = new Fragment_now_playing();

        //Passando os fragmentos e seus respectivos titulos pras listas
        fragmentList.add(fragment_toprated);
        fragmentTitle.add(fragmentAction);

        fragmentList.add(fragment_upcoming);
        fragmentTitle.add(fragmentFight);

        fragmentList.add(fragment_popular);
        fragmentTitle.add(fragmentHorror);

        fragmentList.add(fragment_nowplaying);
        fragmentTitle.add(fragmentParty);

        //recuperando a tabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        //Criando o adapter do ViewPager
        FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(),fragmentList, fragmentTitle);
        //Recuperando o ViewPager
        ViewPager viewPager = findViewById(R.id.viewPager);
        //Setando o Adapter do ViewPager
        viewPager.setAdapter(fragmentPageAdapter);
        //Setando o viewPager no tabLayout
        tabLayout.setupWithViewPager(viewPager);
    }

}
