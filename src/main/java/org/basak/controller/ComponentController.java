package org.basak.controller;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.service.ComponentService;

import java.util.List;
import java.util.Optional;

public class ComponentController extends BaseController {
    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    public void showComponentMenu() {
        while (true) {
            System.out.println("\n--- Bileşen İşlemleri ---");
            System.out.println("1- Bileşen Ekle");
            System.out.println("2- Bileşen Listele");
            System.out.println("3- Bileşen Güncelle");
            System.out.println("4- Bileşen Sil");
            System.out.println("5- Bileşen Arama");
            System.out.println("6- Bileşen Raporları");
            System.out.println("0- Ana Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: addComponent(); break;
                case 2: listComponents(); break;
                case 3: updateComponent(); break;
                case 4: deleteComponent(); break;
                case 5: searchComponentMenu(); break;
                case 6: showReportsMenu(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void addComponent() {
        Long pcId = readLong("Bileşeni ekleyeceğiniz PC ID: ");
        String name = readString("Bileşen Adı: ");

        System.out.println("Bileşen Tipleri:");
        for (EComponentType type : EComponentType.values()) {
            System.out.println(type.ordinal() + "- " + type);
        }
        int typeOrdinal = readInt("Bileşen Tipi numarası: ");
        EComponentType type = EComponentType.values()[typeOrdinal];

        Component component = Component.builder()
                .pcId(pcId)
                .name(name)
                .type(type)
                .build();

        componentService.save(component);
        System.out.println("Bileşen başarıyla eklendi.");
    }

    private void listComponents() {
        List<Component> components = componentService.findAll();
        components.forEach(System.out::println);
    }

    private void updateComponent() {
        Long id = readLong("Güncellenecek Bileşen ID: ");
        Optional<Component> optionalComponent = componentService.findById(id);
        if (optionalComponent.isEmpty()) {
            System.out.println("Bileşen bulunamadı.");
            return;
        }
        Component component = optionalComponent.get();
        String name = readString("Yeni Bileşen Adı (" + component.getName() + "): ");
        if (!name.isBlank()) component.setName(name);

        System.out.println("Bileşen Tipleri:");
        for (EComponentType type : EComponentType.values()) {
            System.out.println(type.ordinal() + "- " + type);
        }
        String typeInput = readString("Yeni Bileşen Tipi (" + component.getType() + ")");
        if (!typeInput.isBlank()) {
            int typeOrdinal = Integer.parseInt(typeInput);
            component.setType(EComponentType.values()[typeOrdinal]);
        }

        componentService.update(component);
        System.out.println("Bileşen başarıyla güncellendi.");
    }

    private void deleteComponent() {
        Long id = readLong("Silinecek Bileşen ID: ");
        componentService.deleteById(id);
        System.out.println("Bileşen başarıyla silindi.");
    }

    private void searchComponentMenu() {
        while (true) {
            System.out.println("\n--- Bileşen Arama ---");
            System.out.println("1- İsme Göre Ara");
            System.out.println("2- Tipe Göre Ara");
            System.out.println("3- PC'ye Göre Ara");
            System.out.println("0- Önceki Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: searchByName(); break;
                case 2: searchByType(); break;
                case 3: searchByPc(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void searchByName() {
        String name = readString("Bileşen Adı: ");
        componentService.findByName(name).forEach(System.out::println);
    }

    private void searchByType() {
        System.out.println("Bileşen Tipleri:");
        for (EComponentType type : EComponentType.values()) {
            System.out.println(type.ordinal() + "- " + type);
        }
        int typeOrdinal = readInt("Aranacak Bileşen Tipi numarası: ");
        EComponentType type = EComponentType.values()[typeOrdinal];
        componentService.findByType(type).forEach(System.out::println);
    }

    private void searchByPc() {
        Long pcId = readLong("Bilgisayar ID: ");
        componentService.findByPcId(pcId).forEach(System.out::println);
    }

    private void showReportsMenu() {
        while (true) {
            System.out.println("\n--- Bileşen Raporları ---");
            System.out.println("1- Bileşene Sahip PC'leri Listele");
            System.out.println("2- En Çok Kullanılan Bileşen Tipi");
            System.out.println("3- PC Başına Ortalama Bileşen Sayısı");
            System.out.println("4- Bileşen Tipi Dağılımı");
            System.out.println("0- Önceki Menüye Dön");
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1: listPcsByComponent(); break;
                case 2: showMostUsedComponentType(); break;
                case 3: showAverageComponentsPerPc(); break;
                case 4: showComponentDistribution(); break;
                case 0: return;
                default: System.out.println("Geçersiz seçim.");
            }
        }
    }

    void listPcsByComponent() {
        String componentName = readString("Bileşen Adı: ");
        componentService.findPcsByComponentName(componentName)
                .forEach(System.out::println);
    }

    void showMostUsedComponentType() {
        System.out.println("En çok kullanılan bileşen tipi: " + componentService.mostUsedComponentType());
    }

    private void showAverageComponentsPerPc() {
        System.out.println("PC başına ortalama bileşen sayısı: " + componentService.getAverageComponentsPerPc());
    }

    private void showComponentDistribution() {
        List<String> distributionList =  componentService.getComponentTypeDistribution();
        distributionList.forEach(System.out::println);
    }
}

