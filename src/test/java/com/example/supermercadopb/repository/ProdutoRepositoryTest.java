package com.example.supermercadopb.repository;

import com.example.supermercadopb.entity.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testSaveProduto() {
        Produto produto = new Produto();
        produto.setNome("Arroz");
        produto.setPreco(30.0);

        Produto savedProduto = produtoRepository.save(produto);

        assertThat(savedProduto.getId()).isNotNull();
    }
    @Test
    public void testSaveProduto1() {
        Produto produto = new Produto();
        produto.setNome("Feijão");
        produto.setPreco(5.0);

        Produto savedProduto = produtoRepository.save(produto);

        assertThat(savedProduto.getId()).isNotNull();
    }
    @Test
    public void testSaveProduto2() {
        Produto produto = new Produto();
        produto.setNome("Macarrão");
        produto.setPreco(80.0);

        Produto savedProduto = produtoRepository.save(produto);

        assertThat(savedProduto.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        Produto produto = new Produto();
        produto.setNome("Feijão");
        produto.setPreco(5.0);
        Produto savedProduto = produtoRepository.save(produto);

        Optional<Produto> fetchedProduto = produtoRepository.findById(savedProduto.getId());

        assertThat(fetchedProduto.isPresent()).isTrue();
        assertThat(fetchedProduto.get().getNome()).isEqualTo("Feijão");
    }

    @Test
    public void testDeleteProduto() {
        Produto produto = new Produto();
        produto.setNome("Macarrão");
        produto.setPreco(3.5);
        Produto savedProduto = produtoRepository.save(produto);

        produtoRepository.delete(savedProduto);

        Optional<Produto> deletedProduto = produtoRepository.findById(savedProduto.getId());

        assertThat(deletedProduto.isEmpty()).isTrue();
    }
}
