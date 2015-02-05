package br.com.denuncialegal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Joao.
 */
@Entity
@Table(name = "T_JOAO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Joao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "sexualidade")
    private String sexualidade;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexualidade() {
        return sexualidade;
    }

    public void setSexualidade(String sexualidade) {
        this.sexualidade = sexualidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Joao joao = (Joao) o;

        if (id != null ? !id.equals(joao.id) : joao.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Joao{" +
                "id=" + id +
                ", idade='" + idade + "'" +
                ", sexualidade='" + sexualidade + "'" +
                '}';
    }
}
