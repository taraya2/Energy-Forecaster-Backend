package energy.forecaster.controller;

import energy.forecaster.dto.EnergyConsumptionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/{userId}/consumption")
public class EnergyConsumptionController {

    @PostMapping
    public ResponseEntity<?> addConsumption(@PathVariable Long userId,
                                            @RequestBody EnergyConsumptionDTO consumptionDTO) {
        return null;
    }

    // get all consumption records for a user
    @GetMapping
    public ResponseEntity<List<EnergyConsumptionDTO>> getAllConsumption(@PathVariable Long userId) {
        return null;
    }

    // get specific record
    @GetMapping("/{record}")
    public ResponseEntity<EnergyConsumptionDTO> getConsumptionRecord(@PathVariable Long userId, @PathVariable Long recordId) {
        return null;
    }

    // delete a specific record
    @DeleteMapping("/{record}")
    public ResponseEntity<?> deleteConsumptionRecord(@PathVariable Long userId, @PathVariable Long recordId) {
        return ResponseEntity.ok("Record deleted successfully");
    }
}
