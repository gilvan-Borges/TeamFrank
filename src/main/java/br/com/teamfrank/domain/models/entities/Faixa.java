package br.com.teamfrank.domain.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class Faixa {
    
    @Id
    private UUID id;
    
    @Column(nullable = false)
    private String cor;
    

    @Column(nullable = false)
    private String nivel;

}
