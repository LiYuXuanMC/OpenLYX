# Reflect-Native-Loader  
A dll to load Reflect-Inject into any version of minecraft  
## Build  
### Windows  
You need MinGW64 to build the x64 dll  
Run `g++ dllmain.cpp pch.cpp -L. -lminhook -shared -o reflect.dll`  
### Linux  
You need MinGW64 too  
Run ` x86_64-w64-mingw32-g++ dllmain.cpp pch.cpp -L. -lminhook -shared -o reflect.dll`  
## Do we support linux inject?  
No we don't have any project to develop a injection GhostClient on Linux  
