REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=backoffice.consoleapp\target\backoffice.consoleapp-1.0-SNAPSHOT.jar;backoffice.consoleapp\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.backoffice.consoleapp.ECafeteriaBackoffice
