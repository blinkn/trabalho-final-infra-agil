package api.rest.models.hateoas;

import api.rest.models.Cliente;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ClienteResource extends Resource<Cliente> {

	public ClienteResource(Cliente cliente, Link... links) {
		super(cliente, links);
	}
	
}