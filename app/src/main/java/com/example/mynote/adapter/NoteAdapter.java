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

import com.example.mynote.configs.Constant;
import com.example.mynote.configs.NoteTag;
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

        TextView subtitle = layout.findViewById(R.id.subtitle);
        ImageView avatar = layout.findViewById(R.id.avatar);

        String title = note.getTitle();
        subtitle.setText(title.isEmpty() ? note.getMessage() : title);

        int ImageId = getIcon(note.getTag());

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

    int getIcon (String id){
        for (int i = 0; i < Constant.noteTagList.length; i++) {
            NoteTag item = Constant.noteTagList[i];
            if(id.equals(item.getId())){
                return item.getTagIconId();
            }
        }
        return R.drawable.ic_home;
    }
}
