import static org.junit.Assert.assertEquals;

import com.google.common.io.Resources;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestUtil {

  public static void test(final String problem, final String testData)
      throws Exception {
    final Method method = getMainMethod(problem);

    final Writer expectedData = new StringWriter();
    final Writer inputData = new StringWriter();

    final String resourceName = String.format("%s-%s.txt", problem.toUpperCase(), testData);
    final URL resourceUrl = Resources.getResource(resourceName);
    final List<String> lines = Resources.readLines(resourceUrl, StandardCharsets.UTF_8);

    if (!parseTestData(expectedData, inputData, lines)) {
      throw new RuntimeException("Invalid test data, see error log");
    }

    final String expected = expectedData.toString();

    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    final InputStream oldIn = System.in;
    final PrintStream oldOut = System.out;
    System.setIn(new ByteArrayInputStream(inputData.toString().getBytes()));
    System.setOut(new PrintStream(outputStream));
    try {
      method.invoke(null, (Object) new String[0]);
    } finally {
      System.setIn(oldIn);
      System.setOut(oldOut);
    }

    final String actual = outputStream.toString("UTF-8");

    assertEquals(expected, actual);

    System.out.println(actual);
  }

  private static boolean parseTestData(final Writer expectedData, final Writer inputData, final List<String> lines) throws IOException {
    boolean ok = true;
    for (final String line : lines) {
      if (line.startsWith("#")) {
        final String removed = removePrefix(line, "## Expected: ");
        if (removed != null) {
          expectedData.write(removed);
          expectedData.write('\n');
        } else {
          System.err.println("Unhandled line: " + line);
          ok = false;
        }
      } else {
        inputData.write(line);
        inputData.write('\n');
      }
    }
    return ok;
  }

  private static String removePrefix(final String s, final String prefix) {
    if (s.startsWith(prefix)) {
      return s.substring(prefix.length());
    }
    return null;
  }

  private static Method getMainMethod(final String problem) throws ClassNotFoundException, NoSuchMethodException {
    final Class<?> clazz = Class.forName(problem + ".Solution");
    return clazz.getMethod("main", String[].class);
  }
}
