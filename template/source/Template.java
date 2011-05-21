import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Template {
	private static String FILENAME = "stdin";
	static {
		//FILENAME = "Template-sample";
		//FILENAME = "Template-small";
		//FILENAME = "Template-large";
	}

	public static void main(String[] args) {
		new Template().run();
	}

	private PrintStream out;
	private final BufferedReader reader;
	private StringTokenizer tokenizer = new StringTokenizer("");

	public Template() {
		try {
			out = System.out;
			if (FILENAME == "stdin") {
				reader = new BufferedReader(new InputStreamReader(System.in));
			} else {
				out = new PrintStream(new FileOutputStream("source/" + FILENAME + ".out"));
				reader = new BufferedReader(new FileReader("source/" + FILENAME + ".in"));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		try {
			runCases();
		} finally {
			closeAll();
		}
	}

	private void runCode() {
		int a = getInt();
		int b = getInt();
		out.print(a + b);
	}

	private void runCases() {
		int cases = getInt();
		for (int c = 1; c <= cases; c++) {
			out.print("Case #" + c + ": ");
			runCode();
		}
	}

	private void closeAll() {
		out.close();
	}

	private String getToken() {
		try {
			while (true) {
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
				String s = reader.readLine();
				if (s == null) {
					return null;
				}
				tokenizer = new StringTokenizer(s, " \t\n\r");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public double getDouble() {
		return Double.parseDouble(getToken());
	}

	public int getInt() {
		return Integer.parseInt(getToken());
	}

	public long getLong() {
		return Long.parseLong(getToken());
	}

	public BigInteger getBigInt() {
		return new BigInteger(getToken());
	}

	public BigDecimal getBigDec() {
		return new BigDecimal(getToken());
	}

}
