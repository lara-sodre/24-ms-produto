package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.CategoriaDTO;
import com.github.cidarosa.ms.produto.entities.Categoria;
import com.github.cidarosa.ms.produto.exeptions.DataBaseExeption;
import com.github.cidarosa.ms.produto.exeptions.ResourceNotFoundExeption;
import com.github.cidarosa.ms.produto.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteCategoriaById(Long id){

        if(! categoriaRepository.existsById(id)){

            throw new ResourceNotFoundExeption("Recurso não encontrado. ID: "+ id);
        }

        try{
            categoriaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataBaseExeption("Não foi possível excluir a categoria. " + "Existem produtos associados a ela.");
        }

    }
}
