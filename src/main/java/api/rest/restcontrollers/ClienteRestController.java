package api.rest.restcontrollers;

import api.rest.repository.ClienteRepository;
import api.rest.repository.EnderecoRepository;
import api.rest.models.hateoas.ClienteResource;
import api.rest.models.hateoas.ClienteResourceAssembler;
import api.rest.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    ClienteRepository repo;

    @Autowired
    EnderecoRepository repoEndereco;

    ClienteResourceAssembler assembler = new ClienteResourceAssembler();

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ClienteResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repo.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
        Cliente cliente = repo.findOne(id);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteResource> create(@RequestBody Cliente cliente) {
        cliente = repo.save(cliente);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResource> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (cliente != null) {
            cliente.setId(id);
            cliente = repo.save(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResource> delete(@PathVariable Long id) {
        Cliente cliente = repo.findOne(id);
        if (cliente != null) {
            repo.delete(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repo.findByNomeContaining(nome)), HttpStatus.OK);
    }

    @GetMapping("/endereco/{endereco}")
    public ResponseEntity<List<ClienteResource>> findByEndereco(@PathVariable String endereco) {
        return new ResponseEntity<>(assembler.toResources(repo.findByEnderecoContaining(endereco)), HttpStatus.OK);
    }
}
