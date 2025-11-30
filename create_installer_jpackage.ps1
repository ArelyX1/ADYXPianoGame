<#
PowerShell helper to create a Windows installer using jpackage.
Requirements:
- JDK 17+ (must include `jpackage` in PATH)
- `dist.jar` created by build.bat in the project root

Usage:
1. Generate `dist.jar` with build.bat
2. Run this script from project root in PowerShell (as admin if needed):
   ./create_installer_jpackage.ps1

#>

Write-Host "Checking for jpackage..."
$jpackage = Get-Command jpackage -ErrorAction SilentlyContinue
if (-not $jpackage) {
    Write-Error "jpackage not found in PATH. Install JDK 17+ and ensure jpackage is available."
    exit 1
}

if (-not (Test-Path -Path .\dist.jar)) {
    Write-Error "dist.jar not found. Run .\build.bat first."
    exit 1
}

$inputDir = "input"
if (-not (Test-Path $inputDir)) { New-Item -ItemType Directory -Path $inputDir | Out-Null }
Copy-Item -Path .\dist.jar -Destination (Join-Path $inputDir "dist.jar") -Force

$outDir = "installer"
if (-not (Test-Path $outDir)) { New-Item -ItemType Directory -Path $outDir | Out-Null }

Write-Host "Running jpackage..."

# Customize options below: --icon path\to\icon.ico, --win-per-user-install, --win-menu
jpackage --input $inputDir --name ADYXPianoGame --main-jar dist.jar --main-class dynamic_beat_17.Main --type exe --dest $outDir --app-version 1.0

if ($LASTEXITCODE -ne 0) {
    Write-Error "jpackage failed (exit code $LASTEXITCODE). Review output above."
    exit $LASTEXITCODE
}

Write-Host "Installer created in: $outDir"
