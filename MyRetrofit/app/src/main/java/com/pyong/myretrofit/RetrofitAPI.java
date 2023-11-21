package com.pyong.myretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/api/subway/{KEY}/{TYPE}/{SERVICE}/{START_INDEX}/{END_INDEX}/{subwayNm}")
    Call<ResponseTrainPosition> getTrainPositionList(@Path("KEY") String key,//@Query("KEY") String key
                                                     @Path("TYPE") String type,
                                                     @Path("SERVICE") String service,
                                                     @Path("START_INDEX") int startIndex,
                                                     @Path("END_INDEX") int endIndex,
                                                     @Path("subwayNm") String subwayNm);
}
