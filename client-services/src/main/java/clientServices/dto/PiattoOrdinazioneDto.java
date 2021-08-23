package clientServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PiattoOrdinazioneDto {
    private String codicePiatto;
    private Integer quantita;
    private String note;
}
