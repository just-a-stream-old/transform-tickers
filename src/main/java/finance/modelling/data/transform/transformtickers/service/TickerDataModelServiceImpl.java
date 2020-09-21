package finance.modelling.data.transform.transformtickers.service;

import finance.modelling.data.transform.transformtickers.repository.TickerRepository;
import finance.modelling.data.transform.transformtickers.service.config.TopicConfig;
import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerDTO;
import finance.modelling.fmcommons.data.schema.fmp.dto.FmpTickerDTO;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class TickerDataModelServiceImpl implements TickerDataModelService {

    private final String inputFmpTickersTopic;
    private final String inputEodTickersTopic;
    private final TickerRepository tickerRepository;

    public TickerDataModelServiceImpl(
            @Value("${spring.cloud.stream.bindings.generateTickerDataModel-in-0.destination}") String inputFmpTickersTopic,
            @Value("${spring.cloud.stream.bindings.generateTickerDataModel-in-1.destination}") String inputEodTickersTopic,
            TickerRepository tickerRepository, TopicConfig topicConfig) {
        this.inputFmpTickersTopic = inputFmpTickersTopic;
        this.inputEodTickersTopic = inputEodTickersTopic;
        this.tickerRepository = tickerRepository;
    }


    @Bean
    public BiConsumer<KStream<String, EodTickerDTO>, KStream<String, FmpTickerDTO>> generateTickerDataModel() {

        return (fmpTickers, eodTickers) -> fmpTickers
                .peek((key, value) -> System.out.println(value));

    }

}
