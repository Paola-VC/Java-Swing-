# Resumen del Módulo Front-End - SecurityApp Swing

Este documento resume de manera técnica y estructurada los temas, procesos, código y mejores prácticas observadas en los videos 8 al 20 del módulo de apoyo Front-End para el desarrollo de la interfaz gráfica de escritorio de la aplicación `SecurityApp` en Java Swing.

---

## Video 8: Crear el paquete de presentación e iniciar los archivos de los formularios
* **Archivo de video analizado:** [8- Crear el paquete de presentacion y iniciar los archivos de los formularios.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/8-%20Crear%20el%20paquete%20de%20presentacion%20y%20iniciar%20los%20archivos%20de%20los%20formularios.mp4)

### Resumen del Contenido
El docente crea la estructura básica de paquetes para la capa de presentación (UI) de la aplicación. Se detalla la diferencia teórica y de comportamiento entre las ventanas principales `JFrame` (ventanas de nivel superior independientes) y `JDialog` (ventanas secundarias dependientes o modales). Se inicializan los archivos `.form` y sus correspondientes clases Java para la pantalla principal (`MainForm`) y cuatro pantallas secundarias (`LoginForm`, `ChangePasswordForm`, `UserReadingForm`, `UserWriteForm`) configurándolas de forma adecuada.

### Tecnologías Utilizadas
* **Java 21:** Lenguaje base para heredar y programar las clases de la interfaz.
* **Swing API:** Librería gráfica estándar de Java (paquete `javax.swing`).

### Herramientas Utilizadas
* **IntelliJ IDEA Community Edition:** IDE utilizado con su herramienta visual integrada **Swing UI Designer**.

### Componentes Swing Utilizados
* **JFrame:** Utilizado como contenedor para la pantalla principal `MainForm`.
* **JDialog:** Utilizado como contenedor base para todas las pantallas secundarias/modales.
* **JPanel:** Panel contenedor de componentes gráficos autogenerado en cada formulario.

### Código Mostrado
Ejemplo de cómo el docente fuerza la herencia de las clases asociadas a los formularios:
```java
// En MainForm.java
package esfe.presentacion;

import javax.swing.JFrame;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    // La clase se asocia al diseño visual mediante la herencia de JFrame
}

// En LoginForm.java
package esfe.presentacion;

import javax.swing.JDialog;

public class LoginForm extends JDialog {
    private JPanel mainPanel;
    // La clase hereda de JDialog ya que será una ventana secundaria modal
}
```

### Pasos Realizados por el Docente
1. En la estructura del proyecto en IntelliJ, localiza el paquete principal `esfe` bajo `src/main/java`.
2. Hace clic derecho sobre `esfe` -> `New` -> `Package` y crea el paquete `presentacion`.
3. Para la ventana principal, hace clic derecho sobre `presentacion` -> `New` -> `Swing UI Designer` -> `GUI Form`. Asigna el nombre `MainForm`.
4. En el archivo Java generado automáticamente `MainForm.java`, añade `extends JFrame` a la definición de la clase para que actúe como ventana de nivel superior independiente.
5. De manera análoga, crea cuatro archivos de interfaz gráfica adicionales del tipo `GUI Form`: `LoginForm`, `ChangePasswordForm`, `UserReadingForm` y `UserWriteForm`.
6. Modifica la cabecera de las clases Java de estas cuatro ventanas secundarias para que hereden de `JDialog` (`extends JDialog`).
7. Confirma que en el árbol de directorios del paquete `presentacion` cada formulario contenga tanto el archivo visual `.form` como su clase asociada `.java`.

### Buenas Prácticas Observadas
* **Modularidad por Capas:** Separar el código de interfaz gráfica en un paquete especializado (`presentacion`) aislado del negocio (`dominio`) y la persistencia (`persistencia`).
* **Elección adecuada de contenedores (JFrame vs JDialog):** Uso de `JFrame` exclusivamente para la ventana de control principal del sistema y `JDialog` para las modales interactivas, lo que optimiza el flujo de navegación del usuario y facilita el control del ciclo de vida de las ventanas secundarias.

---

## Video 9: Diseño del formulario de iniciar sesión
* **Archivo de video analizado:** [9- Diseño del formulario de iniciar sesion.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/9-%20Dise%C3%B1o%20del%20formulario%20de%20iniciar%20sesion.mp4)

### Resumen del Contenido
El docente diseña de forma visual el formulario de login en `LoginForm.form` utilizando el Swing UI Designer. Configura el panel contenedor principal y arrastra etiquetas, campos de texto y botones, estableciendo las propiedades clave a nivel gráfico y renombrando los identificadores de los componentes para su posterior referencia en el código.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Específicamente el modelo Grid Layout Manager para organizar componentes.

### Herramientas Utilizadas
* **IntelliJ IDEA Swing UI Designer:** Inspector de propiedades y paleta gráfica de componentes.

### Componentes Swing Utilizados
* **JPanel:** Contenedor raíz del formulario (`mainPanel`).
* **JLabel:** Etiquetas informativas de texto para guiar al usuario ("Email" y "Contraseña").
* **JTextField:** Campo de texto libre para la entrada del correo electrónico (`txtEmail`).
* **JPasswordField:** Campo especializado de contraseña con máscara protectora (`txtPassword`).
* **JButton:** Botones interactivos para procesar acciones (`btnLogin` y `btnSalir`).

### Código Mostrado
A nivel de código, el diseñador visual actualiza las declaraciones de las variables miembros en `LoginForm.java`:
```java
private JPanel mainPanel;
private JTextField txtEmail;
private JPasswordField txtPassword;
private JButton btnLogin;
private JButton btnSalir;
```

### Pasos Realizados por el Docente
1. Abre el archivo `LoginForm.form` en IntelliJ.
2. Selecciona el panel contenedor principal autogenerado y en la propiedad `Field name` le asigna el nombre `mainPanel`.
3. Arrastra una etiqueta `JLabel` y la sitúa en la parte superior.
4. Arrastra un campo de texto `JTextField` para el email del usuario.
5. Muestra que se puede presionar `Ctrl + Z` para deshacer maquetaciones de rejilla erróneas en el diseñador visual.
6. Arrastra una segunda etiqueta `JLabel` y un `JPasswordField` para la contraseña.
7. Agrega dos botones `JButton` en una misma fila inferior y los distribuye para que ocupen un 50% de ancho de forma simétrica.
8. En el inspector de propiedades, asigna los nombres en `Field name`: `txtEmail`, `txtPassword`, `btnLogin` y `btnSalir`.
9. Cambia los textos de los componentes a través de la propiedad `text`: `Email`, `Contraseña`, `Login` y `Salir`.
10. Hace clic derecho en el lienzo del formulario y selecciona `Preview` para abrir una ventana interactiva de prueba y verificar el enmascarado del password.

### Buenas Prácticas Observadas
* **Uso de nomenclatura consistente:** Renombrar todos los componentes interactivos con prefijos descriptivos de tres letras (`txt`, `btn`) para mejorar la legibilidad y mantenimiento en la fase de codificación.
* **Uso de JPasswordField:** Utilizar el componente nativo de contraseñas de Swing en lugar de un `JTextField` común para asegurar el ocultamiento de caracteres sensibles a nivel de interfaz de usuario.

---

## Video 10: Diseño del formulario de cambiar password
* **Archivo de video analizado:** [10- Diseño del formulario de cambiar password.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/10-%20Dise%C3%B1o%20del%20formulario%20de%20cambiar%20password.mp4)

### Resumen del Contenido
Se realiza el diseño gráfico de la pantalla para el cambio de contraseñas en `ChangePasswordForm.form`. Se configura un campo de texto para mostrar el correo electrónico del usuario activo y un campo de contraseña para la nueva clave. Como el correo debe recuperarse de la sesión del usuario, se deshabilita la propiedad editable de este campo de texto.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Propiedad de bloqueo de edición de componentes de texto.

### Herramientas Utilizadas
* **IntelliJ IDEA Swing UI Designer.**

### Componentes Swing Utilizados
* **JPanel:** Panel principal (`mainPanel`).
* **JLabel:** Etiquetas para "Email" y "Nueva contraseña".
* **JTextField:** Campo de correo configurado como de solo lectura (`txtEmail`).
* **JPasswordField:** Campo para la nueva clave (`txtPassword`).
* **JButton:** Botón de confirmación (`btnChangePassword` / "Cambiar contraseña").

### Código Mostrado
El diseñador asocia los componentes gráficos de la interfaz con la clase `ChangePasswordForm.java`:
```java
private JPanel mainPanel;
private JTextField txtEmail;
private JPasswordField txtPassword;
private JButton btnChangePassword;
```

### Pasos Realizados por el Docente
1. Abre `ChangePasswordForm.form`.
2. Asigna el nombre `mainPanel` al panel contenedor principal.
3. Arrastra una etiqueta `JLabel` y le cambia su texto a `Email`.
4. Coloca un `JTextField` al lado de la etiqueta para representar el email y lo renombra como `txtEmail`.
5. Selecciona el `txtEmail` y en la ventana de propiedades, desmarca la casilla `editable` para que sea de solo lectura.
6. Agrega una etiqueta `JLabel` con texto `Nueva contraseña` y un `JPasswordField` renombrado como `txtPassword`.
7. Inserta un botón `JButton` renombrado como `btnChangePassword` y le asigna el texto `Cambiar contraseña`.
8. Ejecuta la vista previa (`Preview`) y comprueba que el campo de texto de correo no permite la escritura, mientras que el campo de contraseña sí.

### Buenas Prácticas Observadas
* **Bloqueo preventivo de campos (Read-Only Fields):** Deshabilitar la edición de datos que provienen del contexto del sistema (como el correo del usuario activo) para evitar inconsistencias de seguridad o que el usuario modifique datos de cuentas ajenas.

---

## Video 11: Diseño del formulario de lectura de datos de usuario
* **Archivo de video analizado:** [11- Diseño del formulario de lectura de datos de usuario.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/11-%20Dise%C3%B1o%20del%20formulario%20de%20lectura%20de%20datos%20de%20usuario.mp4)

### Resumen del Contenido
El docente diseña el formulario de búsqueda y visualización de usuarios (`UserReadingForm.form`). Se introduce un buscador por nombre en la cabecera del formulario y se dispone un panel de desplazamiento con una tabla para desplegar los registros. Al final se agregan los botones para navegar hacia las funciones de modificación y eliminación.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Uso de contenedores con scroll y tablas de visualización.

### Herramientas Utilizadas
* **IntelliJ IDEA Swing UI Designer.**

### Componentes Swing Utilizados
* **JPanel:** Contenedor raíz (`mainPanel`).
* **JLabel:** Guía de texto ("Nombre").
* **JTextField:** Campo para introducir el criterio de búsqueda (`txtName`).
* **JScrollPane:** Contenedor con soporte de scroll vertical y horizontal (`scrollPane`).
* **JTable:** Tabla gráfica para mostrar los registros (`tableUsers`).
* **JButton:** Botones de acción (`btnCreate` / "Ir a crear", `btnUpdate` / "Ir a modificar", `btnDelete` / "Ir a eliminar").

### Código Mostrado
Las variables miembro declaradas por el diseñador en `UserReadingForm.java`:
```java
private JPanel mainPanel;
private JTextField txtName;
private JButton btnCreate;
private JTable tableUsers;
private JButton btnUpdate;
private JButton btnDelete;
private JScrollPane scrollPane; // Contenedor del JTable
```

### Pasos Realizados por el Docente
1. Abre `UserReadingForm.form` and nombra al panel contenedor raíz como `mainPanel`.
2. En la parte superior coloca una etiqueta `JLabel` (`Nombre`), un `JTextField` (`txtName`) y un `JButton` (`btnCreate` / "Ir a crear").
3. Arrastra un componente `JScrollPane` en el espacio central y dentro de este inserta una tabla `JTable`.
4. Asigna el identificador `tableUsers` en el `Field name` de la tabla gráfica.
5. Coloca dos botones en una fila inferior, renombrándolos como `btnUpdate` ("Ir a modificar") y `btnDelete` ("Ir a eliminar").
6. Ajusta la distribución de filas y columnas para asegurar la simetría del formulario.
7. Valida el diseño mediante la previsualización (`Preview`), observando el renderizado de la tabla con datos ficticios autogenerados.

### Buenas Prácticas Observadas
* **Uso obligatorio de JScrollPane para JTable:** Insertar siempre las tablas de Swing dentro de un panel con barras de desplazamiento. Esto garantiza que las cabeceras de las columnas se rendericen correctamente y previene el desbordamiento visual de la interfaz si se retornan demasiados registros.

---

## Video 12: Diseño del formulario de escritura de los datos de usuario
* **Archivo de video analizado:** [12-Diseño del formulario de escritura de los datos de usuario.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/12-Dise%C3%B1o%20del%20formulario%20de%20escritura%20de%20los%20datos%20de%20usuario.mp4)

### Resumen del Contenido
El docente maqueta la ventana de creación y edición de usuarios en `UserWriteForm.form`. Diseña un formulario alineado de manera vertical para capturar el nombre, correo, contraseña y estatus del usuario. El estatus se diseña utilizando una lista desplegable.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Controles tipo combobox (listas desplegables).

### Herramientas Utilizadas
* **IntelliJ IDEA Swing UI Designer.**

### Componentes Swing Utilizados
* **JPanel:** Panel contenedor (`mainPanel`).
* **JLabel:** Etiquetas para "Nombre", "Email", "Contraseña" y "Estatus".
* **JTextField:** Campos de texto (`txtName`, `txtEmail`).
* **JPasswordField:** Campo de contraseña (`txtPassword`).
* **JComboBox:** Lista desplegable para el estatus (`cbStatus`).
* **JButton:** Botones de control (`btnOk` / "Ok", `btnCancel` / "Cancelar").

### Código Mostrado
Estructura de variables autogeneradas en `UserWriteForm.java`:
```java
private JPanel mainPanel;
private JTextField txtName;
private JTextField txtEmail;
private JPasswordField txtPassword;
private JComboBox cbStatus;
private JButton btnOk;
private JButton btnCancel;
```

### Pasos Realizados por el Docente
1. Abre `UserWriteForm.form`. Asigna `mainPanel` como identificador al panel raíz.
2. Agrega de forma secuencial descendente cuatro etiquetas `JLabel` con sus respectivos controles a la derecha: `txtName`, `txtEmail`, `txtPassword` y `cbStatus` (JComboBox).
3. Modifica los textos de las etiquetas a: `Nombre *`, `Email *`, `Contraseña *` y `Estatus *` (los asteriscos marcan los campos obligatorios).
4. Agrega los dos botones inferiores `btnOk` ("Ok") y `btnCancel` ("Cancelar") distribuyéndolos de forma proporcional.
5. Inicia el modo `Preview` para validar las propiedades visuales y la correcta distribución del formulario de entrada de datos.

### Buenas Prácticas Observadas
* **Uso de ComboBox para Datos Enumerados:** Utilizar listas desplegables para atributos de dominio acotados (como los estados Activo/Inactivo), impidiendo el ingreso de texto erróneo y estandarizando las entradas para persistencia.
* **Indicación visual de campos requeridos:** El uso del asterisco `*` en los textos de las etiquetas ayuda al usuario a identificar inmediatamente qué datos son obligatorios antes de enviar el formulario.

---

## Video 13: Codificación del formulario de iniciar sesión
* **Archivo de video analizado:** [13-Codificacion del formulario de iniciar sesion.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20App%20Java%20Swing/13-Codificacion%20del%20formulario%20de%20iniciar%20sesion.mp4)

### Resumen del Contenido
El docente programa el código del formulario de login (`LoginForm.java`). Modifica la clase `MainForm` para alojar la sesión del usuario. El constructor de `LoginForm` configura la ventana modal de login para centrarla en la pantalla. Asocia eventos a los botones y sobreescribe el cierre de la ventana por la cruz gráfica. En el método de login se conecta a la base de datos a través de `UserDAO` y valida las credenciales.

### Tecnologías Utilizadas
* **Java 21, Swing y JDBC:** Eventos `ActionListener`, escuchadores de ventana `WindowAdapter` y el patrón Data Access Object (`UserDAO`).

### Herramientas Utilizadas
* **IntelliJ IDEA:** Editor de código de Java y depurador.

### Componentes Swing Utilizados
* **JDialog:** Ventana contenedora configurada como modal.
* **JOptionPane:** Ventanas de diálogo emergentes para mensajes (`showMessageDialog`).

### Código Mostrado
#### Lógica completa en `LoginForm.java` (sin comentarios grandes):
```java
package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;
import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JDialog {
    private JPanel mainPanel;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSalir;

    private UserDAO userDAO;
    private MainForm mainForm;

    public LoginForm(MainForm mainForm) {
        super(mainForm, true); // Modal
        this.mainForm = mainForm;
        this.userDAO = new UserDAO();

        setContentPane(mainPanel);
        setModal(true);
        setTitle("Login");
        pack();
        setLocationRelativeTo(mainForm);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void login() {
        try {
            User user = new User();
            user.setEmail(txtEmail.getText());
            user.setPasswordHash(new String(txtPassword.getPassword()));

            User userAuth = userDAO.authenticate(user);
            if (userAuth != null && userAuth.getId() > 0 && userAuth.getEmail().equals(user.getEmail())) {
                mainForm.setUserAuthenticate(userAuth);
                this.dispose(); // Cierra login y vuelve al principal
            } else {
                JOptionPane.showMessageDialog(this, "Email y password incorrecto", "Login", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
```

### Pasos Realizados por el Docente
1. Abre `MainForm.java` y crea la propiedad `userAuthenticate` (tipo `User`) con su respectivo par Getter y Setter.
2. Abre `LoginForm.java`. Declara las variables miembros para `UserDAO` y `MainForm`.
3. Modifica la firma del constructor para recibir una instancia de `MainForm`. Invoca a `super(mainForm, true)` para enlazar la ventana al padre y hacerla modal.
4. Inicializa `userDAO = new UserDAO();`.
5. Llama a `setContentPane(mainPanel);` e inicializa las propiedades de la ventana: `setTitle("Login")`, `pack()` (ajustar componentes) y `setLocationRelativeTo(mainForm)` (centrado).
6. Implementa el escuchador para `btnSalir` forzando la terminación inmediata del proceso del sistema con `System.exit(0)`.
7. Vincula un escuchador de evento al botón `btnLogin` invocando la lógica interna de `login()`.
8. Registra un `WindowListener` utilizando la clase adaptador `WindowAdapter` y sobreescribe `windowClosing` para evitar que el usuario se salte el login cerrando la ventana desde la X.
9. En el método `login()`, lee las entradas de texto del formulario, crea un objeto de dominio `User` y llama al servicio de autenticación de `UserDAO`.
10. Si el resultado es exitoso, inyecta el usuario autenticado en el formulario padre (`mainForm.setUserAuthenticate(userAuth)`) y destruye la ventana con `dispose()`.
11. En caso de fallas, invoca un mensaje en un diálogo de advertencia de `JOptionPane`.

### Buenas Prácticas Observadas
* **Sobreescritura de Cierre de Ventana:** Controlar manualmente el cierre de las ventanas críticas usando `WindowListener`. En el login, cerrar la modal con la X debe abortar la aplicación (`System.exit(0)`) y no dejar al usuario acceder a la pantalla principal oculta.
* **Seguridad en la Recuperación de Contraseña:** Usar `txtPassword.getPassword()` que retorna un arreglo de caracteres (`char[]`) para realizar la conversión controlada en lugar de obtener strings inmutables directamente de la interfaz de usuario.

---

## Video 14: Codificación del formulario de cambiar password
* **Archivo de video analizado:** [14- Codificacion del formulario de cambiar password.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/14-%20Codificacion%20del%20formulario%20de%20cambiar%20password.mp4)

### Resumen del Contenido
El docente programa el cambio de contraseñas de la cuenta activa en `ChangePasswordForm.java`. El formulario se inicia con el correo electrónico del usuario pre-cargado como solo lectura. Al presionar el botón se valida que el campo de contraseña no esté vacío y se procesa la actualización. Al terminar el cambio, el sistema obliga al usuario a re-autenticarse.

### Tecnologías Utilizadas
* **Java 21, Swing y JDBC:** Eventos, validaciones de cadenas en tiempo de ejecución.

### Herramientas Utilizadas
* **IntelliJ IDEA.**

### Componentes Swing Utilizados
* **JDialog:** Ventana secundaria modal.
* **JOptionPane:** Cuadros de diálogo de información y advertencia.

### Código Mostrado
#### Lógica completa en `ChangePasswordForm.java`:
```java
package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;
import javax.swing.*;
import java.awt.event.*;

public class ChangePasswordForm extends JDialog {
    private JPanel mainPanel;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnChangePassword;

    private UserDAO userDAO;
    private MainForm mainForm;

    public ChangePasswordForm(MainForm mainForm) {
        super(mainForm, true);
        this.mainForm = mainForm;
        this.userDAO = new UserDAO();

        setContentPane(mainPanel);
        setModal(true);
        setTitle("Cambiar password");
        pack();
        setLocationRelativeTo(mainForm);

        // Pre-cargar correo del usuario autenticado
        txtEmail.setText(mainForm.getUserAuthenticate().getEmail());

        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        try {
            User userAuth = mainForm.getUserAuthenticate();
            User user = new User();
            user.setId(userAuth.getId());

            String newPassword = new String(txtPassword.getPassword()).trim();
            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La contraseña es obligatoria.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            user.setPasswordHash(newPassword);

            boolean res = userDAO.updatePassword(user);
            if (res) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada con éxito. Por favor, inicie sesión nuevamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // Cierra formulario
                LoginForm loginForm = new LoginForm(mainForm);
                loginForm.setVisible(true); // Fuerza nuevo login
            } else {
                JOptionPane.showMessageDialog(this, "No se logró cambiar la contraseña.", "Cambiar contraseña", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
```

### Pasos Realizados por el Docente
1. Modifica el constructor del formulario `ChangePasswordForm` para recibir a `MainForm`.
2. Asigna las propiedades de la ventana y centra el diálogo.
3. Recupera los datos del usuario en sesión (`mainForm.getUserAuthenticate()`) e inserta el correo del usuario activo en el campo bloqueado `txtEmail`.
4. Asocia la lógica de actualización al botón `btnChangePassword` llamando al método `changePassword()`.
5. En `changePassword()`, lee y valida que la contraseña ingresada no esté vacía utilizando `trim()` y `isEmpty()`. Si está vacía, interrumpe el flujo y muestra una alerta.
6. Hasa la clave ingresada y llama a `userDAO.updatePassword(user)`.
7. Si el resultado es exitoso, cierra la modal y re-abre de forma síncrona la pantalla modal de login `LoginForm` para forzar al usuario a validar la nueva clave.

### Buenas Prácticas Observadas
* **Forzar Re-autenticación tras cambio de credenciales:** Una vez que la contraseña de una sesión ha sido modificada con éxito, se debe cerrar la ventana de trabajo y pedir las credenciales de nuevo. Esto evita que la sesión permanezca abierta si un tercero la modificó.
* **Uso de Trim en contraseñas:** Eliminar espacios en blanco en los extremos del texto ingresado antes de realizar validaciones de longitud.

---

## Video 15: Codificación del formulario de escritura de usuario
* **Archivo de video analizado:** [15- Codificacion del formulario de escritura de usuario.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/15-%20Codificacion%20del%20formulario%20de%20escritura%20de%20usuario.mp4)

### Resumen del Contenido
El docente programa el formulario de escritura (`UserWriteForm.java`). Modifica la maquetación visual para renombrar controles gráficos y permitir ocultar el campo de clave. En el código, inicializa el combobox de estatus cargando objetos `CBOption` (Activo/Inactivo). Basado en el tipo de operación `CUD` recibida (Crear, Modificar, Eliminar), el formulario adapta dinámicamente su título, los textos de sus botones y la visibilidad o edición de sus campos.

### Tecnologías Utilizadas
* **Java 21, Swing y enums:** Uso de la clase de utilidad `CBOption`, del enum `CUD` y enmascaramiento dinámico de campos.

### Herramientas Utilizadas
* **IntelliJ IDEA.**

### Componentes Swing Utilizados
* **JDialog, JComboBox, JPasswordField, JLabel, JButton, JOptionPane.**

### Código Mostrado
#### Lógica de inicialización y adaptación del formulario en `UserWriteForm.java`:
```java
package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;
import esfe.utils.CBOption;
import esfe.utils.CUD;
import javax.swing.*;
import java.awt.event.*;

public class UserWriteForm extends JDialog {
    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox cbStatus;
    private JButton btnOk;
    private JButton btnCancel;
    private JLabel lbPassword; // Etiqueta de contraseña

    private UserDAO userDAO;
    private MainForm mainForm;
    private CUD cud; // Operación actual (CREATE, UPDATE, DELETE)
    private User user; // Usuario a procesar

    public UserWriteForm(MainForm mainForm, CUD cud, User user) {
        super(mainForm, true);
        this.mainForm = mainForm;
        this.cud = cud;
        this.user = user;
        this.userDAO = new UserDAO();

        setContentPane(mainPanel);
        setModal(true);
        pack();
        setLocationRelativeTo(mainForm);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.dispose();
            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok();
            }
        });

        init();
    }

    private void init() {
        initCBStatus();
        switch (cud) {
            case CREATE:
                setTitle("Crear Usuario");
                btnOk.setText("Guardar");
                break;
            case UPDATE:
                setTitle("Modificar Usuario");
                btnOk.setText("Guardar");
                setValuesControls(user);
                break;
            case DELETE:
                setTitle("Eliminar Usuario");
                btnOk.setText("Eliminar");
                setValuesControls(user);
                // Bloquea campos para solo visualización en eliminación
                txtName.setEditable(false);
                txtEmail.setEditable(false);
                cbStatus.setEnabled(false);
                break;
        }
    }

    private void initCBStatus() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbStatus.getModel();
        model.removeAllElements();
        model.addElement(new CBOption("ACTIVO", (byte) 1));
        model.addElement(new CBOption("INACTIVO", (byte) 2));
    }

    private void setValuesControls(User user) {
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());

        // Seleccionar en el combobox según el estatus
        cbStatus.setSelectedItem(new CBOption(user.getStatus(), user.getStatus() == "ACTIVE" ? (byte)1 : (byte)2));

        // Si no es creación, se ocultan los campos de contraseña
        if (cud != CUD.CREATE) {
            txtPassword.setVisible(false);
            lbPassword.setVisible(false);
        }
    }

    // Validaciones y persistencia se detallan más adelante...
}
```

### Pasos Realizados por el Docente
1. Abre `UserWriteForm.form`. Selecciona la etiqueta y campo de clave y los renombra a `lbPassword` y `txtPassword` para poder manipular su visibilidad a nivel de clase.
2. Agrega asteriscos a las etiquetas para marcar la obligatoriedad.
3. Modifica la clase Java para definir propiedades para `CUD` y `User`.
4. En el constructor, guarda estos parámetros y asocia las acciones del botón cancelar (`dispose()`) y botón Ok (`ok()`).
5. Implementa `init()` para evaluar con un `switch` el tipo de operación. Adapta el título de la ventana y el texto del botón de acción.
6. En caso de `CUD.DELETE`, bloquea todos los campos marcando `setEditable(false)` en los campos de texto e inhabilitando el combobox (`setEnabled(false)`).
7. Crea el método `initCBStatus()` para limpiar y llenar el combobox utilizando instancias de `CBOption` con los valores de bytes 1 y 2.
8. En `setValuesControls()`, carga los datos en las cajas de texto y el combo, y oculta dinámicamente los campos `txtPassword` y `lbPassword` si la operación es de modificación o eliminación.

### Buenas Prácticas Observadas
* **Polimorfismo en interfaces (Reutilización de Vistas):** Usar un único formulario físico (`UserWriteForm`) para realizar tres acciones lógicas distintas (Crear, Modificar, Eliminar) mediante la parametrización de un Enum (`CUD`). Esto evita la redundancia de código y pantallas, simplificando el mantenimiento.
* **Uso de CBOption:** Permite poblar los `JComboBox` mapeando objetos complejos (DisplayText, Value) en lugar de cadenas simples. Swing utiliza el método `toString()` sobreescrito en `CBOption` para renderizar el texto ("ACTIVO"), mientras que a nivel de negocio se recupera el byte correspondiente (1) de forma segura.

---

## Video 16: Codificación del formulario de lectura de datos de usuario
* **Archivo de video analizado:** [16- Codificacion del formulario de lectura de datos de usuario.mp4](file:///c:/Users/corte/Downloads/Material%20de%20Apoyo%20front-end%20%20App%20Java%20Swing/16-%20Codificacion%20del%20formulario%20de%20lectura%20de%20datos%20de%20usuario.mp4)

### Resumen del Contenido
Se codifica la lógica de la pantalla de búsqueda y consulta de usuarios (`UserReadingForm.java`). Se implementa la búsqueda interactiva en tiempo real al escribir en el buscador y el renderizado dinámico de la tabla. También se codifica la navegación y transporte de datos a las ventanas modales de creación, edición y eliminación de registros.

### Tecnologías Utilizadas
* **Java 21, Swing y JDBC:** Modelo de tabla `DefaultTableModel`, eventos de teclado `KeyAdapter`, y ocultación de columnas.

### Herramientas Utilizadas
* **IntelliJ IDEA.**

### Componentes Swing Utilizados
* **JDialog, JTable, JScrollPane, JTextField, JButton, JOptionPane.**

### Código Mostrado
#### Lógica de búsqueda y carga de tabla en `UserReadingForm.java`:
```java
package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;
import esfe.utils.CUD;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class UserReadingForm extends JDialog {
    private JPanel mainPanel;
    private JTextField txtName;
    private JButton btnCreate;
    private JTable tableUsers;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JScrollPane scrollPane;

    private UserDAO userDAO;
    private MainForm mainForm;

    public UserReadingForm(MainForm mainForm) {
        super(mainForm, true);
        this.mainForm = mainForm;
        this.userDAO = new UserDAO();

        setContentPane(mainPanel);
        setModal(true);
        setTitle("Buscar usuario");
        pack();
        setLocationRelativeTo(mainForm);

        // Búsqueda en tiempo real mediante evento de teclado
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = txtName.getText().trim();
                if (!query.isEmpty()) {
                    search(query);
                } else {
                    createTable(new ArrayList<User>()); // Limpia la tabla
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserWriteForm form = new UserWriteForm(mainForm, CUD.CREATE, new User());
                form.setVisible(true);
                createTable(new ArrayList<User>()); // Limpia o refresca
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selected = getUserFromTableRows();
                if (selected != null) {
                    UserWriteForm form = new UserWriteForm(mainForm, CUD.UPDATE, selected);
                    form.setVisible(true);
                    createTable(new ArrayList<User>());
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selected = getUserFromTableRows();
                if (selected != null) {
                    UserWriteForm form = new UserWriteForm(mainForm, CUD.DELETE, selected);
                    form.setVisible(true);
                    createTable(new ArrayList<User>());
                }
            }
        });
    }

    private void search(String name) {
        try {
            ArrayList<User> list = userDAO.search(name);
            createTable(list);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createTable(ArrayList<User> list) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Celdas no editables
            }
        };

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Email");
        model.addColumn("Estatus");

        tableUsers.setModel(model);

        for (User u : list) {
            Object[] row = new Object[4];
            row[0] = u.getId();
            row[1] = u.getName();
            row[2] = u.getEmail();
            row[3] = u.getStatus(); // ACTIVE/INACTIVE
            model.addRow(row);
        }
        hideCol(0); // Ocultar columna ID
    }

    private void hideCol(int colIndex) {
        tableUsers.getColumnModel().getColumn(colIndex).setMinWidth(0);
        tableUsers.getColumnModel().getColumn(colIndex).setMaxWidth(0);
        tableUsers.getColumnModel().getColumn(colIndex).setPreferredWidth(0);
        tableUsers.getTableHeader().getColumnModel().getColumn(colIndex).setMinWidth(0);
        tableUsers.getTableHeader().getColumnModel().getColumn(colIndex).setMaxWidth(0);
    }

    private User getUserFromTableRows() {
        User user = null;
        try {
            int selectedRow = tableUsers.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableUsers.getValueAt(selectedRow, 0);
                user = userDAO.getById(id);
                if (user == null || user.getId() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con ese ID.", "Validación", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla.", "Validación", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }
}
```

### Pasos Realizados por el Docente
1. Abre `UserReadingForm.java` e inicializa la lógica del constructor.
2. Registra un `KeyListener` en `txtName` sobreescribiendo el método `keyReleased` para que se lance la búsqueda de usuarios en la base de datos automáticamente cada vez que el usuario escribe o borra un carácter.
3. Programa los botones `btnCreate`, `btnUpdate` y `btnDelete` para que abran el formulario de escritura `UserWriteForm` con el enum `CUD` y el usuario correspondiente.
4. Implementa el método `search()` para realizar la consulta SQL a través de `UserDAO` y llamar a `createTable()`.
5. En `createTable()`, instancia un `DefaultTableModel` sobreescribiendo `isCellEditable()` para retornar `false` en todas las celdas, protegiendo los datos. Llama a `model.addColumn()` para definir los encabezados y recorre la lista poblando las filas.
6. Llama al método `hideCol(0)` para ocultar la columna del ID de usuario, asegurando que no se muestre al usuario pero esté disponible para recuperar el ID al seleccionar una fila.
7. Crea `getUserFromTableRows()` para recuperar la fila seleccionada, extraer el valor del ID y consultar los datos completos del usuario mediante `userDAO.getById(id)`.

### Buenas Prácticas Observadas
* **Protección contra edición en tablas (Read-Only Tables):** Sobreescribir el comportamiento por defecto de las tablas en Swing para que las celdas no sean editables por doble clic, centralizando la edición de datos a través de formularios dedicados.
* **Ocultación visual de identificadores de persistencia:** Mantener los identificadores técnicos de base de datos (`ID`) ocultos en la vista pero almacenados en el modelo de la tabla, de modo que puedan ser accedidos mediante programación sin saturar visualmente al usuario.
* **Búsqueda interactiva asíncrona:** El uso del evento `keyReleased` permite que la tabla de resultados se actualice de manera automática y fluida a medida que el usuario tipea el nombre a buscar.

---

## Video 17: Codificación del formulario principal
* **Archivo de video analizado:** [17-Codificacion del formulario principal.mp4](file:///c:/Users/corte/Downloads/Material de Apoyo front-end App Java Swing/17-Codificacion del formulario principal.mp4)

### Resumen del Contenido
El docente programa la ventana principal de la aplicación (`MainForm.java`). Configura el comportamiento base de la ventana, forzándola a iniciarse maximizada. Implementa la barra de menú superior de forma dinámica mediante código, asociando escuchadores de eventos para desplegar las modales de login, cambio de contraseña, mantenimiento de usuarios y el cierre definitivo del sistema.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Barra de menús dinámica (`JMenuBar`, `JMenu`, `JMenuItem`).

### Herramientas Utilizadas
* **IntelliJ IDEA.**

### Componentes Swing Utilizados
* **JFrame:** Contenedor de nivel superior para la ventana principal.
* **JMenuBar:** Barra de menús superior.
* **JMenu:** Menús de la barra ("Perfil", "Mantenimientos").
* **JMenuItem:** Opciones individuales de los menús ("Cambiar contraseña", "Cambiar usuario", "Salir", "Usuarios").

### Código Mostrado
#### Lógica completa en `MainForm.java`:
```java
package esfe.presentacion;

import esfe.dominio.User;
import javax.swing.*;
import java.awt.event.*;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private User userAuthenticate;

    public User getUserAuthenticate() {
        return userAuthenticate;
    }

    public void setUserAuthenticate(User userAuthenticate) {
        this.userAuthenticate = userAuthenticate;
    }

    public MainForm() {
        setTitle("Sistema en java de escritorio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Iniciar maximizado
        setContentPane(mainPanel);
        createMenu();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menú Perfil
        JMenu menuPerfil = new JMenu("Perfil");
        menuBar.add(menuPerfil);

        JMenuItem itemChangePassword = new JMenuItem("Cambiar contraseña");
        menuPerfil.add(itemChangePassword);
        itemChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePasswordForm form = new ChangePasswordForm(MainForm.this);
                form.setVisible(true);
            }
        });

        JMenuItem itemChangeUser = new JMenuItem("Cambiar de usuario");
        menuPerfil.add(itemChangeUser);
        itemChangeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm form = new LoginForm(MainForm.this);
                form.setVisible(true);
            }
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        menuPerfil.add(itemSalir);
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Menú Mantenimientos
        JMenu menuMantenimiento = new JMenu("Mantenimientos");
        menuBar.add(menuMantenimiento);

        JMenuItem itemUsers = new JMenuItem("Usuarios");
        menuMantenimiento.add(itemUsers);
        itemUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserReadingForm form = new UserReadingForm(MainForm.this);
                form.setVisible(true);
            }
        });
    }
}
```

### Pasos Realizados por el Docente
1. Abre `MainForm.java` y configura el constructor para establecer el título y que al cerrar la ventana termine el proceso con `JFrame.EXIT_ON_CLOSE`.
2. Establece `setExtendedState(JFrame.MAXIMIZED_BOTH)` para que al abrir la ventana se muestre en pantalla completa.
3. Diseña el método `createMenu()` para inicializar programáticamente la barra de menú. Instancia `JMenuBar` y la asocia al JFrame con `setJMenuBar(menuBar)`.
4. Crea los menús principales `JMenu` para "Perfil" y "Mantenimientos" y los agrega a la barra.
5. Declara e instancia los elementos de menú `JMenuItem` para cada opción.
6. A cada `JMenuItem`, le asocia su correspondiente manejador de eventos `ActionListener` para lanzar los diálogos modales correspondientes pasándoles `MainForm.this` como ventana padre.

### Buenas Prácticas Observadas
* **Creación programática de menús:** Diseñar menús mediante código dinámico en lugar de herramientas de arrastrar y soltar. Esto facilita la adición dinámica de opciones basadas en roles del usuario o configuraciones en tiempo de ejecución.
* **Uso correcto de EXIT_ON_CLOSE:** Asegurar que la ventana principal de la aplicación tenga configurada la finalización del proceso de la máquina virtual al cerrarse, evitando que queden proces en segundo plano.

---

## Video 18: Codificación de la clase Main
* **Archivo de video analizado:** [18- Codificacion de la clase Main.mp4](file:///c:/Users/corte/Downloads/Material de Apoyo front-end App Java Swing/18- Codificacion de la clase Main.mp4)

### Resumen del Contenido
El docente codifica el punto de entrada principal del sistema (`Main.java`). Se eliminan los códigos de prueba anteriores y se implementa la inicialización de la interfaz en el hilo de despacho de eventos de Swing de forma segura. Se cargan y muestran tanto la ventana principal como la modal de login.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Hilo de despacho de eventos (Event Dispatch Thread - EDT).

### Herramientas Utilizadas
* **IntelliJ IDEA.**

### Componentes Swing Utilizados
* **SwingUtilities:** Clase utilitaria para interactuar de forma segura con el EDT.

### Código Mostrado
#### Clase completa `Main.java`:
```java
package esfe;

import esfe.presentacion.LoginForm;
import esfe.presentacion.MainForm;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ejecución segura dentro del Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainForm mainForm = new MainForm();
                mainForm.setVisible(true); // Abre principal maximizado

                LoginForm loginForm = new LoginForm(mainForm);
                loginForm.setVisible(true); // Abre login como modal sobrepuesto
            }
        });
    }
}
```

### Pasos Realizados por el Docente
1. Abre el archivo de inicio `Main.java`.
2. Importa las clases del paquete `presentacion` y la utilidad `SwingUtilities`.
3. Dentro del método estático `main`, invoca a `SwingUtilities.invokeLater()`.
4. En el método `run()` del `Runnable`, realiza la instanciación de `MainForm` y la hace visible.
5. Inmediatamente después, crea el formulario de login `LoginForm` pasándole la instancia de `mainForm` como parámetro y lo hace visible.

### Buenas Prácticas Observadas
* **Inicialización en el EDT (Event Dispatch Thread):** Utilizar siempre `SwingUtilities.invokeLater` para iniciar las interfaces gráficas. Swing no es seguro para subprocesos múltiples (Not Thread-Safe), por lo que todas las operaciones de visualización y modificación de componentes deben ser despachadas en este hilo único de eventos para evitar bloqueos y comportamientos inesperados.

---

## Video 19: Prueba de la funcionalidad de las pantallas
* **Archivo de video analizado:** [19-Prueba de la funcionalidad de las pantallas.mp4](file:///c:/Users/corte/Downloads/Material de Apoyo front-end App Java Swing/19-Prueba de la funcionalidad de las pantallas.mp4)

### Resumen del Contenido
Se realiza una fase completa de pruebas del sistema. Se inserta un usuario administrador de forma programática a través de JUnit. Posteriormente se ejecuta la aplicación y se comprueban los flujos de login, búsqueda, creación de usuarios, inhabilitación y eliminación, validando la integridad con SQL Server.

### Tecnologías Utilizadas
* **JUnit 5 y SQL Server Management Studio (SSMS):** Inserción e inspección física de registros.

### Herramientas Utilizadas
* **IntelliJ IDEA y SQL Server.**

### Componentes Swing Probados
* **JFrame, JDialog, JTable, JTextField, JPasswordField, JComboBox, JOptionPane.**

### Código Mostrado
El docente ejecuta el método de prueba de JUnit en `UserDAOTest.java` para poblar el usuario base:
```java
@Test
public void createUser() throws SQLException {
    User user = new User(0, "admin", PasswordHasher.hashPassword("12345"), "admin@gmail.com", (byte) 1);
    User res = userDAO.create(user);
    assertNotNull(res, "El registro no debe retornar nulo");
    assertTrue(res.getId() > 0, "El ID generado debe ser mayor a 0");
}
```

### Pasos Realizados por el Docente
1. Abre `UserDAOTest.java` y ejecuta la prueba unitaria `createUser` para registrar al administrador base (`admin@gmail.com` / `12345`).
2. Abre SSMS y ejecuta una consulta SQL a la tabla `Users` para verificar que la fila del administrador se insertó con estatus 1 (ACTIVO).
3. Inicia la aplicación en IntelliJ ejecutando la clase `Main`.
4. En la pantalla modal de login, introduce datos erróneos para forzar la advertencia de `JOptionPane`.
5. Introduce los datos válidos del administrador, se valida el login, se cierra la modal y se desbloquea el menú principal.
6. Abre el formulario de búsqueda de usuarios (`UserReadingForm`) a través del menú.
7. Comprueba el filtrado en tiempo real en la tabla escribiendo caracteres en el buscador.
8. Hace clic en "Ir a crear" e inserta un nuevo usuario ("Marvin" / `marvin@gmail.com` / contraseña `2025` / estatus ACTIVO). Guarda y comprueba que aparece en la tabla.
9. Selecciona a Marvin en la tabla y presiona "Ir a modificar". Modifica su estatus a `INACTIVO` y guarda los cambios.
10. Cierra la ventana y prueba la opción de "Cambiar usuario" en el menú principal. Intenta iniciar sesión con la cuenta inactiva de Marvin y comprueba que el sistema no lo permite.
11. Vuelve a iniciar sesión como administrador, accede al panel de usuarios, selecciona a Marvin y presiona "Ir a eliminar". Confirma la eliminación y valida en la base de datos local que el registro de Marvin ya no existe.
12. Comprueba el comportamiento de los botones de cierre del formulario de login y los menús de salir del sistema principal.

### Buenas Prácticas Observadas
* **Smoke Testing de Flujos Completos:** Probar el flujo integral (de punta a punta) del sistema, validando que las modales de creación refresquen el contenido de las tablas de los buscadores tras realizar transacciones de datos.
* **Control de accesos por estado (Status Check):** Impedir el inicio de sesión a usuarios inactivos en el sistema en tiempo real.

---

## Video 20: Corrección al crear nuevo usuario validar password obligatorio
* **Archivo de video analizado:** [20- Correcion al crear nuevo usuario validar password obligatorio.mp4](file:///c:/Users/corte/Downloads/Material de Apoyo front-end App Java Swing/20- Correcion al crear nuevo usuario validar password obligatorio.mp4)

### Resumen del Contenido
El docente implementa una corrección en el formulario `UserWriteForm.java`. Se detecta un bug que permitía registrar usuarios sin contraseña (con cadena vacía). Se modifica el método de captura de campos para validar que la contraseña no sea vacía al crear un usuario.

### Tecnologías Utilizadas
* **Java 21 y Swing API:** Validaciones y depuración lógica.

### Herramientas Utilizadas
* **IntelliJ IDEA.**

### Componentes Swing Corregidos
* **UserWriteForm:** Modificaciones al método `getValuesControls()` e interacción con `JOptionPane`.

### Código Mostrado
#### Modificaciones en `UserWriteForm.java` para corregir la validación de la contraseña:
```java
private boolean getValuesControls() {
    boolean res = false;
    // ... Recuperación de otros campos del formulario ...

    // Validación de la contraseña en modo de creación
    if (this.cud == CUD.CREATE) {
        String password = new String(txtPassword.getPassword()).trim();
        if (password.isEmpty()) {
            return false; // Detiene el flujo indicando que los controles no son válidos
        }
        this.user.setPasswordHash(PasswordHasher.hashPassword(password));
    }

    res = true; // Si pasa las validaciones, retorna verdadero
    return res;
}
```

### Pasos Realizados por el Docente
1. Inicia la aplicación y en `UserWriteForm` crea un usuario de prueba dejando el campo de contraseña en blanco. Presiona "Guardar" y nota que el usuario se crea sin contraseña.
2. Cierra la aplicación y edita `UserWriteForm.java`.
3. Navega al método de validación interna `getValuesControls()`.
4. En el bloque condicional `if (this.cud == CUD.CREATE)`, lee el password del campo `txtPassword` y le aplica `.trim().isEmpty()`.
5. Si el resultado es verdadero (vacío), retorna `false` inmediatamente para abortar la transacción y activar el mensaje de error de comandos obligatorios en el método que invoca a la validación.
6. Vuelve a compilar y ejecutar el sistema.
7. Comprueba que al intentar registrar a un usuario sin contraseña, el sistema muestra el mensaje de alerta "Los campos con * son obligatorios".
8. Comprueba que al capturar una contraseña válida, el registro se realiza con éxito en la base de datos.

### Buenas Prácticas Observadas
* **Validaciones estrictas en la Capa de Presentación:** Realizar validaciones de datos requeridos a nivel de UI antes de interactuar con las capas inferiores o base de datos. Esto previene llamadas innecesarias y asegura que el Modelo contenga siempre datos íntegros.
* **Uso de Trim en validaciones críticas:** Asegurar que los datos ingresados no contengan solo espacios en blanco antes de dar por buena una entrada.

---

## Conclusión General del Módulo Front-End

El desarrollo de la interfaz gráfica de usuario en **Java Swing** mediante el uso del Swing UI Designer de **IntelliJ IDEA** demuestra ser una alternativa eficiente para aplicaciones de escritorio. A lo largo de este módulo, se han observado principios clave de ingeniería de software aplicados a la interfaz de usuario:

1. **Separación de Responsabilidades:** El uso de una arquitectura organizada en capas permite que el paquete de presentación interaccione con el negocio de forma limpia, haciendo uso del patrón DAO para delegar el acceso físico a base de datos.
2. **Ciclo de Vida de las Ventanas:** La utilización estratégica de `JFrame` para la ventana de nivel superior maximizada y de `JDialog` síncronos y modales para las tareas secundarias permite un flujo de control estructurado y seguro, previniendo el acceso no autorizado.
3. **Reutilización y Polimorfismo en Vistas:** La parametrización del formulario de captura a través del enum `CUD` demuestra que es posible diseñar interfaces compactas capaces de responder dinámicamente según la operación de negocio solicitada, reduciendo la redundancia de código.
4. **Validaciones en el Cliente:** La implementación de validaciones locales y búsquedas en tiempo real optimizan el consumo de red y garantizan que solo información consistente se persista en base de datos.

## Lista Consolidada de Componentes Swing Utilizados

A continuación, se presenta la lista consolidada de componentes de la librería `javax.swing` y clases gráficas asociadas que fueron implementados a lo largo de las pantallas de la aplicación:

* **JFrame:** Contenedor de nivel superior para la ventana principal maximizada de la aplicación (`MainForm`).
* **JDialog:** Contenedor modal y síncrono para las ventanas secundarias (`LoginForm`, `ChangePasswordForm`, `UserReadingForm`, `UserWriteForm`).
* **JPanel:** Contenedor intermedio base autogenerado para organizar los diseños visuales de cada interfaz.
* **JLabel:** Etiquetas de texto informativo utilizadas en todos los formularios para identificar campos de datos.
* **JTextField:** Caja de entrada de texto de línea única para captura de datos libres (como el correo o búsquedas).
* **JPasswordField:** Caja de entrada de texto enmascarada especializada en la captura de contraseñas.
* **JComboBox:** Lista desplegable utilizada en la captura y mapeo de datos de estatus (Activo/Inactivo).
* **JTable:** Rejilla gráfica para renderizar listas de registros estructurados obtenidos de las consultas.
* **JScrollPane:** Contenedor de soporte de scroll de visualización necesario para JTable.
* **JMenuBar:** Barra superior que contiene las opciones generales del menú.
* **JMenu:** Menú desplegable agrupado en la barra superior.
* **JMenuItem:** Opciones individuales del menú asociadas a sus respectivos ActionListener.
* **JOptionPane:** Diálogos preestablecidos de salida rápida para mensajes informativos, de advertencia o errores.
* **SwingUtilities:** Clase utilitaria para garantizar que el despliegue del software ocurra de manera segura en el hilo EDT.
