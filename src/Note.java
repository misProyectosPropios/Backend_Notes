public class Note {
    private String tittle;
    private String body;


    public Note() {
        this("", "");
    }

    public Note(String body) {
        this("", body);
    }

    public Note (String title, String body) {
        this.tittle = title;
        this.body = body;
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
