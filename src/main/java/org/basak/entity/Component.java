package org.basak.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_component")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Component.findMostUsedType",
        query = "select c.type from Component c group by c.type order by count(c) desc"
)
public class Component extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name= "pc_id",nullable = false)
    Long pcId;
    @Column(nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    EComponentType type;
}
