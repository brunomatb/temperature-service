package pt.com.batista.temperatureservice.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.com.batista.temperatureservice.persistence.entity.TemperatureData;
@Repository
public interface TemperatureRepository extends MongoRepository<TemperatureData, String> {
}
