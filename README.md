# Sistem de Gestiune pentru Bibliotecă

Aplicația este dezvoltată în Java, folosind framework-ul Spring Boot pentru backend și Thymeleaf pentru paginile web. De asemenea, include un sistem de autentificare și o bază de date gata configurată pentru dezvoltare.

## Tehnologii folosite

* Java 25
* Spring Boot 4.0.6
* Spring Security (pentru protejarea paginilor prin login)
* Thymeleaf (pentru generarea interfeței web)
* Spring Data JPA (pentru interacțiunea cu baza de date)
* H2 Database (bază de date în memorie pentru testare) și PostgreSQL (pentru producție)
* Springdoc OpenAPI (pentru documentația API-ului)
* Spring Boot Validation (pentru verificarea datelor introduse de utilizator)

## De ce ai nevoie pentru a rula proiectul

* Java Development Kit (JDK) versiunea 25 instalat pe calculatorul tău.
* Un mediu de dezvoltare (IDE), noi recomandăm IntelliJ IDEA.
* Nu ai nevoie de Maven instalat separat, deoarece proiectul vine deja cu Maven Wrapper configurat.

După ce aplicația a pornit și vezi mesajul de confirmare în consolă, deschide un browser și navighează la:
http://localhost:8080

Nume de utilizator: user

Parola: Este generată automat de fiecare dată când pornești serverul. O poți găsi în consola din IntelliJ (caută un mesaj care conține textul "Using generated security password" urmat de un cod).

# Diagrama ER

![image_alt](https://github.com/Michael-dev-tech/AWBD_Gestiune-Biblioteca/blob/6bf7ddd3671d0010927e1a3c87e4a3c64d7188bc/er_diagram.png)
