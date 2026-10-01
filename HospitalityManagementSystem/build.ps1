$ErrorActionPreference = 'Stop'
New-Item -ItemType Directory -Force out | Out-Null
$sourceFiles = Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName
javac -d out $sourceFiles
$javaHome = 'C:\Program Files\Java\jdk-20'
$jarTool = Join-Path $javaHome 'bin\jar.exe'
if (-not (Test-Path $jarTool)) { $jarTool = 'jar.exe' }
& $jarTool cfe HospitalityManagementSystem.jar hospitality.Main -C out .
Write-Host 'Created HospitalityManagementSystem.jar'
