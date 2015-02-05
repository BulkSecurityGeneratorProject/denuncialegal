package br.com.denuncialegal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Jurisdicionado.
 */
@Entity
@Table(name = "T_JURISDICIONADO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Jurisdicionado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_jurisdicionado")
    private Integer tipoJurisdicionado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipoJurisdicionado() {
        return tipoJurisdicionado;
    }

    public void setTipoJurisdicionado(Integer tipoJurisdicionado) {
        this.tipoJurisdicionado = tipoJurisdicionado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Jurisdicionado jurisdicionado = (Jurisdicionado) o;

        if (id != null ? !id.equals(jurisdicionado.id) : jurisdicionado.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Jurisdicionado{" +
                "id=" + id +
                ", nome='" + nome + "'" +
                ", tipoJurisdicionado='" + tipoJurisdicionado + "'" +
                '}';
    }
}
