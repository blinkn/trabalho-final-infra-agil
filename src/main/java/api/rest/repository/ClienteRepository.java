package api.rest.repository;

import api.rest.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String nome);

	@Query(value =
			"SELECT * " +
					"  FROM Cliente c " +
					"  JOIN Endereco e " +
					"    ON e.id = c.Endereco_id" +
					" WHERE e.rua LIKE %:endereco%" +
					"    OR e.cidade LIKE %:endereco%" +
					"    OR e.estado LIKE %:endereco%",
			nativeQuery = true)
	List<Cliente> findByEnderecoContaining(@Param("endereco") String endereco);
	
}
