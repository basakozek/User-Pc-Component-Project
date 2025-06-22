package org.basak.service;
import org.basak.dto.UserPcCountDTO;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.entity.User;
import org.basak.repository.UserRepository;
import java.util.List;
import java.util.Optional;

public class UserService extends BaseService<User, Long> {

    private final UserRepository userRepository;
    private final PcService pcService;
    private final ComponentService componentService;

    public UserService(UserRepository userRepository, PcService pcService, ComponentService componentService) {
        super(userRepository);
        this.userRepository = userRepository;

        this.pcService = pcService;
        this.componentService = componentService;
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findByAddress(String address) {
        return userRepository.findByAddress(address);
    }

    public List<User> findByAgeRange(int minAge, int maxAge) {
        return userRepository.findByAgeRange(minAge, maxAge);
    }

    public List<User> findUsersByComponentType(EComponentType type) {
        return userRepository.findUsersByComponentType(type);
    }

    public List<UserPcCountDTO> findAllOrderByPcCount() {
        return userRepository.findAllOrderByPcCount();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
