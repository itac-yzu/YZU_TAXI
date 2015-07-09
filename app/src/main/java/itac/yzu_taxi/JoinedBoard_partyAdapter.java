package itac.yzu_taxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ChengYuShao on 15/6/2.
 * JoinedBoard 的Adapter
 */
public class JoinedBoard_partyAdapter extends RecyclerView.Adapter<JoinedBoard_partyAdapter.Holder> {

    private index curIndex;
    private partyNode partyNode;
    private ArrayList<party> parties;
    private Context context;

    public JoinedBoard_partyAdapter(Context context, index index) {
        this.curIndex = index;
        this.context = context;
        this.parties = new ArrayList<party>();
        this.partyNode = new partyNode();
    }

    public JoinedBoard_partyAdapter(Context context, index index, ArrayList<party> parties) {
        this.curIndex = index;
        this.context = context;
        this.parties = parties;
        this.partyNode = new partyNode();
    }

    @Override
    public JoinedBoard_partyAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joinedboadrd_card,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        party tempParty = parties.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        String tempDateString = sdf.format(tempParty.getATime());

        holder.TV_Title.setText(tempParty.getPartyName());
        holder.TV_StartLocation.setText(partyNode.getLocation(tempParty.getAPoint()).getLocationName());
        holder.TV_Destination.setText(partyNode.getLocation(tempParty.getDestination()).getLocationName());
        holder.TV_Time.setText(tempDateString);
        holder.TV_Member.setText(" " + partyNode.status[tempParty.getPartyStatus()] + " (" + Integer.toString(tempParty.getPartyMemberNumber()) + "/4)人");
        if(tempParty.getPartyStatus() == 0)
            holder.TV_Member.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        else if(tempParty.getPartyStatus() == 1)
            holder.TV_Member.setTextColor(context.getResources().getColor(R.color.red));
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

    public class Holder extends RecyclerView.ViewHolder{
        public TextView TV_Title,TV_StartLocation,TV_Destination,TV_Time,TV_Member;

        public Holder(View itemView) {
            super(itemView);
            TV_Title = (TextView) itemView.findViewById(R.id.JoinedBoard_Card_TextView_Title);
            TV_StartLocation = (TextView) itemView.findViewById(R.id.JoinedBoard_Card_TextView_StartLocation);
            TV_Destination = (TextView) itemView.findViewById(R.id.JoinedBoard_Card_TextView_Destination);
            TV_Time = (TextView) itemView.findViewById(R.id.JoinedBoard_Card_TextView_Time);
            TV_Member = (TextView) itemView.findViewById(R.id.JoinedBoard_Card_TextView_Member);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    party tempParty = parties.get(getPosition());
                    curIndex.goToPartyInfo(tempParty);
                }
            });
        }
    }
}
