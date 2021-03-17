package com.example.currency.main

import com.example.currency.data.models.CurrencyResponse
import com.example.currency.util.Resource

interface MainRepository {

    suspend fun getRates(base: String): Resource<CurrencyResponse>
}