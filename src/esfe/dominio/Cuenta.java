package esfe.dominio;

/**
 * Clase que representa una cuenta bancaria.
 */
public class Cuenta {

    private double saldo;

    /**
     * Constructor de la clase Cuenta.
     *
     * @param saldoInicial saldo inicial de la cuenta
     */
    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     *
     * @return saldo disponible
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Deposita dinero en la cuenta.
     *
     * @param monto cantidad de dinero a depositar
     */
    public void depositar(double monto) {
        saldo += monto;
    }

    /**
     * Retira dinero de la cuenta.
     *
     * @param monto cantidad de dinero a retirar
     */
    public void retirar(double monto) {
        saldo -= monto;
    }
}