package io.javabrains.coronavirustracker.models;

public class LocationStats1 {
    private String state;
    private String country;
    private int latestDeathCases;
    private int deathDiffFromPrevDay;
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
    public int getLatestDeathCases()
    {

        return latestDeathCases;
    }
    public void setLatestDeathCases(int latestDeathCases)
    {

        this.latestDeathCases=latestDeathCases;
    }
    public int getDeathDiffFromPrevDay()
    {

        return deathDiffFromPrevDay;
    }
    public void setDeathDiffFromPrevDay(int deathDiffFromPrevDay)
    {

        this.deathDiffFromPrevDay=deathDiffFromPrevDay;
    }
    @Override
    public String toString()
    {
        return "LocationStats{"+
                "state='"+state+'\''+
                ",country='"+country+'\''+
                ", latestTotalDeathCases="+latestDeathCases+
                '}';
    }
}
