package com.github.cidarosa.ms.produto.service;

import com.github.cidarosa.ms.produto.dto.ProdutoDTO;
import com.github.cidarosa.ms.produto.entities.Categoria;
import com.github.cidarosa.ms.produto.entities.Produto;
import com.github.cidarosa.ms.produto.exeptions.DataBaseExeption;
import com.github.cidarosa.ms.produto.repositories.CategoriaRepository;
import com.github.cidarosa.ms.produto.repositories.ProdutoRepository;
import com.github.cidarosa.ms.produto.exeptions.ResourceNotFoundExeption;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAllProdutos(){

        List<Produto> produtos = produtoRepository.findAll();


        return produtos.stream().map(ProdutoDTO::new).toList();

        //return produtos.stream().map(x -> new ProdutoDTO(x)).toList();
    }



    @Transactional(readOnly = true)
    public ProdutoDTO findProdutoById(Long id){

        Produto produto = produtoRepository.findById(id).orElseThrow(

                () -> new ResourceNotFoundExeption("Recurso não encontrado. ID: "+ id));

        return new ProdutoDTO(produto);
    }



    @Transactional(readOnly = true)
    public ProdutoDTO saveProduto(ProdutoDTO produtoDTO){


        try {
            Produto produto = new Produto();
            copyDtoToProduto(produtoDTO, produto);
            produto = produtoRepository.save(produto);

            return new ProdutoDTO(produto);

        } catch (DataIntegrityViolationException e) {

            throw new DataBaseExeption("Não foi possível salvar o Produto. Categoria inexistente"
            + " (ID: " + produtoDTO.getCategoria().getId() + ") ");
        }
    }



    private void copyDtoToProduto(ProdutoDTO produtoDTO, Produto produto){

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setValor(produtoDTO.getValor());


        Categoria categoria = categoriaRepository.getReferenceById(produtoDTO.getCategoria().getId());

        produto.setCategoria(categoria);

    }



    @Transactional
    public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO){

        try{
            Produto produto = produtoRepository.getReferenceById(id);
            copyDtoToProduto(produtoDTO, produto);
            produto = produtoRepository.save(produto);

            return new ProdutoDTO(produto);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Recurso não encontrado. ID: "+ id);
        }
    }




    @Transactional
    public void deleteProdutoById(Long id){

        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundExeption("Recurso não encontrado. ID: "+ id);

        }

        produtoRepository.deleteById(id);
    }


}////
