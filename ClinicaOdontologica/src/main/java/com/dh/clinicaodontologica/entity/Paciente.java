package com.dh.clinicaodontologica.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String cedula;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Domicilio domicilio;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paciente")
    private Set<Turno> turnos;

    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email, Set<Turno> turnos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
        this.turnos = turnos;
    }
}

