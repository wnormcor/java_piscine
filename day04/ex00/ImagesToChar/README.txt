javac -d target/edu.school21.printer src/java/edu.school21.printer/app/Main.java src/java/edu.school21.printer/logic/PrintImage.java
java -cp target/edu.school21.printer/ Main /Users/wnormcor/school21/cursus42/21-java/days/day04/it.bmp
rm target/edu.school21.printer/Main.class target/edu.school21.printer/PrintImage.class
rmdir target/edu.school21.printer
rmdir target