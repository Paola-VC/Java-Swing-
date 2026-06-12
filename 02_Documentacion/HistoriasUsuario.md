

# 3. HistoriasUsuario.md

md
# Historias de Usuario - CajeroAutomático Swing

## Información General

**Proyecto:** CajeroAutomático Swing  
**Rol principal:** Cliente bancario  
**Rol secundario:** Administrador o sistema  
**Objetivo:** Permitir que un usuario realice operaciones básicas de cajero automático desde una aplicación de escritorio.

---

## HU-01: Iniciar sesión en el cajero

**Como** cliente bancario,  
**quiero** ingresar al sistema usando mi número de cuenta y PIN,  
**para** acceder de forma segura a las operaciones del cajero automático.

### Criterios de aceptación

**Dado** que el cliente se encuentra en la pantalla de inicio de sesión,  
**cuando** ingresa una cuenta y PIN válidos,  
**entonces** el sistema debe permitir el acceso al menú principal.

**Dado** que el cliente ingresa datos incorrectos,  
**cuando** presiona el botón de ingresar,  
**entonces** el sistema debe mostrar un mensaje de advertencia.

**Dado** que la cuenta no existe o está inactiva,  
**cuando** el cliente intenta iniciar sesión,  
**entonces** el sistema debe impedir el acceso.

### Prioridad

Alta

---

## HU-02: Consultar saldo

**Como** cliente bancario,  
**quiero** consultar el saldo disponible de mi cuenta,  
**para** conocer cuánto dinero tengo antes de realizar una operación.

### Criterios de aceptación

**Dado** que el cliente inició sesión correctamente,  
**cuando** selecciona la opción Consultar saldo,  
**entonces** el sistema debe mostrar el saldo actual de la cuenta.

**Dado** que el saldo se muestra en pantalla,  
**cuando** el cliente finaliza la consulta,  
**entonces** debe poder regresar al menú principal.

### Prioridad

Alta

---

## HU-03: Realizar retiro de dinero

**Como** cliente bancario,  
**quiero** retirar dinero de mi cuenta,  
**para** disponer de efectivo.

### Criterios de aceptación

**Dado** que el cliente está autenticado,  
**cuando** ingresa un monto válido y tiene saldo suficiente,  
**entonces** el sistema debe descontar el monto de la cuenta.

**Dado** que el cliente ingresa un monto mayor al saldo disponible,  
**cuando** intenta retirar,  
**entonces** el sistema debe mostrar un mensaje de saldo insuficiente.

**Dado** que el monto ingresado es cero, negativo o vacío,  
**cuando** el cliente intenta confirmar el retiro,  
**entonces** el sistema debe mostrar un mensaje de validación.

**Dado** que el retiro se realiza correctamente,  
**cuando** finaliza la operación,  
**entonces** el sistema debe registrar el movimiento en la base de datos.

### Prioridad

Alta

---

## HU-04: Realizar depósito

**Como** cliente bancario,  
**quiero** depositar dinero en mi cuenta,  
**para** aumentar mi saldo disponible.

### Criterios de aceptación

**Dado** que el cliente está autenticado,  
**cuando** ingresa un monto válido,  
**entonces** el sistema debe sumar el monto al saldo actual.

**Dado** que el monto ingresado es inválido,  
**cuando** intenta realizar el depósito,  
**entonces** el sistema debe mostrar una advertencia.

**Dado** que el depósito se realiza correctamente,  
**cuando** finaliza la operación,  
**entonces** el sistema debe registrar el movimiento.

### Prioridad

Alta

---

## HU-05: Realizar transferencia

**Como** cliente bancario,  
**quiero** transferir dinero a otra cuenta,  
**para** enviar fondos a otro usuario registrado.

### Criterios de aceptación

**Dado** que el cliente está autenticado,  
**cuando** ingresa una cuenta destino válida y un monto permitido,  
**entonces** el sistema debe descontar el dinero de la cuenta origen y sumarlo a la cuenta destino.

**Dado** que la cuenta destino no existe,  
**cuando** el cliente intenta transferir,  
**entonces** el sistema debe mostrar un mensaje de error.

**Dado** que el cliente no tiene saldo suficiente,  
**cuando** intenta transferir,  
**entonces** el sistema debe impedir la operación.

**Dado** que la transferencia es exitosa,  
**cuando** finaliza la operación,  
**entonces** el sistema debe registrar los movimientos correspondientes.

### Prioridad

Alta

---

## HU-06: Ver historial de movimientos

**Como** cliente bancario,  
**quiero** ver el historial de movimientos de mi cuenta,  
**para** revisar las operaciones realizadas.

### Criterios de aceptación

**Dado** que el cliente está autenticado,  
**cuando** selecciona la opción Historial,  
**entonces** el sistema debe mostrar una tabla con los movimientos realizados.

**Dado** que existen movimientos registrados,  
**cuando** se muestra la tabla,  
**entonces** debe visualizarse la fecha, tipo de operación, monto y saldo resultante.

**Dado** que no existen movimientos,  
**cuando** el cliente abre el historial,  
**entonces** el sistema debe mostrar la tabla vacía o un mensaje informativo.

### Prioridad

Media

---

## HU-07: Cerrar sesión

**Como** cliente bancario,  
**quiero** cerrar mi sesión,  
**para** proteger mi cuenta cuando termine de usar el cajero.

### Criterios de aceptación

**Dado** que el cliente se encuentra en el menú principal,  
**cuando** selecciona Cerrar sesión,  
**entonces** el sistema debe regresar a la pantalla de inicio de sesión.

**Dado** que la sesión fue cerrada,  
**cuando** otra persona intenta usar el sistema,  
**entonces** debe iniciar sesión con sus propias credenciales.

### Prioridad

Alta

---

## HU-08: Salir del sistema

**Como** usuario,  
**quiero** cerrar la aplicación,  
**para** finalizar el uso del cajero automático.

### Criterios de aceptación

**Dado** que el usuario está en la aplicación,  
**cuando** selecciona la opción Salir,  
**entonces** el sistema debe cerrar completamente la aplicación.

### Prioridad

Media

---

## HU-09: Validar campos obligatorios

**Como** usuario del sistema,  
**quiero** que los formularios validen los campos obligatorios,  
**para** evitar errores al realizar operaciones.

### Criterios de aceptación

**Dado** que el usuario deja un campo vacío,  
**cuando** intenta confirmar una operación,  
**entonces** el sistema debe mostrar un mensaje de advertencia.

**Dado** que el usuario ingresa texto en un campo de monto,  
**cuando** intenta confirmar la operación,  
**entonces** el sistema debe indicar que el valor debe ser numérico.

**Dado** que el usuario ingresa espacios en blanco,  
**cuando** confirma,  
**entonces** el sistema debe tratar el campo como vacío.

### Prioridad

Alta

---

## HU-10: Registrar movimientos automáticamente

**Como** sistema,  
**quiero** registrar cada operación realizada,  
**para** mantener un historial confiable de transacciones.

### Criterios de aceptación

**Dado** que se realiza un retiro, depósito o transferencia,  
**cuando** la operación finaliza correctamente,  
**entonces** el sistema debe guardar el movimiento en la base de datos.

**Dado** que una operación falla,  
**cuando** no se completa la transacción,  
**entonces** el sistema no debe registrar un movimiento como exitoso.

### Prioridad

Alta