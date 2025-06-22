package org.basak.repository;

import jakarta.persistence.EntityManager;
import org.basak.dto.UserPcCountDTO;
import org.basak.entity.EComponentType;
import org.basak.entity.User;
import org.basak.utility.JpaUtility;

import java.util.List;

public class UserRepository extends RepositoryManager<User,Long> {
    EntityManager em;
    public UserRepository() {
        super(User.class);
        em= JpaUtility.getEntityManager();
    }

    public List<User> findByName(String name) {
        List<User> users = em.createQuery(
                        "select u from User u " +
                                "where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
        return users;
    }
    public List<User> findByAddress(String address) {
        List<User> users = em.createQuery(
                        "select u from User u " +
                                "where u.address = :address", User.class)
                .setParameter("address", address)
                .getResultList();
        return users;
    }
    public List<User> findByAgeRange(int minAge, int maxAge) {
        List<User> users = em.createQuery(
                        "select u from User u " +
                                "where u.age  " +
                                "between :min and :max", User.class)
                .setParameter("min", minAge)
                .setParameter("max", maxAge)
                .getResultList();
        return users;
    }
    public List<User> findUsersByComponentType(EComponentType type) {
        List<User> users = em.createQuery(
                        "select u from User u " +
                                "join Pc p on p.userId = u.id " +
                                "join Component c on c.pcId = p.id " +
                                "where c.type = :type", User.class)
                .setParameter("type", type)
                .getResultList();
        return users;
    }
    public List<UserPcCountDTO> findAllOrderByPcCount() {
        return em.createQuery(
                        "select new org.basak.dto.UserPcCountDTO(u.name, (select count(p) from Pc p where p.userId = u.id)) " +
                                "from User u " +
                                "order by (select count(p) from Pc p where p.userId = u.id) desc",
                        UserPcCountDTO.class)
                .getResultList();
    }
}
