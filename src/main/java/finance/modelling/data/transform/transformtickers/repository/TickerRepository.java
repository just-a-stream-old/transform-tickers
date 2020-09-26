package finance.modelling.data.transform.transformtickers.repository;

import finance.modelling.fmcommons.data.schema.model.Ticker;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TickerRepository extends ReactiveMongoRepository<Ticker, UUID> {
}
