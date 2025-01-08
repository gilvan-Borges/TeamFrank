package br.com.teamfrank.domain.models.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Faixa {
    
    @Id
    private UUID id;
    
    @Column(nullable = false)
    private String cor;
    
    private String nivel;
    
    @Column(nullable = false)
    private Date dataAquisicao;

}
