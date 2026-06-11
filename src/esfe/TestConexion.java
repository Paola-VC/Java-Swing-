package esfe;

import esfe.persistencia.ConnectionManager;
import esfe.persistencia.CuentaDAO;
import esfe.persistencia.UsuarioDAO;

public class TestConexion {

    public static void main(String[] args) {
        if (ConnectionManager.probarConexion()) {
            System.out.println("Conexion exitosa a SQL Server.");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            CuentaDAO cuentaDAO = new CuentaDAO();

            String numeroCuenta = "1002003001";
            String pin = "1234";

            boolean loginValido = usuarioDAO.validarLogin(numeroCuenta, pin);

            if (loginValido) {
                System.out.println("Login correcto.");
                System.out.println("Usuario: " + usuarioDAO.obtenerNombreUsuario(numeroCuenta));
                System.out.println("Saldo actual: $" + cuentaDAO.obtenerSaldo(numeroCuenta));
            } else {
                System.out.println("Login incorrecto.");
            }

        } else {
            System.out.println("No se pudo conectar a SQL Server.");
        }
    }
}