package ph.dlsu.mobidev.g301.labadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by G301 on 1/26/2016.
 */
public class JoJoAdapter extends RecyclerView.Adapter<JoJoAdapter.JoJoHolder>{

    ArrayList<String> jojoList;

    public JoJoAdapter(ArrayList<String> jojoList){
        this.jojoList = jojoList;
    }

    public class JoJoHolder extends RecyclerView.ViewHolder{
        TextView jojoText;

        public JoJoHolder(View itemView){
            super(itemView);
            jojoText = (TextView) itemView.findViewById(R.id.jojoChar);

            jojoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteJoJo(jojoText.getText().toString());
                }
            });
        }
    }

    @Override
    public JoJoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jojo_list, null);

        return new JoJoHolder(v);
    }

    @Override
    public void onBindViewHolder(JoJoHolder jojoHolder, int i) {
        String jojo = jojoList.get(i);
        jojoHolder.jojoText.setText(jojo);
    }

    @Override
    public int getItemCount() {
        return this.jojoList.size();
    }

    public void addJoJo(String jojo){
        jojoList.add(jojo);
        notifyItemInserted(getItemCount() - 1);
    }

    public void deleteJoJo(String jojo){
        jojoList.remove(jojo);
        notifyDataSetChanged();
    }
}
