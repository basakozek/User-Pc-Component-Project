package org.basak.controller;
import org.basak.repository.ComponentRepository;
import org.basak.repository.PcRepository;
import org.basak.repository.UserRepository;
import org.basak.service.ComponentService;
import org.basak.service.PcService;
import org.basak.service.UserService;
import org.basak.init.DataInitializer;

public class MainController extends BaseController {
    private UserRepository userRepository;
    private PcRepository pcRepository;
    private ComponentRepository componentRepository;

    private UserService userService;
    private PcService pcService;
    private ComponentService componentService;

    private UserController userController;
    private PcController pcController;
    private ComponentController componentController;

    public MainController() {
        this.userRepository = new UserRepository();
        this.pcRepository = new PcRepository();
        this.componentRepository = new ComponentRepository();

        this.userService = new UserService(userRepository,pcService,componentService);
        this.pcService = new PcService(pcRepository);
        this.componentService = new ComponentService(pcService,componentRepository);

        this.userController = new UserController(userService,componentService,pcService);
        this.pcController = new PcController(pcService, componentService);
        this.componentController = new ComponentController(componentService);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== ANA MENÜ ===");
            System.out.println("1- Kullanıcı İşlemleri");
            System.out.println("2- Bilgisayar İşlemleri");
            System.out.println("3- Bileşen İşlemleri");
            System.out.println("4- Sorgular ve Raporlar");
            System.out.println("5- Test Verisi Yükle");
            System.out.println("0- Çıkış");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1 -> userController.showUserMenu();
                case 2 -> pcController.showPcMenu();
                case 3 -> componentController.showComponentMenu();
                case 4 -> showQueriesMenu();
                case 5 -> initializeTestData();
                case 0 -> {
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void showQueriesMenu() {
        while (true) {
            System.out.println("\n--- Sorgular ve Raporlar ---");
            System.out.println("1- Kullanıcının Bilgisayarlarını Listele");
            System.out.println("2- Kullanıcının Bilgisayar ve Bileşenlerini Listele");
            System.out.println("3- Tüm Kullanıcıların Bilgisayar ve Bileşenlerini Listele");
            System.out.println("4- Bileşen Arama (PC Listesi)");
            System.out.println("5- Bileşene Sahip Kullanıcıları Listele");
            System.out.println("6- En Çok Kullanılan Bileşen Tipi");
            System.out.println("7- Kullanıcı Bilgisayar Sayıları");
            System.out.println("0- Ana Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1 -> pcController.searchPcByUser();
                case 2 -> userController.listUserPcsAndComponents();
                case 3 -> userController.listAllUsersWithPcsAndComponents();
                case 4 -> componentController.listPcsByComponent();
                case 5 -> userController.findUsersByComponent();
                case 6 -> componentController.showMostUsedComponentType();
                case 7 -> userController.showUserPcCounts();
                case 0 -> { return; }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    public void initializeTestData() {
        DataInitializer initializer = new DataInitializer(userService, pcService, componentService);
        initializer.initData();
        System.out.println("Test verileri başarıyla yüklendi!");
    }
}
