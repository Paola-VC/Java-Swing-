

# 4. plan_implementacion.md

`md
# Plan de Implementación - CajeroAutomático Swing

## 1. Objetivo General

Desarrollar una aplicación de escritorio en Java Swing que simule el funcionamiento básico de un cajero automático, permitiendo a los usuarios iniciar sesión, consultar saldo, retirar dinero, depositar, transferir y consultar su historial de movimientos.

---

## 2. Alcance del Proyecto

El proyecto incluye las siguientes funcionalidades:

- Inicio de sesión con número de cuenta y PIN.
- Consulta de saldo.
- Retiro de dinero.
- Depósito de dinero.
- Transferencia entre cuentas.
- Historial de movimientos.
- Registro de operaciones en SQL Server.
- Validaciones de datos.
- Interfaz gráfica en Java Swing.
- Pruebas funcionales.
- Documentación del sistema.
- Control de versiones con Git.

---

## 3. Tecnologías Utilizadas

| Tecnología | Uso dentro del proyecto |
|----------|--------------------------|
| Java 21 | Lenguaje principal de programación |
| Java Swing | Desarrollo de interfaces gráficas |
| Maven | Gestión del proyecto y dependencias |
| SQL Server | Base de datos del sistema |
| JDBC | Conexión entre Java y SQL Server |
| JUnit 5 | Pruebas unitarias |
| Git | Control de versiones |
| GitHub | Repositorio remoto |
| IntelliJ IDEA | Entorno de desarrollo |

---

## 4. Estructura Propuesta del Proyecto

txt
src/main/java/esfe
│
├── dominio
│   ├── Usuario.java
│   ├── Cuenta.java
│   └── Movimiento.java
│
├── persistencia
│   ├── ConnectionManager.java
│   ├── UsuarioDAO.java
│   ├── CuentaDAO.java
│   └── MovimientoDAO.java
│
├── presentacion
│   ├── LoginForm.java
│   ├── MainForm.java
│   ├── ConsultaSaldoForm.java
│   ├── RetiroForm.java
│   ├── DepositoForm.java
│   ├── TransferenciaForm.java
│   └── HistorialMovimientosForm.java
│
├── utils
│   └── Validaciones.java
│
└── Main.java
`

---

## 5. Fases de Implementación

## Fase 1: Creación del proyecto

### Actividades

* Crear el proyecto en IntelliJ IDEA.
* Configurar Java 21.
* Seleccionar Maven como gestor del proyecto.
* Verificar la ejecución inicial desde la clase `Main`.

### Resultado esperado

El proyecto debe compilar y ejecutar correctamente.

---

## Fase 2: Configuración de base de datos

### Actividades

* Crear la base de datos del cajero automático.
* Crear las tablas necesarias.
* Agregar el script SQL en la carpeta `05_SQL`.
* Configurar el driver JDBC de SQL Server.

### Tablas sugeridas

* `usuarios`
* `cuentas`
* `movimientos`

### Resultado esperado

La base de datos debe estar creada y lista para conectarse desde Java.

---

## Fase 3: Conexión con SQL Server

### Actividades

* Crear la clase `ConnectionManager`.
* Configurar la cadena de conexión.
* Probar la conexión con SQL Server.
* Manejar errores de conexión.

### Resultado esperado

El sistema debe conectarse correctamente con la base de datos.

---

## Fase 4: Implementación de entidades

### Actividades

* Crear la clase `Usuario`.
* Crear la clase `Cuenta`.
* Crear la clase `Movimiento`.
* Agregar constructores, getters y setters.
* Definir atributos necesarios para cada clase.

### Resultado esperado

El sistema debe contar con entidades que representen los datos principales del cajero automático.

---

## Fase 5: Implementación de persistencia

### Actividades

* Crear `UsuarioDAO`.
* Crear `CuentaDAO`.
* Crear `MovimientoDAO`.
* Implementar consultas con `PreparedStatement`.
* Crear métodos para autenticar usuario.
* Crear métodos para consultar y actualizar saldo.
* Crear métodos para registrar movimientos.

### Resultado esperado

El sistema debe poder interactuar con la base de datos mediante clases DAO.

---

## Fase 6: Pruebas del backend

### Actividades

* Probar conexión a SQL Server.
* Probar autenticación de usuario.
* Probar consulta de saldo.
* Probar actualización de saldo.
* Probar registro de movimientos.
* Probar transferencia entre cuentas.

### Resultado esperado

Las pruebas deben confirmar que la lógica del sistema funciona correctamente.

---

## Fase 7: Diseño de interfaz gráfica

### Actividades

* Crear el paquete `presentacion`.
* Diseñar el formulario de inicio de sesión.
* Diseñar el menú principal.
* Diseñar pantalla de consulta de saldo.
* Diseñar pantalla de retiro.
* Diseñar pantalla de depósito.
* Diseñar pantalla de transferencia.
* Diseñar pantalla de historial de movimientos.

### Resultado esperado

El sistema debe contar con pantallas claras y fáciles de utilizar.

---

## Fase 8: Programación de formularios

### Actividades

* Programar eventos de botones.
* Conectar formularios con clases DAO.
* Validar campos obligatorios.
* Mostrar mensajes con `JOptionPane`.
* Actualizar datos después de cada operación.
* Controlar el cierre de sesión.

### Resultado esperado

Las pantallas deben ejecutar correctamente las operaciones del cajero automático.

---

## Fase 9: Validación funcional

### Actividades

* Probar login correcto.
* Probar login incorrecto.
* Probar consulta de saldo.
* Probar retiro exitoso.
* Probar retiro con saldo insuficiente.
* Probar depósito.
* Probar transferencia.
* Probar historial de movimientos.
* Probar cierre de sesión.
* Probar salida del sistema.

### Resultado esperado

El sistema debe cumplir correctamente los flujos principales del usuario.

---

## Fase 10: Documentación y entrega

### Actividades

* Crear archivo de evidencias.
* Crear historial de commits.
* Crear historias de usuario.
* Crear plan de implementación.
* Crear resumen técnico.
* Subir el proyecto a GitHub.
* Verificar que los archivos estén ordenados.

### Resultado esperado

El proyecto debe estar documentado y listo para entrega.

---

## 6. Cronograma de Implementación

| Etapa | Actividad                      | Estado     |
| ----- | ------------------------------ | ---------- |
| 1     | Creación del proyecto          | Completado |
| 2     | Organización de carpetas       | Completado |
| 3     | Configuración de base de datos | En proceso |
| 4     | Conexión con SQL Server        | En proceso |
| 5     | Entidades del dominio          | Pendiente  |
| 6     | Clases DAO                     | Pendiente  |
| 7     | Pruebas con JUnit              | Pendiente  |
| 8     | Diseño de formularios Swing    | Pendiente  |
| 9     | Programación de operaciones    | Pendiente  |
| 10    | Validación funcional           | Pendiente  |
| 11    | Documentación                  | En proceso |
| 12    | Subida a GitHub                | Pendiente  |

---

## 7. Roles del Equipo

| Rol                    | Responsabilidad                                           |
| ---------------------- | --------------------------------------------------------- |
| Product Owner          | Define requerimientos, prioridades e historias de usuario |
| Desarrollador Backend  | Implementa base de datos, conexión y lógica DAO           |
| Desarrollador Frontend | Diseña y programa formularios Swing                       |
| Tester                 | Realiza pruebas de funcionamiento                         |
| Documentador           | Organiza evidencias y documentos del proyecto             |

---

## 8. Riesgos Identificados

| Riesgo                         | Impacto | Solución                                          |
| ------------------------------ | ------- | ------------------------------------------------- |
| Error de conexión a SQL Server | Alto    | Verificar instancia, puerto, usuario y contraseña |
| Base de datos no creada        | Alto    | Ejecutar correctamente el script SQL              |
| Monto inválido en operaciones  | Medio   | Validar campos antes de procesar                  |
| Saldo insuficiente             | Medio   | Validar saldo antes de retirar o transferir       |
| Cuenta destino inexistente     | Alto    | Verificar cuenta antes de transferir              |
| Error al registrar movimientos | Alto    | Usar transacciones o validar inserciones          |
| Problemas al subir a GitHub    | Medio   | Revisar rama, commits y remoto                    |

---

## 9. Criterios de Finalización

El proyecto se considera finalizado cuando cumple con los siguientes puntos:

* El proyecto compila sin errores.
* La conexión a SQL Server funciona correctamente.
* La base de datos y tablas existen.
* Se puede iniciar sesión con una cuenta válida.
* No se permite iniciar sesión con datos incorrectos.
* Se puede consultar saldo.
* Se puede retirar dinero si hay saldo suficiente.
* Se puede depositar dinero.
* Se puede transferir dinero a otra cuenta.
* Se registran los movimientos.
* Se puede visualizar el historial.
* Los formularios validan datos incorrectos.
* El código está organizado por capas.
* La documentación está completa.
* El proyecto está subido a GitHub.

---

## 10. Conclusión

El plan de implementación del proyecto CajeroAutomático Swing permite organizar el desarrollo de forma clara y progresiva. La aplicación integra Java Swing para la interfaz gráfica, SQL Server para el almacenamiento de datos y JDBC para la comunicación entre el sistema y la base de datos.

Este proyecto permite simular operaciones reales de un cajero automático, aplicando buenas prácticas como separación por capas, validación de datos, registro de movimientos y control de acceso mediante inicio de sesión.


```