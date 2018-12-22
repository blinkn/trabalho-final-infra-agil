package api.rest.models.hateoas;

import api.rest.restcontrollers.ClienteRestController;
import api.rest.models.Cliente;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClienteResourceAssembler extends ResourceAssemblerSupport<Cliente, ClienteResource> {

	public ClienteResourceAssembler() {
		super(Cliente.class, ClienteResource.class);
	}

	@Override
	public ClienteResource toResource(Cliente cliente) {
		return new ClienteResource(cliente, ControllerLinkBuilder.linkTo(methodOn(ClienteRestController.class).get(cliente.getId())).withSelfRel());
	}
	
	@Override
	protected ClienteResource instantiateResource(Cliente cliente) {
		return new ClienteResource(cliente);
	}

}