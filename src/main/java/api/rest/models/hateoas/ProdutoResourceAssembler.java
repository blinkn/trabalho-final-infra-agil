package api.rest.models.hateoas;

import api.rest.restcontrollers.ProdutoRestController;
import api.rest.models.Produto;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProdutoResourceAssembler extends ResourceAssemblerSupport<Produto, ProdutoResource> {

	public ProdutoResourceAssembler() {
		super(Produto.class, ProdutoResource.class);
	}

	@Override
	public ProdutoResource toResource(Produto Produto) {
		return new ProdutoResource(Produto, ControllerLinkBuilder.linkTo(methodOn(ProdutoRestController.class).get(Produto.getId())).withSelfRel());
	}
	
	@Override
	protected ProdutoResource instantiateResource(Produto Produto) {
		return new ProdutoResource(Produto);
	}

}