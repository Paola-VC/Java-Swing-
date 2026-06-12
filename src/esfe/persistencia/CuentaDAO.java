package esfe.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {

    public double obtenerSaldo(String numeroCuenta) {
        String sql = "SELECT Saldo FROM Cuentas WHERE NumeroCuenta = ? AND Estado = 1";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, numeroCuenta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("Saldo");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener saldo:");
            e.printStackTrace();
        }

        return 0;
    }

    public boolean depositar(String numeroCuenta, double monto) {
        if (monto <= 0) {
            return false;
        }

        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            Integer idCuenta = obtenerIdCuenta(connection, numeroCuenta);

            if (idCuenta == null) {
                connection.rollback();
                return false;
            }

            String sqlActualizar = "UPDATE Cuentas SET Saldo = Saldo + ? WHERE NumeroCuenta = ? AND Estado = 1";

            try (PreparedStatement statement = connection.prepareStatement(sqlActualizar)) {
                statement.setDouble(1, monto);
                statement.setString(2, numeroCuenta);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas <= 0) {
                    connection.rollback();
                    return false;
                }
            }

            registrarMovimiento(connection, idCuenta, "Deposito", monto);

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al depositar:");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            return false;

        } finally {
            cerrarConexion(connection);
        }
    }

    public boolean retirar(String numeroCuenta, double monto) {
        if (monto <= 0) {
            return false;
        }

        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            Integer idCuenta = obtenerIdCuenta(connection, numeroCuenta);

            if (idCuenta == null) {
                connection.rollback();
                return false;
            }

            double saldoActual = obtenerSaldo(connection, numeroCuenta);

            if (monto > saldoActual) {
                connection.rollback();
                return false;
            }

            String sqlActualizar = "UPDATE Cuentas SET Saldo = Saldo - ? WHERE NumeroCuenta = ? AND Estado = 1";

            try (PreparedStatement statement = connection.prepareStatement(sqlActualizar)) {
                statement.setDouble(1, monto);
                statement.setString(2, numeroCuenta);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas <= 0) {
                    connection.rollback();
                    return false;
                }
            }

            registrarMovimiento(connection, idCuenta, "Retiro", monto);

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al retirar:");
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            return false;

        } finally {
            cerrarConexion(connection);
        }
    }

    private Integer obtenerIdCuenta(Connection connection, String numeroCuenta) throws SQLException {
        String sql = "SELECT IdCuenta FROM Cuentas WHERE NumeroCuenta = ? AND Estado = 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, numeroCuenta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("IdCuenta");
            }
        }

        return null;
    }

    private double obtenerSaldo(Connection connection, String numeroCuenta) throws SQLException {
        String sql = "SELECT Saldo FROM Cuentas WHERE NumeroCuenta = ? AND Estado = 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, numeroCuenta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("Saldo");
            }
        }

        return 0;
    }

    private void registrarMovimiento(Connection connection, int idCuenta, String tipoMovimiento, double monto) throws SQLException {
        String sql = "INSERT INTO Movimientos (IdCuenta, TipoMovimiento, Monto) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCuenta);
            statement.setString(2, tipoMovimiento);
            statement.setDouble(3, monto);
            statement.executeUpdate();
        }
    }

    private void cerrarConexion(Connection connection) {
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