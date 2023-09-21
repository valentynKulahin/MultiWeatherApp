package com.example.domain.usecase

import com.example.domain.model.common.Astro
import com.example.domain.model.common.Condition
import com.example.domain.model.current.AirQuality
import com.example.domain.model.forecast.Day
import com.example.domain.model.forecast.Forecast
import com.example.domain.model.forecast.ForecastWeather
import com.example.domain.model.forecast.Forecastday
import com.example.domain.model.forecast.Hour
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastWeatherUseCase @Inject constructor(

) {

    operator fun invoke(): ForecastWeather {
        return ForecastWeather(
            forecast = Forecast(
                forecastday = listOf(
                    element = Forecastday(
                        date = "2023-09-21",
                        date_epoch = 1695254400,
                        day = Day(
                            air_quality = AirQuality(
                                co = 287.1,
                                no2 = 28.1,
                                o3 = 39.0,
                                so2 = 7.8,
                                pm2_5 = 4.2,
                                pm10 = 5.0,
                                us_epa_index = 1,
                                gb_defra_index = 1
                            ),
                            condition = Condition(
                                text = "Moderate rain",
                                icon = "//cdn.weatherapi.com/weather/64x64/day/302.png",
                                code = 1189
                            ),
                            maxtemp_c = 23.0,
                            maxtemp_f = 73.5,
                            mintemp_c = 14.2,
                            mintemp_f = 57.5,
                            avgtemp_c = 19.1,
                            avgtemp_f = 66.4,
                            maxwind_mph = 13.4,
                            maxwind_kph = 21.6,
                            totalprecip_mm = 10.33,
                            totalprecip_in = 0.41,
                            totalsnow_cm = 0.0,
                            avgvis_km = 9.5,
                            avgvis_miles = 5.0,
                            avghumidity = 68.0,
                            daily_will_it_rain = 1,
                            daily_chance_of_rain = 95,
                            daily_will_it_snow = 0,
                            daily_chance_of_snow = 0,
                            uv = 4.0
                        ), astro = Astro(
                            sunrise = "07:16 AM",
                            sunset = "07:34 PM",
                            moonrise = "02:45 PM",
                            moonset = "09:50 PM",
                            moon_phase = "Waxing Crescent",
                            moon_illumination = "31",
                            is_moon_up = 0,
                            is_sun_up = 0
                        ), hour = listOf(
                            Hour(
                                condition = Condition(
                                    text = "Clear",
                                    icon = "//cdn.weatherapi.com/weather/64x64/night/113.png",
                                    code = 1000
                                ),
                                time_epoch = 1695247200,
                                time = "2023-09-21 00:00",
                                temp_c = 19.6,
                                temp_f = 67.3,
                                is_day = 0,
                                wind_mph = 10.5,
                                wind_kph = 16.9,
                                wind_degree = 172,
                                wind_dir = "S",
                                pressure_mb = 1003.0,
                                pressure_in = 29.63,
                                precip_mm = 0.0,
                                precip_in = 0.0,
                                humidity = 54,
                                cloud = 22,
                                feelslike_c = 19.6,
                                feelslike_f = 67.3,
                                windchill_c = 19.6,
                                windchill_f = 67.3,
                                heatindex_c = 19.6,
                                heatindex_f = 67.3,
                                dewpoint_c = 10.1,
                                dewpoint_f = 50.3,
                                will_it_rain = 0,
                                chance_of_rain = 0,
                                will_it_snow = 0,
                                chance_of_snow = 0,
                                vis_km = 10.0,
                                vis_miles = 6.0,
                                gust_mph = 17.6,
                                gust_kph = 28.3,
                                uv = 1.0,
                                air_quality = AirQuality(
                                    co = 287.1,
                                    no2 = 28.1,
                                    o3 = 39.0,
                                    so2 = 7.8,
                                    pm2_5 = 4.2,
                                    pm10 = 5.0,
                                    us_epa_index = 1,
                                    gb_defra_index = 1
                                )
                            )
                        )
                    )
                )
            )
        )
    }

}