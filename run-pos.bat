REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=pos.consoleapp\target\pos.consoleapp-1.0-SNAPSHOT.jar;pos.consoleapp\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.pos.consoleapp.ECafeteriaPOS
