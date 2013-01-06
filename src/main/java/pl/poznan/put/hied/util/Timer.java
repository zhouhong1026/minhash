package pl.poznan.put.hied.util;

import java.util.concurrent.TimeUnit;

/**
 * Constructed to make time records. Uses System.currentTimeMillis().
 * 
 * @author pmendelski
 */
public class Timer {

	private Long startTime = null;

	private Long result = null;

	/**
	 * Starts the timer.
	 * 
	 * @return this instance
	 */
	public Timer start() {
		startTime = System.currentTimeMillis();
		return this;
	}

	/**
	 * Stops the timer.
	 * 
	 * @return this instance
	 */
	public Timer stop() {
		if (startTime == null) {
			throw new IllegalArgumentException(
					"Stop method invoked before start method");
		}
		result = System.currentTimeMillis() - startTime;
		return this;
	}

	/**
	 * Returns result as value in milliseconds.
	 * 
	 * @return
	 */
	public long getResult() {
		if (result == null) {
			throw new IllegalArgumentException(
					"Get results invoked before stop method");
		}
		return result;
	}

	public String getFormattedResult() {
		long milliseconds = getResult();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
		long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
		milliseconds -= TimeUnit.SECONDS.toMillis(seconds);
		seconds -= TimeUnit.MINUTES.toSeconds(minutes);
		minutes -= TimeUnit.HOURS.toMinutes(hours);

		String result = null;
		if (hours > 0) {
			result = String.format("%d:%02d:%02d.%03d[h:m:s.ms]", hours,
					minutes, seconds, milliseconds);
		} else if (minutes > 0) {
			result = String.format("%d:%02d.%03d[m:s.ms]", minutes, seconds,
					milliseconds);
		} else if (seconds > 0) {
			result = String.format("%d.%03d[s.ms]", seconds, milliseconds);
		} else {
			result = String.format("%d[ms]", milliseconds);
		}
		return result;
	}

}
