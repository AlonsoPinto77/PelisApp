package com.example.data.common

import com.example.data.network.DomainMapper
import com.example.data.utils.NetworkUtils
import com.example.domain.common.DB_ENTRY_ERROR
import com.example.domain.common.Failure
import com.example.domain.common.GENERAL_NETWORK_ERROR
import com.example.domain.common.HttpError
import com.example.domain.common.Result
import com.example.domain.common.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Repository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val networkUtils: NetworkUtils by inject()

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                apiDataProvider()
            }
        } else {
            withContext(Dispatchers.IO) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(
                    HttpError(
                        Throwable(DB_ENTRY_ERROR)
                    )
                )
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(dataProvider: suspend () -> Result<T>): Result<T> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }

    /**
     * Use this if you need to cache list of data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchDataList(
        apiDataProvider: suspend () -> Result<List<T>>,
        dbDataProvider: suspend () -> List<R>
    ): Result<List<T>> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                apiDataProvider()
            }
        } else {
            withContext(Dispatchers.IO) {
                val dbResult = dbDataProvider()
                if (dbResult.isNotEmpty()) Success(dbResult.map { it.mapToDomainModel() })
                else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchDataList(dataProvider: suspend () -> Result<List<T>>): Result<List<T>> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }

    protected suspend fun fetchDataFromDB(
        dbDataProvider: suspend () -> R?
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            val dbResult = dbDataProvider()
            if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(
                HttpError(
                    Throwable(DB_ENTRY_ERROR)
                )
            )
        }
    }
}