package com.kevmayo.chalkie.base.exceptions;

/**
 * Created by Kev on 19/06/2014.
 * <p/>
 * implemented from:
 * * @author Markus Zimmermann <a href="http://www.die-seite.ch">http://www.die-seite.ch</a>
 *
 * @version 1.1
 */
public class ChalkieException extends RuntimeException {

    private ChalkieException() {
    }


    public ChalkieException(String s) {
        this(s, new ChalkieException());
    }

    public ChalkieException(String s, Throwable e) {
        super(concatMsg(s, e));
        if (e != null) setStackTrace(e.getStackTrace());
    }

    private static String concatMsg(String customMsg, Throwable cause) {
        String exClass = (cause == null) ? "unknown Exception" : cause.getClass().getSimpleName();
        String linePos = "unknown";
        String causeMsg = "no extended message";
        if (cause != null) {
            StackTraceElement el = cause.getStackTrace()[0];
            linePos = el.getLineNumber() + " on " + el.getClassName();
            causeMsg = cause.getMessage();
        }

        String internMessage = "\nA " + exClass + " is thrown: at Line " + linePos +
                "\nwith message: " + causeMsg + "\nChalkieException says: " + customMsg;

        return internMessage;

    }
}
