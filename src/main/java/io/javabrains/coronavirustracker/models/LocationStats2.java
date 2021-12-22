package io.javabrains.coronavirustracker.models;

public class LocationStats2 {
    private String state;
    private String country;
    private int latestRecoveredCases;
    private int RecoveredDiffFromPrevDay;
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state=state;
    }
    public String getCountry()
    {
        return country;
    }
    public void setCountry(String country)
    {

        this.country=country;
    }
    public int getLatestRecoveredCases()
    {

        return latestRecoveredCases;
    }
    public void setLatestRecoveredCases(int  latestRecoveredCases)
    {

        this.latestRecoveredCases= latestRecoveredCases;
    }
    public int getRecoveredDiffFromPrevDay()
    {

        return RecoveredDiffFromPrevDay;
    }
    public void setRecoveredDiffFromPrevDay(int RecoveredDiffFromPrevDay)
    {

        this.RecoveredDiffFromPrevDay=RecoveredDiffFromPrevDay;
    }
    @Override
    public String toString()
    {
        return "LocationStats{"+
                "state='"+state+'\''+
                ",country='"+country+'\''+
                ", latestTotalDeathCases="+latestRecoveredCases+
                '}';
    }
}
