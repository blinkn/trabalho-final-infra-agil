package api.rest.models.hateoas;

import api.rest.restcontrollers.EnderecoRestController;
import api.rest.models.Endereco;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EnderecoResourceAssembler extends ResourceAssemblerSupport<Endereco, EnderecoResource> {

	public EnderecoResourceAssembler() {
		super(Endereco.class, EnderecoResource.class);
	}

	@Override
	public EnderecoResource toResource(Endereco Endereco) {
		return new EnderecoResource(Endereco, ControllerLinkBuilder.linkTo(methodOn(EnderecoRestController.class).get(Endereco.getId())).withSelfRel());
	}
	
	@Override
	protected EnderecoResource instantiateResource(Endereco Endereco) {
		return new EnderecoResource(Endereco);
	}

}