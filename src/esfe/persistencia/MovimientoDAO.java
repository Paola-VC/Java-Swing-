package esfe.persistencia;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimientoDAO {

    public DefaultTableModel obtenerHistorial(String numeroCuenta) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Tipo");
        modelo.addColumn("Monto");
        modelo.addColumn("Fecha");

        String sql = "SELECT m.IdMovimiento, m.TipoMovimiento, m.Monto, m.FechaMovimiento " +
                "FROM Movimientos m " +
                "INNER JOIN Cuentas c ON m.IdCuenta = c.IdCuenta " +
                "WHERE c.NumeroCuenta = ? " +
                "ORDER BY m.FechaMovimiento DESC";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, numeroCuenta);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] fila = new Object[4];
                fila[0] = resultSet.getInt("IdMovimiento");
                fila[1] = resultSet.getString("TipoMovimiento");
                fila[2] = "$" + resultSet.getDouble("Monto");
                fila[3] = resultSet.getTimestamp("FechaMovimiento");

                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial:");
            e.printStackTrace();
        }

        return modelo;
    }
}