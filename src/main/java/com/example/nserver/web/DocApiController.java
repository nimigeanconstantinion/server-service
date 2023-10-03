package com.example.nserver.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/server/api-docs")
public class DocApiController {

    @GetMapping("/hello")
    @Operation(summary = "Returnează Entry on Api-Doc")
    @ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "text/plain"))
    public String sayHello() {
        return "Documentatieeeeeee";
    }
}
