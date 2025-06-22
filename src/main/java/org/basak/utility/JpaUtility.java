package org.basak.utility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtility {
    private static EntityManagerFactory emf;
    static {
        try {
            emf = Persistence.createEntityManagerFactory("upcPU");
        } catch (Exception e) {
            System.out.println("Emf oluşturulamadı"+e.getMessage());
        }
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("EMF kapatıldı");
        }
    }

}