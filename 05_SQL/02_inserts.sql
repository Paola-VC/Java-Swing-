USE CajeroAutomaticoDB;
GO

INSERT INTO Usuarios (NombreUsuario, Pin, Estado)
VALUES
('Carlos Moran', '1234', 1),
('Paola', '4321', 1);
GO

INSERT INTO Cuentas (IdUsuario, NumeroCuenta, Saldo, Estado)
VALUES
(1, '1002003001', 500.00, 1),
(2, '1002003002', 750.00, 1);
GO

INSERT INTO Movimientos (IdCuenta, TipoMovimiento, Monto)
VALUES
(1, 'Deposito', 500.00),
(2, 'Deposito', 750.00);
GO