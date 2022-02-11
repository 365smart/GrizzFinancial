package com.ou.grizz.financial.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "expenses")
public class Expense implements Serializable { //without Serializable interface the program crashes

    @Id
    @SequenceGenerator(
            name = "expense_sequence",
            sequenceName = "expense_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_sequence"
    )
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    //Many expenses to one user
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
            )
    @JoinColumn(
            name = "user_id", //name of the column that will be added to the expenses table to join users with their expenses.
            referencedColumnName = "id"
    )
    private User user;
    /*
    The @JoinColumn annotation defines the actual physical mapping on the owning side. (usually the many side of the relationship)
    On the other hand, the referencing side is defined using the mappedBy attribute of the @OneToMany annotation. (if we want
    bi-directional relationship of course.
     */
}
