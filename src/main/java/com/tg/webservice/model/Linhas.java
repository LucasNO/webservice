package com.tg.webservice.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "linhas")
public class Linhas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linhas")
    private Long idLinhas;
    @Basic(optional = false)
    @Column(name = "nome_linha")
    private String nomeLinha;
    @Basic(optional = false)
    @Column(name = "numero_linha")
    private int numeroLinha;
    @JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade")
    @ManyToOne(optional = false)
    private Cidade idCidade;

    public Linhas() {
    }

    public Linhas(Long idLinhas) {
        this.idLinhas = idLinhas;
    }

    public Linhas(Long idLinhas, String nomeLinha, int numeroLinha) {
        this.idLinhas = idLinhas;
        this.nomeLinha = nomeLinha;
        this.numeroLinha = numeroLinha;
    }

    public Long getIdLinhas() {
        return idLinhas;
    }

    public void setIdLinhas(Long idLinhas) {
        this.idLinhas = idLinhas;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    public int getNumeroLinha() {
        return numeroLinha;
    }

    public void setNumeroLinha(int numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    public Cidade getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Cidade idCidade) {
        this.idCidade = idCidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinhas != null ? idLinhas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linhas)) {
            return false;
        }
        Linhas other = (Linhas) object;
        if ((this.idLinhas == null && other.idLinhas != null) || (this.idLinhas != null && !this.idLinhas.equals(other.idLinhas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Linhas[ idLinhas=" + idLinhas + " ]";
    }
    
}