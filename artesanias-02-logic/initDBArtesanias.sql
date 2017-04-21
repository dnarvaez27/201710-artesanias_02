DELETE FROM BOLETAENTITY;
DELETE FROM CONFERENCIAENTITY;
DELETE FROM FERIAENTITY;
DELETE FROM ORGANIZADORENTITY;
DELETE FROM REVIEWENTITY;
DELETE FROM ARTESANIAENTITY;
DELETE FROM ARTESANOENTITY;
DELETE from STANDENTITY;
DELETE FROM SALONENTITY;
DELETE FROM PABELLON;
DELETE FROM ESPACIOENTITY;
DELETE FROM ESPECTADORENTITY;
DELETE FROM CIUDADENTITY;

-- Ciudades
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES (null, 'Bogotá', 'Colombia');
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES (null, 'Barranquilla', 'Colombia');
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES (null, 'New Orleans', 'Estados Unidos');
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES (null, 'Humpolec', 'República Checa');

-- Espacios
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (148, 'Nelson Crossing', 'src\utils\img\espacios\Espacio 1.jpg', 'Manley', '33-(749)963-3480', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (393, 'Menomonie Way', 'src\utils\img\espacios\Espacio 2.jpg', 'Burning Wood', '48-(807)425-5186', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (413, 'Cardinal Court', 'src\utils\img\espacios\Espacio 3.jpg', 'Fallview', '976-(180)782-7218', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (250, 'Toban Circle', 'src\utils\img\espacios\Espacio 4.jpg', 'Linden', '234-(408)827-0330', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (152, 'Anderson Alley', 'src\utils\img\espacios\Espacio 5.jpg', 'Golf View', '420-(110)285-0105', 2);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (755, 'Maple Crossing', 'src\utils\img\espacios\Espacio 6.jpg', 'Red Cloud', '86-(929)625-1938', 2);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (376, 'Spohn Avenue', 'src\utils\img\espacios\Espacio 7.jpg', 'Debs', '86-(764)759-2355', 2);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (497, 'Buell Avenue', 'src\utils\img\espacios\Espacio 8.jpg', 'Forster', '62-(190)987-7055', 3);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (273, 'Hooker Junction', 'src\utils\img\espacios\Espacio 9.jpg', 'Sycamore', '1-(520)661-8873', 3);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (537, 'Judy Avenue', 'src\utils\img\espacios\Espacio 10.jpg', 'Bultman', '63-(794)296-2574', 3);

-- Pabellones
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (234, null, 'Plaza', 1);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (752, null, 'Plaza', 1);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (762, null, 'Place', 1);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (296, null, 'Street', 2);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (551, null, 'Place', 2);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (230, null, 'Way', 3);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (303, null, 'Trail', 1);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (278, null, 'Junction', 1);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (268, null, 'Street', 1);
insert into PABELLON (CAPACIDAD, IMAGEN, TIPO, ID_ESPACIO) values (349, null, 'Point', 2);

-- Stands

insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.', '500x500', null, 1, 113920, 1);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', '500x500', null, 2, 116620, 1);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.', '500x500', null, 3, 110170, null);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', '500x500', null, 4, 131451, 2);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices', '500x500', null, 5, 70311, 2);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 'Syntax error in formula ''500x500''', null, 6, 139169, 3);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl.', '500x500', null, 7, 27942, 3);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', '500x500', null, 8, 143879, 4);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis.', '500x500', null, 9, 116798, 4);
insert into STANDENTITY (DESCRIPCION, DIMENSIONES, IMAGEN, NUMERO, PRECIO, PABELLON_ID) values ('Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue.', '500x500', null, 10, 90293, 4);

-- Salones

insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (562, 1, 1);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (141, 2, 1);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (564, 3, 1);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (559, 4, 2);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (293, 5, 2);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (390, 6, 2);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (277, 7, 3);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (171, 8, 3);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (667, 9, 3);
insert into SALONENTITY (CAPACIDAD, NUMERO, PABELLON_ID) values (577, 10, 3);

-- Espectadores
INSERT INTO USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO)VALUES ('EspectadorEntity', 'XjLSG9U', 'jweaver0@tumblr.comsomeone@mail.com', 'src\utils\img\espectadores\Espectador 1.jpg');
INSERT INTO ESPECTADORENTITY (ID, EDAD) VALUES (1, 20);
INSERT INTO USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO)VALUES ('EspectadorEntity', 'rfNILyx9Uaf', 'frivera2@artisteer.com', 'src\utils\img\espectadores\Espectador 2.jpg');
INSERT INTO ESPECTADORENTITY (ID, EDAD) VALUES (2, 18);
INSERT INTO USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO)VALUES ('EspectadorEntity', '8L95Ri', 'lriverag@liebe.com', 'src\utils\img\espectadores\Espectador 3.jpg');
INSERT INTO ESPECTADORENTITY (ID, EDAD) VALUES (3, 21);
INSERT INTO USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO)VALUES ('EspectadorEntity', '8KLk8ym3', 'jstevens8@bbb.org', 'src\utils\img\espectadores\Espectador 4.jpg');
INSERT INTO ESPECTADORENTITY (ID, EDAD) VALUES (4, 40);
INSERT INTO USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO)VALUES ('EspectadorEntity', 'R6SHy4', 'swoods9@eventbrite.com', 'src\utils\img\espectadores\Espectador 5.jpg');
INSERT INTO ESPECTADORENTITY (ID, EDAD) VALUES (5, 35);
INSERT INTO USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO)VALUES ('EspectadorEntity', '0ldTuLf3G35D', 'aholmes7@youku.com', 'src\utils\img\espectadores\Espectador 6.jpg');
INSERT INTO ESPECTADORENTITY (ID, EDAD) VALUES (6, 36);

-- Artesanos
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('42923498', 'src/utils/img/artesanos/Artesano 1.jpg', 'Carol Morgan', '1-(214)359-3669', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('60398633', 'src/utils/img/artesanos/Artesano 2.jpg', 'Irene Price', '86-(749)218-0396', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('22821007', 'src/utils/img/artesanos/Artesano 3.jpg', 'Edward Bailey', '1-(214)359-3669', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('8572601', 'src/utils/img/artesanos/Artesano 4.jpg', 'Ralph Montgomery', '86-(749)218-0396', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('23583385', 'src/utils/img/artesanos/Artesano 5.jpg', 'Doris Tucker', '7-(840)161-5276', 3);

-- Artesanias
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 2);

-- Reviews
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti.',1.1, 1);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Morbi a ipsum. Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.',0.11, 1);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.',1.64, 1);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Curabitur gravida nisi at nibh.', 2.1, 1);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum .',0.76, 1);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesqu Nam ultrices, ',4.15, 1);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti.',4.76, 2);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', 0.11, 2);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Donec dapibus.', 3.84, 2);
INSERT INTO REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) VALUES ('Phasellus in felis. Donec semper sapien a libero. Nam dui.', 0.3, 2);

-- Organizadores
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'DfE1j8JD', 'emedina0@thetimes.co.uk', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (7,'7');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'GRwerZ', 'gsimmons1@mail.ru', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (8,'8');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', '6XyQgDj0Eq73', 'fgonzalez2@simplemachines.org', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (9,'9');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', '3dX2WeTTZwQ0', 'towens3@prweb.com', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (10,'10');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', '4Yiudn', 'jkelly4@usa.gov', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (11,'11');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'XySRYG1', 'smurray5@independent.co.uk', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (12,'13');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'd3GkkbvWpmu3', 'pgonzales6@tripadvisor.com', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (13,'13');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'kzB3qxDPq', 'dbowman7@plala.or.jp', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (14,'14');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'vYwEcY4', 'drichardson8@howstuffworks.com', null);
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (15,'15');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'nKdiNE', 'mcarpenter9@is.gd', null);

-- TODO Ferias

-- Conferencias
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Kathleen Fields', '12/8/2016', '10/28/2016', '2:30', '7:53', 'Open-architected multi-state moratorium', 1, 4);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Patrick Castillo', '10/23/2016', '7/29/2016', '13:43', '17:20', 'Business-focused upward-trending functionalities', 1, 5);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Joe Garrett', '3/28/2017', '11/10/2016', '4:21', '5:13', 'Decentralized interactive installation', 1, 5);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Linda Lynch', '3/14/2017', '12/15/2016', '11:25', '11:47', 'Cross-group empowering paradigm', 1, 4);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Phyllis Robinson', '11/30/2016', '6/11/2016', '3:07', '7:45', 'Cross-group stable policy', 1, 4);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Christine Gibson', '7/27/2016', '5/11/2016', '15:57', '17:57', 'Devolved value-added conglomeration', 1, 3);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Doris Larson', '10/19/2016', '1/2/2017', '20:21', '4:42', 'Reduced system-worthy extranet', 1, 3);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Brenda Berry', '9/11/2016', '11/16/2016', '21:41', '18:15', 'Organic bandwidth-monitored help-desk', 1, 1);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Todd Vasquez', '12/18/2016', '12/15/2016', '16:19', '2:34', 'Intuitive clear-thinking frame', 1, 2);
insert into CONFERENCIAENTITY (CONFERENCISTA, FECHAFIN, FECHAINICIO, HORAFIN, HORAINICIO, TEMA, FERIA_ID, SALON_ID) values ('Larry Grant', '1/16/2017', '1/17/2017', '13:31', '6:34', 'Multi-layered incremental benchmark', 1, 1);

-- TODO Boletas

-- TODO Many-To-Many