package io.javabrains.coronavirustracker.services;


import io.javabrains.coronavirustracker.models.LocationStats1;
import io.javabrains.coronavirustracker.models.LocationStats2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService2{
    //private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<LocationStats2> allStats2 = new ArrayList<>();
    //private List<LocationStats> allStats1 = new ArrayList<>();
    public List<LocationStats2> getAllStats()
    {
        return allStats2;
    }
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")//Schedule the method in every time
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats2> newStats = new ArrayList<>();
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse=client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        StringReader csvBodyReader=new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats2 locationStat2 = new LocationStats2();
            locationStat2.setState(record.get("Province/State"));
            locationStat2.setCountry(record.get("Country/Region"));
            int latestCases=Integer.parseInt(record.get(record.size()-1));
            int prevDayCases=Integer.parseInt(record.get(record.size()-2));
            locationStat2.setLatestRecoveredCases(latestCases);
            locationStat2.setRecoveredDiffFromPrevDay(latestCases-prevDayCases);
            //System.out.println(locationStat);
            newStats.add(locationStat2);
        }
        this.allStats2=newStats;
    }
}

