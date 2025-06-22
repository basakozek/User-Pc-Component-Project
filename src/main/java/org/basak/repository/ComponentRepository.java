package org.basak.repository;

import jakarta.persistence.EntityManager;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.utility.JpaUtility;

import java.util.List;

public class ComponentRepository extends RepositoryManager<Component,Long>{
    EntityManager em;
    public ComponentRepository() {
        super(Component.class);
        em= JpaUtility.getEntityManager();
    }
    public List<Component> findByTypeNative(EComponentType type) {
        List<Component> components = em.createNativeQuery(
                        "select * from tbl_component where type = :type", Component.class)
                .setParameter("type", type.name())
                .getResultList();
        return components;
    }
    public List<Component> findByPcIdNative(Long pcId) {
        List<Component> components = em.createNativeQuery(
                        "select * from tbl_component where pc_id = :pcId", Component.class)
                .setParameter("pcId", pcId)
                .getResultList();
        return components;
    }
    public List<Component> findByComponentName(String name) {
        List<Component> components = em.createQuery(
                        "select c from Component c " +
                                "where c.name = :name", Component.class)
                .setParameter("name", name)
                .getResultList();
        return components;
    }
    public EComponentType findMostUsedType() {
        return em.createNamedQuery("Component.findMostUsedType", EComponentType.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}
