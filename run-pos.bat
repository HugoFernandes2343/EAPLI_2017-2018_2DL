REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=eapli.app.pos.console\target\eapli.app.pos.console-1.3.0-SNAPSHOT.jar;eapli.app.pos.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.app.pos.console.ECafeteriaPOS
