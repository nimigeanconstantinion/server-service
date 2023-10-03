package com.example.nserver.web;

import com.example.nserver.intercom.Command.CommandAdapter;
import com.example.nserver.intercom.Query.QueryAdapter;
import com.example.nserver.model.MapStocOpt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/server")
@CrossOrigin
@Slf4j
public class ServerController {
    private QueryAdapter queryAdapter;
    private CommandAdapter commandAdapter;


    public ServerController(QueryAdapter queryAdapter,CommandAdapter commandAdapter) {
        this.queryAdapter = queryAdapter;
        this.commandAdapter = commandAdapter;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/qallmap")
    public ResponseEntity<List<MapStocOpt>> queryAllMap(){
        try{
            List<MapStocOpt> response=queryAdapter.queryAllMap();
            log.info("Am reusit fetch din bd mapstoc",response.size());
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            log.info("Eroare de fetch lista mapstoc!");
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comallmap")
    public ResponseEntity<List<MapStocOpt>> comAllMap(){
        try{
            List<MapStocOpt> response=commandAdapter.getComAll();
            log.info("Am reusit fetch din bd mapstoc lucru",response.size());
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            log.info("Eroare de fetch lista mapstoc din serv lucru!");
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mapbyid/{idProd}")
    public ResponseEntity<MapStocOpt> queryByIdProd(@PathVariable String idProd){
        try{
            MapStocOpt mp=queryAdapter.queryByIdProd(idProd);
            return ResponseEntity.ok(mp);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addmap")
    public ResponseEntity<MapStocOpt> addMapStocOpt(@RequestBody MapStocOpt mp){
        try{
            MapStocOpt mpp=commandAdapter.addMapStoc(mp);
            return ResponseEntity.ok(mpp);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addbulk")
    public ResponseEntity<Boolean> addBulkMapStocOpt(@RequestBody List<MapStocOpt> mp){
        try{
            boolean mpp=commandAdapter.addBulkMapStoc(mp);
            return ResponseEntity.ok(mpp);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/del/{idP}")
    public ResponseEntity<Boolean> delMapByID(@PathVariable String idP){
        return ResponseEntity.ok(commandAdapter.deleteMapStoc(idP));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/upd")
    public ResponseEntity<MapStocOpt> updateMapStoc(@RequestBody MapStocOpt uMap){
        return ResponseEntity.ok(commandAdapter.updMap(uMap));
    }
}
