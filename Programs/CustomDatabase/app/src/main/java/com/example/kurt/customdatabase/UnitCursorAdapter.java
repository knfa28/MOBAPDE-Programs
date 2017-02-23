package com.example.kurt.customdatabase;

/**
 * Created by Kurt on 3/10/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnitCursorAdapter extends CursorRecyclerViewAdapter<UnitCursorAdapter.UnitViewHolder>{

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener m){
        this.mOnItemClickListener = m;
    }

    public interface OnItemClickListener{
        public void onItemClick(int id);
        public void onItemLongClick(int id);
    }

    public UnitCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_item, null);
        return new UnitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UnitViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Unit.COLUMN_TITLE));
        viewHolder.tv.setText(title);
        viewHolder.container.setTag(cursor.getInt(cursor.getColumnIndex(Unit.COLUMN_ID)));

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dbid = Integer.parseInt(v.getTag().toString());
                mOnItemClickListener.onItemClick(dbid);
            }
        });

        viewHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int dbid = Integer.parseInt(v.getTag().toString());
                mOnItemClickListener.onItemLongClick(dbid);
                return true;
            }
        });
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        View container;

        public UnitViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_unit);
            container = (View) itemView.findViewById(R.id.container);
        }
    }
}

