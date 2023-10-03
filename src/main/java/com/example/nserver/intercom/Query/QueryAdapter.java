package com.example.nserver.intercom.Query;

import com.example.nserver.model.MapStocOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QueryAdapter {

    private QueryClient queryClient;

    public QueryAdapter(QueryClient queryClient) {
        this.queryClient = queryClient;
    }

    public List<MapStocOpt> queryAllMap() {
            ResponseEntity<List<MapStocOpt>> response = queryClient.queryAll();
            if(response.getStatusCode()==HttpStatus.OK&&response.hasBody()){
                return response.getBody();
            }else{
                throw new RuntimeException("Nu am putut extrage datele");
            }
    }

    public MapStocOpt queryByIdProd(String idP) {
          try{
              ResponseEntity<MapStocOpt> mp=queryClient.getByIdProd(idP);
              return mp.getBody();
          }catch (RuntimeException e){
              throw e;
          }

    }
}
