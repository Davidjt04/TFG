/*USUARIO*/
INSERT INTO `tfg`.`usuario` (`idUsuario`, `nombreUsuario`, `contrasenia`, `email`, `rol`) VALUES
/*(1, 'usuario1', 'pass123', 'usuario1@email.com', 'CLIENTE'),
(2, 'usuario2', 'pass123', 'usuario2@email.com', 'CLIENTE'),
(3, 'usuario3', 'pass123', 'usuario3@email.com', 'CLIENTE'),
(4, 'usuario4', 'pass123', 'usuario4@email.com', 'CLIENTE'),*/
/*(5, 'usuario5', 'pass123', 'usuario5@email.com', 'CLIENTE'),*/
/*(6, 'usuario6', 'pass123', 'usuario6@email.com', 'ADMIN'),*/
/*(7, 'usuario7', 'pass123', 'usuario7@email.com', 'TRABAJADOR'),*/
(8, 'usuario8', 'pass123', 'usuario8@email.com', 'TRABAJADOR'),
(9, 'usuario9', 'pass123', 'usuario9@email.com', 'TRABAJADOR'),
(10, 'usuario10', 'pass123', 'usuario10@email.com', 'TRABAJADOR');

/*CORTE PREDEFINIDO*/
INSERT INTO `tfg`.`corte_predefinido` (`idCorte_Predefinido`, `Nombre`, `Precio_Total`, `Duracion_Base`, `Imagen`) VALUES
(1, 'Corte Clásico', 15.00, '2025-11-17 00:30:00', 'CorteClasico.jfif'),
(2, 'Corte Moderno', 20.00, '2025-11-17 00:40:00', 'CorteModerno.jfif'),
(3, 'Corte Degradado', 18.50, '2025-11-17 00:35:00', 'CorteDegradado.jfif'),
(4, 'Corte Infantil', 12.00, '2025-11-17 00:25:00', 'CorteInfantil.jfif'),
(5, 'Corte Profesional', 25.00, '2025-11-17 00:50:00', 'CorteProfesional.jfif'),
(6, 'Corte Estilo Libre', 22.00, '2025-11-17 00:45:00', 'CorteEstiloLibre.jfif'),
(7, 'Corte Ejecutivo', 30.00, '2025-11-17 01:00:00', 'CorteEjecutivo.jfif'),
(8, 'Corte Rápido', 10.00, '2025-11-17 00:20:00', 'CorteRapido.jfif'),
(9, 'Corte Fiesta', 28.00, '2025-11-17 00:55:00', 'CorteFiesta.jfif'),
(10, 'Corte Vintage', 26.00, '2025-11-17 00:50:00', 'CorteVintage.jfif');

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

/*DETALLE TRABAJADOR*/
INSERT INTO `tfg`.`detalle_trabajador` 
(`idDetalle_Trabajador`, `Horario_Trabajador`, `especializacion`, `imagen`, `Corte_Trabajador_idCorte_Trabajador`, `Usuario_idUsuario`, `nombre`) 
VALUES
(1, '2025-11-17 09:00:00', 'Cortes clásicos', 'Trabajador.jfif', 1, 7, 'Manolo'),
(2, '2025-11-17 10:00:00', 'Cortes modernos', 'Trabajador.jfif', 2, 8, 'Miguel'),
(3, '2025-11-17 11:00:00', 'Degradados', 'Trabajador.jfif', 3, 9, 'Juan'),
(4, '2025-11-17 12:00:00', 'Infantiles', 'Trabajador.jfif', 4, 10, 'Antonio'),
(5, '2025-11-17 13:00:00', 'Profesionales', 'Trabajador.jfif', 5, 7, 'Felipe'),
(6, '2025-11-17 14:00:00', 'Estilo libre', 'Trabajador.jfif', 6, 8, 'Marcos'),
(7, '2025-11-17 15:00:00', 'Ejecutivos', 'Trabajador.jfif', 7, 9, 'Antoñito'),
(8, '2025-11-17 16:00:00', 'Rápidos', 'Trabajador.jfif', 8, 10, 'Atanasio');


/*HORARIO TRABAJADOR*/
INSERT INTO `tfg`.`horario_trabajador` 
(`idHorario_trabajador`, `fecha`, `hora`, `disponible`, `Detalle_Trabajador_idDetalle_Trabajador`) 
VALUES
(1, '2025-11-18', '09:00:00', 1, 1),
(2, '2025-11-18', '10:00:00', 1, 1),
(3, '2025-11-18', '11:00:00', 1, 1),
(4, '2025-11-18', '12:00:00', 1, 2),
(5, '2025-11-18', '13:00:00', 1, 2),
(6, '2025-11-19', '09:00:00', 1, 3),
(7, '2025-11-19', '10:30:00', 1, 3),
(8, '2025-11-19', '11:30:00', 1, 4),
(9, '2025-11-19', '12:30:00', 1, 4),
(10, '2025-11-19', '13:30:00', 1, 5),
(11, '2025-11-19', '14:30:00', 1, 6),
(12, '2025-11-19', '15:30:00', 1, 7),
(13, '2025-11-19', '16:30:00', 1, 8);

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

/*ARTICULOS*/
INSERT INTO `tfg`.`articulo` (`idArticulo`, `Nombre`, `Descripcion`, `Imagen`, `Precio`, `Categoria`) VALUES
(1, 'Crema Facial', 'Crema hidratante', 'CremaFacial.jfif', 19.99, 'Cosmética'),
(2, 'Sérum Antiedad', 'Sérum para reducir arrugas', 'SerumAntiEdad.jfif', 29.99, 'Cosmética'),
(3, 'Perfume Floral', 'Fragancia floral de larga duración', 'PerfumeFloral.jfif', 49.99, 'Perfumería'),
(4, 'Champú Revitalizante', 'Champú para cabello', 'ChampuRevitalizante.jfif', 12.50, 'Cuidado Capilar'),
(5, 'Mascarilla Capilar', 'Mascarilla nutritiva', 'MascarillaCapilar.jfif', 15.75, 'Cuidado Capilar'),
(6, 'Gel Corporal', 'Gel refrescante y energizante', 'GelCorporal.jfif', 9.99, 'Cuidado Corporal'),
(7, 'Exfoliante Facial', 'Exfoliante suave para todo tipo de piel', 'ExfolianteFacial.jfif', 14.99, 'Cosmética'),
(8, 'Loción Hidratante', 'Loción corporal hidratante', 'LocionHidratante.jfif', 11.50, 'Cuidado Corporal'),
(9, 'Bálsamo Labial', 'Bálsamo nutritivo para labios secos', 'BalsamoLabial.jfif', 4.99, 'Cosmética'),
(10, 'Aceite Esencial', 'Aceite esencial relajante de lavanda', 'AceiteEsencial.jfif', 19.50, 'Aromaterapia');


UPDATE detalle_trabajador
SET Usuario_idUsuario = 10
WHERE idDetalle_Trabajador = 5;