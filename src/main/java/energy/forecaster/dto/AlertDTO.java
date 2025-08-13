package energy.forecaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AlertDTO {

    private Long id;
    private String message;
    private LocalDateTime triggeredAt;
    private Boolean read;
}
