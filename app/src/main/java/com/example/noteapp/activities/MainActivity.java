package com.example.noteapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.noteapp.R;
import com.example.noteapp.database.NotesDatabase;
import com.example.noteapp.entities.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_NOTE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(v -> startActivityForResult(
                new Intent(getApplicationContext(), CreateNoteActivity.class), REQUEST_CODE_ADD_NOTE)
        );

        getNotes();
    }

    private void getNotes() {

        @SuppressLint("StaticFieldLeak")
        class GetNoteTask extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getNotesDatabase(getApplicationContext())
                        .noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
//                if (requestCode == REQUEST_CODE_SHOW_NOTES) {
//                    noteList.addAll(notes);
//                    notesAdapter.notifyDataSetChanged();
//                } else if (requestCode == REQUEST_CODE_ADD_NOTE) {
//                    noteList.add(0, notes.get(0));
//                    notesAdapter.notifyItemInserted(0);
//                    notesRecyclerView.smoothScrollToPosition(0);
//                } else if (requestCode == REQUEST_CODE_UPDATE_NOTE) {
//                    noteList.remove(noteClickedPosition);
//                    if (isNoteDeleted) {
//                        notesAdapter.notifyItemRemoved(noteClickedPosition);
//                    } else {
//                        noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
//                        notesAdapter.notifyItemChanged(noteClickedPosition);
//                    }
//                }
                Log.d("My notes: ", notes.toString());
            }
        }

        new GetNoteTask().execute();
    }
}