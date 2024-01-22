package com.example.nserver.intercom.Query;

import com.example.nserver.model.MapStocOpt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@Value("${token.jwt.secretKey}")

//@FeignClient(name = "query-service",url = "http://localhost:8082/api/v1/query")
@FeignClient(name = "query-service",url = "http://"+"${param.query-service}"+":8082/api/v1/query")

public interface QueryClient {
    @GetMapping("")
    ResponseEntity<List<MapStocOpt>> queryAll();

    @GetMapping("/byid/{idP}")
    ResponseEntity<MapStocOpt> getByIdProd(@PathVariable String idP);
}
