ECHO START
if exist "./src/main/java" rmdir "./src/main/java" /s /q
if not exist "./target/classes/org/xmlet/androidlayoutsapi" mkdir "./target/classes/org/xmlet/androidlayoutsapi"
call mvn exec:java -D"exec.mainClass"="org.xmlet.xsdasm.main.XsdAsmMain" -D"exec.args"="./src/main/resources/android.xsd androidlayoutsapi"
ECHO END