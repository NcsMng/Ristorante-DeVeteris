package com.deveteris.clientservices;

import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.dto.PiattoOrdinazioneDto;
import com.deveteris.clientservices.mapper.OrdinazioneMapper;
import com.deveteris.clientservices.mapper.PiattoOrdinazioneMapper;
import com.deveteris.clientservices.request.OrdinazioneRequest;
import com.deveteris.clientservices.request.PiattoOrdinazioneRequest;
import com.deveteris.clientservices.services.OrdinazioneService;
import com.deveteris.notificationsmanager.repository.OrdinazioniRepository;
import com.deveteris.notificationsmanager.repository.PiattiOrdinazioneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class OrdinazioneServiceTests {
    @Autowired
    private PiattoOrdinazioneMapper piattoOrdinazioneMapper;
    @Autowired
    private OrdinazioneMapper ordinazioneMapper;
    @Autowired
    private OrdinazioniRepository ordinazioniRepository;
    @Autowired
    private PiattiOrdinazioneRepository piattiOrdinazioneRepository;
    @Autowired
    private OrdinazioneService ordinazioneService;

    @Test
    void canGetAllOrdinazioni() {

    }

    @Test
    @Rollback(value = true)
    void canSaveOrdinazione() {
        OrdinazioneRequest request = new OrdinazioneRequest();
        request.setNote("NOTA TEST");
        PiattoOrdinazioneRequest request1 = new PiattoOrdinazioneRequest();
        request1.setCodicePiatto("ABC30");
        request1.setNote("NOTA TEST");
        request1.setQuantita(2);
        List<PiattoOrdinazioneRequest> piattoOrdinazioneRequests = new ArrayList<>();
        piattoOrdinazioneRequests.add(request1);
        request.setPiattiOrdinazione(piattoOrdinazioneRequests);

        OrdinazioneDto expected = new OrdinazioneDto();
        expected.setNote("NOTA TEST");
        expected.setCosto(null);
        expected.setStato(null);
        Set<PiattoOrdinazioneDto> expectedSet = new HashSet<>();

        PiattoOrdinazioneDto piattoOrdinazioneDto = new PiattoOrdinazioneDto();
        piattoOrdinazioneDto.setCodicePiatto("ABC30");
        piattoOrdinazioneDto.setNote("NOTA TEST");
        piattoOrdinazioneDto.setQuantita(2);
        expectedSet.add(piattoOrdinazioneDto);
        expected.setPiattiOrdinazione(expectedSet);

        OrdinazioneDto ordinazioneDto = ordinazioneService.persistOrdinazione(request);
        expected.setId(ordinazioneDto.getId());
        PiattoOrdinazioneDto piattoOrdinazioneDto1 = new ArrayList<>(ordinazioneDto.getPiattiOrdinazione()).get(0);
        piattoOrdinazioneDto.setId(piattoOrdinazioneDto1.getId());

        boolean equals = ordinazioneDto.equals(expected);
        assertThat(equals).isEqualTo(true);


    }


}
