package com.example.ch18_image

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit

// https://apis.data.go.kr/9760000/PolplcInfoInqireService2/getPolplcOtlnmapTrnsportInfoInqire?serviceKey=Vpi6St7a9MH4GooTM8GMZzlO%2Fb1I8Ca6%2B%2FoMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww%3D%3D&pageNo=1&numOfRows=10&sgId=20231011&sdName=%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C&wiwName=%EA%B0%95%EC%84%9C%EA%B5%AC
class RetrofitConnection{

    //객체를 하나만 생성하는 싱글턴 패턴을 적용합니다.
    companion object {
        //API 서버의 주소가 BASE_URL이 됩니다.
        private const val BASE_URL = "https://apis.data.go.kr/9760000/PolplcInfoInqireService2/"

        var xmlNetworkService : NetworkService
        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
        val xmlRetrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()

        init{
            xmlNetworkService = xmlRetrofit.create(NetworkService::class.java)
        }
    }
}