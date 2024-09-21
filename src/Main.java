import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hola");
        BBDD base_de_datos = new BBDD(3306, "notes", "root", "", "localhost");
        String[] array = new String[] {"id"};
        base_de_datos.createConnection();
        base_de_datos.create_table("veamos");
        base_de_datos.closeConnection();
    }
}