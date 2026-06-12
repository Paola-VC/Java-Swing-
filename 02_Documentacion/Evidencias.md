`md
# Evidencias del Proyecto - CajeroAutomático Swing

## 1. Información General del Proyecto

**Nombre del proyecto:** CajeroAutomático Swing  
**Tipo de aplicación:** Aplicación de escritorio  
**Lenguaje:** Java 21  
**Interfaz gráfica:** Java Swing  
**Base de datos:** SQL Server  
**Conexión:** JDBC  
**Gestor de dependencias:** Maven  
**IDE utilizado:** IntelliJ IDEA  
**Control de versiones:** Git y GitHub

El proyecto CajeroAutomático Swing consiste en una aplicación de escritorio desarrollada en Java, cuyo objetivo principal es simular las operaciones básicas de un cajero automático. El sistema permite autenticar usuarios, consultar saldo, realizar retiros, depósitos, transferencias y registrar movimientos de cuenta.

---

## 2. Evidencia de la Estructura del Proyecto

El proyecto fue organizado por carpetas y paquetes para separar correctamente la lógica del sistema.

### Paquetes principales

- `esfe.dominio`: contiene las clases principales del sistema, como usuario, cuenta y movimiento.
- `esfe.persistencia`: contiene la conexión con SQL Server y las clases DAO.
- `esfe.presentacion`: contiene los formularios gráficos de Java Swing.
- `esfe.utils`: contiene clases auxiliares y utilidades del sistema.
- `05_SQL`: contiene scripts de base de datos.
- `02_Documentacion`: contiene los documentos del proyecto.


`

---

## 3. Evidencia de la Base de Datos

Se creó una base de datos para almacenar la información necesaria del cajero automático.

### Elementos principales de la base de datos

* Tabla de usuarios.
* Tabla de cuentas.
* Tabla de movimientos.
* Relación entre usuario y cuenta.
* Registro de transacciones realizadas.



## 4. Evidencia de Conexión a SQL Server

El proyecto utiliza JDBC para conectarse con SQL Server. La conexión se gestiona mediante una clase especializada que permite abrir y cerrar la conexión con la base de datos.

### Elementos implementados

* Driver JDBC de SQL Server.
* Clase de conexión.
* Cadena de conexión.
* Prueba de conexión.
* Validación de errores de conexión.




---

## 5. Evidencia de Inicio de Sesión

Se desarrolló una pantalla de inicio de sesión para validar el acceso del usuario al cajero automático.

### Funcionalidades

* Ingreso de número de cuenta o usuario.
* Ingreso de PIN o contraseña.
* Validación contra la base de datos.
* Mensajes de error si los datos son incorrectos.
* Acceso al menú principal si las credenciales son válidas.

## 6. Evidencia del Menú Principal

Después de iniciar sesión correctamente, el sistema muestra un menú principal con las operaciones disponibles.

### Opciones del menú

* Consultar saldo.
* Realizar retiro.
* Realizar depósito.
* Realizar transferencia.
* Ver historial de movimientos.
* Cerrar sesión.
* Salir del sistema.
## 7. Evidencia de Consulta de Saldo

El sistema permite consultar el saldo disponible de la cuenta autenticada.

### Funcionalidades

* Mostrar nombre del usuario.
* Mostrar número de cuenta.
* Mostrar saldo actual.
* Evitar modificación directa del saldo desde la interfaz.

## 8. Evidencia de Retiro de Dinero

El sistema permite realizar retiros validando que el monto ingresado sea correcto y que exista saldo suficiente.

### Validaciones

* El monto no debe estar vacío.
* El monto debe ser mayor que cero.
* El monto no debe superar el saldo disponible.
* La transacción debe registrarse en la base de datos.

## 9. Evidencia de Depósito de Dinero

El sistema permite realizar depósitos a la cuenta del usuario autenticado.

### Validaciones

* El monto debe ser numérico.
* El monto debe ser mayor que cero.
* El saldo debe actualizarse correctamente.
* El movimiento debe quedar registrado.
## 10. Evidencia de Transferencias

El sistema permite transferir dinero hacia otra cuenta registrada.

### Validaciones

* La cuenta destino debe existir.
* El usuario no debe transferirse a la misma cuenta.
* El monto debe ser mayor que cero.
* El usuario debe tener saldo suficiente.
* Deben registrarse los movimientos de salida y entrada.

## 11. Evidencia de Historial de Movimientos

El sistema permite consultar los movimientos realizados por el usuario.

### Información mostrada

* Fecha de movimiento.
* Tipo de operación.
* Monto.
* Saldo resultante.
* Cuenta relacionada.

## 12. Evidencia de Pruebas

Se realizaron pruebas para verificar el funcionamiento del cajero automático.

### Pruebas realizadas

* Conexión a la base de datos.
* Inicio de sesión correcto.
* Inicio de sesión incorrecto.
* Consulta de saldo.
* Retiro con saldo suficiente.
* Retiro con saldo insuficiente.
* Depósito válido.
* Transferencia válida.
* Transferencia a cuenta inexistente.
* Registro de movimientos.
* Cierre de sesión.

---

## 13. Conclusión de Evidencias

Las evidencias demuestran que el proyecto CajeroAutomático Swing fue desarrollado de forma ordenada, integrando interfaz gráfica, conexión a base de datos, validaciones y operaciones principales de un cajero automático.

El sistema permite simular un flujo real de uso, desde el inicio de sesión hasta la ejecución de transacciones financieras básicas.