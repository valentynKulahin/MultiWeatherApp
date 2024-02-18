package com.example.data.util.mappers

import com.example.model.model.country.CountryItemExternalModel
import com.example.database.model.CountryItemDatabaseModel
import com.example.network.models.country.CountryItemNetworkModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

//ROOM MAP TO DATA
@JvmName(name = "flowListCountryEntityToData")
fun Flow<List<CountryItemDatabaseModel>>.mapToData(): Flow<List<CountryItemExternalModel>> = transform { value ->
    return@transform emit(value = value.mapToData())
}

@JvmName(name = "listCountryEntityToData")
fun List<CountryItemDatabaseModel>.mapToData(): List<CountryItemExternalModel> {
    return mutableListOf<CountryItemExternalModel>().apply {
        this@mapToData.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "CountryEntityToData")
fun CountryItemDatabaseModel.mapToData(): CountryItemExternalModel {
    return CountryItemExternalModel(id, country, lat, lon, name, region, url)
}


//MAP TO DATABASE
@JvmName(name = "CountryModelToDatabase")
fun CountryItemExternalModel.mapToDatabase(): CountryItemDatabaseModel {
    return CountryItemDatabaseModel(id = 0, country, lat, lon, name, region, url)
}