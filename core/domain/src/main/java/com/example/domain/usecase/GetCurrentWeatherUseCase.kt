package com.example.domain.usecase

import com.example.domain.model.common.Condition
import com.example.domain.model.current.AirQuality
import com.example.domain.model.current.Current
import com.example.domain.model.current.CurrentWeather
import com.example.domain.model.current.Location
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentWeatherUseCase @Inject constructor(

) {

    operator fun invoke(): CurrentWeather {
        return CurrentWeather(
            location = Location(
                name = "Essen",
                region = "Nordrhein-Westfalen",
                country = "Germany",
                lat = 51.45,
                lon = 7.02,
                tz_id = "Europe/Berlin",
                localtime_epoch = 1695312770,
                localtime = "2023-09-21 18:12"
            ),
            current = Current(
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
                last_updated_epoch = 1695312000,
                last_updated = "2023-09-21 18:00",
                temp_c = 17.0,
                temp_f = 62.6,
                is_day = 1,
                wind_mph = 3.8,
                wind_kph = 6.1,
                wind_degree = 230,
                wind_dir = "SW",
                pressure_mb = 997.0,
                pressure_in = 29.44,
                precip_mm = 0.45,
                precip_in = 0.02,
                humidity = 94,
                cloud = 25,
                feelslike_c = 17.0,
                feelslike_f = 62.6,
                vis_km = 9.0,
                vis_miles = 5.0,
                uv = 4.0,
                gust_mph = 17.3,
                gust_kph = 27.8
            )
        )
    }

}