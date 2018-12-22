package api.rest.models.hateoas;

import api.rest.models.Endereco;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class EnderecoResource extends Resource<Endereco> {

	public EnderecoResource(Endereco Endereco, Link... links) {
		super(Endereco, links);
	}
	
}