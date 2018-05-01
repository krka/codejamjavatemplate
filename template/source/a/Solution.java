/*
 * This submission is using the template from https://github.com/krka/codejamjavatemplate
 * Revision: __REVISION__
 */

// Comment this out before submitting
package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution {

  public static void main(String[] args) {
    new Solution().run();
  }

  private final PrintStream out;
  private final BufferedReader reader;
  private StringTokenizer tokenizer = new StringTokenizer("");

  public Solution() {
    out = System.out;
    reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public void run() {
    try {
      runCases();
    } finally {
      out.close();
    }
  }

  private void runCases() {
    int cases = getInt();
    for (int c = 1; c <= cases; c++) {
      try {
        String answer = new Solver(c).solve();
        String s = "Case #" + c + ": " + answer;
        out.println(s);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public String readLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String getToken() {
    while (true) {
      if (tokenizer.hasMoreTokens()) {
        return tokenizer.nextToken();
      }
      String s = readLine();
      if (s == null) {
        return null;
      }
      tokenizer = new StringTokenizer(s, " \t\n\r");
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

  public class Solver {

    private final int caseNumber;

    public Solver(int caseNumber) {
      this.caseNumber = caseNumber;
    }

    public String solve() {
      return "not implemented";
    }
  }
}
