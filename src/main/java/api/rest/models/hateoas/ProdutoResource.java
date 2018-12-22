package api.rest.models.hateoas;

import api.rest.models.Produto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ProdutoResource extends Resource<Produto> {

	public ProdutoResource(Produto Produto, Link... links) {
		super(Produto, links);
	}
	
}