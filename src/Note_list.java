import java.util.ArrayList;

public class Note_list {
    private ArrayList<Note> note_lists = new ArrayList<>();

    public Note_list() {
        this.note_lists = new ArrayList<>();
    }

    public void add_note_to_list_last_index(Note note) {
        this.note_lists.add(note);
    }

    public void add_note_to_list(Note note, int index) {
        this.note_lists.add(index, note);
    }

    public void delete_note_to_list(int index) {
        this.note_lists.remove(index);
    }

    public Note get_note_from_list(int index) {
        return this.note_lists.get(index);
    }

}
