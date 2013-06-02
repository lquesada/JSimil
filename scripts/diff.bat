@echo off
if "%5" == "" (
   echo JSimil - diff between two targets
   echo .
   echo Usage: %0 [old] [new] [profile] [similarity_thres] [tempdir]
   echo .
   echo This script processes the two targets and generates diff results.
   echo .
   echo Both old and new must be two jar files, java files, or directories.
   pause
   exit /b
)
rd /S /Q %5\temp %5\tempres > %5\tempnull
mkdir %5\temp > %5\tempnull
mkdir %5\temp\old > %5\tempnull
mkdir %5\temp\new > %5\tempnull
xcopy %1 %5\temp\old /s /i > %5\tempnull
xcopy %2 %5\temp\new /s /i > %5\tempnull
java -Xms128M -Xmx1024M -jar JSimilCLI.jar -c default.jcf -t %5\temp -p %3 --diff-simil %4 -k %5\tempres -f -i -y -r > %5\tempnull
type %5\tempres\old-new.diff
rd /S /Q %5\temp %5\tempres > %5\tempnull
