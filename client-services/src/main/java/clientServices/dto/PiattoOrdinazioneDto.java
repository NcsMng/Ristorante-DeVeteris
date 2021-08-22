package clientServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PiattoOrdinazioneDto {
    private String codicePiatto;
    private int quantita;
    private String note;
}
