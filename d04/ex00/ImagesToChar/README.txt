1. Compile files:
	mkdir target && javac -d target $(find ./ -name "*.java")

2. Run:
	java -cp target edu.school21.printer.app.Program --white=[symbol for while color] --black=[symbol for black color] [absolute path to bmp image]

Example:
	java -cp target edu.school21.printer.app.Program --white=. --black=o src/resources/image.bmp
