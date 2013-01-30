gcc -D_JNI_IMPLEMENTATION_ -Wl,--kill-at -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include" -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include\win32" -c edflib.c -Wall

gcc -D_JNI_IMPLEMENTATION_ -Wl,--kill-at -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include" -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include\win32" -shared -o edflib.dll edflib.o 

gcc -D_JNI_IMPLEMENTATION_ -Wl,--kill-at -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include" -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include\win32" -c ReadSignals.c 

gcc -D_JNI_IMPLEMENTATION_ -Wl,--kill-at -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include" -I"C:\Program Files (x86)\Java\jdk1.6.0_26\include\win32" -shared -o ReadSignals.dll ReadSignals.o -L./ -ledflib


mv ReadSignals.dll ../..
mv edflib.dll ../..

del *.o

pause