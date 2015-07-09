package itac.yzu_taxi;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;

/**
 * Created by ChengYuShao on 15/7/4.
 */
public class ViewPagerAdapter_CreateParty extends android.support.v4.view.PagerAdapter{

    private Context parentContext;
    private int totalNumber;
    private int curIndex;
    private View curView;
    private TextView tv[];

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        curView = (View) object;
    }

    public ViewPagerAdapter_CreateParty(int totalNumber, Context context) {
        this.totalNumber = totalNumber;
        this.parentContext = context;
        tv = new TextView[totalNumber];
    }

    @Override
    public int getCount() {
        return totalNumber;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        curIndex = position;

        TextView tv_number = new TextView(parentContext);
        //tv_number.setWidth(LayoutParams.WRAP_CONTENT);
        //tv_number.setHeight(LayoutParams.WRAP_CONTENT);
        tv_number.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        tv_number.setText(Integer.valueOf(position + 1).toString());
        tv_number.setTextSize(30);
        tv_number.setTextColor(parentContext.getResources().getColor(R.color.black));
        tv[position] = tv_number;

        LinearLayout layout = new LinearLayout(parentContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(layoutParams);
        layout.addView(tv_number);

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position)*0.2f;
    }

    public void setBigText(int position){
        for (int i = 0 ; i < totalNumber ; i++)
            tv[i].setTextSize(30);
        tv[position].setTextSize(50);
    }
}
