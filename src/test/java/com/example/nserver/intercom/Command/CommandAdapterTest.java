package com.example.nserver.intercom.Command;

import com.example.nserver.jwt.JWTTokenProvider;
import com.example.nserver.model.MapStocOpt;
import com.example.nserver.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestPropertySource(
        locations="classpath:application-dev.properties"
)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)

class CommandAdapterTest {

    @Mock
    private CommandClient commandClient;
    @Mock
    private JWTTokenProvider jwtTokenProvider;
    @Captor
    private ArgumentCaptor<MapStocOpt> mapStocOptArgumentCaptor;

    @Test
    void getComAll() {
//                User user=new User();
//        user.setEmail("lksdk@kllkflfk");
//        user.setPassword("klldkklds");
//        System.out.println(jwtTokenProvider.generateJWTToken(user));

        MapStocOpt m1=new MapStocOpt();
        m1.setArticol("test");
        List<MapStocOpt> lista= List.of(m1);
        ResponseEntity<List<MapStocOpt>> responseEntity=ResponseEntity.ok(lista);
        when(commandClient.getAllMapStoc()).thenReturn(responseEntity);
        ResponseEntity<List<MapStocOpt>> result = commandClient.getAllMapStoc();
        assertEquals(1, result.getBody().size());
        assertEquals("test", result.getBody().get(0).getArticol());
//

    }

    @Test
    void addMapStoc() {
//        MapStocOpt m1=new MapStocOpt();
//        m1.setArticol("test");
//        ResponseEntity<MapStocOpt> responseEntity=ResponseEntity.ok(m1);
//        when(commandClient.addMapStoc(m1)).thenReturn(responseEntity);
//
//        ResponseEntity<MapStocOpt> response=commandClient.addMapStoc(m1);
//
//        assertEquals("test",response.getBody().getArticol());
//        assertEquals(true,response.getBody() instanceof MapStocOpt);
//        assertEquals(200,response.getStatusCode().value());
    }

    @Test
    void deleteMapStoc() {
//        MapStocOpt m1=new MapStocOpt();
//        m1.setArticol("test");
//        m1.setIdIntern("idtest");
//        when(commandClient.delMapStoc(m1.getIdIntern())).thenReturn(ResponseEntity.ok(true));
//
//        ResponseEntity<Boolean> responseEntity1=commandClient.delMapStoc(m1.getIdIntern());
//        assertEquals(true,responseEntity1.getBody());
    }

    @Test
    void updMap() {

    }

    @Test
    void addBulkMapStoc() {
    }
}