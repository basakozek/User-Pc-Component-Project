package org.basak.init;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.entity.User;
import org.basak.service.ComponentService;
import org.basak.service.PcService;
import org.basak.service.UserService;

public class DataInitializer {

    private final UserService userService;
    private final PcService pcService;
    private final ComponentService componentService;

    public DataInitializer(UserService userService, PcService pcService, ComponentService componentService) {
        this.userService = userService;
        this.pcService = pcService;
        this.componentService = componentService;
    }

    public void initData() {
       /* User user1 = User.builder().name("Ali Veli").address("İstanbul").phone("34343434").age(28).build();
        User user2 = User.builder().name("Ayşe Yılmaz").address("Ankara").phone("06060606").age(37).build();
        userService.save(user1);
        userService.save(user2);

        Pc pc1 = Pc.builder().pcName("İş Bilgisayarı").userId(user1.getId()).build();
        Pc pc2 = Pc.builder().pcName("Oyun Bilgisayarı").userId(user1.getId()).build();
        Pc pc3 = Pc.builder().pcName("Ev Bilgisayarı").userId(user2.getId()).build();
        pcService.save(pc1);
        pcService.save(pc2);
        pcService.save(pc3);

        Component component1 = Component.builder().pcId(pc1.getId()).name("Intel i5-12400").type(EComponentType.CPU).build();
        Component component2 = Component.builder().pcId(pc1.getId()).name("Crucial 16GB DDR4").type(EComponentType.RAM).build();
        Component component3 = Component.builder().pcId(pc1.getId()).name("MSI B660M").type(EComponentType.MAINBOARD).build();
        componentService.save(component1);
        componentService.save(component2);
        componentService.save(component3);

        */


    }
}
