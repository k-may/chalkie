public class Timer { 

	// Stores the start time of the timer.
	private long startTime;
	// Stores the stop time of the timer.
	private long stopTime;
	// Stores if the timer is currently active.
	private boolean active;
 
	public Timer() {
		startTime = 0;
		stopTime = 0;
		active = false;
	}

	/**
	 * Starts timer <b>Preconditions:</b> None <b>Postconditions:</b> Sets timer
	 * active, stores start time. <b>Throws:</b> None
	 */
	public void startTimer() {
		startTime = System.nanoTime();
		active = true;
	}

	/**
	 * Stops timer <b>Preconditions:</b> None <b>Postconditions:</b> Sets timer
	 * inactive, stores stopped time. <b>Throws:</b> None
	 */
	public void stopTimer() {
		stopTime = System.nanoTime();
		active = false;
	}

	/**
	 * Resets timer, does not change active state. <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Sets start time to current time. <b>Throws:</b>
	 * None
	 */
	public void resetTimer() {
		startTime = System.nanoTime();
	}

	/**
	 * Gets and returns elapsed time in nano seconds, does not change active
	 * state. <b>Preconditions:</b> None <b>Postconditions:</b> Gets the current
	 * elapsed time. <b>Throws:</b> None
	 */
	public long getElapsedTime() {
		if (active) {
			return (System.nanoTime() - startTime);
		} else {
			return stopTime - startTime;
		}
	}

	/**
	 * Convenience method to stop timer, reset it, and return elapsed time.
	 * <b>Preconditions:</b> None <b>Postconditions:</b> Sets timer to inactive,
	 * resets timer, returns elapsed time. <b>Throws:</b> None
	 */
	public long getSRTimer() {
		stopTimer();
		long time = getElapsedTime();
		resetTimer();
		return time;
	}

	/**
	 * Returns boolean of active state. <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Returns boolean of active state. <b>Throws:</b>
	 * None
	 */
	public boolean isActive() {
		return active;
	}
}
