package api.rest.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nome;
    String descricao;
    String marca;
    String valor;
    String dataCriacao;

    public Produto(String nome, String descricao, String marca, String valor, String dataCriacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }

}