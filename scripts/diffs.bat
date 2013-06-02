@echo off
if "%5" == "" (
  echo JSimil - diff between two targets
  echo .
  echo Usage: %0 [battery] [profile] [similarity_thres] [tempdir]
  echo .
  echo This script processes a battery and generates diff results.
  pause
  exit /b
)

java -Xms128M -Xmx1024M -jar JSimilCLI.jar -c default.jcf -b %1 -p %2 -u %3 --diff-simil %5 -k %4 -f -i -y -r

