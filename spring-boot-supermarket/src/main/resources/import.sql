INSERT INTO users (username, password) VALUES ('Gonzalo','123456');
INSERT INTO carnes (proveedor,nombre,peso,precio,fecha_elaboracion,fecha_vencimiento,animal,categoria) VALUES ('Pechuga King-Chicken','Pechuga de Pollo',1000,800,NOw(),NOW(), 'Gallina','Economica');
INSERT INTO verduras (proveedor,nombre,peso,precio,fecha_elaboracion,fecha_vencimiento,categoria,congelado) VALUES ('Verduras Pompeya','Tomate',1000,450,NOw(),NOW(), 'Premium',TRUE);
INSERT INTO annual_percentages (categoria,year,ene,feb,mar,abr,may,jun,jul,ago,sep,oct,nov,dic) VALUES ('carne',2023,1.2,1.3,1.1,0.9,2.1,0.3,0.7,0.7,1.1,1.4,1.6,2.2);
INSERT INTO annual_percentages (categoria,year,ene,feb,mar,abr,may,jun,jul,ago,sep,oct,nov,dic) VALUES ('verdura',2023,1.5,2.3,0.1,1.3,1.1,1.3,1.7,1.7,0.10,1.2,1.1,2.0);