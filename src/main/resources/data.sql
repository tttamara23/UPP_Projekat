insert into poslovniprocesi.casopis (id,naziv,issn_broj,is_open_access) values
(1,'Hemijske reakcije','11221122',1);
insert into poslovniprocesi.casopis (id,naziv,issn_broj,is_open_access) values
(2,'Uticaj softvera na drustvo','23232323',0);

insert into poslovniprocesi.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(1,'Opste',2);

insert into poslovniprocesi.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(2, 'Hemija',2);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (1,'Srbija',
'malimrav119@gmail.com','Subotica','Tamara','Perlinac',1,2,'Titula','tamip','tamara',1);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (2,'Srbija',
'studentie2novisad@gmail.com','Novi Sad','Pera','Peric',0,2,'Titula','perap','perica',1);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka) values (3,'Srbija',
'tamara.perlinac@yahoo.com','Novi Sad','Mare','Recenzent',0,1,'Titula','marer','mare');

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka) values (4,'Srbija',
'sneza.perlinac@yahoo.com','Novi Sad','Jovica','Jovic',0,1,'Titula','jjovic','jovica');

insert into poslovniprocesi.casopis_recenzent (id,casopis_id,recenzent_id) values
(1,1,3);

insert into poslovniprocesi.casopis_recenzent (id,casopis_id,recenzent_id) values
(2,1,4);
