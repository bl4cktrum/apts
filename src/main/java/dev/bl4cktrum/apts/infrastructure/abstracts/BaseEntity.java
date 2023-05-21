package dev.bl4cktrum.apts.infrastructure.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private Date created_at;
    @JsonIgnore
    private Date updated_at;
    @JsonIgnore
    private Date deleted_at;

    @PrePersist
    public void onCreate(){
        this.created_at = new Date();
    }
    @PreUpdate
    public void onUpdate(){
        this.updated_at = new Date();
    }
    @PreRemove
    public void onDelete(){
        this.deleted_at = new Date();
    }
}
