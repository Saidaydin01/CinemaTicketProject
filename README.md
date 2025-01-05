# Kinobilletsalg

Dette prosjektet er en enkel webapplikasjon for bestilling av kinobilletter. Applikasjonen er utviklet med Java Spring Boot for back-end, og HTML, CSS, JavaScript for front-end.

## Funksjonaliteter

- **Bestilling av billetter**: Brukere kan velge film, antall billetter og fylle ut personlige detaljer for å bestille.
- **Validering**:
    - **Frontend**: Skjemaet validerer at alle felt er korrekt utfylt før innsending.
    - **Backend**: Serveren validerer følgende krav:
        - Film må velges.
        - Antall billetter må være et positivt tall.
        - Fornavn og etternavn må være oppgitt.
        - Telefonnummer må være fylt ut.
        - E-postadresse må være gyldig.
- **Visning av bestillinger**: Alle registrerte bestillinger vises i en tabell.
- **Sletting av bestillinger**: Mulighet for å slette alle bestillinger.

## Teknologier brukt

- **Backend**: Java Spring Boot
- **Frontend**: HTML, CSS, JavaScript, Bootstrap
- **Database**: JDBC med SQL for lagring av bestillinger
- **Ajax**: Brukt for å hente og sende data mellom klient og server uten å oppdatere siden.

## Struktur

### Frontend

- **HTML**: `index.html` inneholder skjema for bestilling og tabell for visning av data.
- **CSS**: Tilpasset stilark for å forbedre brukeropplevelsen.
- **JavaScript**: Håndterer validering og kommunikasjon med backend via Ajax.

### Backend

- **Kontroller**: `BilletController` håndterer API-forespørsler og inkluderer serverside-validering.
- **Modell**: `Billet` representerer en billett med attributter som filmnavn, antall, navn, etc.
- **Repository**: `BilletRepository` håndterer kommunikasjon med databasen.
- **Applikasjon**: `Ticket3Application` starter Spring Boot-applikasjonen.

### Serverside Validering

I `BilletController` utføres følgende valideringer:
- Filmfeltet må ikke være tomt.
- Antall billetter må være større enn 0.
- Fornavn og etternavn må være oppgitt.
- Telefonnummer og e-post må være korrekt utfylt.

Ved feil i validering returnerer API-et en passende feilmelding.

## Hvordan kjøre prosjektet

1. **Installer avhengigheter**: Sørg for å ha Java, Maven, og en SQL-database installert.
2. **Sett opp databasen**:
    - Bruk SQL-skjemaet (`schema.sql`) for å opprette nødvendige tabeller.
3. **Start backend**:
    - Naviger til prosjektets rotmappe.
    - Kjør `mvn spring-boot:run` for å starte serveren.
4. **Åpne frontend**:
    - Åpne `index.html` i en nettleser for å få tilgang til applikasjonen.

## Endepunkter

- `POST /lagre`: Lagre en ny billett (med serverside-validering).
- `GET /hentAlle`: Hent alle billetter.
- `GET /slettAlle`: Slett alle billetter.

## Bidragsytere

- **Utvikler**: Said Aydin


Denne README-filen inkluderer både frontend- og serverside-valideringene, noe som gir en mer komplett oversikt over funksjonaliteten.
