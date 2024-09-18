public class Note {
    final private int ID;
    final private String author;
    private String tittle;
    private String body;


    public Note(int ID, String author) {
        this("", "", ID, author);
    }

    public Note(String body, int ID, String author) {
        this("", body, ID, author);
    }

    // requires: (forall i : Z) (0 <= i < |title| ==> s[i] != '\n'
    public Note (String title, String body, int ID, String author) {
        this.tittle = title;
        this.body = body;
        this.ID = ID;
        this.author = author;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return this.body;
    }

    public String getTittle() {
        return this.tittle;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getID() {
        return this.ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            Note noteToCompare = (Note) obj;
            return (this.tittle).equals(noteToCompare.getTittle()) && (this.body).equals(noteToCompare.getBody());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.tittle + "\n" + this.body;
    }
}
