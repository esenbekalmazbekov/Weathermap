package com.example.weathermap.Models;

import com.example.weathermap.Models.getfromapi.CityWeather;
import com.example.weathermap.Models.getlocationkey.CityData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    String DOMAIN = "https://dataservice.accuweather.com/";

    @GET( "forecasts/v1/daily/5day/{cityKey}?apikey=lWxMIY5UdU5ADGGCsy0oEVrib99819aj")
    Call<CityWeather> getData(@Path("cityKey") String cityKey);

    @GET( "locations/v1/cities/geoposition/search?apikey=lWxMIY5UdU5ADGGCsy0oEVrib99819aj")
    Call<CityData> getLocationKey(@Query("q") String latlng);
}