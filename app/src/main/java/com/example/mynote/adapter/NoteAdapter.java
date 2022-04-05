package com.example.mynote.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynote.models.ArrayObserver;
import com.example.mynote.models.Note;
import com.example.mynote.R;

import java.util.List;
import java.util.function.Function;

public class NoteAdapter extends ArrayAdapter<Note>  {

    private Context ctx;
    private List<Note> data;
    public ArrayObserver observer;

    public NoteAdapter(Context context,
                            List<Note> objects) {
        super(context, R.layout.note_list_item, objects);

        this.ctx = context;
        this.data = objects;
    }

    public View getView(int position, View previous, ViewGroup parent) {

        boolean isNull = (previous == null);

        Note note = data.get(position);

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View layout = inflater.inflate(R.layout.note_list_item, parent, false);

        TextView title = layout.findViewById(R.id.title);
        TextView subtitle = layout.findViewById(R.id.subtitle);
        ImageView avatar = layout.findViewById(R.id.avatar);

        title.setText(note.getTitle());
        subtitle.setText(note.getMessage());

        int ImageId = R.drawable.ic_home;

        avatar.setImageResource(ImageId);

        return layout;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        observer.onChange();
    }

    public void onDataChange(){
        observer.onChange();
    }

}
