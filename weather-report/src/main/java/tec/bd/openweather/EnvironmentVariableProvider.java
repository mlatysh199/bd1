package tec.bd.openweather;

public class EnvironmentVariableProvider implements OpenWeatherAPIProvider {

    private static final String API_KEY = "OW_API_KEY";

    // zsh: ... % export OW_API_KEY=c559e941a0da745aa0139aef272bf16c

    @Override
    public String getAPIKey() {
        return System.getenv(API_KEY);
    }
    
}
