INSERT INTO Genre(valeur) VALUES ('Homme');
INSERT INTO Genre(valeur) VALUES ('Femme');

INSERT INTO Utilisateur(nom, prenom, email, motDePasse, contact, adresse, idGenre, dateDeNaissance) VALUES 
('RAKOTO', 'Fara', 'Rakoto@gmail.com', '123LAZA', '0331246544', 'B112Faravohitra', 1, '1990-10-12');
INSERT INTO Utilisateur(nom, prenom, email, motDePasse, contact, adresse, idGenre, dateDeNaissance) VALUES 
('ANDRIAMALALA', 'Benja', 'Benja@gmail.com', '00012FA', '0321524633', 'TER103Antanimena', 2, '1990-10-12');
INSERT INTO Utilisateur(nom, prenom, email, motDePasse, contact, adresse, idGenre, dateDeNaissance) VALUES 
('RAKOTO', 'Jean', 'Jean@gmail.com', 'jean123', '0324496385', 'Lot 116 II A Nanisana', 1, '1990-10-12');

INSERT INTO Compte(idUtilisateur, numero, dateCompte, solde) VALUES (1, 'COMPTE01', '2022-05-14', 21000);
INSERT INTO Compte(idUtilisateur, numero, dateCompte, solde) VALUES (2, 'COMPTE02', '2022-09-23', 150000);
INSERT INTO Compte(idUtilisateur, numero, dateCompte, solde) VALUES (3, 'COMPTE03', '2022-09-23', 350000);

INSERT INTO Categorie(idCategorie, valeur) VALUES (1, 'electronique');
INSERT INTO Categorie(idCategorie, valeur) VALUES (2, 'decoration');

INSERT INTO Rechargement(idCompte, montant, dateRechargement) VALUES (1, 1000, '2023-01-09');
INSERT INTO Rechargement(idCompte, montant, dateRechargement) VALUES (2, 15500, '2023-01-10');

INSERT INTO RechargementValide(idRechargement, dateValidation) VALUES (1, '2023-01-09');

INSERT INTO Commission(idCommission, taux, dateCommission) VALUES (1, 10, '2022-05-12');

INSERT INTO Enchere(nom,descriptions,prixEnchere,idUtilisateur,idCommission,idCategorie,dateEnchere,duree )
VALUES ('Vase','En porceline venant d''Afrique', 100000,1,1,2, '2023-01-13 15:00:00', '2:00:00');
INSERT INTO Enchere(nom,descriptions,prixEnchere,idUtilisateur,idCommission,idCategorie,dateEnchere,duree ) 
VALUES ('Appareil photo','lourde', 175000,2,1,1, '2023-01-14 15:00:00', '2:00:00');

INSERT INTO Admins(nom, prenom, email, motDePasse) VALUES ('ANDRIAMBELO', 'Liantsoa', 'Liantsoa@gmail.com', '1234LI');

INSERT INTO Offre(idEnchere, idUtilisateur, prixOffre, dateOffre) VALUES (1, 2, 120000, '2023-01-13 15:15:00');

-- INSERT INTO EnchereVendu(idEnchere, idOffre) VALUES (1, 1);
-- INSERT INTO EnchereVendu(idEnchere, idOffre) VALUES (2, 2);

-- INSERT INTO ImageEnchere(idImageEnchere, nomImage, format, idEnchere) VALUES (1, 'IMG01', 'PNG', 1);
-- INSERT INTO ImageEnchere(idImageEnchere, nomImage, format, idEnchere) VALUES (2, 'IMG03', 'JPG', 2);
