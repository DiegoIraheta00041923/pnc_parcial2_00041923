package com.example.demo.controller;

import com.example.demo.domain.request.CreateMagicArticleRequest;
import com.example.demo.domain.response.GeneralResponse;
import com.example.demo.service.MagicArticleServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/artefacts")
@AllArgsConstructor
public class MagicArticleController {

    private MagicArticleServiceImp magicArticleImp;

    @PostMapping
    public ResponseEntity<GeneralResponse> createMagicArticle(@RequestBody CreateMagicArticleRequest request){
        return buildResponse(
                "Magic Article created!",
                HttpStatus.OK,
                magicArticleImp.createMagicArticle(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteArticle(@PathVariable UUID id){
        return buildResponse("Article deleted!",
                HttpStatus.OK,
                magicArticleImp.deleteMagicArticle(id));
    }


    
    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }
}
