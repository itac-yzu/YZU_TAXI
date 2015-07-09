package itac.yzu_taxi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ChengYuShao on 15/6/1.
 */
public class ViewPagerAdapterToolBar extends FragmentStatePagerAdapter {

    public ViewPagerAdapterToolBar(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            PersonalInfo_ToolBar personalInfo_toolBar = new PersonalInfo_ToolBar();
            return personalInfo_toolBar;
        }
        else if(position == 1){
            BulletinBoard_ToolBar bulletinBoard_toolBar = new BulletinBoard_ToolBar();
            return bulletinBoard_toolBar;
        }
        else{
            JoinedBoard_ToolBar joinedBoard_toolBar = new JoinedBoard_ToolBar();
            return joinedBoard_toolBar;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "QAQ";
    }
}
