package com.example.nserver.system;

import com.example.nserver.intercom.Query.QueryAdapter;
import com.example.nserver.intercom.Query.QueryClient;
import com.example.nserver.model.MapStocOpt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyCommandLiner implements CommandLineRunner {

    @Autowired
    QueryAdapter queryAdapter;

    @Override
    public void run(String... args) throws Exception {
//       ResponseEntity<List<MapStocOpt>> lista= queryAdapter.queryAllMap();
//        System.out.println(lista.getBody());
    }
}
