public class AlarmClock extends Clock
{
    private static int alarmHours;
    private static int alarmMinutes;

    public AlarmClock()
    {
        super();
    }
    
    public AlarmClock(int h, int m)
    {
        super(h, m);
    }

    public AlarmClock(int h, int m, int s)
    {
        super(h, m, s);
    }

    public int getAlarmHours()
    {
        return alarmHours;
    }

    public int getAlarmMinutes()
    {
        return alarmMinutes;
    }

    public void setAlarm(int h, int m)
    {
        if (h < 0 || h >= HOURS_IN_A_DAY) 
        {
            throw new IllegalArgumentException("invalid alarm hours");
        }
        if (m < 0 || m >= MINUTES_IN_AN_HOUR)
        {
            throw new IllegalArgumentException("invalid alarm minutes");
        }
        alarmHours = h;
        alarmMinutes = m;
    }

    public boolean isRinging()
    {
        if (getHours() == getAlarmHours() && getAlarmMinutes() == getMinutes() && getSeconds() >= 0 && getSeconds() < 15)
        {
            return true;
        }
        return false;
    }

    public void display()
    {
        if (isRinging())
        {
            System.out.println(this + " - WAKE UP!");
        }
        else
        {
            System.out.println(this);
        }
    }
}