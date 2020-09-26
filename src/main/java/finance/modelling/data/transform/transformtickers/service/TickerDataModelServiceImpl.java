package finance.modelling.data.transform.transformtickers.service;

import finance.modelling.data.transform.transformtickers.repository.TickerRepository;
import finance.modelling.data.transform.transformtickers.repository.config.MongoDbConfig;
import finance.modelling.data.transform.transformtickers.service.config.TopicConfig;
import finance.modelling.fmcommons.data.logging.kstream.LogMessageConsumed;
import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerDTO;
import finance.modelling.fmcommons.data.schema.fmp.dto.FmpTickerDTO;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class TickerDataModelServiceImpl implements TickerDataModelService {

    private final TopicConfig topics;
    private final TickerRepository tickerRepository;
    private final MongoDbConfig dbConfig;

    public TickerDataModelServiceImpl(TopicConfig topics, TickerRepository tickerRepository, MongoDbConfig dbConfig) {
        this.topics = topics;
        this.tickerRepository = tickerRepository;
        this.dbConfig = dbConfig;
    }


    @Bean
    public BiConsumer<KStream<String, FmpTickerDTO>, KStream<String, EodTickerDTO>> generateTickerDataModel() {

        return (eodTickers, fmpTickers) -> eodTickers

                .transformValues(() -> new LogMessageConsumed<>("x-trace-id"))
                .peek((key, value) -> System.out.println(value));

    }

}
