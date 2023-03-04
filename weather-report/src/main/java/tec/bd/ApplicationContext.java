package tec.bd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tec.bd.imn.IMNProvider;
import tec.bd.openweather.OpenWeatherResource;
import tec.bd.openweather.EnvironmentVariableProvider;
import tec.bd.openweather.OpenWeatherAPIProvider;
import tec.bd.openweather.OpenWeatherProvider;
import tec.bd.weather.service.WeatherProvider;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.storage.LocalWeatherReportStorage;
import tec.bd.weather.storage.WeatherReportStorage;

public class ApplicationContext {

    private final static String BASE_URL = "https://api.openweathermap.org";

    public OpenWeatherAPIProvider openWeatherAPIKeyProvider;
    public OpenWeatherResource openWeatherResource;
    public WeatherProvider openWeatherProvider;
    public WeatherReportStorage weatherReportStorage;
    public WeatherService openWeatherService;

    public WeatherProvider imnProvider;
    public WeatherService imnService;

    public static ApplicationContext init() {
        ApplicationContext appContext = new ApplicationContext();

        appContext.weatherReportStorage = initWeatherReportStorage();

        appContext.openWeatherAPIKeyProvider = initOpenWeatherAPIProvider();
        appContext.openWeatherResource = initOpenWeatherResource();
        appContext.openWeatherProvider = initOpenWeatherProvider(appContext.openWeatherResource, appContext.openWeatherAPIKeyProvider);
        appContext.openWeatherService = initWeatherService(appContext.openWeatherProvider, appContext.weatherReportStorage);

        appContext.imnProvider = initIMNProvider();
        appContext.imnService = initWeatherService(appContext.imnProvider, appContext.weatherReportStorage);

        return appContext;
    }

    private static WeatherReportStorage initWeatherReportStorage() {
        return new LocalWeatherReportStorage();
    }

    private static OpenWeatherAPIProvider initOpenWeatherAPIProvider() {
        return new EnvironmentVariableProvider();
    }

    private static OpenWeatherResource initOpenWeatherResource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenWeatherResource.class);
    }

    private static WeatherProvider initOpenWeatherProvider(OpenWeatherResource openWeatherResource, OpenWeatherAPIProvider openWeatherAPIKeyProvider) {
        return new OpenWeatherProvider(openWeatherResource, openWeatherAPIKeyProvider);
    }

    private static WeatherService initWeatherService(WeatherProvider remoteWeatherProvider, WeatherReportStorage weatherReportStorage) {
        return new WeatherService(remoteWeatherProvider, weatherReportStorage);
    }

    private static IMNProvider initIMNProvider() {
        return new IMNProvider();
    }
}
