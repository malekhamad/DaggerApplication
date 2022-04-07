package com.example.daggerapplication.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerapplication.R;
import com.example.daggerapplication.util.AppConstant;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

  @Singleton
  @Provides
  static OkHttpClient provideOkHttpClient(){
    return new OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .followRedirects(true)
            .build();
  }
  @Singleton
  @Provides
  static Retrofit provideRetrofit(OkHttpClient okHttpClient){
    return new Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
  }
  @Singleton
  @Provides
    static RequestOptions provideRequestOptions(){
      return RequestOptions.placeholderOf(R.drawable.white_background)
              .centerCrop()
              .error(R.drawable.white_background);
  }
  @Singleton
  @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
      return Glide.with(application)
              .setDefaultRequestOptions(requestOptions);
  }
  @Singleton
  @Provides
    static Drawable provideAppDrawable(Application application){
      return ContextCompat.getDrawable(application,R.drawable.logo);
  }


}
