package com.yasinexample.note;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yasinexample.note.placeholder.PlaceholderContent.PlaceholderItem;
import com.yasinexample.note.databinding.FragmentNoteBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Note> notes;

    private NoteFragment.OnNoteListInteractionListener listener;

    public MyNoteRecyclerViewAdapter(ArrayList<Note> notes, NoteFragment.OnNoteListInteractionListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return new ViewHolder(FragmentNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = notes.get(position);
        holder.mDesc.setText(notes.get(position).getHeader());
        holder.mDate.setText((new SimpleDateFormat("yyyy-MM-dd")).format(notes.get(position).getDate()));
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.OnNoteSelected(holder.mItem);
                }
            }
        });

        if(position %2==1 ){
            holder.root.setBackgroundColor(Color.BLUE);
        }else{
            holder.root.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() { return notes.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View root;
        public final TextView mDesc;
        public final TextView mDate;
        public Note mItem;

    public ViewHolder(FragmentNoteBinding binding) {
      super(binding.getRoot());
      root = binding.getRoot();
      mDesc = binding.noteHeader;
      mDate = binding.noteDate;
    }

        @Override
        public String toString() {
            return super.toString() + " '" + mDesc.getText() + "'";
        }
    }
}