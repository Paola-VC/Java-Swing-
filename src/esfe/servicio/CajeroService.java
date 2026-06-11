package esfe.servicio;

import esfe.dominio.Cuenta;
import esfe.dominio.Usuario;

/**
 * Clase de servicio que contiene la lógica principal del cajero automático.
 * Aquí se validan el PIN, depósitos, retiros y consultas de saldo.
 */
public class CajeroService {

    private Usuario usuario;
    private Cuenta cuenta;

    /**
     * Constructor del servicio.
     * Se crea un usuario y una cuenta de prueba para simular el sistema.
     */
    public CajeroService() {
        this.usuario = new Usuario("Usuario Demo", "1234");
        this.cuenta = new Cuenta(500.00);
    }

    /**
     * Valida si el PIN ingresado coincide con el PIN del usuario.
     *
     * @param pinIngresado PIN escrito en el formulario de login
     * @return true si el PIN es correcto, false si es incorrecto
     */
    public boolean validarPin(String pinIngresado) {
        return usuario.getPin().equals(pinIngresado);
    }

    /**
     * Obtiene el nombre del usuario actual.
     *
     * @return nombre del usuario
     */
    public String obtenerNombreUsuario() {
        return usuario.getNombre();
    }

    /**
     * Consulta el saldo actual de la cuenta.
     *
     * @return saldo disponible
     */
    public double consultarSaldo() {
        return cuenta.getSaldo();
    }

    /**
     * Realiza un depósito si el monto ingresado es válido.
     *
     * @param monto cantidad de dinero a depositar
     * @return true si el depósito fue exitoso, false si el monto no es válido
     */
    public boolean depositar(double monto) {

        // Se valida que el monto sea mayor que cero.
        if (monto <= 0) {
            return false;
        }

        cuenta.depositar(monto);
        return true;
    }

    /**
     * Realiza un retiro si el monto es válido y hay saldo suficiente.
     *
     * @param monto cantidad de dinero a retirar
     * @return true si el retiro fue exitoso, false si no se pudo realizar
     */
    public boolean retirar(double monto) {

        // Se valida que el monto sea mayor que cero.
        if (monto <= 0) {
            return false;
        }

        // Se valida que el usuario tenga saldo suficiente.
        if (monto > cuenta.getSaldo()) {
            return false;
        }

        cuenta.retirar(monto);
        return true;
    }
}