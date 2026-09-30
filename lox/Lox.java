package lox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset; 
import java.nio.file.Files;
import java.util.List; import java.nio.file.Path; import java.nio.file.Paths;

//The scanners job is to look at individual characters and seperate those characters into smaller sequences known as lexemes. Lexemes are however only the raw substrings of the source code

public class Lox{
	static boolean hadError = false; //default case is that theres no error 


	public static void main(String[] args) throws IOException{
		if (args.length > 1){
			System.out.println("Usage: jlox [script]");
			System.exit(64); // what does this 64 mean?
		}else if (args.length == 1){
			runFile(args[0]); // what does runFile do?
		}
		else{
			runPrompt();
		}
	}

	private static void runFile(String path) throws IOException{
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		run(new String(bytes, Charset.defaultCharset()));

		// we wont execute code that has a known error
		if (hadError){
			System.exit(65); //what does 65 mean?
			
		}
	}

	private static void runPrompt() throws IOException{
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		for (;;){
			System.out.print("> ");
			String line = reader.readLine();
			if (line == null){
				break;
			}
			run(line);
			

		}

		hadError = false;// we reset hadError back to false
	}

	private static void run(String source){
		Scanner scanner = new Scanner(source);
		List<Token> tokens = scanner.scanTokens();

		// for now we just printing the tokens
		for (Token token : tokens){
			System.out.println(token);
		}
	}



	// the error and report functions tells us the syntax error on a given line, it just tells us the line on which the error occured
	static void error(int line, String message){
		report(line, "", message);
	}
	private static void report(int line, String where, String message){
		System.err.println("[line " + line + "] Error" + ": " + message);
		hadError = true;
	}
}
