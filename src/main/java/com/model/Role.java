package com.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.lang.annotation.Annotation;

@Entity
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameRole;
    private Boolean isActive;

    @Override
    public String getAuthority() {
        return nameRole;
    }
}
