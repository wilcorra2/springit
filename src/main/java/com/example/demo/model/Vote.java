package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Vote extends Auditable {
    @Id
    @GeneratedValue
    private Long id;
    //private User user;

    @NonNull
    private short direction;

    @NonNull
    @ManyToOne
    private Link link;


}
