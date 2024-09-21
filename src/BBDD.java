import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class BBDD {
    final private int port;
    final private String database_name;
    private Connection con;
    final private String username;
    final private String password;
    final private String server_name;
    private MysqlDataSource  dataSource = new MysqlDataSource();
    //f
    //final private String database_name;
    //final private String table;
    //final

    public BBDD(int port, String database_name, String username, String password, String server_name) {
        this.port = port;
        this.database_name = database_name;
        this.con = null;
        this.username = username;
        this.password = password;
        this.server_name = server_name;
        this.dataSource.setPassword(password);
        this.dataSource.setUser(username);
        this.dataSource.setServerName(server_name);
        this.dataSource.setDatabaseName(database_name);
    }

    public void createConnection() throws SQLException{
        if (this.dataSource != null) {
            this.con = this.dataSource.getConnection();
        }
    }

    public void closeConnection() throws SQLException {
        this.con.close();
        this.con = null;
    }

    //Requires (forall i :str) (0 <= i < |columnas| ==> i in this.table)
    //Asures: shows all the columns in the table with the given name in table.
    public void show_table(String[] columnas) throws  SQLException {

        //Create obj statement
        Statement statement = this.con.createStatement();

        //Execute SQL
        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS");

        //Run over the resultSet

        while (resultSet.next()) {
            for (String string : columnas) {
                System.out.print(resultSet.getString(string));
            }
            System.out.println();
        }
        resultSet.close();
    }

    public void create_table(String table_name) throws  SQLException{
        Statement statement = this.con.createStatement();
        String query = """ 
                    CREATE TABLE """ + " IF NOT EXISTS " + table_name + "(" + """
                    
                    id int NOT NULL,
                    author varchar(30) NOT NULL,
                    title varchar(120) NOT NULL,
                    body varchar(300) NOT NULL,
                    last_date DATE NOT NULL,
                    PRIMARY KEY (ID)
                );""";
        statement.executeUpdate(query);
    }

    public void change_table(String table_name, int id, Note nota_a_guardar) {

    }
    /*
    public void create_table(String table_name) throws  SQLException{
        //Creating the connection
        Connection my_conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.database_name, "root", "");

        //Create obj statement

        Statement statement = my_conexion.createStatement();

        //Execute SQL

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS");

        //Run over the resultSet

        resultSet.close();

    }

    public void change_table() throws  SQLException {
        //Creating the connection
        Connection my_conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.database_name, "root", "");

        //Create obj statement

        Statement statement = my_conexion.createStatement();

        //Execute SQL

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS");

        //Run over the resultSet
        resultSet.close();
    }

     */
}
