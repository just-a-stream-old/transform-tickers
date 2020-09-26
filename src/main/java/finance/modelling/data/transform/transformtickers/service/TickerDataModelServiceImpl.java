package finance.modelling.data.transform.transformtickers.service;

import finance.modelling.data.transform.transformtickers.repository.TickerRepository;
import finance.modelling.data.transform.transformtickers.repository.config.MongoDbConfig;
import finance.modelling.data.transform.transformtickers.repository.mapper.TickerMapper;
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
    public BiConsumer<KStream<String, EodTickerDTO>, KStream<String, FmpTickerDTO>> generateTickerDataModel() {

        // Todo:

        return (eodTickers, fmpTickers) -> eodTickers
                .transformValues(() -> new LogMessageConsumed<>(topics.getTraceIdHeaderName()))
                // Todo: If I want to use log compaction I will need to select a new key here - but that's heap...
                //        '--> But I need to perform the reduce...
                .mapValues(TickerMapper.INSTANCE::tickerEodDTOToTicker)
                .merge(
                        fmpTickers
                                .transformValues(() -> new LogMessageConsumed<>(topics.getTraceIdHeaderName()))
                                // Todo: I need to determine the country for fmp in order to reduce
                                .mapValues(TickerMapper.INSTANCE::tickerFmpDTOToTicker)
                );
//                .groupByKey()
                // Todo: Reduce in here to remove duplicates across clients & also determine 'financeApis' field.
//                .reduce(
//                );

    }

}












