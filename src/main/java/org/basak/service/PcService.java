package org.basak.service;
import org.basak.dto.PcWithUserDTO;
import org.basak.dto.UserPcCountDTO;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.repository.PcRepository;

import java.util.List;

public class PcService extends BaseService<Pc, Long> {

    private final PcRepository pcRepository;

    public PcService(PcRepository pcRepository) {
        super(pcRepository);
        this.pcRepository = pcRepository;
    }

    public List<PcWithUserDTO> findByPcName(String pcName) {
        return pcRepository.findByPcName(pcName);
    }

    public List<Pc> findByComponentType(EComponentType type) {
        return pcRepository.findByComponentType(type);
    }

    public UserPcCountDTO countByUserId(Long userId) {
        return pcRepository.countByUserId(userId);
    }

    public List<Pc> findByUserId(Long userId) {
        return pcRepository.findByUserId(userId);
    }
}
