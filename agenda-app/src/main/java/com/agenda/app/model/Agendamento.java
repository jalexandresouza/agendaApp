package com.agenda.app.model;


import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //construotres
@AllArgsConstructor
@Data  //getseters
@Entity(name = "tb_agendamento")

public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgendamento;

    @Column( name="data", nullable=false)
    private LocalDate data;

    @Column( name="hora", nullable=false)
    private LocalTime hora;

    @JoinColumn(name="idUsuario", nullable=false )
    @OneToOne
    private Usuario  usuario;

   
    @JoinColumn(name="idServico", nullable=false )
    @OneToOne
    private Servico  servico;






    
}

