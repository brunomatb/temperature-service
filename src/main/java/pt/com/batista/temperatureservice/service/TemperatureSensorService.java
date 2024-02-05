package pt.com.batista.temperatureservice.service;

import org.springframework.stereotype.Service;
import pt.com.batista.temperatureservice.model.TemperatureSensorRequest;
import pt.com.batista.temperatureservice.model.TemperatureSensorResponse;

import java.util.List;
@Service
public interface TemperatureSensorService {

    TemperatureSensorResponse create(TemperatureSensorRequest request);
    List<TemperatureSensorResponse> getAll();

    TemperatureSensorResponse delete(String Id);
    TemperatureSensorResponse update(TemperatureSensorRequest request, String id);
}
