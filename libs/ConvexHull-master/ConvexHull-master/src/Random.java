
/**
 * A class to return random numbers (int and double) within a user specified
 * range. <br>
 * 
 */
public class Random {
	private static Random random = new Random();
	/**
	 * The object that will generate the random numbers. Only one random number
	 * generator is ever needed.
	 */
	private java.util.Random rand;

	private Random() {
		rand = new java.util.Random(System.currentTimeMillis());
	}

	public static Random getRandomNumberGenerator() {
		return random;
	}

	/**
	 * Returns a randomly generated integer between low and high (inclusive of
	 * both).
	 */
	public int randomInt(int low, int high) {
		return rand.nextInt(high - low + 1) + low;
	}

	/**
	 * Returns a randomly generated floating point number between low
	 * (inclusive) and high (exclusive).
	 */
	public double randomFloat(int low, int high) {
		return rand.nextDouble() * (high - low) + low;
	}
}
