mkdir lib && mkdir target && mkdir target/resources;
cd lib && { curl -O https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -O https://repo1.maven.org/maven2/com/beust/jcommander/1.81/jcommander-1.81.jar ; cd ..; } ;
javac -d target/ -cp lib/\*  src/java/edu/school21/printer/app/Main.java  src/java/edu/school21/printer/logic/*.java;
cp src/resources/it.bmp target/resources;
cd target && jar xf ../lib/jcommander-1.81.jar com && jar xf ../lib/JCDP-4.0.2.jar com && cd .. ;
rm -f ./target/images-to-chars-printer.jar ;
jar cvfm target/images-to-chars-printer.jar  src/manifest.txt -C target . ;
java -jar ./target/images-to-chars-printer.jar --white=GREEN --black=RED