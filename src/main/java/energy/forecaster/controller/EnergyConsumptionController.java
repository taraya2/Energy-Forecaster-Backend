package energy.forecaster.controller;

import energy.forecaster.dto.EnergyConsumptionDTO;
import energy.forecaster.entity.EnergyConsumption;
import energy.forecaster.security.CustomUserDetails;
import energy.forecaster.service.EnergyConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energy")
@RequiredArgsConstructor
public class EnergyConsumptionController {

    private final EnergyConsumptionService energyService;

    @PostMapping
    public ResponseEntity<EnergyConsumption> addConsumption(
            @RequestBody EnergyConsumptionDTO dto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(energyService.addConsumption(dto, userDetails.getId()));
    }

    @GetMapping
    public ResponseEntity<List<EnergyConsumption>> getUserConsumptions(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(energyService.getUserConsumptions(userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyConsumption> getConsumptionById(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(energyService.getConsumptionById(id, userDetails.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnergyConsumption> updateConsumption(
            @PathVariable Long id,
            @RequestBody EnergyConsumptionDTO dto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(energyService.updateConsumption(id, dto, userDetails.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumption(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        energyService.deleteConsumption(id, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}
