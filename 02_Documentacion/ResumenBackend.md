# Resumen del Módulo Backend - Cajero Automático Swing

Este documento resume de manera técnica y estructurada los temas, procesos, código y mejores prácticas observadas en los videos 2 al 6 del módulo de apoyo Backend para el desarrollo de la aplicación de escritorio en Java Swing.

---

## Video 2: Crear el Proyecto
* **Archivo de video analizado:** [2-Crear el proyecto.mp4](file:///c:/Users/corte/OneDrive/Documentos/CajeroAutomaticoSwing/01_VideosDocente/Material%20de%20Apoyo%20Backend%20App%20Java%20Swing/2-Crear%20el%20proyecto.mp4)

### Resumen del Contenido
El docente realiza la creación del proyecto base denominado `SecurityApp` utilizando el IDE IntelliJ IDEA. Configura el proyecto con la herramienta de automatización de compilación Maven y la versión LTS de Java 21, y comprueba que la estructura se compile e inicie correctamente mediante la impresión de un mensaje por consola desde la clase `Main`. Para finalizar, realiza una limpieza inicial borrando la plantilla de código por defecto.

### Tecnologías Utilizadas
* **Java 21 (LTS):** Lenguaje de desarrollo para codificar la lógica del programa.
* **Maven:** Sistema de automatización de construcción para gestionar dependencias y el ciclo de vida del proyecto.

### Herramientas Utilizadas
* **IntelliJ IDEA Community Edition 2025.1.1.1:** Entorno de Desarrollo Integrado (IDE) utilizado.
* **Windows 10/11 (Sistema Operativo):** Sistema operativo del entorno local.

### Código Mostrado
La clase `Main.java` simplificada por el docente para pruebas de ejecución:
```java
package esfe;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
    }
}
```

### Pasos Realizados por el Docente
1. Abre IntelliJ IDEA en su entorno local.
2. Navega a `File` -> `New` -> `Project...`.
3. En la ventana de configuración:
    * Asigna el nombre `SecurityApp` al proyecto.
    * Define la ruta de almacenamiento (Location).
    * Selecciona `Maven` como sistema de construcción (`Build system`).
    * Configura el JDK apuntando a `21 (Oracle OpenJDK 21.0.2)`.
4. Abre la sección de `Advanced Settings` y asigna `esfe` en `GroupId` y `SecurityApp` en `ArtifactId`.
5. Hace clic en `Create` y selecciona abrir en una nueva ventana (`New Window`).
6. Tras la indexación inicial del IDE, abre `src/main/java/esfe/Main.java`.
7. Ejecuta el código haciendo clic en el botón verde de reproducción situado junto a la definición del método `main`.
8. Comprueba en la consola la salida del mensaje `"Hello and welcome!"`.
9. Elimina el bucle `for` de plantilla autogenerado para mantener el archivo base limpio.

### Buenas Prácticas Observadas
* **Uso de un sistema estándar de construcción (Maven):** Esto asegura la portabilidad y la fácil gestión de las dependencias externas que se integrarán en pasos futuros.
* **Prueba de sanidad inmediata (Smoke Test):** Validar que el entorno y el JDK estén correctamente enlazados ejecutando la aplicación básica antes de añadir código de lógica compleja.

---

## Video 3: Configurar la Conexión en el Proyecto
* **Archivo de video analizado:** [3- Configurar la conexion en el proyecto.mp4](file:///c:/Users/corte/OneDrive/Documentos/CajeroAutomaticoSwing/01_VideosDocente/Material%20de%20Apoyo%20Backend%20App%20Java%20Swing/3-%20Configurar%20la%20conexion%20en%20el%20proyecto.mp4)

### Resumen del Contenido
El docente configura la conectividad del proyecto a la base de datos `SecurityDB2025` de SQL Server usando JDBC. Implementa la clase `ConnectionManager` usando el patrón de diseño Singleton para garantizar una única conexión física activa a nivel de aplicación. También agrega las dependencias necesarias de `JUnit 5` y el driver oficial de SQL Server en el archivo `pom.xml`, y valida la conexión mediante una prueba automatizada en `ConnectionManagerTest`, forzando intencionalmente errores de credenciales y red para demostrar fallos de conexión comunes.

### Tecnologías Utilizadas
* **JDBC (Java Database Connectivity):** API de Java para la conexión con bases de datos.
* **Microsoft JDBC Driver para SQL Server:** Biblioteca que permite que las aplicaciones Java se comuniquen con SQL Server.
* **JUnit 5 (Jupiter):** Framework para escribir y ejecutar pruebas automatizadas en Java.

### Herramientas Utilizadas
* **IntelliJ IDEA:** Para el desarrollo de la clase de conexión y las pruebas unitarias.
* **SQL Server Management Studio (SSMS):** Utilizado para obtener el nombre de la instancia en las propiedades del servidor.

### Código Mostrado
#### `ConnectionManager.java` (patrón Singleton):
```java
package esfe.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    // Cadena de conexión JDBC configurada para la instancia local
    private static final String STR_CONNECTION = "jdbc:sqlserver://DESKTOP-BH3DU89:1433;databaseName=SecurityDB2025;encrypt=true;trustServerCertificate=true;user=java2025;password=12345;";
    
    private static Connection connection = null;
    private static ConnectionManager instance;

    private ConnectionManager() {
        try {
            // Carga dinámica de la clase del driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver JDBC de SQL Server", e);
        }
    }

    public synchronized Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(STR_CONNECTION);
            } catch (SQLException e) {
                throw new SQLException("Error al conectar a la base de datos: " + e.getMessage(), e);
            }
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
}
```

#### `ConnectionManagerTest.java` (Prueba de conexión):
```java
package esfe.persistencia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionManagerTest {
    private ConnectionManager connectionManager;

    @BeforeEach
    void setUp() {
        connectionManager = ConnectionManager.getInstance();
    }

    @AfterEach
    void tearDown() throws SQLException {
        connectionManager.disconnect();
        connectionManager = null;
    }

    @Test
    void connect() throws SQLException {
        Connection conn = connectionManager.connect();
        assertNotNull(conn, "la conexion no debe ser nula");
        assertFalse(conn.isClosed(), "la conexion debe estar abierta");
        conn.close();
    }
}
```

#### Bloque de dependencias en `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.13.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.microsoft.sqlserver</groupId>
        <artifactId>mssql-jdbc</artifactId>
        <version>12.10.0.jre11</version>
    </dependency>
</dependencies>
```

### Pasos Realizados por el Docente
1. Crea un paquete llamado `persistencia` (`esfe.persistencia`) en el árbol del proyecto.
2. Crea la clase `ConnectionManager.java` en este paquete.
3. Define la constante `STR_CONNECTION` con la URL de conexión. Para obtener la instancia correcta del servidor local, abre SSMS, hace clic derecho en el nodo principal de Object Explorer -> selecciona `Properties` y copia el valor de `Name` (ej. `DESKTOP-BH3DU89`), integrándolo a la cadena JDBC.
4. Implementa el constructor privado para prevenir instanciaciones externas y el método `getInstance()` (Singleton).
5. Implementa los métodos `connect()` y `disconnect()`.
6. Abre el archivo `pom.xml` e introduce el bloque de dependencias con el driver JDBC de SQL Server y JUnit 5.
7. Actualiza el proyecto a través del panel lateral de Maven de IntelliJ IDEA (`Sync/Reload All Maven Projects`).
8. Verifica que en `External Libraries` se hayan descargado los archivos `.jar` del driver y de JUnit.
9. En `ConnectionManager.java`, hace clic derecho -> `Go to` -> `Test` -> `Create New Test...` para generar de manera automática la clase de pruebas unitarias.
10. Escribe los métodos `@BeforeEach`, `@AfterEach` y el método de prueba de conexión en `ConnectionManagerTest.java`.
11. Ejecuta la prueba mediante el botón de reproducción de JUnit en IntelliJ y verifica su aprobación (barra verde).
12. Provoca fallos deliberados para ilustrar los mensajes de error en la consola:
    * Cambia el nombre de la base de datos a `SecurityDB20250` (Error: *Cannot open database...*).
    * Modifica el nombre del usuario a uno incorrecto (Error: *Login failed...*).
    * Modifica la contraseña a una inválida (Error: *Login failed...*).
    * Altera el nombre de la instancia del servidor o puerto (Error: *No se pudo realizar la conexión TCP/IP al host...*).

### Buenas Prácticas Observadas
* **Patrón Singleton:** Garantiza que una única instancia controle las conexiones físicas a la base de datos, previniendo el desperdicio de recursos del sistema al abrir múltiples conexiones concurrentes.
* **Validación Automatizada del Entorno:** Probar la conexión física a la base de datos mediante pruebas unitarias en lugar de probarlo de forma manual a través de interfaces gráficas.
* **Propagación Controlada de Excepciones:** Encapsular las excepciones SQL en mensajes de negocio inteligibles o en `RuntimeException` para no exponer detalles internos de la base de datos en la pila de llamadas.

---

## Video 4: Programar el Paquete Dominio y Utils
* **Archivo de video analizado:** [4-programar el paquete dominio y utils.mp4](file:///c:/Users/corte/OneDrive/Documentos/CajeroAutomaticoSwing/01_VideosDocente/Material%20de%20Apoyo%20Backend%20App%20Java%20Swing/4-programar%20el%20paquete%20dominio%20y%20utils.mp4)

### Resumen del Contenido
El docente implementa la clase entidad `User` en la capa de dominio, y crea el paquete de utilidades `utils` para agrupar clases auxiliares reutilizables: `PasswordHasher` para encriptación de datos, `CBOption` para el enlace de datos en listas JComboBox, y el enum `CUD` para las operaciones CRUD.

### Tecnologías Utilizadas
* **Java 21:** Programación orientada a objetos (POJO, Enums, Clases genéricas).
* **SHA-256:** Algoritmo hash criptográfico para el almacenamiento seguro de contraseñas.
* **Base64:** Codificación de bytes a caracteres legibles de texto.

### Herramientas Utilizadas
* **IntelliJ IDEA:** Para crear los paquetes, entidades y clases utilitarias del proyecto.

### Código Mostrado
#### `User.java` (Entidad de Dominio):
```java
package esfe.dominio;

public class User {
    private int id;
    private String name;
    private String passwordHash;
    private String email;
    private byte status;

    public User() {}

    public User(int id, String name, String passwordHash, String email, byte status) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
        this.status = status;
    }

    // Getters y Setters estándar...

    public String getStatus() {
        switch (status) {
            case 1: return "ACTIVE";
            case 2: return "INACTIVE";
            default: return "";
        }
    }
}
```

#### `PasswordHasher.java` (Encriptador en SHA-256):
```java
package esfe.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
```

#### `CBOption.java` (Enlace de datos para ComboBoxes):
```java
package esfe.utils;

import java.util.Objects;

public class CBOption {
    private String displayText;
    private Object value;

    public CBOption(String displayText, Object value) {
        this.displayText = displayText;
        this.value = value;
    }

    public String getDisplayText() { return displayText; }
    public Object getValue() { return value; }

    @Override
    public String toString() {
        return displayText;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CBOption other = (CBOption) obj;
        return Objects.equals(this.value, other.value);
    }
}
```

#### `CUD.java` (Enum de Operación):
```java
package esfe.utils;

public enum CUD {
    CREATE,
    UPDATE,
    DELETE
}
```

### Pasos Realizados por el Docente
1. Crea el paquete `dominio` (`esfe.dominio`).
2. Diseña la clase `User.java` definiendo los atributos privados `id`, `name`, `passwordHash`, `email`, `status`. Genera constructores, Getters y Setters.
3. Modifica el método `getStatus()` para incluir una lógica basada en `switch` que convierta los códigos numéricos de la base de datos (como `1` o `2`) a Strings representativos como `ACTIVE` o `INACTIVE`.
4. Crea el paquete `utils` (`esfe.utils`).
5. Crea la clase `PasswordHasher.java` e implementar la encriptación mediante la clase `MessageDigest` configurada con el algoritmo `SHA-256`, codificando el resultado en `Base64`.
6. Crea la clase `CBOption.java` con propiedades de texto y valor genérico. Sobrescribe `toString()` para devolver el texto a mostrar en listas de Swing y `equals()` para comparar por valor.
7. Crea el enumerador `CUD.java` con los estados de operación correspondientes.

### Buenas Prácticas Observadas
* **Seguridad en Contraseñas:** Encriptar contraseñas mediante funciones criptográficas de un solo sentido (hash SHA-256) antes de almacenarlas en base de datos.
* **Encapsulamiento y POJO:** Mapeo de tablas de base de datos como objetos planos de Java (POJO) para desacoplar la persistencia de otras capas del software.
* **Uso de Enumerados (Enums):** Limitar las operaciones lógicas a opciones específicas conocidas en tiempo de compilación (`CREATE`, `UPDATE`, `DELETE`), evitando errores tipográficos en el manejo de Strings.
* **Sobrescritura de métodos estructurales (`toString`/`equals`):** Necesario para que controles gráficos de Swing (como combos y listas) muestren y busquen elementos adecuadamente basándose en su valor real, no en su dirección de memoria.

---

## Video 5: Programar el Paquete de Persistencia, la Clase UserDAO y su Prueba con JUnit
* **Archivo de video analizado:** [5- Programar el paquete de persistencia la clase UserDAO y su prueba con jUnit.mp4](file:///c:/Users/corte/OneDrive/Documentos/CajeroAutomaticoSwing/01_VideosDocente/Material%20de%20Apoyo%20Backend%20App%20Java%20Swing/5-%20Programar%20el%20paquete%20de%20persistencia%20la%20clase%20UserDAO%20y%20su%20prueba%20con%20jUnit.mp4)

### Resumen del Contenido
El docente implementa el patrón DAO (Data Access Object) para interactuar con la tabla de usuarios en SQL Server. Codifica métodos para insertar, actualizar, eliminar, buscar por ID, realizar búsquedas parciales por coincidencia y autenticar usuarios en `UserDAO`. Valida todo el ciclo de vida de la entidad de manera continua mediante una prueba integral e idempotente en `UserDAOTest`, la cual crea datos aleatorios para evitar colisiones en campos únicos y limpia los registros de prueba de la base de datos al finalizar.

### Tecnologías Utilizadas
* **Java 21:** Uso de colecciones genéricas (`ArrayList`).
* **JDBC (`PreparedStatement`, `ResultSet`):** API para enviar sentencias SQL parametrizadas de forma segura y procesar los resultados.
* **JUnit 5:** Mapeo y aserciones para validar el ciclo CRUD completo.
* **java.util.Random:** Utilidad para generación de correos aleatorios durante la ejecución del test.

### Herramientas Utilizadas
* **IntelliJ IDEA:** Para codificar la lógica del DAO y compilar las pruebas.
* **SQL Server:** Motor de datos activo donde impactan las pruebas de integración.

### Código Mostrado
#### `UserDAO.java` (Clase de Acceso a Datos):
```java
package esfe.persistencia;

import esfe.dominio.User;
import esfe.utils.PasswordHasher;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private final ConnectionManager connectionManager;

    public UserDAO() {
        this.connectionManager = ConnectionManager.getInstance();
    }

    public User create(User user) throws SQLException {
        String sql = "INSERT INTO users (name, password, email, status) VALUES (?, ?, ?, ?)";
        Connection conn = connectionManager.connect();
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, PasswordHasher.hashPassword(user.getPasswordHash())); // Encriptación antes de guardar
            ps.setString(3, user.getEmail());
            ps.setByte(4, user.getStatus());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
            }
            return user;
        } finally {
            connectionManager.disconnect();
        }
    }

    public boolean update(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, status = ? WHERE id = ?";
        Connection conn = connectionManager.connect();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setByte(3, user.getStatus());
            ps.setInt(4, user.getId());
            return ps.executeUpdate() > 0;
        } finally {
            connectionManager.disconnect();
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection conn = connectionManager.connect();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } finally {
            connectionManager.disconnect();
        }
    }

    public User getById(int id) throws SQLException {
        String sql = "SELECT id, name, email, status FROM users WHERE id = ?";
        Connection conn = connectionManager.connect();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), "", rs.getString("email"), rs.getByte("status"));
                }
            }
            return null;
        } finally {
            connectionManager.disconnect();
        }
    }

    public ArrayList<User> search(String name) throws SQLException {
        String sql = "SELECT id, name, email, status FROM users WHERE name LIKE ?";
        Connection conn = connectionManager.connect();
        ArrayList<User> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new User(rs.getInt("id"), rs.getString("name"), "", rs.getString("email"), rs.getByte("status")));
                }
            }
            return list;
        } finally {
            connectionManager.disconnect();
        }
    }

    public User authenticate(User user) throws SQLException {
        String sql = "SELECT id, name, email, status FROM users WHERE email = ? AND password = ? AND status = 1";
        Connection conn = connectionManager.connect();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, PasswordHasher.hashPassword(user.getPasswordHash()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), "", rs.getString("email"), rs.getByte("status"));
                }
            }
            return null;
        } finally {
            connectionManager.disconnect();
        }
    }

    public boolean updatePassword(User user) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        Connection conn = connectionManager.connect();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, PasswordHasher.hashPassword(user.getPasswordHash()));
            ps.setInt(2, user.getId());
            return ps.executeUpdate() > 0;
        } finally {
            connectionManager.disconnect();
        }
    }
}
```

#### `UserDAOTest.java` (Prueba de Integración JUnit 5):
```java
package esfe.persistencia;

import esfe.dominio.User;
import esfe.utils.PasswordHasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void testUserDAO() throws SQLException {
        // Generación de correo aleatorio para evitar colisión de UNIQUE constraint
        Random random = new Random();
        int num = random.nextInt(1000) + 1;
        String email = "test" + num + "@gmail.com";

        // 1. Crear usuario
        User user = new User(0, "test user", "password", email, (byte) 2);
        User userCreated = userDAO.create(user);
        assertNotNull(userCreated);
        assertTrue(userCreated.getId() > 0);
        assertEquals("test user", userCreated.getName());

        // 2. Modificar usuario
        userCreated.setName(userCreated.getName() + "_U");
        userCreated.setEmail("u_" + userCreated.getEmail());
        userCreated.setStatus((byte) 1); // Activo
        boolean isUpdated = userDAO.update(userCreated);
        assertTrue(isUpdated);

        // 3. Buscar usuario por coincidencia parcial de nombre
        ArrayList<User> list = userDAO.search("test");
        assertTrue(list.size() > 0);
        boolean found = false;
        for (User u : list) {
            if (u.getName().equals(userCreated.getName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);

        // 4. Autenticación Exitosa
        User userToAuth = new User();
        userToAuth.setEmail(userCreated.getEmail());
        userToAuth.setPassword("password");
        User authUser = userDAO.authenticate(userToAuth);
        assertNotNull(authUser);
        assertEquals(userCreated.getId(), authUser.getId());

        // 5. Autenticación Fallida (Contraseña errónea)
        userToAuth.setPassword("12345");
        User failedAuthUser = userDAO.authenticate(userToAuth);
        assertNull(failedAuthUser);

        // 6. Actualización de Contraseña
        userCreated.setPassword("new_password");
        boolean isPasswordUpdated = userDAO.updatePassword(userCreated);
        assertTrue(isPasswordUpdated);

        // 7. Autenticación Exitosa con la Nueva Contraseña
        userToAuth.setPassword("new_password");
        User newAuthUser = userDAO.authenticate(userToAuth);
        assertNotNull(newAuthUser);

        // 8. Eliminar Usuario
        boolean isDeleted = userDAO.delete(userCreated.getId());
        assertTrue(isDeleted);

        // 9. Verificar Eliminación en Base de Datos
        User deletedUser = userDAO.getById(userCreated.getId());
        assertNull(deletedUser);
    }
}
```

### Pasos Realizados por el Docente
1. Crea la clase `UserDAO.java` dentro del paquete `esfe.persistencia`.
2. Escribe el constructor para inicializar la clase `ConnectionManager`.
3. Implementa los métodos CRUD parametrizados y el mapeo mediante sentencias preparadas.
4. Diseña los métodos específicos para la lógica de cajero automático: `search`, `authenticate` y `updatePassword`.
5. Asegura la desconexión a la base de datos llamando a `connectionManager.disconnect()` en bloques `finally`.
6. En `UserDAO.java`, genera de manera asistida la clase `UserDAOTest.java`.
7. Diseña el método de prueba de integración `@Test void testUserDAO()`.
8. Emplea la clase `Random` para generar valores únicos en la propiedad de correo en cada ejecución del test.
9. Codifica el encadenamiento de validación para insertar, modificar, buscar, autenticar con éxito y fallo, cambiar contraseña y eliminar.
10. Ejecuta el set de pruebas y verifica su correcta conclusión.

### Buenas Prácticas Observadas
* **Uso de PreparedStatements:** Evita vulnerabilidades de Inyección SQL (SQL Injection) al separar la estructura de la consulta de los parámetros de entrada.
* **Prueba de Integración Idempotente:** Limpiar la base de datos eliminando el registro al finalizar la ejecución del test de integración. Esto evita dejar datos basura y asegura que la prueba se pueda repetir infinitamente.
* **Recuperación de llaves generadas automáticamente:** Evita consultas `SELECT` adicionales para consultar el ID de un registro recién insertado.
* **Evitar almacenamiento de claves en texto plano:** Encriptar contraseñas mediante `PasswordHasher` en la inserción, autenticación y actualización de la misma.

---

## Video 6: Respaldar el Script de la Base en el Proyecto
* **Archivo de video analizado:** [6-Respaldar el script de la base  en el proyecto.mp4](file:///c:/Users/corte/OneDrive/Documentos/CajeroAutomaticoSwing/01_VideosDocente/Material%20de%20Apoyo%20Backend%20App%20Java%20Swing/6-Respaldar%20el%20script%20de%20la%20base%20%20en%20el%20proyecto.mp4)

### Resumen del Contenido
El docente realiza una copia del script DDL empleado para la creación de la base de datos y la tabla directamente dentro de la estructura de archivos del proyecto (`script.sql` bajo el paquete `persistencia`). Luego, documenta su trabajo realizando el control de cambios de Git mediante la terminal de comandos y empuja el commit a un repositorio remoto en GitHub.

### Tecnologías Utilizadas
* **SQL:** Estructuras de bases de datos.
* **Git:** Sistema de control de versiones distribuido.

### Herramientas Utilizadas
* **IntelliJ IDEA:** Terminal incorporada y visor de archivos.
* **Explorador de archivos de Windows:** Para crear el archivo de extensión `.sql`.
* **Notepad (Bloc de notas):** Utilizado para insertar el script copiado desde SSMS.
* **GitHub:** Servidor en la nube para alojar y compartir repositorios.

### Código Mostrado
El script SQL respaldado en `src/main/java/esfe/persistencia/script.sql`:
```sql
CREATE DATABASE SecurityDB2025;
GO
USE SecurityDB2025;
GO
CREATE TABLE users (
  id INT PRIMARY KEY IDENTITY(1,1),
  name VARCHAR(100) NOT NULL,
  password VARCHAR(64) NOT NULL,
  email VARCHAR(200) NOT NULL UNIQUE,
  status TINYINT NOT NULL
);
```

### Pasos Realizados por el Docente
1. En IntelliJ IDEA, hace clic derecho en la carpeta `persistencia` -> selecciona `Open In` -> `Explorer`.
2. En la ventana del Explorador de Windows, crea un archivo de texto nuevo y lo renombra como `script.sql` (modificando la extensión `.txt` a `.sql`).
3. Hace clic derecho sobre `script.sql` -> `Editar` (abre el Bloc de Notas).
4. Copia el script SQL original desde SSMS, lo pega en el bloc de notas y guarda el archivo.
5. De regreso en IntelliJ, abre la terminal y ejecuta los siguientes comandos de Git:
    * `git add .` para preparar el archivo recién creado.
    * `git status` para comprobar los cambios listados para commit.
    * `git commit -m "crear el archivo script sql"` para generar el commit local.
    * `git push origin master` para enviar los cambios al repositorio remoto en GitHub.
6. Abre el navegador web y accede a su cuenta de GitHub para comprobar que los archivos en el repositorio `SecurityAppJava` hayan sido actualizados correctamente.

### Buenas Prácticas Observadas
* **Base de Datos como Código (Database-as-Code):** Mantener el script SQL de creación de la base de datos y tablas dentro del mismo repositorio de código fuente del proyecto. Esto asegura que cualquier desarrollador o servidor de integración continua (CI/CD) pueda recrear el ambiente de forma idéntica e inmediata.
* **Commits atómicos y descriptivos:** Utilizar descripciones concisas en los mensajes de Git para documentar de forma clara los cambios introducidos en el repositorio.

---

## Conclusión General del Módulo Backend
El módulo de apoyo Backend provee una base robusta, segura y bien estructurada que facilita la integración de datos y el manejo de seguridad para la aplicación final de escritorio en Swing:
1. **Desacoplamiento de Arquitectura:** El uso de patrones como **DAO (Data Access Object)** y la separación en paquetes (`dominio`, `persistencia`, `utils`) garantiza que la interfaz gráfica (front-end) no conozca detalles de conectividad ni interactúe directamente con SQL Server, aumentando la mantenibilidad y modularidad de la aplicación.
2. **Seguridad Integrada:** La protección de contraseñas mediante **SHA-256** a nivel de aplicación (utilidad `PasswordHasher`) y la protección de datos contra inyecciones SQL mediante **`PreparedStatement`** implementan estándares de desarrollo seguros necesarios.
3. **Calidad mediante Pruebas Automatizadas:** El desarrollo y la ejecución exitosa de pruebas con **JUnit 5** tanto para la conexión (`ConnectionManagerTest`) como para la lógica DAO (`UserDAOTest`) garantizan que el código es estable, funcional y que puede modificarse con la seguridad de que cualquier regresión o fallo en el RDBMS será detectado de manera automática.