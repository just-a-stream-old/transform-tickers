package finance.modelling.data.transform.transformtickers.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TopicConfig {

    private final String traceIdHeaderName;
    private final String ingestEodTickersTopic;
    private final String ingestFmpTickersTopic;

    public TopicConfig(
            @Value("${spring.cloud.stream.kafka.streams.header.traceId}") String traceIdHeaderName,
            @Value("${spring.cloud.stream.bindings.generateTickerDataModel-in-1.destination}") String ingestEodTickersTopic,
            @Value("${spring.cloud.stream.bindings.generateTickerDataModel-in-0.destination}")String ingestFmpTickersTopic) {
        this.traceIdHeaderName = traceIdHeaderName;
        this.ingestEodTickersTopic = ingestEodTickersTopic;
        this.ingestFmpTickersTopic = ingestFmpTickersTopic;
    }
}
