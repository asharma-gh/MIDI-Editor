package cs3500.music.mock;

/**
 * Created by nbuqu on 6/17/2016.
 */
public class MidiMockTracer {
  private static StringBuilder trace = new StringBuilder();

  public MidiMockTracer() {
  }

  public static void updateTrace(String s) {
    trace.append(s);
  }

  public static String getTrace() {
    return trace.toString();
  }
}
