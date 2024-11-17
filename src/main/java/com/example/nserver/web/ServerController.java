package com.example.nserver.web;

import com.example.nserver.dto.UserDTO;
import com.example.nserver.intercom.Command.CommandAdapter;
import com.example.nserver.intercom.Query.QueryAdapter;
import com.example.nserver.jwt.JWTTokenProvider;
import com.example.nserver.model.MapStocOpt;
import com.example.nserver.model.User;
import com.example.nserver.rabbitMqProducer.MessagePublisher;
import com.example.nserver.rabbitMqProducer.MyMessage;
import com.example.nserver.security.UserRole;
import com.example.nserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/server")
@Slf4j
public class ServerController {
    private QueryAdapter queryAdapter;
    private CommandAdapter commandAdapter;
//    private SecurityAdapter securityAdapter;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private MessagePublisher messagePublisher;
    public ServerController(QueryAdapter queryAdapter,
                            CommandAdapter commandAdapter,UserService userService
    ,JWTTokenProvider jwtTokenProvider,AuthenticationManager authenticationManager,
                            BCryptPasswordEncoder bCryptPasswordEncoder,MessagePublisher messagePublisher) {
        this.queryAdapter = queryAdapter;
        this.commandAdapter = commandAdapter;
//        this.securityAdapter=securityAdapter;
        this.userService=userService;
        this.jwtTokenProvider=jwtTokenProvider;
        this.authenticationManager=authenticationManager;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.messagePublisher=messagePublisher;
    }


    @Tag(name = "Query-service",description = "query controller - get all External Products")
    @Operation(summary = "fetch data from external DB",description = "${springdoc.api-docs.query-serv.notes}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) })})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/qallmap")
    public ResponseEntity<List<MapStocOpt>> queryAllMap(){
        try{


            List<MapStocOpt> response=queryAdapter.queryAllMap();
            log.info("Am reusit fetch din bd mapstoc",response.size());
            System.out.println("Am obtinut raspuns din query ");
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            log.info("Eroare de fetch lista mapstoc!");
            throw e;
        }
    }

//
//    @Tag(name = "Group 2", description = "Endpoints Group 2")
//
//    @Tag(name ="${api.command-service.get-all-command-mapping-products.name}")
////            description = "${api.command-service.get-all-command-mapping-products.description}")
//    @Operation(
//        summary = "sulkslklkwe;;l;"
//    )
@Tag(name = "Command-service")

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
//
//    @Tag(name = "Group 2", description = "Endpoints Group 2")
//
//    @Tag(name ="get-by-id",
//           description = "descreire by id")
//    @Operation(
//            summary = "rezumat"
//    )
    @Tag(name = "Command-service")
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

    @Tag(name = "Command-service")

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addmap")
    public ResponseEntity<MapStocOpt> addMapStocOpt(@RequestBody MapStocOpt mp){
        try{

            MapStocOpt mpp=commandAdapter.addMapStoc(mp);
            log.info("_ADDED:"+mp.toString());
            return ResponseEntity.ok(mpp);

        }catch (RuntimeException e){
            throw e;
        }
    }
    @Tag(name = "Command-service")

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
    @Tag(name = "Command-service")

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/del/{idP}")
    public ResponseEntity<Boolean> delMapByID(@PathVariable String idP){
        MyMessage myMessage=new MyMessage();
        myMessage.setPriority(1);
        myMessage.setContent(idP);
        myMessage.setMessage("DEL_PROD_ID");
        log.info("DELPROD_ANTE");
        boolean status=messagePublisher.sendMessage(myMessage);

        log.info("DELPROD_AFTER "+status);
        if(status==true){
//            return ResponseEntity.ok(commandAdapter.deleteMapStoc(idP));
              return ResponseEntity.ok(true);
        }else{
              return ResponseEntity.badRequest().body(false);
        }
    }
    @Operation(tags = "Command-service",summary = "update product with body content",
            description = "${}")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/upd")
    public ResponseEntity<MapStocOpt> updateMapStoc(@RequestBody MapStocOpt uMap){

        try{
            MapStocOpt mpp=commandAdapter.updMap(uMap);
            return ResponseEntity.ok(mpp);
        }catch (RuntimeException e){
            throw e;
        }
    }

//    @CrossOrigin(origins = {"http://localhost:3000"})

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/direct")
    public ResponseEntity<List<MapStocOpt>> directqueryAllMap(){
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
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO user){
        log.info("2LOGIN with:"+user.getEmail());
        User usr=userService.getUserFromEmail(user.getEmail());

        if(usr!=null){

            if(bCryptPasswordEncoder.matches(user.getPassword(),usr.getPassword())){
                String tkn=jwtTokenProvider.generateJWTToken(usr);
                UserDTO usrDT=userService.userToDTO(usr);
                usrDT.setToken(tkn);
                log.info("serverLoginSucces: "+usr.getEmail());
                return new ResponseEntity<>(usrDT,HttpStatus.OK);
            }else{
                log.error("serverLoginFailPassword: "+usr.getEmail());
                throw new RuntimeException("Password did not match , retry!!");
            }

        }else{
            log.error("serverLoginNoUser: "+user.getEmail());

            throw new RuntimeException("User din not exists !! Please Sign-up!!");


        }

    }
//    @CrossOrigin(origins = {"*"})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    public ResponseEntity<String> signup(@RequestBody User user){

        User usr=userService.getUserFromEmail(user.getEmail());

        if(usr==null){
            User newUser=new User();
            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setRole(UserRole.USER);
            log.info("serverRegisterSucces: "+user.getEmail());

            //            newUser.setRole();
            newUser.setName(user.getName());
            userService.addUser(newUser);

            return new ResponseEntity<>(jwtTokenProvider.generateJWTToken(newUser),HttpStatus.OK);


        }else{
            log.error("serverRegisterFail: "+usr.getEmail());

            throw new RuntimeException("You can not sign-up !! User is already signed!!");


        }

    }
}
