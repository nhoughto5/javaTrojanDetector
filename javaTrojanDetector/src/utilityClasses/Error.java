package utilityClasses;

public class Error {
	public static void printError(String message, StackTraceElement sT){
	    Thread.currentThread().getStackTrace();
	    System.err.println(message + ": " + sT.getFileName() + " at " + sT.getLineNumber());
	    System.exit(-1);
	}
}
