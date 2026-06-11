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
        String sql = "UPDATE Cuentas SET Saldo = Saldo + ? WHERE NumeroCuenta = ? AND Estado = 1";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, monto);
            statement.setString(2, numeroCuenta);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                registrarMovimiento(numeroCuenta, "Deposito", monto);
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al depositar:");
            e.printStackTrace();
        }

        return false;
    }

    public boolean retirar(String numeroCuenta, double monto) {
        double saldoActual = obtenerSaldo(numeroCuenta);

        if (monto > saldoActual) {
            return false;
        }

        String sql = "UPDATE Cuentas SET Saldo = Saldo - ? WHERE NumeroCuenta = ? AND Estado = 1";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, monto);
            statement.setString(2, numeroCuenta);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                registrarMovimiento(numeroCuenta, "Retiro", monto);
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al retirar:");
            e.printStackTrace();
        }

        return false;
    }

    private void registrarMovimiento(String numeroCuenta, String tipoMovimiento, double monto) {
        String sqlIdCuenta = "SELECT IdCuenta FROM Cuentas WHERE NumeroCuenta = ?";
        String sqlMovimiento = "INSERT INTO Movimientos (IdCuenta, TipoMovimiento, Monto) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statementCuenta = connection.prepareStatement(sqlIdCuenta)) {

            statementCuenta.setString(1, numeroCuenta);

            ResultSet resultSet = statementCuenta.executeQuery();

            if (resultSet.next()) {
                int idCuenta = resultSet.getInt("IdCuenta");

                try (PreparedStatement statementMovimiento = connection.prepareStatement(sqlMovimiento)) {
                    statementMovimiento.setInt(1, idCuenta);
                    statementMovimiento.setString(2, tipoMovimiento);
                    statementMovimiento.setDouble(3, monto);
                    statementMovimiento.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar movimiento:");
            e.printStackTrace();
        }
    }
}