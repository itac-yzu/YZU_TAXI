package itac.yzu_taxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by ChengYuShao on 15/5/26.
 * 主頁面
 */
public class index extends ActionBarActivity {

    private ViewPager mPager,mPagerToolBar;
    private ViewPagerAdapter mAdapter;
    private ViewPagerAdapterToolBar mAdapterToolBar;
    private SlidingTabLayout mTabs,mTabsToolBar;
    private CharSequence Titles[]={"","",""};
    private int Numboftabs =3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.setClass(index.this, Home.class);
        startActivity(intent);//進入index後，直接進入Hom頁面顯示Logo


        setContentView(R.layout.layout_index);

        mTabs = (SlidingTabLayout) this.findViewById(R.id.index_tabs);
        mTabsToolBar = (SlidingTabLayout) this.findViewById(R.id.index_tabs_toolbar);
        mPager = (ViewPager) this.findViewById(R.id.index_pager);
        mPagerToolBar = (ViewPager) this.findViewById(R.id.index_pager_toolbar);




        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);
        mAdapterToolBar = new ViewPagerAdapterToolBar(getSupportFragmentManager());


        mPager.setAdapter(mAdapter);
        mPagerToolBar.setAdapter(mAdapterToolBar);

        mTabs.setDistributeEvenly(true);
        mTabs.setSyncViewPager(mPagerToolBar);//將須同步的viewpage放入SlidingTabLayou中，透過pagechengListene同步移動
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });//設定滑動Bar的顏色

        mTabs.setViewPager(mPager);//設定連動的PagerView(自訂)

        mTabsToolBar.setDistributeEvenly(true);
        mTabsToolBar.setSyncViewPager(mPager);
        mTabsToolBar.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.mainYello);
            }
        });

        mTabsToolBar.setViewPager(mPagerToolBar);

        mPager.setCurrentItem(1);//設定現在位置為頁面1
        mPagerToolBar.setCurrentItem(1);
    }

    public void goToPartyInfo(party targetParty){//從Boar點選揪團後，呼叫此functio進入partyinfo
        Intent intent = new Intent();
        intent.setClass(index.this, partyInfo.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("party", targetParty);//將點選的part傳入partyinf顯示
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void createGroup(){//點選+，進入Creat頁面
        Intent intent = new Intent();
        intent.setClass(index.this,CreateParty.class);
        startActivity(intent);
    }
}
