package com.example.data.repo.weather.local

import com.example.model.model.country.CountryItemExternalModel
import com.example.data.util.mappers.mapToData
import com.example.data.util.mappers.mapToDatabase
import com.example.database.dao.FavouritesCountriesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class WeatherDataLocalRepoImpl @Inject constructor(
    private val favouriteCountriesRepo: FavouritesCountriesDao
) : WeatherDataLocalRepo {

    override suspend fun addCountryToFavourite(countryItemExternalModel: CountryItemExternalModel) =
        favouriteCountriesRepo.insertFavouriteCountry(countryItemDatabaseModel = countryItemExternalModel.mapToDatabase())

    override fun getFavouriteCountries(): Flow<List<CountryItemExternalModel>> =
        favouriteCountriesRepo.getFavouritesCountries().mapToData()

    override suspend fun deleteCountryFromFavourite(countryItemExternalModel: CountryItemExternalModel) =
        favouriteCountriesRepo.deleteCountryFromFavourite(id = countryItemExternalModel.id ?: 0)

}