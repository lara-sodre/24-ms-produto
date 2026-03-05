package com.github.cidarosa.ms.produto.entities;

import com.github.cidarosa.ms.produto.service.CategoriaService;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") //para gerar apenas
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;


    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)

        private Categoria categoria;


}
