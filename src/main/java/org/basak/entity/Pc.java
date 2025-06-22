package org.basak.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Entity
@Table(name = "tbl_pc")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Pc extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id",nullable = false)
    Long userId;
    @Column(name ="pc_name",nullable = false )
    String pcName;


}
