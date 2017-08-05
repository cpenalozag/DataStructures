package taller.mundo;

/**
 *  The <tt>Stopwatch</tt> data type is for measuring
 *  the time that elapses between the start and end of a
 *  programming task (wall-clock time).
 *
 *  See {@link StopwatchCPU} for a version that measures CPU time.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */


public class Stopwatch 
{

    private long start;
    private AlgorithmTournament.TimeReference units;

    /**
     * Initialize a stopwatch object.
     */
    public Stopwatch(AlgorithmTournament.TimeReference units) 
    {
        this.units = units;
        start = System.currentTimeMillis();
    } 

    /**
     * Returns the elapsed time (in seconds) since this object was created.
     */
    public double elapsedTime() 
    {
        long now = System.currentTimeMillis();
        double seconds = (now - start) / 1000.0;
        if(units == AlgorithmTournament.TimeReference.SECONDS)
        {
             return seconds;
        }
        else
        {
             return seconds/60;
        }
    }

    public void restartTime()
    {
        start = System.currentTimeMillis();
    }

} 

