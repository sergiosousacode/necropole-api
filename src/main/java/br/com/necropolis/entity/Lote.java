package br.com.necropolis.entity;

import br.com.necropolis.entity.base.BaseEntity;
import br.com.necropolis.enums.StatusLote;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lotes")
public class Lote extends BaseEntity {
    
    @Column(nullable = false, length = 50)
    private String numero;
    
    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer capacidade = 3;

    @Enumerated
    @Column(nullable = false)
    private StatusLote status = StatusLote.LIVRE;
    

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quadra_id", nullable = false)
    private Quadra quadra;

    public Lote(){
    }

    public String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade){
        this.capacidade = capacidade;
    }

    public StatusLote getStatus(){
        return status;
    }

    public void setStatus(StatusLote status){
        this.status = status;
    }

    public Boolean getAtivo(){
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }

    public Quadra getQuadra(){
        return quadra;
    }
    
    public void setQuadra(Quadra quadra){
        this.quadra = quadra;
    }
    
}
