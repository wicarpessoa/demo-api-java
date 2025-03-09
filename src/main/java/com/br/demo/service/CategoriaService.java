package com.br.demo.service;

import com.br.demo.dto.request.CategoriaRequestDTO;
import com.br.demo.dto.response.CategoriaResponseDTO;
import com.br.demo.model.Categoria;
import com.br.demo.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponseDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaResponseDTO(c.getId(), c.getNome(), c.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO requestDTO){

        Categoria novaCategoria = new Categoria(null, requestDTO.getNome(), requestDTO.getDescricao());
        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);
        return new CategoriaResponseDTO(categoriaSalva.getId(), categoriaSalva.getNome(), categoriaSalva.getDescricao());
    }

    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO requestDTO){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        categoriaExistente.setNome(requestDTO.getNome());
        categoriaExistente.setDescricao(requestDTO.getDescricao());

        Categoria categoriaAtualizada = categoriaRepository.update(categoriaExistente);
        return new CategoriaResponseDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome(), categoriaAtualizada.getDescricao());

    }

    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
