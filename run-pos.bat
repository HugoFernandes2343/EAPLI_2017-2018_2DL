REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=ecafeteria.app.pos.console\target\app.pos.console-1.3.0-SNAPSHOT.jar;ecafeteria.app.pos.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.app.pos.console.ECafeteriaPOS
