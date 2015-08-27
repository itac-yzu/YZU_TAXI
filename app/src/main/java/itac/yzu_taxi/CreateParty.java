package itac.yzu_taxi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.w3c.dom.Text;

/**
 * Created by ChengYuShao on 15/6/22.
 */
public class CreateParty extends ActionBarActivity{
    private ViewPager mPagerTime,mPagerMember;
    //private ButtonFloat buttonFloat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_createparty);

        //MultiViewPager multiViewPager = (MultiViewPager) findViewById(R.id.viewpager_Time);
        //multiViewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
        //buttonFloat = (ButtonFloat) this.findViewById(R.id.buttonFloat);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public float getPageWidth(int position) {
                return super.getPageWidth(position)*0.2f;
            }

            @Override
            public Fragment getItem(int position) {
                return pagerFragment.create(position);
            }

            @Override
            public int getCount() {
                return 6;
            }


        };

        //multiViewPager.setAdapter(fragmentPagerAdapter);

        /*mPagerTime = (ViewPager) this.findViewById(R.id.createParty_pager_Time);
        ViewPagerAdapter_CreateParty mAdapter_Time = new ViewPagerAdapter_CreateParty(6,CreateParty.this);
        mPagerTime.setAdapter(mAdapter_Time);
        mPagerTime.setOnPageChangeListener(new mPagerChangeListener(mAdapter_Time));

        mPagerMember = (ViewPager) this.findViewById(R.id.createParty_pager_Member);
        ViewPagerAdapter_CreateParty mAdapter_Member = new ViewPagerAdapter_CreateParty(10,CreateParty.this);
        mPagerMember.setAdapter(mAdapter_Member);
        mPagerMember.setOnPageChangeListener(new mPagerChangeListener(mAdapter_Member));*/
    }

    /*private class mPagerChangeListener extends ViewPager.SimpleOnPageChangeListener{
        private ViewPagerAdapter_CreateParty curPagerAdapter;
        public mPagerChangeListener(ViewPagerAdapter_CreateParty vpa) {
            curPagerAdapter = vpa;
        }

        @Override
        public void onPageSelected(int position) {
            curPagerAdapter.setBigText(position);
        }
    }*/
}
