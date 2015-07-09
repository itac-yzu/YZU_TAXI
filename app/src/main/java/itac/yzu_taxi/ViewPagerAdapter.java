package itac.yzu_taxi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by ChengYuShao on 15/5/26.
 * 修改Adapter去達成不同頁面的切換
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private CharSequence titles[];
    private int NumbOfTabs;
    private int curIndex;

    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.curIndex = 0;
    }

    @Override
    public Fragment getItem(int position) {//建立分頁時，根據位置產生各個Fragment
        if(position == 0) {
            PersonalInfo personalInfo = new PersonalInfo();
            return personalInfo;
        }
        else if(position == 1){
            BulletinBoard bulletinBoard = new BulletinBoard();
            return bulletinBoard;
        }
        else{
            JoinedBoard joinedBoard = new JoinedBoard();
            return joinedBoard;
        }
    }

    @Override
    public int getCount() {
        return this.NumbOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    public int getCurIndex() {return this.curIndex;}
}
