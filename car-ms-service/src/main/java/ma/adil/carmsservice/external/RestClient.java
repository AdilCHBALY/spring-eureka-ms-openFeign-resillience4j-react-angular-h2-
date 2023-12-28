package ma.adil.carmsservice.external;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.adil.carmsservice.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient("CLIENT-SERVICE")
public interface RestClient {
        @GetMapping("/api/client")
        @CircuitBreaker(name="clientservice",fallbackMethod = "getDefaultClients")
        List<Client> findAll();
        @GetMapping("/api/client/{id}")
        @CircuitBreaker(name="clientservice",fallbackMethod = "getDefaultClient")
        Client findById(@PathVariable Long id) throws Exception ;

        default Client getDefaultClient(Long id, Exception e){
            return Client.builder()
                    .id(id)
                    .name("NA")
                    .age(0)
                    .build();
        }

        default List<Client> getDefaultClients(Exception e){
            return List.of();
        }
}
