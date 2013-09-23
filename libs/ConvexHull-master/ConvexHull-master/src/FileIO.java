import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;


/**
 * This class abstracts the basic Java File I/O classes to make File I/O more
 * straightforward.
 */

public class FileIO {
	/** The constant used to indicate that a file is for reading */
	public final static int FOR_READING = 1;
	/** The constant used to indicate that a file is for writing */
	public final static int FOR_WRITING = 2;

	/** Stores whether the file was intended for writing or for reading */
	private int setting;

	/** The abstracted BufferedReader if the file was for reading */
	private BufferedReader br;
	/** The abstracted PrintWriter if the file was for writing */
	private PrintWriter pw;

	/** Indicates whether the end of the file has been reached or not */
	private boolean EOF = false;
	/**
	 * Indicates whether the user wanted to extract tokens from each line in the
	 * file
	 */
	private boolean tokens;
	/** The delimiter to use if tokens are desired by the user */
	private String delimiter;

	/**
	 * General purpose constructor that can be used to specify a file and
	 * whether it is for reading or for writing. <br>
	 * <b>Preconditions</b>: The file desired (not null) and whether it is for
	 * reading or writing must be specified (only options possible).<br>
	 * <b>Postconditions</b>: The specified file is prepared for the desired
	 * operation.<br>
	 * <b>Throws</b>: RuntimeException if neither reading nor writing is
	 * specified or a problem occurs in preparing the file connections.<br>
	 */
	public FileIO(String fileName, int operation) throws FileIOException {
		try {
			if (operation == FOR_READING || operation == FOR_WRITING) {
				setting = operation;
			} else {
				throw new FileIOException("Must specify reading or writing.");
			}

			if (setting == FOR_READING) {
				FileReader fr = new FileReader(fileName);
				br = new BufferedReader(fr);
			} else if (setting == FOR_WRITING) {
				FileWriter fw = new FileWriter(fileName);
				BufferedWriter bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);
			}
		} catch (FileNotFoundException fnfe) {
			throw new FileIOException("File not found.");
		} catch (IOException ioe) {
			throw new FileIOException("IO Error");
		}

	}

	/**
	 * Constructor used to read in tokens from a file. <br>
	 * <b>Preconditions</b>: The file desired (not null) and the delimited
	 * desired for extracting tokens must be specified.<br>
	 * <b>Postconditions</b>: The specified file is prepared for reading and
	 * token extraction.<br>
	 * <b>Throws</b>: RuntimeException if a problem occurs in preparing the file
	 * connections.<br>
	 */
	public FileIO(String fileName, String delimiter) throws FileIOException {
		try {
			setting = FOR_READING;
			tokens = true;
			this.delimiter = delimiter;

			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException fnfe) {
			throw new FileIOException("File not found.");
		}

	}

	/**
	 * Returns the tokens from a single line of text read from a file. <br>
	 * <b>Preconditions</b>: The file must have been opened for reading tokens.<br>
	 * <b>Postconditions</b>: Returns the tokens from a single line of text in
	 * the file in an Iterator.<br>
	 * <b>Throws</b>: RuntimeException if the file was not opened for reading or
	 * if a problem occurs when reading or if the end of the file has been
	 * reached.<br>
	 */
	public Iterator<String> getTokens() throws FileIOException {
		ArrayList<String> list = new ArrayList<String>();

		if (tokens && !EOF) {
			String temp = readLine();
			if (!EOF) {
				String[] toks = temp.split(delimiter);
				list = new ArrayList<String>();

				for (String str : toks) {
					list.add(str);
				}
			}
		} else {
			throw new FileIOException("Tokens not available.");
		}

		return list.iterator();
	}

	/**
	 * General purpose constructor that can be used to specify a file and a path
	 * and whether it is for reading or for writing. <br>
	 * <b>Preconditions</b>: The file and path desired (not null) and whether it
	 * is for reading or writing must be specified (only options possible).<br>
	 * <b>Postconditions</b>: The specified file is prepared for the desired
	 * operation.<br>
	 * <b>Throws</b>: RuntimeException if neither reading nor writing is
	 * specified or a problem occurs in preparing the file connections.<br>
	 */
	public FileIO(String fileName, String path, int operation)
			throws FileIOException {
		try {
			File file = new File(path, fileName);

			if (operation == FOR_READING || operation == FOR_WRITING) {
				setting = operation;
			} else {
				throw new IOException("Must specify reading or writing.");
			}

			if (setting == FOR_READING) {
				FileReader fr = new FileReader(file);
				br = new BufferedReader(fr);
			} else if (setting == FOR_WRITING) {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);
			}
		} catch (IOException ioe) {
			throw new FileIOException("IO Error");
		}

	}

	/**
	 * Indicates whether the end of the file has been reached. <br>
	 * <b>Preconditions</b>: None.<br>
	 * <b>Postconditions</b>: Returns true if the end of the file has been
	 * reached or false otherwise.<br>
	 */
	public boolean EOF() {
		return EOF;
	}

	/**
	 * Reads in a line of text from a file. <br>
	 * <b>Preconditions</b>: The file must have been opened for reading.<br>
	 * <b>Postconditions</b>: Returns a string with the next line of text from
	 * the file or null if the end of the file has been reached.<br>
	 * <b>Throws</b>: RuntimeException if the file was not opened for reading or
	 * if a problem occurs when reading from the file.<br>
	 */
	public String readLine() throws FileIOException {

		String temp = null;
		try {
			if (setting == FOR_READING) {
				temp = br.readLine();
				if (temp == null) {
					EOF = true;
				}
			} else {
				throw new FileIOException("File is not open for reading.");
			}
		} catch (IOException ioe) {
			throw new FileIOException("IO Error");
		}

		return temp;
	}

	/**
	 * Writes a line of text to a file. <br>
	 * <b>Preconditions</b>: The file must have been opened for writing, and the
	 * line of text to be written supplied (not null).<br>
	 * <b>Postconditions</b>: The line of text is written to the file.<br>
	 * <b>Throws</b>: RuntimeException if the file was not opened for writing or
	 * a problem occurs during writing to the file.<br>
	 */
	public void writeLine(String line) throws FileIOException {

		if (setting == FOR_WRITING) {
			pw.println(line);
		} else {
			throw new FileIOException("File is not open for writing.");
		}

	}

	/**
	 * Closes the connection to a file. <br>
	 * <b>Preconditions</b>: None.<br>
	 * <b>Postconditions</b>: The connection to the file is closed.<br>
	 * <b>Throws</b>: RuntimeException if a problem occurs when closing the
	 * file.<br>
	 */
	public void close() throws FileIOException {
		try {
			if (setting == FOR_READING) {
				br.close();
				setting = -1;
				br = null;
			} else if (setting == FOR_WRITING) {
				pw.close();
				setting = -1;
				pw = null;
			}
		} catch (IOException ioe) {
			throw new FileIOException("IO Error");
		}
	}

}
