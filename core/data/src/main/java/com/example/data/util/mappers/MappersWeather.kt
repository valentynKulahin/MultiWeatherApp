package com.example.data.util.mappers

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
import com.example.network.models.weather.AirQualityNetworkModel
import com.example.network.models.weather.AlertNetworkModel
import com.example.network.models.weather.AlertsNetworkModel
import com.example.network.models.weather.AstroNetworkModel
import com.example.network.models.weather.ConditionNetworkModel
import com.example.network.models.weather.CurrentNetworkModel
import com.example.network.models.weather.DayNetworkModel
import com.example.network.models.weather.ForecastNetworkModel
import com.example.network.models.weather.ForecastdayNetworkModel
import com.example.network.models.weather.HourNetworkModel
import com.example.network.models.weather.LocationNetworkModel
import com.example.network.models.weather.WeatherNetworkModel

fun WeatherNetworkModel.mapToData(): WeatherDataModel {
    return WeatherDataModel(
        alerts?.mapToData(),
        current?.mapToData(),
        forecast.mapToData(),
        location?.mapToData()
    )
}

@JvmName(name = "alertsNetworkModelToData")
fun AlertsNetworkModel.mapToData(): AlertsDataModel {
    return AlertsDataModel(alerts.mapToData())
}

@JvmName(name = "listAlertNetworkModelToData")
fun List<AlertNetworkModel?>?.mapToData(): List<AlertDataModel> {
    return mutableListOf<AlertDataModel>().apply {
        this@mapToData?.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "alertNetworkModelToData")
fun AlertNetworkModel?.mapToData(): AlertDataModel {
    return AlertDataModel(
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

@JvmName(name = "currentNetworkModelToData")
fun CurrentNetworkModel.mapToData(): CurrentDataModel {
    return CurrentDataModel(
        airQuality?.mapToData(),
        cloud,
        condition?.mapToData(),
        feelslikeC,
        feelslikeF,
        gustKph,
        gustMph,
        humidity,
        isDay,
        lastUpdated,
        lastUpdatedEpoch,
        precipIn,
        precipMm,
        pressureIn,
        pressureMb,
        tempC,
        tempF,
        uv,
        visKm,
        visMiles,
        windDegree,
        windDir,
        windKph,
        windMph
    )
}

@JvmName(name = "airQualityNetworkModelToData")
fun AirQualityNetworkModel.mapToData(): AirQualityDataModel {
    return AirQualityDataModel(
        co,
        gbDefraIndex,
        no2,
        o3,
        pm10,
        pm25,
        so2,
        usEpaIndex
    )
}

@JvmName(name = "conditionNetworkModelToData")
fun ConditionNetworkModel.mapToData(): ConditionDataModel {
    return ConditionDataModel(code, icon, text)
}

@JvmName(name = "forecastNetworkModelToData")
fun ForecastNetworkModel?.mapToData(): ForecastDataModel {
    return ForecastDataModel(this?.forecastday.mapToData())
}

@JvmName(name = "listForecastDayNetworkModelToData")
fun List<ForecastdayNetworkModel>?.mapToData(): List<ForecastdayDataModel> {
    return mutableListOf<ForecastdayDataModel>().apply {
        this@mapToData?.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "forecastDayNetworkModelToData")
fun ForecastdayNetworkModel.mapToData(): ForecastdayDataModel {
    return ForecastdayDataModel(
        astro?.mapToData(),
        date,
        dateEpoch,
        day?.mapToData(),
        hour?.mapToData()
    )
}

@JvmName(name = "astroNetworkModelToData")
fun AstroNetworkModel.mapToData(): AstroDataModel {
    return AstroDataModel(
        isMoonUp,
        isSunUp,
        moonIllumination,
        moonPhase,
        moonrise,
        moonset,
        sunrise,
        sunset
    )
}

@JvmName(name = "dayNetworkModelToData")
fun DayNetworkModel.mapToData(): DayDataModel {
    return DayDataModel(
        avghumidity,
        avgtempC,
        avgtempF,
        avgvisKm,
        avgvisMiles,
        condition?.mapToData(),
        dailyChanceOfRain,
        dailyChanceOfSnow,
        dailyWillItRain,
        dailyWillItSnow,
        maxtempC,
        maxtempF,
        maxwindKph,
        maxwindMph,
        mintempC,
        mintempF,
        totalprecipIn,
        totalprecipMm,
        totalsnowCm,
        uv
    )
}

@JvmName(name = "listHourNetworkModelToData")
fun List<HourNetworkModel>.mapToData(): List<HourDataModel> {
    return mutableListOf<HourDataModel>().apply {
        this@mapToData.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "hourNetworkModelToData")
fun HourNetworkModel.mapToData(): HourDataModel {
    return HourDataModel(
        chanceOfRain,
        chanceOfSnow,
        cloud,
        condition?.mapToData(),
        dewpointC,
        dewpointF,
        feelslikeC,
        feelslikeF,
        gustKph,
        gustMph,
        heatindexC,
        heatindexF,
        humidity,
        isDay,
        precipIn,
        precipMm,
        pressureIn,
        pressureMb,
        tempC,
        tempF,
        time,
        timeEpoch,
        uv,
        visKm,
        visMiles,
        willItRain,
        willItSnow,
        windDegree,
        windDir,
        windKph,
        windMph,
        windchillC,
        windchillF
    )
}

@JvmName(name = "locationNetworkModelToData")
fun LocationNetworkModel.mapToData(): LocationDataModel {
    return LocationDataModel(
        country,
        lat,
        localtime,
        localtimeEpoch,
        lon,
        name,
        region,
        tzId
    )
}



