run: VigenereCipher.class
	java VigenereCipher $(ARGS)

VigenereCipher.class: VigenereCipher.java
	javac VigenereCipher.java

clean:
	rm *.class
