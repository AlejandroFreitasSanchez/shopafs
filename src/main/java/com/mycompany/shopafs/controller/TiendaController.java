/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopafs.controller;

import com.mycompany.shopafs.model.Categoria;
import com.mycompany.shopafs.model.Promocion;
import com.mycompany.shopafs.model.Opcion;
import com.mycompany.shopafs.model.Tienda;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pedro
 */
@Named
@ApplicationScoped
public class TiendaController extends AbstractController<Tienda> {

    @Inject
    private PromocionController promocionController;
    @Inject
    private CategoriaController categoriaController;
    @Inject
    private OpcionController opcionController;

    public TiendaController() {
        super(Tienda::new);
        //this.load();
    }

    @Override
    public Tienda getSelected() {
        return super.getSelected();
    }

    @Override
    @PostConstruct
    public void load() {
        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(-1);
        
        this.getSelected().setNombre("Ikea");
        this.getSelected().setDireccion("Calle san marcos");
        this.getSelected().setDescripcion("Ikea es una empresa nordica de muebles");
        this.getSelected().setCoordenadas("2323d");
        
        this.add();

        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(-2);
        this.getSelected().setNombre("Hiperber");
         this.getSelected().setDireccion("plaza del sol");
        this.getSelected().setDescripcion("Hiperber es una cadena de supermercados de comina y articulos del hogar");
        this.getSelected().setCoordenadas("2323awd");
        this.add();

        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(3);
        this.getSelected().setNombre("Zara");
         this.getSelected().setDireccion("Las habaneras");
        this.getSelected().setDescripcion("Cadena de ropa fast fashion perteneciente a inditex");
        this.getSelected().setCoordenadas("232d3d");
        this.add();
    }

    public String remove() {
        if (this.getSelected() != null) {
            this.repositorio.remove(this.getSelected());
            return "remove";
        } else {
            return "";
        }

    }

    @Override
    public String preEdit() {
        return "edit";
    }

    public void selectedChange(ValueChangeEvent event) {
        this.setSelected((Tienda) event.getNewValue());
    }

    public Tienda getTiendaById(int id) {
        Tienda p = null;
        Optional<Tienda> element = this.getItems().stream().filter(item -> {
            return item.getId() == id;
        }).findFirst();
        if (!element.isEmpty()) {
            p = element.get();
        }
        return p;
    }

    /*public void removeImage(Imagen img){
       this.getSelected().removeImagen(img);
    }*/
    @Override
    public String add() {
        //si es nuevo
        if (this.getSelected() != null) {
            if (this.getSelected().getId() == -1) {
                this.getSelected().setId(this.repositorio.getAll().size() + 1);
                this.repositorio.create(this.getSelected());
            } else {

                this.repositorio.update(this.getSelected());
            }
        }
        return "sucess";
    }
    
}
