REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=ecafeteria.app.user.console\target\app.user.console-1.3.0-SNAPSHOT.jar;ecafeteria.app.user.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.app.user.console.ECafeteriaUserApp
