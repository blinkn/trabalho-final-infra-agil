package api.rest.restcontrollers;

import api.rest.repository.ProdutoRepository;
import api.rest.models.Produto;
import api.rest.models.hateoas.ProdutoResource;
import api.rest.models.hateoas.ProdutoResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoRestController {

    @Autowired
    ProdutoRepository repo;

    ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProdutoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repo.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
        Produto Produto = repo.findOne(id);
        if (Produto != null) {
            return new ResponseEntity<>(assembler.toResource(Produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoResource> create(@RequestBody Produto Produto) {
        Produto = repo.save(Produto);
        if (Produto != null) {
            return new ResponseEntity<>(assembler.toResource(Produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto Produto) {
        if (Produto != null) {
            Produto.setId(id);
            Produto = repo.save(Produto);
            return new ResponseEntity<>(assembler.toResource(Produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
        Produto Produto = repo.findOne(id);
        if (Produto != null) {
            repo.delete(Produto);
            return new ResponseEntity<>(assembler.toResource(Produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repo.findByNomeContaining(nome)), HttpStatus.OK);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
        return new ResponseEntity<>(assembler.toResources(repo.findByMarcaContaining(marca)), HttpStatus.OK);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<ProdutoResource>> findByDataNascimentoAtMesCorrente(@PathVariable String data) {
        data = data;
        return new ResponseEntity<>(assembler.toResources(repo.findByDataCriacao(data)), HttpStatus.OK);
    }
}
