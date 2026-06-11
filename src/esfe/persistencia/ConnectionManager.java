package esfe.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL =
            "jdbc:sqlserver://localhost\\MSSQLSERVER01;" +
            "databaseName=CajeroAutomaticoDB;" +
            "encrypt=true;" +
            "trustServerCertificate=true;";

    private static final String USER = "cajero_user";
    private static final String PASSWORD = "Cajero12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean probarConexion() {
        try (Connection connection = getConnection()) {
            return connection != null;
        } catch (SQLException e) {
            System.out.println("Error al conectar con SQL Server:");
            e.printStackTrace();
            return false;
        }
    }
}