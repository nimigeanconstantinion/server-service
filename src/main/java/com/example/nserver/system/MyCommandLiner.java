package com.example.nserver.system;

import com.example.nserver.intercom.Query.QueryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
