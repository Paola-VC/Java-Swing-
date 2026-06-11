package esfe.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarLogin(String numeroCuenta, String pin) {
        String sql = "SELECT u.IdUsuario " +
                "FROM Usuarios u " +
                "INNER JOIN Cuentas c ON u.IdUsuario = c.IdUsuario " +
                "WHERE c.NumeroCuenta = ? AND u.Pin = ? AND u.Estado = 1 AND c.Estado = 1";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, numeroCuenta);
            statement.setString(2, pin);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("Error al validar login:");
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerNombreUsuario(String numeroCuenta) {
        String sql = "SELECT u.NombreUsuario " +
                "FROM Usuarios u " +
                "INNER JOIN Cuentas c ON u.IdUsuario = c.IdUsuario " +
                "WHERE c.NumeroCuenta = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, numeroCuenta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("NombreUsuario");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener nombre del usuario:");
            e.printStackTrace();
        }

        return null;
    }
}