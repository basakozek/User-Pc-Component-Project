package org.basak;
import org.basak.controller.MainController;
import org.basak.utility.JpaUtility;

public class Runner {
    public static void main(String[] args) {
        // Uygulama başlatıldığında gerekli controller'ları new'liyoruz
        MainController mainController = new MainController();

        try {
            // Test verilerini başlatıyoruz
            mainController.initializeTestData();

            // Menüyi çalıştırıyoruz
            mainController.run(); // Ana menüyü çalıştırmak için run metodunu çağırıyoruz
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // EMF'yi kapatıyoruz, uygulama sonunda kaynakları temizliyoruz
            JpaUtility.closeEntityManagerFactory();
        }
    }
}
