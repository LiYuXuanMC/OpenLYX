:clean
cd build
cd libs
del Reflect-Inject-1.0-SNAPSHOT.jar
cd ..
cd ..
del classes.h
cd Reflect-Native
del classes.h
del /s /f /q dllmain.o
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

g++ -Wall -DBUILD_DLL -O2 -std=c++11 -m64 -c dllmain.cpp -o dllmain.o -L. -lminhook
g++ -shared -Wl,--dll dllmain.o -o reflect.dll -s -m64 -static -static-libgcc -static-libstdc++ -luser32 -L. -lminhook
copy "reflect.dll" "../reflect.dll"
pause