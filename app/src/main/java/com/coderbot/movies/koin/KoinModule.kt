package com.coderbot.movies.koin

import com.coderbot.movies.data.repository.MoviesRepositoryImplementation
import com.coderbot.movies.domain.repository.MoviesRepository
import com.coderbot.movies.domain.usecase.GetMovies
import com.coderbot.movies.domain.utils.Constants.Companion.URL
import com.coderbot.movies.presentation.view_model.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val koinModule = module {

    single { provideRetrofit() }

    single { MoviesRepositoryImplementation(get()) as MoviesRepository }

    single { GetMovies(get()) }

    viewModel { MainViewModel(get()) }
}

fun provideRetrofit(): Retrofit
{
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    return Retrofit.Builder().baseUrl(URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
}