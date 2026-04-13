package com.training.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "patient_details",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_dob", columnNames = {"first_name", "dob"})
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "DOB must be in the past")
    @Column(nullable = false)
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "Male|Female|Other")
    @Column(nullable = false)
    private String gender;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200)
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false)
    private String email;
}