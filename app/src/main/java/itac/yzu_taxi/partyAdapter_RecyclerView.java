package itac.yzu_taxi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengYuShao on 15/5/8.
 * RecyclerView所需的Adapter
 */
public class partyAdapter_RecyclerView extends RecyclerView.Adapter<partyAdapter_RecyclerView.Holder> {

    private index curIndex;
    private partyNode partyNode;
    private ArrayList<party> parties;
    private Context context;

    public partyAdapter_RecyclerView(Context context, index index) {
        this.curIndex = index;
        this.parties = new ArrayList<party>();
        this.context = context;
        this.partyNode = new partyNode();
    }

    public partyAdapter_RecyclerView(Context context, index index,ArrayList<party> parties){
        this.curIndex = index;
        this.parties = parties;
        this.context = context;
        this.partyNode = new partyNode();
    }

    @Override
    public partyAdapter_RecyclerView.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bulletinboard_party_card,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(partyAdapter_RecyclerView.Holder holder, int position) {
        party tempParty = parties.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        String tempDateString = sdf.format(tempParty.getATime());

        holder.TV_StartLocation.setText(partyNode.getLocation(tempParty.getAPoint()).getLocationName());
        holder.TV_Destination.setText(partyNode.getLocation(tempParty.getDestination()).getLocationName());
        holder.TV_Time.setText(tempDateString);
        holder.TV_GroupStatus.setText(" " + partyNode.status[tempParty.getPartyStatus()] + " (" + Integer.toString(tempParty.getPartyMemberNumber()) + "/4)人");
        if(tempParty.getPartyStatus() == 0)
            holder.TV_GroupStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        else if(tempParty.getPartyStatus() == 1)
            holder.TV_GroupStatus.setTextColor(context.getResources().getColor(R.color.red));
    }

    public void addParty(party newParty){
        this.parties.add(newParty);
        this.notifyDataSetChanged();
    }

    public void removeParty(party targetParty){
        this.parties.remove(targetParty);
        this.notifyDataSetChanged();
    }

    public void refresh(ArrayList<party> newParties){
        this.parties = newParties;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.parties.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        public TextView TV_StartLocation,TV_Destination,TV_Time,TV_GroupStatus;

        public Holder(final View itemView) {
            super(itemView);
            TV_StartLocation = (TextView) itemView.findViewById(R.id.partyDetial_card_textView_StartLocation);
            TV_Destination = (TextView) itemView.findViewById(R.id.partyDetial_card_textView_Destination);
            TV_Time = (TextView) itemView.findViewById(R.id.partyDetial_card_textView_Time);
            TV_GroupStatus = (TextView) itemView.findViewById(R.id.partyDetial_card_textView_GroupStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    party tempParty = parties.get(getPosition());
                    Log.d("postion", Integer.toString(tempParty.getPartyMemberNumber()));
                    curIndex.goToPartyInfo(tempParty);
                }
            });
        }
    }
}
