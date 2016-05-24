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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "posicao_onibus")
public class PosicaoOnibus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_posicao_onibus")
    private Long idPosicaoOnibus;
    @Basic(optional = false)
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "data")
    private String data;
    @JoinColumn(name = "id_linha", referencedColumnName = "id_linhas")
    @ManyToOne(optional = false)
    private Linhas idLinha;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Usuario email;

    public PosicaoOnibus() {
    }

    public PosicaoOnibus(Long idPosicaoOnibus) {
        this.idPosicaoOnibus = idPosicaoOnibus;
    }

    public PosicaoOnibus(Long idPosicaoOnibus, double latitude, double longitude, String data) {
        this.idPosicaoOnibus = idPosicaoOnibus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.data = data;
    }

    public Long getIdPosicaoOnibus() {
        return idPosicaoOnibus;
    }

    public void setIdPosicaoOnibus(Long idPosicaoOnibus) {
        this.idPosicaoOnibus = idPosicaoOnibus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Linhas getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Linhas idLinha) {
        this.idLinha = idLinha;
    }

    public Usuario getEmail() {
        return email;
    }

    public void setEmail(Usuario email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPosicaoOnibus != null ? idPosicaoOnibus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicaoOnibus)) {
            return false;
        }
        PosicaoOnibus other = (PosicaoOnibus) object;
        if ((this.idPosicaoOnibus == null && other.idPosicaoOnibus != null) || (this.idPosicaoOnibus != null && !this.idPosicaoOnibus.equals(other.idPosicaoOnibus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return data.toString();
    }
    
}