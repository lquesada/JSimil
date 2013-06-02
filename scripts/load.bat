@echo off
if "%2" == "" (
  echo JSimil - Load a battery.
  echo .
  echo Usage: %0 [source_dir] [target_file]
  pause
  exit /b
)

java -Xms128M -Xmx1024M -jar JSimilCLI.jar -c default.jcf -t %1 -g %2 -v
