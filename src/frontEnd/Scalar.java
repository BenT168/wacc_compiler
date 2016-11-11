package frontEnd;

public class Scalar {
  private static double MAX = Math.pow(2, 31);
  private static double MIN = Math.pow(-2, 31) - 1;

  public static boolean isAcceptableInt (int value) {
    return MIN <= value && value <= MAX;
  }

}
