import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ConvexDriver is the main driver. It displays the user a menu of options and
 * takes in data from the user. It checks for valid input and reacts
 * accordingly.
 */
public class ConvexDriver {

	public static void main(String[] args) {
		// Collection to store the points
		ArrayList<Point> coordCollection = null;
		while (true) {
			while (coordCollection == null) {
				// Display menu
				// If input is 1, 2, 3, 4
				String input = getMenuInput();

				if (input.equals("1")) {
					/**
					 * Read File Selected
					 */
					input = Keyboard.getKeyboard().readString("Enter the file name containing the randomly generated points: ");
					File file = new File(input);
					if (file.exists()) {
						coordCollection = readFile(input);
					} else {
						System.out.println("File does not exist.");
					}
				} else if (input.equals("2")) {
					/**
					 * Generate Random Points selected
					 */
					int num = 0;
					while (num < 3) {
						num = Keyboard.getKeyboard().readInt(
								"Number of points to generate: ");
					}
					int low = Keyboard.getKeyboard().readInt(
							"Minimum point value: ");
					int high = Keyboard.getKeyboard().readInt(
							"Maximum point value: ");
					// Generate a collection of points with a given value range.
					ArrayList<Point> points = generatePoints(num, low, high);
					// Put points in a file.
					writePoints(points);
				} else if (input.equals("3")) {
					/**
					 * Timing benchmark selected
					 */
					List<Point> benchCollection = new ArrayList<Point>();
					// Prepare the file "timing.txt" for writing
					FileIO file = new FileIO("timing.txt", FileIO.FOR_WRITING);
					String benchFile = Keyboard.getKeyboard().readString(
							"File name to run timing benchmark on: ");
					File read = new File(benchFile);
					if (read.exists()) {
						benchCollection = readFile(benchFile);
						int start = 0, step = 0;
						while(start < 3){
							start = Keyboard.getKeyboard().readInt(
								"Enter start size (3 or higher): ");
						}
						while(step < 1){
							step = Keyboard.getKeyboard().readInt(
								"Enter step size (1 or higher): ");
						}
						// Start timing test, label BRUTE FORCE, specified as
						// algorithm 1.
						file = startBenchmark(benchCollection, file,
								"BRUTE FORCE", 1, step, start);
						// Start timing test, label QUICK HULL, specified as
						// algorithm 2.
						file = startBenchmark(benchCollection, file,
								"QUICK HULL", 2, step, start);
						// Close the file when done.
						file.close();
						System.out.println("Timing benchmark saved to timing.txt");
					} else {
						System.out.println("File does not exist.");
					}
				} else if (input.equals("4")) {
					/**
					 * Quit
					 */
					return;
				}
			}
			// displayCoords("Original",coordCollection);
			// Calculate the hull from the given collection.
			// True specifies to output the data to console.
			// 3 specifies to do both algorithm 1 and 2.
			calculateHull(coordCollection, true, 3);
			coordCollection = null;
		}
	}

	/**
	 * <b>Preconditions:</b> File input is valid. <b>Throws</b> FileIOException
	 * <b>Postconditions:</b> Returns x value.
	 * 
	 * @param collection
	 * @param file
	 * @param name
	 * @param type
	 * @param steps
	 * @param start
	 */
	private static FileIO startBenchmark(List<Point> benchCollection,
			FileIO file, String name, int type, int step, int start) {
		// Minimum and maximum constant range for random point generation
		// Create a new timer
		Timer timer = new Timer();
		System.out.println("Beginning timing benchmark for algorithm " + name
				+ ".");
		try {
			file.writeLine(name + ": ");
			for (int i = start; i <= benchCollection.size(); i += step) {
				List<Point> list = new ArrayList<Point>();
				//Generate subset to test from collection.
				for(int j = 0; j<i; j++){
					list.add(benchCollection.get(j));
				}
				timer.startTimer();
				calculateHull(list, false, type);
				file.writeLine(Integer.toString(i) + ", "
						+ Long.toString(timer.getSRTimer()));
			}
			file.writeLine("\n");
			return file;
		} catch (FileIOException fie) {
			throw new FileIOException("Failed to write file.");
		}
	}

	/**
	 * Starts the algorithm functions with the corresponding algorithm type.
	 * Displays the results of the convex hull algorithm. <b>Preconditions:</b>
	 * Collection size must be greater than 2. <b>Postconditions:</b> Calculates
	 * the hull with given algorithm, displays results if flagged to.
	 * 
	 * @param coordCollection
	 * @param display
	 */
	private static boolean calculateHull(List<Point> coordCollection,
			boolean display, int type) {
		if (coordCollection.size() < 3) {
			System.out
					.println("The collection size is too small. Must have at least 3 points.");
			return false;
		}
		Algorithm algorithm = null;
		List<Point> verticeCollection = new ArrayList<Point>();
		if (type == 1 || type == 3) {
			algorithm = new BruteForce();
			verticeCollection = algorithm.startAlgorithm(coordCollection);
			if (display == true) {
				displayCoords("Brute Force", verticeCollection);
			}
		}
		if (type == 2 || type == 3) {
			algorithm = new QuickHull();
			verticeCollection = algorithm.startAlgorithm(coordCollection);
			if (display == true) {
				displayCoords("Quick Hull", verticeCollection);
			}
		}
		return true;
	}

	/**
	 * Display collection with title. <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Outputs to console the title and collection.
	 * 
	 * @param type
	 * @param coords
	 */
	public static void displayCoords(String type, List<Point> coords) {
		System.out.println("\n" + type);
		for (int i = 0; i < coords.size(); i++) {
			System.out.println(coords.get(i));
		}
	}

	/**
	 * Generates a collection of points to given size with given value range.
	 * <b>Preconditions:</b> None <b>Postconditions:</b> Creates given number of
	 * points within given range. Returns the collection.
	 * 
	 * @param num
	 * @param low
	 * @param high
	 * @return
	 */
	public static ArrayList<Point> generatePoints(int num, int low, int high) {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < num; i++) {
			// Create random point between range.
			Point point = new Point(Random.getRandomNumberGenerator()
					.randomFloat(low, high), Random.getRandomNumberGenerator()
					.randomFloat(low, high));
			points.add(point);
		}
		return points;
	}

	/**
	 * Gives user main menu, loops until valid input. <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Returns valid user input.
	 * 
	 * @return
	 */
	public static String getMenuInput() {
		String input = null;
		while (true) {
			System.out.println("\nEnter 1, 2, 3, or 4.");
			System.out.println("1 | Calculate Convex from Points in a File");
			System.out.println("2 | Generate Points to a File");
			System.out.println("3 | Timing Test");
			System.out.println("4 | Quit");
			input = Keyboard.getKeyboard().readString("> ");
			if (input.equals("1") || input.equals("2") || input.equals("3")
					|| input.equals("4")) {
				break;
			}
		}
		return input;
	}

	/**
	 * Reads given data file. <b>Preconditions:</b> File name with proper
	 * formatted content. <b>Catch:</b> Number Format Exception; Returns null
	 * collection. <b>Postconditions:</b> Reads the file and stores the content
	 * into array list. Will return collection if no exception is made.
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<Point> readFile(String fileName) {
		FileIO file = new FileIO(fileName, FileIO.FOR_READING);
		ArrayList<Point> coordCollection = new ArrayList<Point>();
		double xCord = 0, yCord = 0;
		try {
			int numPoints = Integer.parseInt(file.readLine());
			for (int i = 0; i < numPoints; i++) {
				xCord = Double.parseDouble(file.readLine());
				yCord = Double.parseDouble(file.readLine());
				Point xyCord = new Point(xCord, yCord);
				coordCollection.add(xyCord);
			}
			return coordCollection;
		} catch (NumberFormatException nfe) {
			System.out
					.println("First line format is invalid. Number of points cannot be determined.\n\n");
		}
		return null;
	}

	/**
	 * Writes the points to a file. <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Writes the passed collection to a file.
	 * 
	 * @param points
	 */
	public static void writePoints(ArrayList<Point> points) {
		String fileName = "points.txt";
		int num = points.size();
		try {
			FileIO file = new FileIO(fileName, FileIO.FOR_WRITING);
			file.writeLine(Integer.toString(num));
			for (int i = 0; i < num; i++) {
				file.writeLine(Double.toString(points.get(i).x()));
				file.writeLine(Double.toString(points.get(i).y()));
			}
			System.out.println(num + " random points generated in " + fileName
					+ ".\n\n");
			file.close();
		} catch (FileIOException fie) {
			throw new FileIOException("Failed to write file.");
		}
	}
}
