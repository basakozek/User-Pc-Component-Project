package org.basak;

import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.entity.User;
import org.basak.repository.ComponentRepository;
import org.basak.repository.PcRepository;
import org.basak.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepo= new UserRepository();
        User user1= User.builder().name("Mert").address("İstanbul").phone("343434").age(23).build();
        User user2= User.builder().name("Yunus").address("Ankara").phone("060606").age(27).build();
        User user3= User.builder().name("Başak").address("Ankara").phone("060607").age(24).build();
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        //userRepo.findAll().forEach(System.out::println);
        PcRepository pcRepo= new PcRepository();
        Pc pc1= Pc.builder().userId(1L).pcName("Mrt").build();
        Pc pc2= Pc.builder().userId(2L).pcName("Yns").build();
        Pc pc3= Pc.builder().userId(3L).pcName("Bşk").build();
        pcRepo.save(pc1);
        pcRepo.save(pc2);
        pcRepo.save(pc3);

        ComponentRepository cRepo= new ComponentRepository();
        Component c1= Component.builder().pcId(1L).name("Intel").type(EComponentType.CPU).build();
        Component c2= Component.builder().pcId(2L).name("AMD").type(EComponentType.CPU).build();
        Component c3= Component.builder().pcId(3L).name("Kingston 16GB").type(EComponentType.RAM).build();
        Component c4= Component.builder().pcId(1L).name("ASUS").type(EComponentType.MAINBOARD).build();
        Component c5= Component.builder().pcId(3L).name("Samsung SSD").type(EComponentType.STORAGE).build();

        cRepo.save(c1);
        cRepo.save(c2);
        cRepo.save(c3);
        cRepo.save(c4);

        /*userRepo.findByName("Mert").forEach(System.out::println);
        userRepo.findByAddress("Ankara").forEach(System.out::println);
        userRepo.findByAgeRange(25,30).forEach(System.out::println);
        userRepo.findUsersByComponentType(EComponentType.CPU).forEach(System.out::println);
        userRepo.findAllOrderByPcCount().forEach(System.out::println);

        pcRepo.findByUserId(1L).forEach(System.out::println);
        pcRepo.findByPcName("Mrt").forEach(System.out::println);
        System.out.println(pcRepo.countByUserId(1L));

         */


        cRepo.findByTypeNative(EComponentType.CPU).forEach(System.out::println);
        cRepo.findByPcIdNative(3L).forEach(System.out::println);
        cRepo.findByComponentName("Intel").forEach(System.out::println);
        System.out.println("En çok kullanılan tip: "+cRepo.findMostUsedType());

    }
}