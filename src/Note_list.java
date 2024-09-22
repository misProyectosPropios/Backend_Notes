import java.util.ArrayList;

public class Note_list {
    private ArrayList<Note> note_lists = new ArrayList<>();

    public Note_list() {
        this.note_lists = new ArrayList<>();
    }

    //requires: note_lists = note_lists0
    //ensures: |note_lists| = |note_lists0| + 1
    //ensures: note_lists = concat(note_lists0, <note>)
    public void add_note_to_list_last_index(Note note) {
        this.note_lists.add(note);
    }

    //requires: note_lists = note_lists0
    //ensures: |note_lists| = |note_lists0| + 1
    //ensures: note_lists = concat(subset(0, index), <note>, subset(index + 1, |note_lists0| - 1)
    public void add_note_to_list(Note note, int index) {
        this.note_lists.add(index, note);
    }

    //requires: 0 <= index < |note_lists|
    //ensures. removes the index from the array, and all the next's elements gets a positions i - 1
    public void delete_note_to_list(int index) {
        this.note_lists.remove(index);
    }

    //requires: 0 <= index < |note_lists|
    //ensures: res = index.get(index)
    public Note get_note_from_list(int index) {
        return this.note_lists.get(index);
    }

    //requires: (forall i : Z) (0 <= i < |seq| ==> (exists j : Z) (0 <= j < |note_lists| && note_lists.get(j).getId() = seq[i]))
    //ensures  |seq| = |note_lists|
    //ensures: (forall i : Z) (0 <= i < |seq| ==> note_lists.get(i).getId() = seq[i])
    public void order_by_a_seq(int[] seq) {

    }

}
