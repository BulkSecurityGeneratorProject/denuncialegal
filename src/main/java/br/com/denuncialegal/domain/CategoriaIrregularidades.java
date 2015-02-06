package br.com.denuncialegal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A CategoriaIrregularidades.
 */
@Entity
@Table(name = "T_CATEGORIAIRREGULARIDADES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CategoriaIrregularidades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoriaIrregularidades categoriaIrregularidades = (CategoriaIrregularidades) o;

        if (id != null ? !id.equals(categoriaIrregularidades.id) : categoriaIrregularidades.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "CategoriaIrregularidades{" +
                "id=" + id +
                ", nome='" + nome + "'" +
                '}';
    }
}
