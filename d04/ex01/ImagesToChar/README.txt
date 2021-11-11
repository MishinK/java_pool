1. Complile:
	mkdir target && cp -R src/resources target && javac -d target $(find ./ -name "*.java")

2. Make jar:
	jar cfm target/images-to-char-printer.jar src/manifest.txt -C target .

3. Run:
	java -jar target/images-to-char-printer.jar --white=[symbol for while color] --black=[symbol for black color]

Example:
    java -jar target/images-to-char-printer.jar --white=. --black=o
