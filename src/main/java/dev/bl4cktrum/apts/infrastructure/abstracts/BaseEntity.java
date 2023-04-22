package dev.bl4cktrum.apts.infrastructure.abstracts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = true)
    public String id;

    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    @PrePersist
    public void updateCreatedAt(){
        this.created_at = new Date();
    }
    @PreUpdate
    public void updateUpdatedAt(){
        this.updated_at = new Date();
    }
    @PreRemove
    public void updateDeletedAt(){
        this.deleted_at = new Date();
    }
}
