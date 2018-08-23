package com.damian.moviedb.data.api;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.damian.TestUtils;
import com.damian.moviedb.Constants;
import com.damian.moviedb.data.api.model.ApiDiscoverResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

public class MovieApiTest {

    private MovieApi movieApi;;
    private MockWebServer server;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void before() throws IOException {
        server = new MockWebServer();
        server.start();
        HttpUrl baseUrl = server.url("/");

        movieApi = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi.class);

        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void test_getMostPopularMovies_200_empty() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));
        ApiDiscoverResponse response = movieApi.getMostPopularMovies(3, Constants.API_KEY).blockingGet();

        assertEquals(null, response.getResults());
        assertEquals(0, response.getTotalResults());
    }

    @Test
    public void test_getMostPopularMovies_200_validJson() throws IOException {

        URL responseFileUrl = getClass().getClassLoader().getResource("good_api_response.json");
        String responseJson = TestUtils.loadStringFromFile(responseFileUrl.getPath());
        int pageToRetrieve = 3;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseJson));
        ApiDiscoverResponse response = movieApi.getMostPopularMovies(pageToRetrieve, Constants.API_KEY).blockingGet();

        assertEquals(20, response.getResults().size());
        assertEquals(374872, response.getTotalResults());
        assertEquals(pageToRetrieve, response.getPage());
        assertEquals(18744, response.getTotalPages());

    }

    @Test
    public void test_getMostPopularMovies_error() {
        int pageToRetrieve = 3;

        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("{}"));

        Observable<ApiDiscoverResponse> responseObservable =
                movieApi.getMostPopularMovies(pageToRetrieve, Constants.API_KEY).toObservable();


        TestObserver<ApiDiscoverResponse> testObserver = new TestObserver<>();
        responseObservable.subscribe(testObserver);

        testObserver.assertError(HttpException.class);
        testObserver.assertErrorMessage("HTTP 400 Client Error");
    }
}
