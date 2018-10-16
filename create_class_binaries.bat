if exist "./src/main/java" rmdir "./src/main/java" /s /q
if not exist "./target/classes/org/xmlet/androidlayoutsapi" mkdir "./target/classes/org/xmlet/androidlayoutsapi"
call mvn exec:java -D"exec.mainClass"="org.xmlet.xsdasmfaster.main.XsdAsmMain" -D"exec.args"="./src/main/resources/android.xsd androidlayoutsapi"