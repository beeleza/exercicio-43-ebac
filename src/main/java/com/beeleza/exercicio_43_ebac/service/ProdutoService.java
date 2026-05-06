package com.beeleza.exercicio_43_ebac.service;

import com.beeleza.exercicio_43_ebac.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProdutoService {

    private final ConcurrentHashMap<Integer, Produto> produtos = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public Produto criar(Produto produto) {
        int id = idGenerator.getAndIncrement();
        produto.setId(id);
        produtos.put(id, produto);
        return produto;
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }

    public Optional<Produto> buscarPorId(int id) {
        return Optional.ofNullable(produtos.get(id));
    }

    public Optional<Produto> atualizar(int id, Produto produtoAtualizado) {
        if (!produtos.containsKey(id)) {
            return Optional.empty();
        }
        produtoAtualizado.setId(id);
        produtos.put(id, produtoAtualizado);
        return Optional.of(produtoAtualizado);
    }

    public boolean deletar(int id) {
        return produtos.remove(id) != null;
    }
}
