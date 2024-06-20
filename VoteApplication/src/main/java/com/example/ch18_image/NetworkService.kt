package com.example.ch18_image

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://apis.data.go.kr/9760000/PolplcInfoInqireService2/getPolplcOtlnmapTrnsportInfoInqire?serviceKey=Vpi6St7a9MH4GooTM8GMZzlO%2Fb1I8Ca6%2B%2FoMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww%3D%3D&pageNo=1&numOfRows=10&sgId=20231011&sdName=%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C&wiwName=%EA%B0%95%EC%84%9C%EA%B5%AC

interface NetworkService {
    @GET("getPolplcOtlnmapTrnsportInfoInqire")
    fun getXmlList(
        @Query("sdName") name:String,
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
        @Query("returnType") returnType:String,
        @Query("serviceKey") apiKey:String
    ) : Call<XmlResponse>
}