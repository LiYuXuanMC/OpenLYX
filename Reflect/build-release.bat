:clean
rd /s /q .\native-code
mkdir native-code
cd build
cd libs
del Reflect-Inject-1.0-SNAPSHOT.jar
del Reflect-Inject-1.0-SNAPSHOT-all.jar
del Reflect-Inject-1.0-SNAPSHOT-all-obf.jar
cd ..
cd ..
cd Reflect-Obf
cd output
del Reflect-Inject-1.0-SNAPSHOT-all-obf.jar
cd ..
cd ..
del classes.h
cd Reflect-Native
del classes.h
del reflectRelease.dll
cd ..
del reflectRelease.dll
:buildJar
start /wait cmd /c "gradlew build"
start /wait cmd /c "gradlew shadowJar"
start /wait cmd /c .\proguard-7.2.2\bin\proguard.bat @proguard.pro
java -jar native-obfuscator.jar -a .\build\libs\Reflect-Inject-1.0-SNAPSHOT-all-obf.jar native-code
cd native-code
cd cpp
cmake .
cmake --build . --config Release
cd build
cd lib
copy native_library.dll ..\..\..\..\runtimeRelease.dll
cd ..
cd ..
cd ..
cd ..
java -jar ProjectL-1.0-SNAPSHOT-all.jar projectl.yml
cd Reflect-Obf
java -jar ZKM.jar script.txt
cd ..
java -jar JavaDllPacker.jar ./Reflect-Obf/output/Reflect-Inject-1.0-SNAPSHOT-all-obf-obf.jar
:buildNative
copy "classes.h" "./Reflect-Native/classes.h"
cd Reflect-Native
msbuild .\Reflect-Native.vcxproj -p:Configuration=Release -p:Platform=x64
cd x64
cd Release
copy "Reflect-Native.dll" "../../../reflectRelease.dll"
pause