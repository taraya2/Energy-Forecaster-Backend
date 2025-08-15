package energy.forecaster.service;

import energy.forecaster.dto.PredictionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MLModelClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public double getPredictionFromPython(PredictionRequestDTO dto) {
        String pythonApiUrl = "http://localhost:5000/predict"; // Flask/FastAPI endpoint

        Map<String, Object> requestBody = Map.of(
                "temperature", dto.getTemperature(),
                "humidity", dto.getHumidity(),
                "pastEnergyUsage", dto.getPastEnergyUsage()
        );

        Map response = restTemplate.postForObject(pythonApiUrl, requestBody, Map.class);

        if (response != null && response.containsKey("predictedValue")) {
            return ((Number) response.get("predictedValue")).doubleValue();
        }
        throw new RuntimeException("Invalid response from ML service");
    }
}
