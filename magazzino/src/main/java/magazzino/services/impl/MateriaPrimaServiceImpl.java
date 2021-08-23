package magazzino.services.impl;

import magazzino.exceptions.MateriaPrimaNonTrovataException;
import magazzino.mapper.MateriaPrimaMapper;
import magazzino.model.MateriaPrima;
import magazzino.repository.MateriaPrimaRepository;
import magazzino.requests.MateriaPrimaRequest;
import magazzino.services.MateriaPrimaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MateriaPrimaServiceImpl implements MateriaPrimaService {
    private final MateriaPrimaMapper materiaPrimaMapper;
    private final MateriaPrimaRepository materiaPrimaRepository;

    public MateriaPrimaServiceImpl(MateriaPrimaMapper materiaPrimaMapper, MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaMapper = materiaPrimaMapper;
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    @Override
    public MateriaPrima persistMateriaPrima(MateriaPrimaRequest materiaPrimaRequest) {
        if (StringUtils.isNotBlank(materiaPrimaRequest.getId())) {
            MateriaPrima materiaPrima = materiaPrimaRepository.findById(materiaPrimaRequest.getId())
                    .orElseThrow(() -> new MateriaPrimaNonTrovataException("Materia con Id {} non trovata!", materiaPrimaRequest.getId()));
            return materiaPrimaMapper.updateMateriaPrimaFromRequest(materiaPrima, materiaPrimaRequest);
        } else {
            return materiaPrimaRepository
                    .save(materiaPrimaMapper.getEntityFromRequest(materiaPrimaRequest));
        }
    }

    @Override
    public boolean deleteMateriaPrima(String id) {
        return materiaPrimaRepository.deleteByIdEquals(id);
    }
}
