1. Complile:
	mkdir target && cp -R src/resources target
	
	mkdir lib && curl -o lib/JCommander.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.81/jcommander-1.81.jar && curl -o lib/JColor.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.0.1/JColor-5.0.1.jar
	
	cd target && jar xf ../lib/JCommander.jar com && jar xf ../lib/JColor.jar com && cd ..
	
	javac -d target  -cp "lib/*" $(find ./src/ -name "*.java")

2. Make jar:
	jar cfm target/images-to-char-printer.jar src/manifest.txt -C target .

3. Run:
	java -jar target/images-to-char-printer.jar --white=[color name] --black=[symbol for black color]

Example:
	java -jar target/images-to-char-printer.jar --white=WHITE --black=BLACK
