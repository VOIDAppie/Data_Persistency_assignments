-- ------------------------------------------------------------------------
-- Data & Persistency
-- Opdracht S6: Views
--
-- (c) 2020 Hogeschool Utrecht
-- Tijmen Muller (tijmen.muller@hu.nl)
-- André Donk (andre.donk@hu.nl)
-- ------------------------------------------------------------------------


-- S6.1.
--
-- 1. Maak een view met de naam "deelnemers" waarmee je de volgende gegevens uit de tabellen inschrijvingen en uitvoering combineert:
--    inschrijvingen.cursist, inschrijvingen.cursus, inschrijvingen.begindatum, uitvoeringen.docent, uitvoeringen.locatie
-- 2. Gebruik de view in een query waarbij je de "deelnemers" view combineert met de "personeels" view (behandeld in de les):
CREATE OR REPLACE VIEW personeel AS
SELECT mnr, voorl, naam as medewerker, afd, functie
FROM medewerkers;
-- 3. Is de view "deelnemers" updatable ? Waarom ?
CREATE OR REPLACE VIEW deelnemers AS
SELECT
    i.cursist,
    i.cursus,
    i.begindatum,
    u.docent,
    u.locatie
FROM
    inschrijvingen i
        JOIN
    uitvoeringen u ON i.cursus = u.cursus AND i.begindatum = u.begindatum;

SELECT d.*, p.mnr, p.voorl, p.medewerker, p.afd, p.functie
FROM deelnemers d
         JOIN personeel p ON d.cursist = p.mnr;

-- het is mogelijk om de view te updaten maar de moet de row die geupdate moet worden overeenkomen met de row uit de tabel.

-- S6.2.
--
-- 1. Maak een view met de naam "dagcursussen". Deze view dient de gegevens op te halen:
--      code, omschrijving en type uit de tabel curssussen met als voorwaarde dat de lengte = 1. Toon aan dat de view werkt.
-- 2. Maak een tweede view met de naam "daguitvoeringen".
--    Deze view dient de uitvoeringsgegevens op te halen voor de "dagcurssussen" (gebruik ook de view "dagcursussen"). Toon aan dat de view werkt
-- 3. Verwijder de views en laat zien wat de verschillen zijn bij DROP view <dagcursussen> CASCADE en bij DROP view <daguitvoeringen> RESTRICT

CREATE OR REPLACE VIEW dagcursussen AS
SELECT code, omschrijving, type
FROM cursussen
WHERE lengte = 1;

select * from dagcursussen;

CREATE OR REPLACE VIEW daguitvoeringen AS
SELECT u.cursus, u.begindatum, u.docent, u.locatie
FROM uitvoeringen u
         JOIN dagcursussen d ON u.cursus = d.code;

SELECT * FROM daguitvoeringen;

-- To drop the view dagcursussen with CASCADE
DROP VIEW IF EXISTS dagcursussen CASCADE;

-- To drop the view daguitvoeringen with RESTRICT
DROP VIEW IF EXISTS daguitvoeringen RESTRICT;

-- DROP VIEW CASCADE is eigenlijk een hard delete wat ook alle objecten die hieraan verbonden zit verwijderd
-- DROP VIEW RESTRICT is wat veiliger en checkt of er nog dependencies zijn op de view als dit wel zo is dan gooit hij een error message.
-- dus de DROP VIEW RESTRICT is meer aan te raden als je veel objecten hebt die vastzitten aan de desbetreffende view.
