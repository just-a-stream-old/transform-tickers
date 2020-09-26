package finance.modelling.data.transform.transformtickers.service;

import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerDTO;
import finance.modelling.fmcommons.data.schema.fmp.dto.FmpTickerDTO;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.BiConsumer;

public interface TickerDataModelService {
    BiConsumer<KStream<String, EodTickerDTO>, KStream<String, FmpTickerDTO>> generateTickerDataModel();

}
