package com.example.data.util.mappers

import com.example.data.model.country.CountryItemDataModel
import com.example.database.model.CountryItemDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

//MAP TO DATA
@JvmName(name = "flowListCountryEntityToData")
fun Flow<List<CountryItemDatabaseModel>>.mapToData(): Flow<List<CountryItemDataModel>> = transform { value ->
    return@transform emit(value = value.mapToData())
}

@JvmName(name = "listCountryEntityToData")
fun List<CountryItemDatabaseModel>.mapToData(): List<CountryItemDataModel> {
    return mutableListOf<CountryItemDataModel>().apply {
        this@mapToData.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "CountryEntityToData")
fun CountryItemDatabaseModel.mapToData(): CountryItemDataModel {
    return CountryItemDataModel(id, country, lat, lon, name, region, url)
}


//MAP TO DATABASE
@JvmName(name = "CountryModelToDatabase")
fun CountryItemDataModel.mapToDatabase(): CountryItemDatabaseModel {
    return CountryItemDatabaseModel(id = 0, country, lat, lon, name, region, url)
}