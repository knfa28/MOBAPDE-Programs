package com.example.kurt.labdatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by Kurt on 2/23/2016.
 */
public class NoteCursorAdapter extends CursorRecyclerViewAdapter<NoteCursorAdapter.NoteViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener m){
        this.mOnItemClickListener = m;
    }

    public interface OnItemClickListener{
        public void OnItemClick(int id);
    }

    public NoteCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE));
        viewHolder.textTitle.setText(title);
        viewHolder.container.setTag(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dbid = Integer.parseInt(v.getTag().toString());
                mOnItemClickListener.OnItemClick(dbid);
            }
        });
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, null);
        return new NoteViewHolder(v);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        View container;

        public NoteViewHolder(View itemView){
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.noteTitle);
            container = (View) itemView.findViewById(R.id.container);
        }
    }
}
