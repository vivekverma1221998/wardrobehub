package com.wardrobehub.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String firstName;

    private String lastName;

    private String password;

    private String email;


    private String role;

    private String mobile;

    //CascadeType.ALL ----> if we want to delete user it will auto delete the list of address
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();


    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation> paymentInformation = new ArrayList<>();


    // mappedBy ---> This avoids creating an additional foreign key column in the user entity
    // and helps JPA understand that the Rating entity should have a reference to the user entity
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> review = new ArrayList<>();


    private LocalDateTime createdAt;
}
