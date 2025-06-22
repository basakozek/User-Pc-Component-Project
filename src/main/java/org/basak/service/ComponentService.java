package org.basak.service;
import org.basak.entity.Component;
import org.basak.entity.EComponentType;
import org.basak.entity.Pc;
import org.basak.repository.ComponentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComponentService extends BaseService<Component, Long> {
    private final PcService pcService;
    private final ComponentRepository componentRepository;

    public ComponentService(PcService pcService, ComponentRepository componentRepository) {
        super(componentRepository);
        this.pcService = pcService;
        this.componentRepository = componentRepository;
    }


    public List<Component> findByType(EComponentType type) {
        return componentRepository.findByTypeNative(type);
    }

    public List<Component> findByPcId(Long pcId) {
        return componentRepository.findByPcIdNative(pcId);
    }

    public List<Component> findByName(String name) {
        return componentRepository.findByComponentName(name);
    }

    public EComponentType mostUsedComponentType() {
        return componentRepository.findMostUsedType();
    }

    public List<Pc> findPcsByComponentName(String componentName) {
        List<Component> components = componentRepository.findByComponentName(componentName);
        List<Pc> pcs = new ArrayList<>();
        for (Component component : components) {
            Optional<Pc> pcOptional = pcService.findById(component.getPcId());
            pcOptional.ifPresent(pcs::add);
        }
        return pcs;
    }


    public String getAverageComponentsPerPc() {
        List<Component> components = componentRepository.findAll();
        long totalPcs = components.stream()
                .map(Component::getPcId)
                .distinct()
                .count();
        long totalComponents = components.size();
        double average = (double) totalComponents / totalPcs;
        return String.format("%.2f", average);
    }

    public List<String> getComponentTypeDistribution() {
        List<Component> components = componentRepository.findAll();
        Map<EComponentType, Long> distribution = components.stream()
                .collect(Collectors.groupingBy(Component::getType, Collectors.counting()));
        List<String> distributionList = distribution.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
        return distributionList;
    }
}
