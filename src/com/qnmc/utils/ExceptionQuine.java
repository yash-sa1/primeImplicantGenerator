package qnmc.src.com.qnmc.utils;

public class ExceptionQuine extends Exception {

	private static final long serialVersionUID = 1L;

	// Step 1: Create a single static instance of this class
	private static ExceptionQuine instance;

	// Step 2: Make the constructor private to restrict instantiation from outside
	private ExceptionQuine(String str) {
		super(str);
	}

	// Step 3: Provide a public static method to get the single instance
	public static ExceptionQuine getInstance(String str) {
		if (instance == null) {
			// Initialize the instance only if it doesn't exist
			synchronized (ExceptionQuine.class) { // Thread-safe Singleton instance creation
				if (instance == null) {
					instance = new ExceptionQuine(str);
				}
			}
		}
		return instance;
	}
}