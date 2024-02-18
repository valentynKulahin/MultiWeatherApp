package com.example.data.util.mappers

import com.example.model.model.weather.AirQualityExternalModel
import com.example.model.model.weather.AlertExternalModel
import com.example.model.model.weather.AlertsExternalModel
import com.example.model.model.weather.AstroExternalModel
import com.example.model.model.weather.ConditionExternalModel
import com.example.model.model.weather.CurrentExternalModel
import com.example.model.model.weather.DayExternalModel
import com.example.model.model.weather.ForecastExternalModel
import com.example.model.model.weather.ForecastdayExternalModel
import com.example.model.model.weather.HourExternalModel
import com.example.model.model.weather.LocationExternalModel
import com.example.model.model.weather.WeatherExternalModel
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

fun WeatherNetworkModel.mapToData(): WeatherExternalModel {
    return WeatherExternalModel(
        alerts?.mapToData(),
        current?.mapToData(),
        forecast.mapToData(),
        location?.mapToData()
    )
}

@JvmName(name = "alertsNetworkModelToData")
fun AlertsNetworkModel.mapToData(): AlertsExternalModel {
    return AlertsExternalModel(alerts.mapToData())
}

@JvmName(name = "listAlertNetworkModelToData")
fun List<AlertNetworkModel?>?.mapToData(): List<AlertExternalModel> {
    return mutableListOf<AlertExternalModel>().apply {
        this@mapToData?.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "alertNetworkModelToData")
fun AlertNetworkModel?.mapToData(): AlertExternalModel {
    return AlertExternalModel(
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
fun CurrentNetworkModel.mapToData(): CurrentExternalModel {
    return CurrentExternalModel(
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
fun AirQualityNetworkModel.mapToData(): AirQualityExternalModel {
    return AirQualityExternalModel(
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
fun ConditionNetworkModel.mapToData(): ConditionExternalModel {
    return ConditionExternalModel(code, icon, text)
}

@JvmName(name = "forecastNetworkModelToData")
fun ForecastNetworkModel?.mapToData(): ForecastExternalModel {
    return ForecastExternalModel(this?.forecastday.mapToData())
}

@JvmName(name = "listForecastDayNetworkModelToData")
fun List<ForecastdayNetworkModel>?.mapToData(): List<ForecastdayExternalModel> {
    return mutableListOf<ForecastdayExternalModel>().apply {
        this@mapToData?.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "forecastDayNetworkModelToData")
fun ForecastdayNetworkModel.mapToData(): ForecastdayExternalModel {
    return ForecastdayExternalModel(
        astro?.mapToData(),
        date,
        dateEpoch,
        day?.mapToData(),
        hour?.mapToData()
    )
}

@JvmName(name = "astroNetworkModelToData")
fun AstroNetworkModel.mapToData(): AstroExternalModel {
    return AstroExternalModel(
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
fun DayNetworkModel.mapToData(): DayExternalModel {
    return DayExternalModel(
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
fun List<HourNetworkModel>.mapToData(): List<HourExternalModel> {
    return mutableListOf<HourExternalModel>().apply {
        this@mapToData.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "hourNetworkModelToData")
fun HourNetworkModel.mapToData(): HourExternalModel {
    return HourExternalModel(
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
fun LocationNetworkModel.mapToData(): LocationExternalModel {
    return LocationExternalModel(
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



