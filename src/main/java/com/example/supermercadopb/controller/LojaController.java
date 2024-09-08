package com.example.supermercadopb.controller;

import com.example.supermercadopb.entity.Produto;
import com.example.supermercadopb.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LojaController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Listar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @GetMapping("/produtos")// mostrar todos produtos
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Obter um produto específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/produtos/{id}")// mostrar produto específico
    public ResponseEntity<Produto> getProdutoById(@PathVariable int id) {
        return produtoService.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary = "Cadastrar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/produtos")// cadastrar novo produto
    public ResponseEntity<String> addProduto(@RequestBody Produto produto) {
        Produto savedProduto = produtoService.addProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso: " + savedProduto.getNome());
    }

    @Operation(summary = "Editar um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/produtos/{id}")// editar produto existente
    public ResponseEntity<String> updateProduto(@PathVariable int id, @RequestBody Produto produto) {
        Produto updatedProduto = produtoService.updateProduto(id, produto);
        if (updatedProduto.getId() == id) {
            return ResponseEntity.ok("Produto atualizado com sucesso: " + updatedProduto.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Novo produto criado com sucesso: " + updatedProduto.getNome());
        }
    }

    @Operation(summary = "Deletar um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/produtos/{id}")// deletar produto existente
    public ResponseEntity<String> deleteProduto(@PathVariable int id) {
        if (produtoService.deleteProduto(id)) {
            return ResponseEntity.noContent().header("Message", "Produto deletado com sucesso.").build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
    }
}