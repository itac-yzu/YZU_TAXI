package itac.yzu_taxi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ChengYuShao on 15/5/4.
 * partyInfo 內的Members顯示所需的Adapter
 */
public class memberAdapter extends ArrayAdapter<user> {

    private int resource;
    private List<user> members;


    public memberAdapter(Context context, int resource, List<user> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.members = objects;
    }

    public memberAdapter(Context context, int resource, int resource1, Context context1) {
        super(context, resource);
        resource = resource1;
        context = context1;
    }

    @Override
    public user getItem(int position) {
        return this.members.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        user tempUser = getItem(position);

        if(convertView == null){
            linearLayout = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, linearLayout , true);
        }
        else{
            linearLayout = (LinearLayout) convertView;
        }

        ImageView IV_Sex = (ImageView) linearLayout.findViewById(R.id.partyInfo_Member_imageView_Sex);
        TextView TV_Name = (TextView) linearLayout.findViewById(R.id.partyInfo_Member_textView_Name);
        TextView TV_Appraisal = (TextView) linearLayout.findViewById(R.id.partyInfo_Member_textView_Appraisal);
        ImageView IV_Main = (ImageView) linearLayout.findViewById(R.id.partyInfo_Member_imageView_Main);
        ImageView IV_Friend = (ImageView) linearLayout.findViewById(R.id.partyInfo_Member_imageView_Friend);


        if(tempUser.getUserSex())//顯示性別
            IV_Sex.setImageResource(R.drawable.gender_male);
        else
            IV_Sex.setImageResource(R.drawable.gender_female);
        TV_Name.setText(tempUser.getUserName());
        TV_Appraisal.setText(Double.toString(tempUser.getUserAppraisal()));
        IV_Main.setImageResource(R.drawable.partyinfo_member_maintag);
        IV_Friend.setImageResource(R.drawable.partyinfo_member_friendtag);
        if(tempUser.isMain())//顯示是否為主揪
            IV_Main.setVisibility(View.VISIBLE);
        else
            IV_Main.setVisibility(View.GONE);

        if(tempUser.isFriend())//顯示是否為朋友
            IV_Friend.setVisibility(View.VISIBLE);
        else
            IV_Friend.setVisibility(View.INVISIBLE);

        return linearLayout;
    }

    public void addMember(user newUser){
        this.members.add(newUser);
        this.notifyDataSetChanged();
    }

    public void removeMember(user targetUser){
        this.members.remove(targetUser);
        this.notifyDataSetChanged();
    }

    public void refresh(List<user> newUsers){
        this.members = newUsers;
        this.notifyDataSetChanged();
    }

}
