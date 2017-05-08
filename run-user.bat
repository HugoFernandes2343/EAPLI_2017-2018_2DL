REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=user.consoleapp\target\user.consoleapp-1.0-SNAPSHOT.jar;user.consoleapp\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.user.consoleapp.ECafeteriaUserApp
