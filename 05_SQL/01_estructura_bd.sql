```sql
CREATE DATABASE CajeroAutomaticoDB;
GO

USE CajeroAutomaticoDB;
GO

CREATE TABLE Usuarios (
    IdUsuario INT IDENTITY(1,1) PRIMARY KEY,
    NombreUsuario VARCHAR(100) NOT NULL,
    Pin VARCHAR(10) NOT NULL,
    Estado BIT NOT NULL DEFAULT 1
);
GO

CREATE TABLE Cuentas (
    IdCuenta INT IDENTITY(1,1) PRIMARY KEY,
    IdUsuario INT NOT NULL,
    NumeroCuenta VARCHAR(20) NOT NULL UNIQUE,
    Saldo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    Estado BIT NOT NULL DEFAULT 1,
    CONSTRAINT FK_Cuentas_Usuarios
        FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);
GO

CREATE TABLE Movimientos (
    IdMovimiento INT IDENTITY(1,1) PRIMARY KEY,
    IdCuenta INT NOT NULL,
    TipoMovimiento VARCHAR(20) NOT NULL,
    Monto DECIMAL(10,2) NOT NULL,
    FechaMovimiento DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Movimientos_Cuentas
        FOREIGN KEY (IdCuenta) REFERENCES Cuentas(IdCuenta)
);
GO
```
