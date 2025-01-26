package persistence

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import model.Note

class NoteRepository {
    private var databaseReference: DatabaseReference
    init {
        databaseReference = FirebaseDatabase.getInstance("URL_DE_TU_BBDD_FIREBASE").reference
    }

    fun addNote(note: Note) {
        val key = databaseReference.child("notes").push().key
        note.idNote = key
        databaseReference.child("notes").child(key!!).setValue(note)
    }

    fun getNotes(): MutableLiveData<List<Note>> {
        val notes = MutableLiveData<List<Note>>()

        // Cada vez que los datos cambien, se llamará al evento onDataChange con la nueva lista de datos
        val firebaseDataListener = FirebaseDataListener(notes)
        databaseReference.child("notes").addValueEventListener(firebaseDataListener)

        return notes
    }

}