# Hospitality Management System

A Core Java project for managing hotels, rooms, guests, and reservations.

## Features

- Swing GUI with Hotel, Room, Guest, and Reservation tabs.
- CRUD-style create, view, and delete operations.
- Reservation date-overlap validation and room availability checking.
- Reservation cost calculation by room price and number of nights.
- MySQL schema in `schema.sql`.
- Runs without MySQL using an in-memory DAO implementation, so it is easy to demonstrate.

## Build and run

From this project directory in PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out hospitality.Main
```

## Create the JAR

```powershell
jar cfe HospitalityManagementSystem.jar hospitality.Main -C out .
java -jar HospitalityManagementSystem.jar
```

The optional `DatabaseConnector` reads `HOSPITALITY_DB_URL`, `HOSPITALITY_DB_USER`, and `HOSPITALITY_DB_PASSWORD` when a MySQL JDBC driver is added to the classpath.
