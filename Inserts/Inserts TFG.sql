
/*USUARIO*/
INSERT INTO `tfg`.`usuario` (`idUsuario`, `nombreUsuario`, `contrasenia`, `email`, `rol`) VALUES
(1, 'usuario1', 'pass123', 'usuario1@email.com', 'CLIENTE'),
(2, 'usuario2', 'pass123', 'usuario2@email.com', 'CLIENTE'),
(3, 'usuario3', 'pass123', 'usuario3@email.com', 'CLIENTE'),
(4, 'usuario4', 'pass123', 'usuario4@email.com', 'CLIENTE'),
(5, 'usuario5', 'pass123', 'usuario5@email.com', 'CLIENTE'),
(6, 'usuario6', 'pass123', 'usuario6@email.com', 'ADMIN'),
(7, 'usuario7', 'pass123', 'usuario7@email.com', 'TRABAJADOR'),
(8, 'usuario8', 'pass123', 'usuario8@email.com', 'TRABAJADOR'),
(9, 'usuario9', 'pass123', 'usuario9@email.com', 'TRABAJADOR'),
(10, 'usuario10', 'pass123', 'usuario10@email.com', 'TRABAJADOR');

/*CORTE PREDEFINIDO*/
INSERT INTO `tfg`.`corte_predefinido` (`idCorte_Predefinido`, `Nombre`, `Precio_Total`, `Duracion_Base`, `Imagen`) VALUES
(1, 'Corte Clásico', 15.00, '2025-11-17 00:30:00', 'corte_clasico.jpg'),
(2, 'Corte Moderno', 20.00, '2025-11-17 00:40:00', 'corte_moderno.jpg'),
(3, 'Corte Degradado', 18.50, '2025-11-17 00:35:00', 'corte_degradado.jpg'),
(4, 'Corte Infantil', 12.00, '2025-11-17 00:25:00', 'corte_infantil.jpg'),
(5, 'Corte Profesional', 25.00, '2025-11-17 00:50:00', 'corte_profesional.jpg'),
(6, 'Corte Estilo Libre', 22.00, '2025-11-17 00:45:00', 'corte_estilo_libre.jpg'),
(7, 'Corte Ejecutivo', 30.00, '2025-11-17 01:00:00', 'corte_ejecutivo.jpg'),
(8, 'Corte Rápido', 10.00, '2025-11-17 00:20:00', 'corte_rapido.jpg'),
(9, 'Corte Fiesta', 28.00, '2025-11-17 00:55:00', 'corte_fiesta.jpg'),
(10, 'Corte Vintage', 26.00, '2025-11-17 00:50:00', 'corte_vintage.jpg');

/*CORTE TRABAJADOR*/
INSERT INTO `tfg`.`corte_trabajador` (`idCorte_Trabajador`, `Duracion`, `Precio`, `Corte_Predefinido_idCorte_Predefinido`) VALUES
(1, '2000-01-01 00:30:00', 15.00, 1),
(2, '2000-01-01 00:40:00', 20.00, 2),
(3, '2000-01-01 00:35:00', 18.50, 3),
(4, '2000-01-01 00:25:00', 12.00, 4),
(5, '2000-01-01 00:50:00', 25.00, 5),
(6, '2000-01-01 00:45:00', 22.00, 6),
(7, '2000-01-01 01:00:00', 30.00, 7),
(8, '2000-01-01 00:20:00', 10.00, 8),
(9, '2000-01-01 00:55:00', 28.00, 9),
(10, '2000-01-01 00:50:00', 26.00, 10);


/*DETALLE TRABAJADOR */

INSERT INTO `tfg`.`detalle_trabajador` (`idDetalle_Trabajador`, `Horario_Trabajador`, `especializacion`, `imagen`, `Corte_Trabajador_idCorte_Trabajador`, `Usuario_idUsuario`,`nombre`) VALUES
(1, '2025-11-17 09:00:00', 'Cortes clásicos', 'trabajador1.jpg', 1, 1, 'Feliipe'),
(2, '2025-11-17 10:00:00', 'Cortes modernos', 'trabajador2.jpg', 2, 2, 'Manolo'),
(3, '2025-11-17 11:00:00', 'Degradados', 'trabajador3.jpg', 3, 3, 'Feliipe'),
(4, '2025-11-17 12:00:00', 'Infantiles', 'trabajador4.jpg', 4, 4, 'Feliipe'),
(5, '2025-11-17 13:00:00', 'Profesionales', 'trabajador5.jpg', 5, 5, 'David'),
(6, '2025-11-17 14:00:00', 'Estilo libre', 'trabajador6.jpg', 6, 6, 'Juan'),
(7, '2025-11-17 15:00:00', 'Ejecutivos', 'trabajador7.jpg', 7, 7, 'Feliipe'),
(8, '2025-11-17 16:00:00', 'Rápidos', 'trabajador8.jpg', 8, 8, 'Miguel'),
(9, '2025-11-17 17:00:00', 'Fiesta', 'trabajador9.jpg', 9, 9, 'Feliipe'),
(10, '2025-11-17 18:00:00', 'Vintage', 'trabajador10.jpg', 10, 10, 'Feliipe');

/*HORARIO TRABAJADOR*/
INSERT INTO `tfg`.`horario_trabajador` (`idHorario_trabajador`, `fecha`, `hora`, `disponible`, `Detalle_Trabajador_idDetalle_Trabajador`) VALUES
(1, '2025-11-18', '09:00:00', 1, 1),
(2, '2025-11-18', '10:00:00', 1, 2),
(3, '2025-11-18', '11:00:00', 1, 3),
(4, '2025-11-18', '12:00:00', 1, 4),
(5, '2025-11-18', '13:00:00', 1, 5),
(6, '2025-11-19', '09:00:00', 1, 6),
(7, '2025-11-19', '10:30:00', 1, 7),
(8, '2025-11-19', '11:30:00', 1, 8),
(9, '2025-11-19', '12:30:00', 1, 9),
(10, '2025-11-19', '13:30:00', 1, 10);

/*SERVICIOS*/
INSERT INTO `tfg`.`servicio` (`idServicio`, `Nombre`, `Precio`) VALUES
(1, 'Corte de Pelo', 12.50),
(2, 'Manicura Básica', 8.99),
(3, 'Pedicura Completa', 15.75),
(4, 'Masaje Relajante', 25.00),
(5, 'Depilación Facial', 9.50),
(6, 'Tratamiento Facial', 30.20),
(7, 'Tinte de Cabello', 22.00),
(8, 'Lavado y Peinado', 10.00),
(9, 'Afeitado Profesional', 7.80),
(10, 'Spa de Manos', 13.40);

/*RESENIA*/
INSERT INTO `tfg`.`resenia` (`idResenia`, `Estrellas`, `Hora`, `Usuario_idUsuario`, `Resenia`) VALUES
(1, 5, '2025-11-01 14:30:00', 1, '¡Excelente servicio! Mi corte quedó perfecto.'),
(2, 4, '2025-11-02 10:15:00', 2, 'Buen trato y profesionalidad, volveré seguro.'),
(3, 3, '2025-11-03 16:00:00', 3, 'Está bien, pero tardaron un poco en atenderme.'),
(4, 2, '2025-11-04 09:45:00', 4, 'No me gustó mucho el resultado, esperaba algo mejor.'),
(5, 1, '2025-11-05 11:20:00', 5, 'Muy mala experiencia, no lo recomiendo.'),
(6, 5, '2025-11-06 15:00:00', 6, 'Servicio excelente, estilista muy profesional.'),
(7, 4, '2025-11-07 13:10:00', 7, 'Me gustó el corte, aunque un poco caro.'),
(8, 3, '2025-11-08 17:30:00', 8, 'Normal, nada destacable.'),
(9, 5, '2025-11-09 12:00:00', 9, '¡Fantástico! Muy recomendable.'),
(10, 4, '2025-11-10 18:45:00', 10, 'Buen servicio y buen ambiente, volveré.');





