package frontEnd;

public class Scalar {
  private static double MAX = Math.pow(2, 31) - 1;
  private static double MIN = Math.pow(-2, 31);

  public static boolean isAcceptableInt (long value) {
    return MIN <= value && value <= MAX;
  }

}
