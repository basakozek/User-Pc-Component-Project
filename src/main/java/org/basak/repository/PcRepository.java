package org.basak.repository;
import jakarta.persistence.EntityManager;
import org.basak.dto.PcWithUserDTO;
import org.basak.dto.UserPcCountDTO;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.utility.JpaUtility;

import java.util.List;

public class PcRepository extends RepositoryManager<Pc,Long> {
    EntityManager em;
    public PcRepository() {
        super(Pc.class);
        em= JpaUtility.getEntityManager();
    }
    public List<Pc> findByUserId(Long userId) {
        List<Pc> pcs = em.createQuery(
                        "select p from Pc p " +
                                "where p.userId = :userId", Pc.class)
                .setParameter("userId", userId)
                .getResultList();
        return pcs;
    }
    public List<PcWithUserDTO> findByPcName(String pcName) {
        List<PcWithUserDTO> result = em.createQuery(
                        "select new org.basak.dto.PcWithUserDTO(p, u.name) " +
                                "from Pc p join User u on p.userId = u.id " +
                                "where p.pcName = :pcName", PcWithUserDTO.class)
                .setParameter("pcName", pcName)
                .getResultList();
        return result;
    }
    public List<Pc> findByComponentType(EComponentType type) {
        List<Pc> pcs = em.createQuery(
                        "select distinct p from Pc p " +
                                "join Component c on c.pcId = p.id " +
                                "where c.type = :type", Pc.class)
                .setParameter("type", type)
                .getResultList();
        return pcs;
    }

    public UserPcCountDTO countByUserId(Long userId) {
        UserPcCountDTO result = em.createQuery(
                        "select new org.basak.dto.UserPcCountDTO(u.name, count(p)) " +
                                "from Pc p join User u on p.userId = u.id " +
                                "where u.id = :userId " +
                                "group by u.name", UserPcCountDTO.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return result;
    }
}
