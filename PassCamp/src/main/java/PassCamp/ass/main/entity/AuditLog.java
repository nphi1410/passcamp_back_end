/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

/**
 *
 * @author AD
 */
import PassCamp.ass.main.constant.ChangeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "AuditLog")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditId")
    private int auditId;

    @Column(name = "tableName")
    private String tableName;
    
    @Column(name = "recordId")
    private String recordId;

    @Enumerated(EnumType.STRING)
    @Column(name = "changeType")
    private ChangeType changeType;

    @Column(name = "changeDate")
    private LocalDateTime changeDate;

    @Column(name = "changeBy")
    private String changeBy;

}
