package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.CategoriaDTO;
import com.github.cidarosa.ms.produto.entities.Categoria;
import com.github.cidarosa.ms.produto.exeptions.ResourceNotFoundExeption;
import com.github.cidarosa.ms.produto.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAllCategorias(){

        return categoriaRepository.findAll().stream().map(CategoriaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findCategoriaById(Long id){

        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExeption("Recurso não encontrado. ID: "+ id)
        );

        return new CategoriaDTO(categoria);
    }
}
