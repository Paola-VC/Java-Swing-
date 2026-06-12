package esfe.persistencia;

import java.sql.*;

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

    public String crearCuenta(String nombreUsuario, String pin, double saldoInicial) {
        String sqlUsuario = "INSERT INTO Usuarios (NombreUsuario, Pin, Estado) VALUES (?, ?, 1)";
        String sqlCuenta = "INSERT INTO Cuentas (IdUsuario, NumeroCuenta, Saldo, Estado) VALUES (?, ?, ?, 1)";
        String sqlMovimiento = "INSERT INTO Movimientos (IdCuenta, TipoMovimiento, Monto) VALUES (?, ?, ?)";

        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            int idUsuario;

            try (PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                statementUsuario.setString(1, nombreUsuario);
                statementUsuario.setString(2, pin);
                statementUsuario.executeUpdate();

                ResultSet generatedKeys = statementUsuario.getGeneratedKeys();

                if (generatedKeys.next()) {
                    idUsuario = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el IdUsuario generado.");
                }
            }

            String numeroCuenta = generarNumeroCuenta();
            int idCuenta;

            try (PreparedStatement statementCuenta = connection.prepareStatement(sqlCuenta, Statement.RETURN_GENERATED_KEYS)) {
                statementCuenta.setInt(1, idUsuario);
                statementCuenta.setString(2, numeroCuenta);
                statementCuenta.setDouble(3, saldoInicial);
                statementCuenta.executeUpdate();

                ResultSet generatedKeys = statementCuenta.getGeneratedKeys();

                if (generatedKeys.next()) {
                    idCuenta = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el IdCuenta generado.");
                }
            }

            if (saldoInicial > 0) {
                try (PreparedStatement statementMovimiento = connection.prepareStatement(sqlMovimiento)) {
                    statementMovimiento.setInt(1, idCuenta);
                    statementMovimiento.setString(2, "Apertura");
                    statementMovimiento.setDouble(3, saldoInicial);
                    statementMovimiento.executeUpdate();
                }
            }

            connection.commit();
            return numeroCuenta;

        } catch (SQLException e) {
            System.out.println("Error al crear cuenta:");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            return null;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String generarNumeroCuenta() {
        String tiempo = String.valueOf(System.currentTimeMillis());
        return tiempo.substring(tiempo.length() - 10);
    }
}