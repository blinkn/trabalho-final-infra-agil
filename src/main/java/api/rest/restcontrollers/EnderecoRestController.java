package api.rest.restcontrollers;

    import api.rest.models.Endereco;
import api.rest.repository.EnderecoRepository;
import api.rest.models.hateoas.EnderecoResource;
import api.rest.models.hateoas.EnderecoResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoRestController {

    @Autowired
    EnderecoRepository repo;

    EnderecoResourceAssembler assembler = new EnderecoResourceAssembler();

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<EnderecoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repo.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResource> get(@PathVariable Long id) {
        Endereco Endereco = repo.findOne(id);
        if (Endereco != null) {
            return new ResponseEntity<>(assembler.toResource(Endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EnderecoResource> create(@RequestBody Endereco Endereco) {
        Endereco = repo.save(Endereco);
        if (Endereco != null) {
            return new ResponseEntity<>(assembler.toResource(Endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResource> update(@PathVariable Long id, @RequestBody Endereco Endereco) {
        if (Endereco != null) {
            Endereco.setId(id);
            Endereco = repo.save(Endereco);
            return new ResponseEntity<>(assembler.toResource(Endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResource> delete(@PathVariable Long id) {
        Endereco Endereco = repo.findOne(id);
        if (Endereco != null) {
            repo.delete(Endereco);
            return new ResponseEntity<>(assembler.toResource(Endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
