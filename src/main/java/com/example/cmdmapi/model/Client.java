package com.example.cmdmapi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
//import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

//    @Column(nullable = false)
//    private String lastName;
//
//    @Column(nullable = false)
//    private Date birth;
//
//    @Column(nullable = false)
//    private String phone;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String address;


    public Client(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;

        return id != null && id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return 1756406093;
    }
}
