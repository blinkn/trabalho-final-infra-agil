package api.rest;

import api.rest.repository.ClienteRepository;
import api.rest.models.Cliente;
import api.rest.models.Endereco;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ClienteRepository repository;

    @Test
    public void testSaveCliente() throws Exception {
        Cliente cliente = new Cliente(
                "Thiago Hencke",
                "th.hencke@gmail.com",
                "075.656.149-23",
                "02/01/1991",
                new Endereco("Erminio Fronza", "Rio do Sul", "SC", "89.160-548")
        );

        cliente = repository.save(cliente);

        assertNotNull(cliente);
        assertTrue(cliente.getId() != null);
    }

    @Test
    public void testDeleteCliente() throws Exception {
        Cliente cliente = new Cliente(
                "Thiago Hencke",
                "th.hencke@gmail.com",
                "075.656.149-23",
                "02/01/1991",
                new Endereco("Erminio Fronza", "Rio do Sul", "SC", "89.160-548")
        );

        cliente = repository.save(cliente);
        repository.delete(cliente);
        cliente = repository.findOne(cliente.getId());

        assertNull(cliente);
    }

    @Test
    public void testFindByNome() throws Exception {
        List<Cliente> clientes = repository.findByNomeContaining("Claire");
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.get(0).getNome().equals("Claire Farron"));
    }


}
