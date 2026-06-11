package esfe.dominio;

/**
 * Clase que representa al usuario del cajero automático.
 * Contiene los datos básicos necesarios para iniciar sesión.
 */
public class Usuario {

    private String nombre;
    private String pin;

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombre nombre del usuario
     * @param pin PIN de acceso del usuario
     */
    public Usuario(String nombre, String pin) {
        this.nombre = nombre;
        this.pin = pin;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el PIN del usuario.
     *
     * @return PIN del usuario
     */
    public String getPin() {
        return pin;
    }
}