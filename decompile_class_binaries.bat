ECHO START
if not exist "./src/main/java/org/xmlet/androidlayoutsapi" mkdir "./src/main/java/org/xmlet/androidlayoutsapi"
call mvn exec:java -D"exec.mainClass"="org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler" -D"exec.args"="-dgs=true -log=WARN ./target/classes/org/xmlet/androidlayoutsapi ./src/main/java/org/xmlet/androidlayoutsapi"
if exist "./target/classes/org" rmdir "./target/classes/org" /s /q
ECHO END