package com.example.domain.util

import com.example.data.model.weather.AirQualityDataModel
import com.example.data.model.weather.AlertDataModel
import com.example.data.model.weather.AlertsDataModel
import com.example.data.model.weather.AstroDataModel
import com.example.data.model.weather.ConditionDataModel
import com.example.data.model.weather.CurrentDataModel
import com.example.data.model.weather.DayDataModel
import com.example.data.model.weather.ForecastDataModel
import com.example.data.model.weather.ForecastdayDataModel
import com.example.data.model.weather.HourDataModel
import com.example.data.model.weather.LocationDataModel
import com.example.data.model.weather.WeatherDataModel
import com.example.domain.model.weather.AirQualityDomainModel
import com.example.domain.model.weather.AlertDomainModel
import com.example.domain.model.weather.AlertsDomainModel
import com.example.domain.model.weather.AstroDomainModel
import com.example.domain.model.weather.ConditionDomainModel
import com.example.domain.model.weather.CurrentDomainModel
import com.example.domain.model.weather.DayDomainModel
import com.example.domain.model.weather.ForecastDomainModel
import com.example.domain.model.weather.ForecastdayDomainModel
import com.example.domain.model.weather.HourDomainModel
import com.example.domain.model.weather.LocationDomainModel
import com.example.domain.model.weather.WeatherDomainModel

fun WeatherDataModel.mapToDomain(): WeatherDomainModel {
    return WeatherDomainModel(
        alerts?.mapToDomain(),
        current?.mapToDomain(),
        forecast.mapToDomain(),
        location?.mapToDomain()
    )
}

@JvmName(name = "alertsDataModelToDomain")
fun AlertsDataModel.mapToDomain(): AlertsDomainModel {
    return AlertsDomainModel(alert.mapToDomain())
}

@JvmName(name = "listAlertDataModelToDomain")
fun List<AlertDataModel?>?.mapToDomain(): List<AlertDomainModel> {
    return mutableListOf<AlertDomainModel>().apply {
        this@mapToDomain?.forEach {
            this.add(it.mapToDomain())
        }
    }
}

@JvmName(name = "alertDataModelToDomain")
fun AlertDataModel?.mapToDomain(): AlertDomainModel {
    return AlertDomainModel(
        this?.areas,
        this?.category,
        this?.certainty,
        this?.desc,
        this?.effective,
        this?.event,
        this?.expires,
        this?.headline,
        this?.instruction,
        this?.msgtype,
        this?.note,
        this?.severity,
        this?.urgency
    )
}

@JvmName(name = "currentDataModelToDomain")
fun CurrentDataModel.mapToDomain(): CurrentDomainModel {
    return CurrentDomainModel(
        air_quality?.mapToDomain(),
        cloud,
        condition?.mapToDomain(),
        feelslike_c,
        feelslike_f,
        gust_kph,
        gust_mph,
        humidity,
        is_day,
        last_updated,
        last_updated_epoch,
        precip_in,
        precip_mm,
        pressure_in,
        pressure_mb,
        temp_c,
        temp_f,
        uv,
        vis_km,
        vis_miles,
        wind_degree,
        wind_dir,
        wind_kph,
        wind_mph
    )
}

@JvmName(name = "airQualityDataModelToDomain")
fun AirQualityDataModel.mapToDomain(): AirQualityDomainModel {
    return AirQualityDomainModel(
        co,
        gb_defra_index,
        no2,
        o3,
        pm10,
        pm2_5,
        so2,
        us_epa_index
    )
}

@JvmName(name = "conditionDataModelToDomain")
fun ConditionDataModel.mapToDomain(): ConditionDomainModel {
    return ConditionDomainModel(code, icon, text)
}

@JvmName(name = "forecastDataModelToDomain")
fun ForecastDataModel?.mapToDomain(): ForecastDomainModel {
    return ForecastDomainModel(this?.forecastday.mapToDomain())
}

@JvmName(name = "listForecastDayDataModelToDomain")
fun List<ForecastdayDataModel>?.mapToDomain(): List<ForecastdayDomainModel> {
    return mutableListOf<ForecastdayDomainModel>().apply {
        this@mapToDomain?.forEach {
            this.add(it.mapToDomain())
        }
    }
}

@JvmName(name = "forecastDayDataModelToDomain")
fun ForecastdayDataModel.mapToDomain(): ForecastdayDomainModel {
    return ForecastdayDomainModel(
        astro?.mapToDomain(),
        date,
        date_epoch,
        day?.mapToDomain(),
        hour?.mapToDomain()
    )
}

@JvmName(name = "astroDataModelToDomain")
fun AstroDataModel.mapToDomain(): AstroDomainModel {
    return AstroDomainModel(
        is_moon_up,
        is_sun_up,
        moon_illumination,
        moon_phase,
        moonrise,
        moonset,
        sunrise,
        sunset
    )
}

@JvmName(name = "dayDataModelToDomain")
fun DayDataModel.mapToDomain(): DayDomainModel {
    return DayDomainModel(
        avghumidity,
        avgtemp_c,
        avgtemp_f,
        avgvis_km,
        avgvis_miles,
        condition?.mapToDomain(),
        daily_chance_of_rain,
        daily_chance_of_snow,
        daily_will_it_rain,
        daily_will_it_snow,
        maxtemp_c,
        maxtemp_f,
        maxwind_kph,
        maxwind_mph,
        mintemp_c,
        mintemp_f,
        totalprecip_in,
        totalprecip_mm,
        totalsnow_cm,
        uv
    )
}

@JvmName(name = "listHourDataModelToDomain")
fun List<HourDataModel>.mapToDomain(): List<HourDomainModel> {
    return mutableListOf<HourDomainModel>().apply {
        this@mapToDomain.forEach {
            this.add(it.mapToDomain())
        }
    }
}

@JvmName(name = "hourDataModelToDomain")
fun HourDataModel.mapToDomain(): HourDomainModel {
    return HourDomainModel(
        chance_of_rain,
        chance_of_snow,
        cloud,
        condition?.mapToDomain(),
        dewpoint_c,
        dewpoint_f,
        feelslike_c,
        feelslike_f,
        gust_kph,
        gust_mph,
        heatindex_c,
        heatindex_f,
        humidity,
        is_day,
        precip_in,
        precip_mm,
        pressure_in,
        pressure_mb,
        temp_c,
        temp_f,
        time,
        time_epoch,
        uv,
        vis_km,
        vis_miles,
        will_it_rain,
        will_it_snow,
        wind_degree,
        wind_dir,
        wind_kph,
        wind_mph,
        windchill_c,
        windchill_f
    )
}

@JvmName(name = "locationDataModelToDomain")
fun LocationDataModel.mapToDomain(): LocationDomainModel {
    return LocationDomainModel(
        country,
        lat,
        localtime,
        localtime_epoch,
        lon,
        name,
        region,
        tz_id
    )
}



