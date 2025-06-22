package org.basak.controller;
import org.basak.dto.UserPcCountDTO;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.entity.User;
import org.basak.service.ComponentService;
import org.basak.service.PcService;
import org.basak.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserController extends BaseController {
    private final UserService userService;
    private final ComponentService componentService;
    private final PcService pcService;

    public UserController(UserService userService, ComponentService componentService, PcService pcService) {
        this.userService = userService;
        this.componentService = componentService;
        this.pcService = pcService;
    }

    public void showUserMenu() {
        while (true) {
            System.out.println("\n--- Kullanıcı İşlemleri ---");
            System.out.println("1- Kullanıcı Ekle");
            System.out.println("2- Kullanıcı Listele");
            System.out.println("3- Kullanıcı Ara");
            System.out.println("4- Kullanıcı Güncelle");
            System.out.println("5- Kullanıcı Sil");
            System.out.println("0- Ana Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: addUser(); break;
                case 2: listUsers(); break;
                case 3: searchUserMenu(); break;
                case 4: updateUser(); break;
                case 5: deleteUser(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void addUser() {
        String name = readString("Adı: ");
        String address = readString("Adres: ");
        String phone = readString("Telefon: ");
        int age = readInt("Yaş: ");
        User user = User.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .age(age)
                .build();
        userService.save(user);
        System.out.println("Kullanıcı başarıyla eklendi.");
    }

    private void listUsers() {
        List<User> users = userService.findAll();
        users.forEach(System.out::println);
    }

    private void searchUserMenu() {
        while (true) {
            System.out.println("\n--- Kullanıcı Arama ---");
            System.out.println("1- İsme Göre Ara");
            System.out.println("2- Adrese Göre Ara");
            System.out.println("3- Yaş Aralığına Göre Ara");
            System.out.println("0- Önceki Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: searchUserByName(); break;
                case 2: searchUserByAddress(); break;
                case 3: searchUserByAge(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void searchUserByName() {
        String name = readString("Aranacak İsim: ");
        List<User> users = userService.findByName(name);
        users.forEach(System.out::println);
    }

    private void searchUserByAddress() {
        String address = readString("Aranacak Adres: ");
        List<User> users = userService.findByAddress(address);
        users.forEach(System.out::println);
    }

    private void searchUserByAge() {
        int minAge = readInt("Minimum Yaş: ");
        int maxAge = readInt("Maksimum Yaş: ");
        List<User> users = userService.findByAgeRange(minAge, maxAge);
        users.forEach(System.out::println);
    }

    private void updateUser() {
        Long id = readLong("Güncellenecek Kullanıcı ID: ");
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            System.out.println("Kullanıcı bulunamadı.");
            return;
        }
        User user = optionalUser.get();

        String name = readString("Yeni Adı (" + user.getName() + "): ");
        if (!name.isBlank()) user.setName(name);

        String address = readString("Yeni Adres (" + user.getAddress() + "): ");
        if (!address.isBlank()) user.setAddress(address);

        String phone = readString("Yeni Telefon (" + user.getPhone() + "): ");
        if (!phone.isBlank()) user.setPhone(phone);

        String ageInput = readString("Yeni Yaş (" + user.getAge() + "): ");
        if (!ageInput.isBlank()) user.setAge(Integer.parseInt(ageInput));

        userService.update(user);
        System.out.println("Kullanıcı başarıyla güncellendi.");
    }

    private void deleteUser() {
        Long id = readLong("Silinecek Kullanıcı ID: ");
        userService.deleteById(id);
        System.out.println("Kullanıcı başarıyla silindi.");
    }

    public void listUserPcsAndComponents() {
        Long userId = readLong("Bilgisayarları listelenecek kullanıcı ID: ");
        Optional<User> optionalUser = userService.findById(userId);
        User user = optionalUser.get();
        System.out.println("Kullanıcı: " + user.getName());
        List<Pc> pcs = pcService.findByUserId(userId);
        if (pcs.isEmpty()) {
            System.out.println("Bu kullanıcının bilgisayarı yok.");
            return;
        }
        for (Pc pc : pcs) {
            System.out.println("\tBilgisayar: " + pc.getPcName());
            List<Component> components = componentService.findByPcId(pc.getId());
            if (components.isEmpty()) {
                System.out.println("\t\tBu bilgisayara ait bileşen yok.");
            } else {
                for (Component component : components) {
                    System.out.println("\t\tBileşen: " + component.getName() + " - " + component.getType());
                }
            }
        }
    }

    public void listAllUsersWithPcsAndComponents() {
        List<User> users = userService.findAll();

        for (User user : users) {
            System.out.println("Kullanıcı: " + user.getName());

            List<Pc> pcs = pcService.findByUserId(user.getId());
            if (pcs.isEmpty()) {
                System.out.println("\t- Bu kullanıcının bilgisayarı yok.");
            } else {
                for (Pc pc : pcs) {
                    System.out.println("\tBilgisayar: " + pc.getPcName());

                    List<Component> components = componentService.findByPcId(pc.getId());
                    if (components.isEmpty()) {
                        System.out.println("\t\t- Bu bilgisayara ait bileşen yok.");
                    } else {
                        for (Component component : components) {
                            System.out.println("\t\t- Bileşen: " + component.getName() + " - " + component.getType());
                        }
                    }
                }
            }
            System.out.println();
        }
    }


    public void findUsersByComponent() {
        String componentName = readString("Bileşen Adı: ");
        try {
            EComponentType type = EComponentType.valueOf(componentName.trim().toUpperCase());
            userService.findUsersByComponentType(type)
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Geçersiz bileşen tipi girdiniz.");
        }
    }

    public void showUserPcCounts() {
        List<UserPcCountDTO> results = userService.findAllOrderByPcCount();
        for (UserPcCountDTO dto : results) {
            System.out.println(dto.name() + " - Bilgisayar Sayısı: " + dto.pcCount());
        }
    }
}

