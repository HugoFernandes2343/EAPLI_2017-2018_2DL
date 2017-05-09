REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=ecafeteria.bootstrapapp\target\bootstrapapp-1.2-SNAPSHOT.jar;ecafeteria.bootstrapapp\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.bootstrapapp.ECafeteriaBootstrap
