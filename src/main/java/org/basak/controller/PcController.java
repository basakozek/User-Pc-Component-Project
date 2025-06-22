package org.basak.controller;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.service.ComponentService;
import org.basak.service.PcService;

import java.util.List;
import java.util.Optional;

public class PcController extends BaseController {
    private final PcService pcService;
    private final ComponentService componentService;

    public PcController(PcService pcService, ComponentService componentService) {
        this.pcService = pcService;
        this.componentService = componentService;
    }

    public void showPcMenu() {
        while (true) {
            System.out.println("\n--- Bilgisayar İşlemleri ---");
            System.out.println("1- Bilgisayar Ekle");
            System.out.println("2- Bilgisayar Listele");
            System.out.println("3- Kullanıcıya Bilgisayar Ata");
            System.out.println("4- Bilgisayar Güncelle");
            System.out.println("5- Bilgisayar Sil");
            System.out.println("6- Bilgisayara Bileşen Ekle");
            System.out.println("7- Bilgisayar Arama");
            System.out.println("0- Ana Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: addPc(); break;
                case 2: listPcs(); break;
                case 3: assignPcToUser(); break;
                case 4: updatePc(); break;
                case 5: deletePc(); break;
                case 6: addComponentToPc(); break;
                case 7: searchPcMenu(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void addPc() {
        String name = readString("Bilgisayar Adı: ");
        Long userId = readLong("Kullanıcı ID: ");
        Pc pc = Pc.builder()
                .pcName(name)
                .userId(userId)
                .build();
        pcService.save(pc);
        System.out.println("Bilgisayar başarıyla eklendi.");
    }

    private void listPcs() {
        List<Pc> pcs = pcService.findAll();
        pcs.forEach(pc -> {
            System.out.println(pc);
            List<Component> components = componentService.findByPcId(pc.getId());
            components.forEach(component -> System.out.println("\t- " + component));
        });
    }

    private void assignPcToUser() {
        Long pcId = readLong("Bilgisayar ID: ");
        Long userId = readLong("Atanacak Kullanıcı ID: ");
        Optional<Pc> optionalPc = pcService.findById(pcId);
        if (optionalPc.isEmpty()) {
            System.out.println("Bilgisayar bulunamadı.");
            return;
        }
        Pc pc = optionalPc.get();
        pc.setUserId(userId);
        pcService.update(pc);
        System.out.println("Bilgisayar başarıyla kullanıcıya atandı.");
    }

    private void updatePc() {
        Long id = readLong("Güncellenecek Bilgisayar ID: ");
        Optional<Pc> optionalPc = pcService.findById(id);
        if (optionalPc.isEmpty()) {
            System.out.println("Bilgisayar bulunamadı.");
            return;
        }
        Pc pc = optionalPc.get();
        String name = readString("Yeni Bilgisayar Adı (" + pc.getPcName() + "): ");
        if (!name.isBlank()) pc.setPcName(name);

        String userIdInput = readString("Yeni Kullanıcı ID (" + pc.getUserId() + "): ");
        if (!userIdInput.isBlank()) pc.setUserId(Long.parseLong(userIdInput));

        pcService.update(pc);
        System.out.println("Bilgisayar başarıyla güncellendi.");
    }

    private void deletePc() {
        Long id = readLong("Silinecek Bilgisayar ID: ");
        pcService.deleteById(id);
        System.out.println("Bilgisayar başarıyla silindi.");
    }

    private void addComponentToPc() {
        Long pcId = readLong("Bilgisayar ID: ");
        System.out.println("Bileşen Tipleri:");
        for (EComponentType type : EComponentType.values()) {
            System.out.println(type.ordinal() + "- " + type);
        }
        int typeOrdinal = readInt("Eklemek istediğiniz bileşen tipi numarası: ");
        EComponentType type = EComponentType.values()[typeOrdinal];

        String name = readString("Bileşen Adı: ");
        Component component = Component.builder()
                .pcId(pcId)
                .name(name)
                .type(type)
                .build();
        componentService.save(component);
        System.out.println("Bileşen başarıyla eklendi.");
    }

    private void searchPcMenu() {
        while (true) {
            System.out.println("\n--- Bilgisayar Arama ---");
            System.out.println("1- İsme Göre Ara");
            System.out.println("2- Kullanıcıya Göre Ara");
            System.out.println("3- Bileşen Tipine Göre Ara");
            System.out.println("0- Önceki Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: searchPcByName(); break;
                case 2: searchPcByUser(); break;
                case 3: searchPcByComponentType(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void searchPcByName() {
        String name = readString("Aranacak Bilgisayar Adı: ");
        pcService.findByPcName(name).forEach(System.out::println);
    }

    void searchPcByUser() {
        Long userId = readLong("Kullanıcı ID: ");
        pcService.findByUserId(userId).forEach(System.out::println);
    }

    private void searchPcByComponentType() {
        System.out.println("Bileşen Tipleri:");
        for (EComponentType type : EComponentType.values()) {
            System.out.println(type.ordinal() + "- " + type);
        }
        int typeOrdinal = readInt("Aranacak Bileşen Tipi numarası: ");
        EComponentType type = EComponentType.values()[typeOrdinal];
        pcService.findByComponentType(type).forEach(System.out::println);
    }
}

