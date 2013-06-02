@echo off
if "%4" == "" (
  echo JSimil - Process
  echo .
  echo Usage: %0 [source_file] [profile] [number_results] [dest_dir]
  echo .
  echo This script performs a processing and generates HTML results.
  pause
  exit /b
)

java -Xms128M -Xmx1024M -jar JSimilCLI.jar -c default.jcf -b %1 -p %2 -u %3 -e %4 -f -i -y -r
