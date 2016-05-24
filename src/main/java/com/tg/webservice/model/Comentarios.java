package com.tg.webservice.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "comentarios")
public class Comentarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentarios")
    private Long idComentarios;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Lob
    @Column(name = "data")
    private String data;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "id_linha", referencedColumnName = "id_linhas")
    @ManyToOne(optional = false)
    private Linhas idLinha;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Usuario email;

    public Comentarios() {
    }

    public Comentarios(Long idComentarios) {
        this.idComentarios = idComentarios;
    }

    public Comentarios(Long idComentarios, String descricao, String data,
			String titulo, Linhas idLinha, Usuario email) {
		this.idComentarios = idComentarios;
		this.descricao = descricao;
		this.data = data;
		this.titulo = titulo;
		this.idLinha = idLinha;
		this.email = email;
	}

	public Long getIdComentarios() {
		return idComentarios;
	}

	public void setIdComentarios(Long idComentarios) {
		this.idComentarios = idComentarios;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
        hash += (idComentarios != null ? idComentarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if ((this.idComentarios == null && other.idComentarios != null) || (this.idComentarios != null && !this.idComentarios.equals(other.idComentarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Comentarios[ idComentarios=" + idComentarios + " ]";
    }
    
}