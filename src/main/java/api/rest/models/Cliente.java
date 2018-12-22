package api.rest.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nome;
    String email;
    String cpf;
    String dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    Endereco Endereco;

    public Cliente(String nome, String email, String cpf, String dataNascimento, Endereco Endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.Endereco = Endereco;
    }

}