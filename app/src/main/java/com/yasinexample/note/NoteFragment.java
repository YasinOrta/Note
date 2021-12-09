package com.yasinexample.note;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class NoteFragment extends Fragment {

    private static final String ARG_Notes = "notes";

    private ArrayList<Note> notes;

    private OnNoteListInteractionListener listener;

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Fragment newInstance(ArrayList<Note> notes) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_Notes, notes);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            notes = (ArrayList<Note>) getArguments().getSerializable(ARG_Notes);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(notes, listener));
            }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof OnNoteListInteractionListener) {
            listener = (OnNoteListInteractionListener) context;
        }else{
            throw new RuntimeException(context.getClass().getName()+ "should be implemented");
        }
    }

    public interface OnNoteListInteractionListener{
        void OnNoteSelected(Note note);

    }
}