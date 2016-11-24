package frontEnd;

public class Scalar {

  public static boolean isAcceptableInt (long value) {
    return Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE;
  }

}
