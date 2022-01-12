package com.example.carros;

import com.example.carros.api.carros.Carro;
import com.example.carros.api.carros.CarroDTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest extends BaseAPITest {

    private ResponseEntity<CarroDTo> getCarro(String url) {
        return get(url, CarroDTo.class);
    }

    private ResponseEntity<List<CarroDTo>> getCarros(String url) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<CarroDTo>>() {
                });
    }

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        ResponseEntity response = post("/api/v1/carros", carro, null);
        System.out.println(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);
        CarroDTo c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        delete(location, null);

        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        List<CarroDTo> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {

        ResponseEntity<CarroDTo> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarroDTo c = response.getBody();
        assertNotNull(c);
        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound() {

        ResponseEntity response = getCarro("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}