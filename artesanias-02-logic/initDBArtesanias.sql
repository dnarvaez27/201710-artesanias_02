DELETE FROM BOLETAENTITY;
DELETE FROM REVIEWENTITY;
DELETE FROM ARTESANIAENTITY;
DELETE FROM ESPECTADORENTITY;
DELETE FROM CONFERENCIAENTITY;
DELETE FROM SALONENTITY;
DELETE FROM ORGANIZADORENTITY;
DELETE FROM USUARIOENTITY;
DELETE FROM STANDENTITY;
DELETE FROM ARTESANOENTITY;
DELETE FROM PABELLON;
DELETE FROM FERIAENTITY;
DELETE FROM ESPACIOENTITY;
DELETE FROM CIUDADENTITY;

-- Ciudades
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES ('https://www.las2orillas.co/wp-content/uploads/2017/02/Bogota1.jpg', 'Bogotá', 'Colombia');
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES ('https://www.bodehogar.co/wp-content/uploads/2016/05/barranquilla_03.jpg', 'Barranquilla', 'Colombia');
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES ('https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/NOLA_Header.jpg/300px-NOLA_Header.jpg', 'New Orleans', 'Estados Unidos');
INSERT INTO CIUDADENTITY (IMAGE, NOMBRE, PAIS) VALUES ('https://upload.wikimedia.org/wikipedia/commons/3/38/Humpolec_%286%29.jpg', 'Humpolec', 'República Checa');

-- Espacios
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (148, 'Nelson Crossing', 'src\utils\img\espacios\Espacio 1.jpg', 'Manley', '33-(749)963-3480', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (393, 'Menomonie Way', 'http://www.cbeventos.cl/imagenes/fondo1.fw.png', 'Burning Wood', '48-(807)425-5186', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (413, 'Cardinal Court', 'http://www.publiboda.com/redpb/wp-content/uploads/2015/05/IMG_1115_6_7_fused1.jpg', 'Fallview', '976-(180)782-7218', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (250, 'Toban Circle', 'http://www.centroeventoslaspalmeras.cl/img/licanray/7.jpg', 'Linden', '234-(408)827-0330', 1);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (152, 'Anderson Alley', 'https://media-cdn.tripadvisor.com/media/photo-o/07/2a/e5/fa/centro-de-eventos-olmue.jpg', 'Golf View', '420-(110)285-0105', 2);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (755, 'Maple Crossing', 'http://images.adsttc.com/media/images/5131/2c5c/b3fc/4b0d/9800/1184/newsletter/1336620936-san-francisco-1.jpg?1412053001', 'Red Cloud', '86-(929)625-1938', 2);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (376, 'Spohn Avenue', 'https://images.ineventos.com/cl/2015/08/116158/centro-de-eventos-ojo-de-agua-175289-i-l.jpg', 'Debs', '86-(764)759-2355', 2);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (497, 'Buell Avenue', 'http://images.adsttc.com/media/images/50b4/3a8d/b3fc/4b0c/f500/015c/large_jpg/4pp.jpg?1414178015', 'Forster', '62-(190)987-7055', 3);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (273, 'Hooker Junction', 'http://www.colarte.com/graficas/colecciones/Cali/Arquitectura/CalArsa22111.jpg', 'Sycamore', '1-(520)661-8873', 3);
insert into ESPACIOENTITY (CAPACIDAD, DIRECCION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) values (537, 'Judy Avenue', 'http://centrodeeventos.ce.gov.br/wp-content/uploads/2014/08/CEC-Fachada-a%C3%A9rea.jpg', 'Bultman', '63-(794)296-2574', 3);

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
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('16563156', 'src/utils/img/artesanos/Artesano 6.jpg', 'Ricky Pilmoor', '86-(952)223-5101', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('29287496', 'src/utils/img/artesanos/Artesano 7.jpg', 'Ramonda Ewdale', '84-(278)925-1037', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('23052339', 'src/utils/img/artesanos/Artesano 8.jpg', 'Garnette Stockings', '46-(917)931-9177', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('22985852', 'src/utils/img/artesanos/Artesano 9.jpg', 'Renato Reinbeck', '62-(903)203-1228', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('24050174', 'src/utils/img/artesanos/Artesano 10.jpg', 'Whitaker Adanet', '995-(538)915-0893', 3);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('14668492', 'src/utils/img/artesanos/Artesano 11.jpg', 'Albert Hazle', '57-(342)472-3802', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('21147436', 'src/utils/img/artesanos/Artesano 12.jpg', 'Artur Pavinese', '359-(133)209-8563', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('21272239', 'src/utils/img/artesanos/Artesano 13.jpg', 'Stefan Blackader', '66-(942)579-3227', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('4526684', 'src/utils/img/artesanos/Artesano 14.jpg', 'Timmy Warrell', '86-(209)516-9896', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('15894444', 'src/utils/img/artesanos/Artesano 15.jpg', 'Giuseppe Auckland', '48-(312)492-1052', 3);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('15964397', 'src/utils/img/artesanos/Artesano 16.jpg', 'Rene Geard', '380-(614)820-6908', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('25710140', 'src/utils/img/artesanos/Artesano 17.jpg', 'Bendix Hutt', '976-(440)494-9259', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('4137850', 'src/utils/img/artesanos/Artesano 18.jpg', 'Feliks Sterte', '62-(909)912-9060', 2);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('13950668', 'src/utils/img/artesanos/Artesano 19.jpg', 'Gilberta Jendrusch', '51-(366)744-2335', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('5672631', 'src/utils/img/artesanos/Artesano 20.jpg', 'Marie Kerby', '46-(505)546-6933', 3);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('16924973', 'src/utils/img/artesanos/Artesano 21.jpg', 'Minnie Burnep', '1-(480)986-1480', 1);
INSERT INTO ARTESANOENTITY (IDENTIFICACION, IMAGE, NOMBRE, TELEFONO, CIUDAD_ID) VALUES ('3822286', 'src/utils/img/artesanos/Artesano 22.jpg', 'Eadith Sherington', '92-(535)231-7086', 2);

-- Artesanias
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 1);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 2);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 3);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 3);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros', 3);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 3);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 3);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 3);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 4);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 4);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 4);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 4);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 4);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 4);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 5);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 5);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 5);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 5);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 5);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 5);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 6);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 6);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros',6 );
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 6);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 6);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 6);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 7);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 7);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 7);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 7);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 7);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 7);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 8);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 8);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 8);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 8);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 8);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 8);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 9);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 9);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros', 9);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 9);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 9);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 9);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 10);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 10);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 10);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 10);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 10);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 10);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 11);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 11);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 11);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 11);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 11);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 11);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 12);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 12);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros', 12);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 12);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 12);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 12);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 13);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 13);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 13);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 13);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 13);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 13);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 14);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 14);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 14);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 14);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 14);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 14);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 15);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 15);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros', 15);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 15);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 15);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 15);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 16);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 16);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 16);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 16);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 16);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 16);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 17);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 17);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 17);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 17);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 17);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 17);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 18);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 18);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros', 18);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 18);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 18);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 18);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Manualidades', 19);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 2.jpg', 'Vasijas', 19);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Porcelana', 19);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Manualidades', 19);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 9.jpg', 'Vasijas', 19);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 12.jpg', 'Porcelana', 19);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 4.jpg', 'Collares', 20);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Maderas', 21);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 6.jpg', 'Sombreros', 21);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 7.jpg', 'Collares', 21);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 10.jpg', 'Maderas', 21);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 11.jpg', 'Sombreros', 21);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 13.jpg', 'Collares', 22);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 14.jpg', 'Maderas', 22);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 1.jpg', 'Sombreros', 22);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 5.jpg', 'Collares', 22);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 8.jpg', 'Maderas', 22);
INSERT INTO ARTESANIAENTITY (IMAGEN, NOMBRE, ARTESANO_ID) VALUES ('src/utils/img/artesanias/Artesania 3.jpg', 'Sombreros', 22);

-- Reviews
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia.', 1, 9);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum.', 3, 5);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit.', 2, 11);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Mauris ullamcorper purus sit amet nulla.', 5, 19);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Aliquam non mauris.', 4, 18);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nulla justo. Aliquam quis turpis eget elit sodales scelerisque.', 5, 5);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nulla suscipit ligula in lacus.', 4, 10);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nulla tellus. In sagittis dui vel nisl.', 3, 15);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 1, 5);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 1, 15);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Vivamus tortor. Duis mattis egestas metus.', 4, 17);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 5, 18);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', 3, 3);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', 4, 18);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', 4, 3);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Integer ac neque.', 2, 16);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Phasellus in felis.', 1, 4);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', 3, 13);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', 5, 13);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 2, 20);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.', 5, 8);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 2, 17);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Morbi non quam nec dui luctus rutrum. Nulla tellus.', 5, 21);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nulla justo.', 4, 3);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Aliquam non mauris.', 5, 16);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 2, 19);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 4, 10);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis.', 4, 19);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Integer ac neque. Duis bibendum.', 2, 10);
insert into REVIEWENTITY (COMENTARIO, PUNTUACION, ARTESANO_ID) values ('Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum.', 1, 17);

-- Organizadores
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'DfE1j8JD', 'emedina0@thetimes.co.uk', 'src\utils\img\organizadores\organizador 1.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (7,'7');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'GRwerZ', 'gsimmons1@mail.ru', 'src\utils\img\organizadores\organizador 2.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (8,'8');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', '6XyQgDj0Eq73', 'fgonzalez2@simplemachines.org', 'src\utils\img\organizadores\organizador 3.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (9,'9');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', '3dX2WeTTZwQ0', 'towens3@prweb.com', 'src\utils\img\organizadores\organizador 4.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (10,'10');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', '4Yiudn', 'jkelly4@usa.gov', 'src\utils\img\organizadores\organizador 5.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (11,'11');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'XySRYG1', 'smurray5@independent.co.uk', 'src\utils\img\organizadores\organizador 6.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (12,'13');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'd3GkkbvWpmu3', 'pgonzales6@tripadvisor.com', 'src\utils\img\organizadores\organizador 7.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (13,'13');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'kzB3qxDPq', 'dbowman7@plala.or.jp', 'src\utils\img\organizadores\organizador 8.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (14,'14');
insert into USUARIOENTITY (DTYPE, CONTRASENA, CORREO, FOTO) values ('OrganizadorEntity', 'vYwEcY4', 'drichardson8@howstuffworks.com', 'src\utils\img\organizadores\organizador 9.jpg');
INSERT INTO ORGANIZADORENTITY (ID, IDENTIFICACION) VALUES (15,'15');


-- Ferias

insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.8, 0.7, 1.0, '12/30/2016', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/01/2016', 'Feria Artesanal 1', 400, 1);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.8, 0.7, 1.0, '12/29/2016', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/02/2016', 'Feria Artesanal 2', 500, 3);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.8, 0.7, 1.0, '12/30/2016', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/01/2016', 'Feria Artesanal 3', 420, 2);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.9, 0.8, 1.0, '12/29/2017', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/02/2017', 'Feria Artesanal 4', 530, 4);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.8, 0.9, 1.0, '12/30/2018', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/01/2018', 'Feria Artesanal 5', 440, 5);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.7, 0.7, 1.0, '12/29/2019', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/02/2019', 'Feria Artesanal 6', 550, 6);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.8, 0.7, 1.0, '12/30/2020', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/01/2020', 'Feria Artesanal 7', 420, 2);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.9, 0.8, 1.0, '12/29/2021', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/02/2021', 'Feria Artesanal 8', 530, 4);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.8, 0.9, 1.0, '12/30/2022', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/01/2022', 'Feria Artesanal 9', 440, 5);
insert into FERIAENTITY (descuentomayores, descuentomenores, descuentoregular,
fin, image, inicio, nombre, totalboletas, espacio_id)
values (0.7, 0.7, 1.0, '12/29/2023', 
'https://dummyimage.com/1524x2000/948e94/fff.jpg', 
'10/02/2023', 'Feria Artesanal 10', 550, 6);

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

insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/30/2016', '10/01/2016', 30.0, 1, 1, 1);
insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/30/2016', '10/01/2016', 30.0, 1, 2, 1);
insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/30/2016', '10/01/2016', 30.0, 1, 3, 1);
insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/30/2016', '10/01/2016', 30.0, 1, 4, 1);
insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/20/2016', '10/10/2016', 20.0, 1, 5, 1);
insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/20/2016', '10/10/2016', 25.0, 2, 5, 1);
insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/20/2016', '10/10/2016', 40.0, 3, 5, 1);

insert into BOLETAENTITY (fin, inicio, precio, tipo, espectador_id, id_feria)
values ('12/30/2016', '10/01/2016', 30.0, 1, 6, 2);

insert into artesanoferiaassociation (fecha, artesano_id, feria_id, stand_id)
values ('12/20/2016', 1, 1, 1);
insert into artesanoferiaassociation (fecha, artesano_id, feria_id, stand_id)
values ('12/21/2016', 2, 1, 2);
insert into artesanoferiaassociation (fecha, artesano_id, feria_id, stand_id)
values ('12/22/2016', 3, 1, 3);
