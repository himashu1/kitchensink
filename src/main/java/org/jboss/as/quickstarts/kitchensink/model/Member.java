package org.jboss.as.quickstarts.kitchensink.model;

import jakarta.persistence.*;

import org.springframework.lang.NonNull;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
//    @Pattern  -
    private String name;

    @NonNull
    private String email;

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
