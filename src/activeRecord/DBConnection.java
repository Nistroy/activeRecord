package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static DBConnection instance = null;

    private static String dbName = "testpersonne";

    private final Connection connection;

    private DBConnection(String dbName) {
        Connection dbConnection = null;

        String userName = "root";
        String password = "";
        String serverName = "127.0.0.1";
        //String portNumber = "3306";
        String portNumber = "3306"; // Port par défaut sur MAMP

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // creation de la connection
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            String urlDB = "jdbc:mysql://" + serverName + ":";
            urlDB += portNumber + "/" + dbName;
            System.out.println(urlDB);

            dbConnection = DriverManager.getConnection(urlDB, connectionProps);
        }
        catch (SQLException sqlException) {
            throw new RuntimeException("Impossible de se connecter à la base de données", sqlException);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException("Driver JDBC non trouvé", classNotFoundException);
        }

        this.connection = dbConnection;

        System.out.println("Connection à la base de données " + dbName + " établie");
    }

    public static synchronized DBConnection getConnection() {
        if (instance == null) {
            instance = new DBConnection(dbName);
        }
        return instance;
    }

    public static void setNomDB(String nomDB) {
        dbName = nomDB;

        instance = new DBConnection(dbName);
    }
}
