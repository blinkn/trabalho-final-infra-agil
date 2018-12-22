package api.rest.repository;

import api.rest.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContaining(String nome);

    List<Produto> findByMarcaContaining(String marca);

    @Query("SELECT a FROM Produto a WHERE to_date(a.dataCriacao, 'dd-MM-yyyy') = to_date(:data, 'dd-MM-yyyy')")
    List<Produto> findByDataCriacao(@Param("data") String data);

}
