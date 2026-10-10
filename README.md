# Minecraft Launcher (JavaFX + Official Minecraft Support)

Ovaj projekat je osnovni, legalan i ispravan launcher za Minecraft koji koristi JavaFX, Maven i arhitekturu kompatibilnu sa zvaničnim Minecraft klientom. Cilj je napraviti launcher koji može da pokreće zvanični Minecraft klijent, podržava profile, autentifikaciju i upravljanje verzijama, a ne imituje piratsku ili nelegalnu praksu.

Napomena:
- Ovaj projekat je fokusiran na ispravno i legalno pokretanje Minecraft-a.
- Ne koristi crack autentifikaciju, ne bypass-uje Microsoft/Mojang autentifikaciju i ne koristi ilegalan model rada.
- UI je dizajniran da izgleda premium i moderni, sličan launcher iskustvu, ali bez narušavanja pravila.

## Tehnologije

- Java 17+
- JavaFX 21
- Maven
- Gson za JSON
- ProcessBuilder za pokretanje igre
- Microsoft OAuth 2.0 za logovanje (u daljem razvoju)
- Mojang/Microsoft metadata za verzije i assets

## Zašto je Java + JavaFX najbolji izbor

Java + JavaFX je najbolji izbor zato što:
- Java je stabilna i popularna platforma za launcher aplikacije
- JavaFX omogućava vrlo lep i responsivan UI
- lakše je napraviti premium dark theme i animacije
- podržava pokretanje Minecraft igre sa proper Java processom
- daje dobar osnov za profesionalni launcher

## Arhitektura projekta

Projekt je strukturisan na način koji podržava realni launcher flow:

- UI layer: JavaFX
  - login ekran
  - profile kartice
  - launch ekran
  - settings ekran
  - animation i tema

- Auth layer: Microsoft OAuth
  - Microsoft account login
  - Xbox Live auth token flow
  - Minecraft access token
  - korisnički profil

- Version management layer
  - Mojang version manifest
  - libraries
  - assets index
  - game jar
  - native libraries

- Game launch layer
  - Java detection
  - JVM args
  - classpath generation
  - launch process creation
  - task lifecycle management

- Profile system
  - vanilla profile
  - Fabric profile
  - Forge profile
  - custom JVM arguments
  - custom memory settings
  - instance folders

## Legalni i ispravan pristup

Najbolji cilj launcher-a je da pokreće zvanični Minecraft klijent sa validnim account-om i local .minecraft installation folder-om. To znači:

- korisnik se prijavljuje preko Microsoft account-a
- launcher pravi validan token flow
- launcher koristi official Minecraft metadata
- launcher ne modifikuje igru i ne uključuje crack ili bypass
- modovi se podržavaju kao profili i instance, ali ne preko ilegalnih mehanizama

## Funkcionalnosti koje treba da postoje u finalnoj verziji

### 1. UI / iskustvo
- premium dark theme
- smooth glow efekti
- hover animacije
- play dugmad
- loading indikator
- profile cards
- settings panel
- status bar

### 2. Account sistemi
- Microsoft login
- offline mode opcija
- logged-in user profile
- skin support (u kasnijoj fazi)

### 3. Profile / instance management
- vanilla profile
- Fabric profile
- Forge profile
- multiple instances
- separate game directories
- custom memory allocation per profile

### 4. Version management
- list available versions
- download official Minecraft versions
- validate files
- parse version JSON
- install libraries and assets

### 5. Launch process
- detect installed Java
- detect version
- build classpath
- set JVM args
- run Minecraft process via ProcessBuilder
- handle exit and status updates

## Arhitektura launch procesa

Pravi launch flow treba da radi ovako:

1. Korisnik se prijavi
2. Launcher učita profile
3. Loader proverava verziju i instalaciju
4. Launcher proverava da li su libraries/assets dostupni
5. Java path se detektuje
6. Kreira se classpath za Minecraft
7. Dodaju se JVM argumenti
8. Pokreće se official Minecraft Main class
9. Launcher prati status igre i reaguje na izlazak

Primer logike:

```java
ProcessBuilder builder = new ProcessBuilder(
    javaPath,
    "-Xmx4G",
    "-Xms1G",
    "-cp",
    classpath,
    "net.minecraft.client.main.Main",
    "--version",
    version,
    "--gameDir",
    gameDir,
    "--assetsDir",
    assetsDir,
    "--username",
    username
);
```

## Microsoft OAuth flow

Najispravniji OAuth pristup za launcher je:

- Otvoriti Microsoft login page
- Dobiti code od Microsoft-a
- Zameniti code za access token
- Zameniti access token za Xbox Live token
- Dobiti XSTS token
- Dobiti Minecraft access token
- Preuzeti korisnički profil i skin

Ovaj tok je potreban ako launcher treba da bude potpuno funkcionalan i validan.

## Forge i Fabric podrška

Ako želiš launcher koji podržava mod loader-e, treba da postoji:

- profile type: vanilla / fabric / forge
- loader metadata
- installer download / setup
- custom arguments
- separate instance directory for each profile

Bez pravilnog profile modela, launcher neće biti stabilan ni za jednu ozbiljniju upotrebu.

## Najvažnije pravilo

Ne treba praviti launcher kao crack ili “hack” model. Najbolje i najsigurnije je napraviti launcher koji koristi zvanični Minecraft klijent, validan auth flow, i čiste instance/profile management strukture.

To je pravi put ka profesionalnom i legalnom launcher-u.

## Sledeći koraci

U narednom koraku će biti dodati:
- Microsoft autentifikacija
- version manifest parser
- Minecraft library downloader
- Forge / Fabric profile handler
- real game launch logic
- final launcher UI

Ovaj repo je već inicijalizovan kao kvalitetna osnova za razvoj pravog JavaFX Minecraft launchera.
