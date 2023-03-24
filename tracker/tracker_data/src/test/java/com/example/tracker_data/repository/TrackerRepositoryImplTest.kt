package com.example.tracker_data.repository

import com.example.tracker_data.remote.IOpenFoodApi
import com.example.tracker_data.remote.malformedFoodResponse
import com.example.tracker_data.remote.validFoodResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TrackerRepositoryImplTest {

    private lateinit var trackerRepository: TrackerRepositoryImpl
    private lateinit var mockServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: IOpenFoodApi

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockServer.url("/"))
            .build()
            .create(IOpenFoodApi::class.java)
        trackerRepository = TrackerRepositoryImpl(
            dao = mockk(relaxed = true),
            api = api
        )

    }

    @After
    fun tearDown() {
        mockServer.close()
    }

    @Test
    fun `Search food, valid response, returns results`() = runBlocking {
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validFoodResponse)
        )

        val result = trackerRepository.searchFood("banana", 1, 40)

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Search food, invalid response, returns failure`() = runBlocking {
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(malformedFoodResponse)
        )

        val result = trackerRepository.searchFood("banana", 1, 40)

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Search food, malformed response, returns failure`() = runBlocking {
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(malformedFoodResponse)
        )

        val result = trackerRepository.searchFood("banana", 1, 40)

        assertThat(result.isFailure).isTrue()
    }
}