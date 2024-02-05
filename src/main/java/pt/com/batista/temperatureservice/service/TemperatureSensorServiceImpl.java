package pt.com.batista.temperatureservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.com.batista.temperatureservice.Exceptions.DuplicateUidException;
import pt.com.batista.temperatureservice.model.TemperatureSensorRequest;
import pt.com.batista.temperatureservice.model.TemperatureSensorResponse;
import pt.com.batista.temperatureservice.persistence.entity.TemperatureSensorData;
import pt.com.batista.temperatureservice.persistence.repository.TemperatureSensorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureSensorServiceImpl implements TemperatureSensorService {
    @Autowired
    private TemperatureSensorRepository repository;
    @Override
    public TemperatureSensorResponse create(TemperatureSensorRequest request) {
        // Verifica se o UID do sensor existe
        if (isUidAlreadyExists(request.getUid())) {
            throw new DuplicateUidException("UID do sensor já existe: " + request.getUid());
        }

        TemperatureSensorData temperature = new TemperatureSensorData();
        temperature.setTemperature(request.getTemperature());
        temperature.setTimestamp(request.getTimestamp());
        temperature.setUid(request.getUid());

        repository.save(temperature);

        TemperatureSensorResponse response = new TemperatureSensorResponse();
        response.setId(temperature.getId());
        response.setTemperature(temperature.getTemperature());
        response.setUid(temperature.getUid());
        response.setTimestamp(temperature.getTimestamp());
        return createResponse(temperature);
    }

    @Override
    public List<TemperatureSensorResponse> getAll() {
        List<TemperatureSensorResponse> responses = new ArrayList<>();
        List<TemperatureSensorData> temperatureSensorData = repository.findAll();
        if(!temperatureSensorData.isEmpty()){
            temperatureSensorData.forEach(data -> responses.add(createResponse(data)));
        }
        return responses;
    }

    @Override
    public TemperatureSensorResponse delete(String id) {

        Optional<TemperatureSensorData> optionalTemperatureData = repository.findById(id);

        if(optionalTemperatureData.isPresent()){
            TemperatureSensorData temperatureSensorData = optionalTemperatureData.get();
            repository.deleteById(temperatureSensorData.getId());
            return createResponse(temperatureSensorData);
        }else{
            return null;
        }
    }

    @Override
    public TemperatureSensorResponse update(TemperatureSensorRequest request, String id) {
        Optional<TemperatureSensorData> optionalTemperatureData = repository.findById(id);

        if (optionalTemperatureData.isPresent()) {
            TemperatureSensorData existingTemperatureSensorData = optionalTemperatureData.get();

            // Atualiza os campos necessários com os valores do request
            existingTemperatureSensorData.setUid(request.getUid());
            existingTemperatureSensorData.setTemperature(request.getTemperature());
            existingTemperatureSensorData.setTimestamp(request.getTimestamp());

            // Salva a entidade atualizada no repositório
            repository.save(existingTemperatureSensorData);

            return createResponse(existingTemperatureSensorData);
        } else {
            return null; // Ou uma resposta adequada se não encontrar o registro
        }
    }


    private TemperatureSensorResponse createResponse(TemperatureSensorData temperature){


        TemperatureSensorResponse response = new TemperatureSensorResponse();
        response.setId(temperature.getId());
        response.setTemperature(temperature.getTemperature());
        response.setUid(temperature.getUid());
        response.setTimestamp(temperature.getTimestamp());
        return response;
    }

    private boolean isUidAlreadyExists(String uid) {
        return repository.existsByUid(uid);
    }
}
