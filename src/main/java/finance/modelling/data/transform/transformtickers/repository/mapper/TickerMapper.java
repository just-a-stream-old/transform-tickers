package finance.modelling.data.transform.transformtickers.repository.mapper;

import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerDTO;
import finance.modelling.fmcommons.data.schema.fmp.dto.FmpTickerDTO;
import finance.modelling.fmcommons.data.schema.model.Ticker;
import finance.modelling.fmcommons.data.schema.model.enums.FinanceApi;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TickerMapper {
    TickerMapper INSTANCE = Mappers.getMapper(TickerMapper.class);

    @Mapping(source = "exchange", target = "exchangeCode")
    Ticker tickerEodDTOToTicker(EodTickerDTO dto);

    @AfterMapping
    default void determineFinanceApisField(EodTickerDTO dto, @MappingTarget Ticker ticker) {
        ticker.setFinanceApis(List.of(FinanceApi.EOD_HISTORICAL_DATA));
    }

    Ticker tickerFmpDTOToTicker(FmpTickerDTO dto);

    @AfterMapping
    default void determineFinanceApisField(FmpTickerDTO dto, @MappingTarget Ticker ticker) {
        ticker.setFinanceApis(List.of(FinanceApi.FINANCIAL_MODELLING_PREP));
    }
}
