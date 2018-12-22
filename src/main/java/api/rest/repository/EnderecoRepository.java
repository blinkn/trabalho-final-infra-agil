package api.rest.repository;

import api.rest.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByRuaContaining(String rua);
	
}
