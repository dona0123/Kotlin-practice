package com.example.joyceapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year=2020&pageNo=1&numOfRows=2&returnType=json&serviceKey=Vpi6St7a9MH4GooTM8GMZzlO%2Fb1I8Ca6%2B%2FoMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww%3D%3D
interface NetworkService {
    @GET("getUlfptcaAlarmInfo")
    fun getJsonList(
        @Query("year") year:Int,
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
        @Query("returnType") returnType:String,
        @Query("serviceKey") serviceKey:String
    ) : Call<JsonResponse>
}