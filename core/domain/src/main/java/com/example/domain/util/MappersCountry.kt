package com.example.domain.util

import com.example.data.model.country.CountryItemDataModel
import com.example.data.util.mappers.mapToData
import com.example.domain.model.country.CountryItemDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

//MAP TO DOMAIN
@JvmName(name = "flowListCountryItemDataToDomain")
fun Flow<List<CountryItemDataModel>>.mapToDomain(): Flow<List<CountryItemDomainModel>> = transform { value ->
    return@transform emit(value = value.mapToDomain())
}

@JvmName(name = "listCountryItemDataToDomain")
fun List<CountryItemDataModel>.mapToDomain(): List<CountryItemDomainModel> {
    return mutableListOf<CountryItemDomainModel>().apply {
        this@mapToDomain.forEach {
            this.add(it.mapToDomain())
        }
    }
}

@JvmName(name = "countryDomainModelToData")
fun CountryItemDataModel.mapToDomain(): CountryItemDomainModel {
    return CountryItemDomainModel(id, country, lat, lon, name, region, url)
}


//MAP TO DATA
@JvmName(name = "listCountryItemDomainToData")
fun List<CountryItemDomainModel>.mapToData(): List<CountryItemDataModel> {
    return mutableListOf<CountryItemDataModel>().apply {
        this@mapToData.forEach {
            this.add(it.mapToData())
        }
    }
}

@JvmName(name = "countryDomainModelToData")
fun CountryItemDomainModel.mapToData(): CountryItemDataModel {
    return CountryItemDataModel(id, country, lat, lon, name, region, url)
}
