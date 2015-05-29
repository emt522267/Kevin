package Parse.Automation;

import java.util.concurrent.TimeUnit;

public class timeWatch {
	
	 long starts;

	    private timeWatch() {
	        reset();
	    }

	    public static timeWatch start() {
	        return new timeWatch();
	    }

	    public timeWatch reset() {
	        starts = System.nanoTime();
	        return this;
	    }

	    public long time() {
	        long ends = System.nanoTime();
	        return ends - starts;
	    }

	    public long time(TimeUnit unit) {
	        return unit.convert(time(), TimeUnit.NANOSECONDS);
	    }

	    public String toMinuteSeconds(){
	        return String.format("%d min, %d sec", time(TimeUnit.MINUTES),
	                time(TimeUnit.SECONDS) - time(TimeUnit.MINUTES));
	    }

}
