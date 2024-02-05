package pt.com.batista.temperatureservice.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.com.batista.temperatureservice.persistence.entity.TemperatureSensorData;
/**
 * Repositório para entidades TemperatureSensorData.
 * Este repositório fornece métodos para interagir com a coleção MongoDB que armazena dados de sensores de temperatura.
 */
@Repository
public interface TemperatureSensorRepository extends MongoRepository<TemperatureSensorData, String> {
    /**
     * Verifica se já existe um sensor com o UID fornecido.
     *
     * @param uid UID do sensor a ser verificado.
     * @return true se o sensor com o UID existir, false caso contrário.
     */
    boolean existsByUid(String uid);
}
