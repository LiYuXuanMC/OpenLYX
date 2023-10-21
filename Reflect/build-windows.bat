:clean
cd build
cd libs
del Reflect-Inject-1.0-SNAPSHOT.jar
cd ..
cd ..
del classes.h
cd Reflect-Native
del classes.h
del reflect.dll
cd ..
del reflect.dll
:buildJar
start /wait cmd /c "gradlew build"
start /wait cmd /c "gradlew shadowJar"
java -jar JavaDllPacker.jar ./build/libs/Reflect-Inject-1.0-SNAPSHOT-all.jar
:buildNative
copy "classes.h" "./Reflect-Native/classes.h"
cd Reflect-Native
g++ dllmain.cpp pch.cpp -L. -lminhook -shared -o reflect.dll
copy "reflect.dll" "../reflect.dll"
pause