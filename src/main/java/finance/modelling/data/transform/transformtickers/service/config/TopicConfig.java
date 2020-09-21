package finance.modelling.data.transform.transformtickers.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TopicConfig {

    private final String inputFmpTickersTopic;
    private final String inputEodTickersTopic;


    public TopicConfig(
            @Value("${spring.cloud.stream.bindings.generateTickerDataModel-in-0.destination}") String inputFmpTickersTopic,
            @Value("${spring.cloud.stream.bindings.generateTickerDataModel-in-1.destination}") String inputEodTickersTopic) {
        this.inputFmpTickersTopic = inputFmpTickersTopic;
        this.inputEodTickersTopic = inputEodTickersTopic;
    }
}
