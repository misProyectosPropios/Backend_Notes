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

    //Ensures: if dataSource is not null, then it will create a connections. Else nothing is done
    public void createConnection() throws SQLException{
        if (this.dataSource != null) {
            this.con = this.dataSource.getConnection();
        }
    }

    //Requires: True
    //Ensures: this.con will be closed and set as null
    public void closeConnection() throws SQLException {
        if (con != null) {
            this.con.close();
        }
        this.con = null;
    }

    //Requires (forall i :str) (0 <= i < |columns| ==> i in this.table)
    //Ensures: shows all the columns in the table with the given name in table.
    public void show_table(String[] columns) throws  SQLException {

        //Create obj statement
        Statement statement = this.con.createStatement();

        //Execute SQL
        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS");

        //Run over the resultSet

        while (resultSet.next()) {
            for (String string : columns) {
                System.out.print(resultSet.getString(string));
            }
            System.out.println();
        }
        resultSet.close();
    }

    //Requires: True
    //Ensures: if a table exists, doesn't do anything. Else create an empty table with the given name
    public void create_table(String table_name) throws  SQLException{
        Statement statement = this.con.createStatement();
        String query = """ 
                    CREATE TABLE""" + " IF NOT EXISTS " + table_name + "(\n" + """
                    id int NOT NULL,
                    author varchar(30) NOT NULL,
                    title varchar(120) NOT NULL,
                    body varchar(600) NOT NULL,
                    last_date varchar(30) NOT NULL,
                    PRIMARY KEY (ID)
                );""";
        statement.executeUpdate(query);
    }

    //Require: true
    //Ensures: res = True <==> Exist a table such as TABLE_NAME = table_name
    private Boolean existTable(String table_name) throws SQLException {
        String look_for = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + table_name + "';";
        Statement statement = this.con.createStatement();
        ResultSet resultSet = statement.executeQuery(look_for);
        boolean res = resultSet.next();
        //Close the connections
        statement.close();
        resultSet.close();
        return res;
    }

    //Requires: table_name must be a table name of the database
    //Ensures: res = True <==> Exist a row with the row.id = id
    private Boolean existId(String table_name, int  id) throws  SQLException {
        String look_for = "SELECT * FROM " + table_name + " WHERE id=" + id + ";";
        Statement statement = this.con.createStatement();
        ResultSet resultSet = statement.executeQuery(look_for);
        boolean res = resultSet.next();
        //Close the connections
        statement.close();
        resultSet.close();
        return res;
    }

    //requires: table_name should be a table name of the database
    //Ensures: if the note exists, it will be modified. Else it will create a new one
    public void change_table(String table_name, Note to_store) throws SQLException{
        boolean exists_some_row_with_id = existId(table_name, to_store.getID());

        String query;
        if (!exists_some_row_with_id) {
            query = "INSERT INTO " + table_name + " (id, author, title, body, last_date) \nVALUES ("
            + to_store.getID() + ", '" + to_store.getAuthor() + "', '" + to_store.getTittle() + "', '" + to_store.getBody() +
            "', '" + to_store.getDate() + "');";
        } else {
            query = "UPDATE " + table_name +
                    " SET id=" + to_store.getID() + ",author='" + to_store.getAuthor() + "',title='" + to_store.getID() +
                    "',body='" + to_store.getID() + "',last_date='" + to_store.getDate() +
                    "'\n" +
                    "WHERE id=" + to_store.getID() + ";";
        }
       Statement statement = this.con.createStatement();
       statement.execute(query);
       statement.close();
    }

    //Requires: True
    //Ensures: given the table name, it will disappear the table from the given database
    public void delete_table(String table_name) throws SQLException {
        String query = "DROP TABLE " + table_name + ";";
        Statement statement = this.con.createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        statement.close();
    }

    //Requires: True (or to be a table name inside the database)
    //Ensures: It will delete all the data inside a table in the database
    public void truncate_table(String table_name) throws SQLException {
        String query = " TRUNCATE TABLE " + table_name + ";";
        Statement statement = this.con.createStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        statement.close();
    }

    //Requires: True
    //Ensures: given the table name, all the ids different to id will remain the same. And the id will no longer be in the database
    public void delete_row_id(String table_name, int id) throws SQLException {
        String query = " DELETE FROM " + table_name + "WHERE id=" + id +";";
        Statement statement = this.con.createStatement();
        statement.execute(query);
        statement.close();
    }

}
