import java.sql.*;

public class BBDD {
    final private String database_name;
    final private String table;

    public BBDD(String database_name, String table) {
        this.database_name = database_name;
        this.table = table;
    }

    //Requires (forall i :str) (0 <= i < |columnas| ==> i in this.table)
    //Asures: shows all the columns in the table with the given name in table.
    public void show_table(String[] columnas) {
            try {
            //Creating the connection
            Connection my_conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.database_name, "root", "");

            //Create obj statement

            Statement statement = my_conexion.createStatement();

            //Execute SQL

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.table);

            //Run over the resultSet

            while(resultSet.next()) {
                for(String string : columnas) {
                    System.out.println(resultSet.getString(string));
                }
            }
            resultSet.close();
        }
        catch (Exception e) {
            System.err.println("Exception" + e.getMessage());
        }
        System.out.println();
    }

    public void create_table() {

    }

    public void change_table() {}
}
