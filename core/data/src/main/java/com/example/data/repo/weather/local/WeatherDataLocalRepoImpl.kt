package com.example.data.repo.weather.local

import com.example.data.model.country.CountryItemDataModel
import com.example.data.util.mappers.mapToData
import com.example.data.util.mappers.mapToDatabase
import com.example.database.dao.FavouritesCountriesDao
import com.example.database.model.CountryItemDatabaseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class WeatherDataLocalRepoImpl @Inject constructor(
    private val favouriteCountriesRepo: FavouritesCountriesDao
) : WeatherDataLocalRepo {

    override suspend fun addCountryToFavourite(countryItemDataModel: CountryItemDataModel) {
        favouriteCountriesRepo.insertFavouriteCountry(countryItemDatabaseModel = countryItemDataModel.mapToDatabase())
    }

    override fun getFavouriteCountries(): Flow<List<CountryItemDataModel>> {
        return favouriteCountriesRepo.getFavouritesCountries().mapToData()
    }

    override suspend fun deleteCountryFromFavourite(countryItemDataModel: CountryItemDataModel) {
        favouriteCountriesRepo.deleteCountryFromFavourite(id = countryItemDataModel.id ?: 0)
    }


}