package com.example.nserver.dto;

import com.example.nserver.model.MapStocOpt;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MapStocOptimDTO {
    private MapStocOpt mapStocOpt;

    public MapStocOpt toMapSTocOpt(Object mapst){
        MapStocOpt mp=new MapStocOpt();
        mp=(MapStocOpt) mapst;
        return mp;
    }
}
