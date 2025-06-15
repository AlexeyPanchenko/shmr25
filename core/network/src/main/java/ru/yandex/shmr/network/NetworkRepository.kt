package ru.yandex.shmr.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.yandex.shmr.model.DogInfo

class NetworkRepository(
    private val service: DogApiService
) {

    suspend fun getDogInfo(): Response<DogInfo> = service.getDogInfo()
}

interface DogApiService {
    @GET("/api/breeds/image/random")
    suspend fun getDogInfo(): Response<DogInfo>
}

fun networkRepository() = NetworkRepository(
    Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(DogApiService::class.java)
)