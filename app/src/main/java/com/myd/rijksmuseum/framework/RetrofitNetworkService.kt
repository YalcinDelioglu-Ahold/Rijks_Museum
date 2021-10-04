package com.myd.rijksmuseum.framework

import com.myd.rijksmuseum.data.NetworkService
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.domain.Details
import com.myd.rijksmuseum.framework.db.entity.CollectionEntity
import com.myd.rijksmuseum.framework.db.entity.DetailsEntity
import com.myd.rijksmuseum.framework.db.entity.toCollection
import com.myd.rijksmuseum.framework.db.entity.toDetails
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RetrofitNetworkService : NetworkService {
    companion object {
        private const val BASE_URL = "https://www.rijksmuseum.nl/api/nl/"
        private const val API_KEY = "0fiuZFh4"
        private const val SORT_BY = "artist"
        private const val NUMBER_OF_RESULT_PER_PAGE = "10"
    }

    private val retrofit = Retrofit.Builder().apply {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        baseUrl(BASE_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    private val services = retrofit.create(Services::class.java)

    override suspend fun fetchCollections(pageNumber: Int): List<Collection> =
        services.fetchCollections(pageNumber).artObjects.map {
            it.toCollection()
        }

    override suspend fun getDetails(objectNumber: String): Details =
        with(services.getDetails(objectNumber).artObject) {
            this.toDetails()
        }

    interface Services {
        @GET("collection?key=$API_KEY&imgonly=True&s=$SORT_BY&ps=$NUMBER_OF_RESULT_PER_PAGE")
        suspend fun fetchCollections(@Query("p") pageNumber: Int): FetchResponse

        @GET("collection/{objectNumber}?key=$API_KEY")
        suspend fun getDetails(@Path("objectNumber") objectNumber: String): GetDetailsResponse
    }
}

data class FetchResponse(val artObjects: List<CollectionEntity>)

data class GetDetailsResponse(val artObject: DetailsEntity)
