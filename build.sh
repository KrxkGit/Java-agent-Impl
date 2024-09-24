rm -rf intermediate
rm -rf build

javac -cp "lib/byte-buddy-1.15.1.jar;lib/byte-buddy-agent-1.15.1.jar" -d intermediate src/CompareAgent.java src/MyAdvice.java
jar cvfm build/MyAgent.jar META-INF/MANIFEST.MF -C intermediate .