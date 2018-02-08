REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=backoffice.app.console\target\backoffice.app.console-1.3.0-SNAPSHOT.jar;backoffice.app.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.app.backoffice.console.ECafeteriaBackoffice
