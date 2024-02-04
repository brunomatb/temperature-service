package pt.com.batista.temperatureservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.com.batista.temperatureservice.model.TemperatureRequest;
import pt.com.batista.temperatureservice.model.TemperatureResponse;
import pt.com.batista.temperatureservice.persistence.entity.TemperatureData;
import pt.com.batista.temperatureservice.persistence.repository.TemperatureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureServiceImpl implements TemperatureService{
    @Autowired
    private TemperatureRepository repository;
    @Override
    public TemperatureResponse create(TemperatureRequest request) {
        TemperatureData temperature = new TemperatureData();
        temperature.setTemperature(request.getTemperature());
        temperature.setTimestamp(request.getTimestamp());
        temperature.setUid(request.getUid());

        repository.save(temperature);

        TemperatureResponse response = new TemperatureResponse();
        response.setId(temperature.getId());
        response.setTemperature(temperature.getTemperature());
        response.setUid(temperature.getUid());
        response.setTimestamp(temperature.getTimestamp());
        return createResponse(temperature);
    }

    @Override
    public List<TemperatureResponse> getAll() {
        List<TemperatureResponse> responses = new ArrayList<>();
        List<TemperatureData> temperatureData = repository.findAll();
        if(!temperatureData.isEmpty()){
            temperatureData.forEach(data -> responses.add(createResponse(data)));
        }
        return responses;
    }

    @Override
    public TemperatureResponse delete(String id) {

        Optional<TemperatureData> optionalTemperatureData = repository.findById(id);

        if(optionalTemperatureData.isPresent()){
            TemperatureData temperatureData = optionalTemperatureData.get();
            repository.deleteById(temperatureData.getId());
            return createResponse(temperatureData);
        }else{
            return null;
        }
    }

    @Override
    public TemperatureResponse update(TemperatureRequest request, String id) {
        Optional<TemperatureData> optionalTemperatureData = repository.findById(id);

        if (optionalTemperatureData.isPresent()) {
            TemperatureData existingTemperatureData = optionalTemperatureData.get();

            // Atualiza os campos necessários com os valores do request
            existingTemperatureData.setUid(request.getUid());
            existingTemperatureData.setTemperature(request.getTemperature());
            existingTemperatureData.setTimestamp(request.getTimestamp());

            // Salva a entidade atualizada no repositório
            repository.save(existingTemperatureData);

            return createResponse(existingTemperatureData);
        } else {
            return null; // Ou uma resposta adequada se não encontrar o registro
        }
    }


    private TemperatureResponse createResponse(TemperatureData temperature){


        TemperatureResponse response = new TemperatureResponse();
        response.setId(temperature.getId());
        response.setTemperature(temperature.getTemperature());
        response.setUid(temperature.getUid());
        response.setTimestamp(temperature.getTimestamp());
        return response;
    }
}
