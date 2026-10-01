# Java Training Assignments

This folder contains Java practice assignments and a small Hospitality Management System.

## Folders

- `Module3_Assignments` - Java programming and student examples.
- `Module4_Assignments` - Calculator and exception-handling exercises.
- `HospitalityManagementSystem` - A Java Swing application for hotels, rooms, guests, and reservations.

## Requirements

- Java JDK installed
- PowerShell or a Java-supported terminal

## Run Module 3

```powershell
cd Module3_Assignments
javac *.java
java Main
```

## Run the Hospitality Management System

```powershell
cd HospitalityManagementSystem
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out hospitality.Main
```

The hospitality application runs with in-memory data. Its database design is available in `HospitalityManagementSystem/schema.sql`.

## Notes

The assignment folders contain separate Java exercises. Compile and run the class that contains the `main` method for each exercise.
