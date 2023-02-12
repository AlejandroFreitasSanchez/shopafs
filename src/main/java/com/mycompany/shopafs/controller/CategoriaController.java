/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopafs.controller;

import com.mycompany.shopafs.model.Categoria;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
@ApplicationScoped
public class CategoriaController extends AbstractController<Categoria> {
    //introduce un objeto de tiendacontroller en la clase 
    //(también la aplicación)
    //no siendo necesario crear uno nuevocon “new”
    @Inject
    TiendaController tiendacontroller;
    public CategoriaController() {
        super(Categoria::new);
        //this.load();
    }
    //se ejecuta al crear un objeto de la clase
    @Override
    @PostConstruct
    public void load() {
        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(-1);
        this.getSelected().setNombre("Alimentos");
        this.add();

        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(-2);
        this.getSelected().setNombre("Muebles");
        this.add();

        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(3);
        this.getSelected().setNombre("Ropa");
        this.add();
    }

    //si no hay ninguna tienda con la categoria, se elimina
    public String remove() {
        if (this.getSelected() != null) {
            if (this.tiendacontroller.getItems().stream().filter(item -> {
                return item.getCategoria()== this.getSelected();
            }).count() == 0) {
                this.repositorio.remove(this.getSelected());
                return "remove";
            } else {
                return "";
            }

        }
        //se tiene que poner el error
        return "";

    }
    //devuelve el string edit, para poder enviarlo al jsf con el formulario a partir del archivo faces-config
    @Override
    public String preEdit() {

        return "edit";
    }
    //añade una nueva categoria
    @Override
    public String add() {
        //si es nuevo
        if (this.getSelected().getId() == -1) {
            this.getSelected().setId(this.repositorio.getAll().size() + 1);
            this.repositorio.create(this.getSelected());
        } else {
            this.repositorio.update(this.getSelected());
            //si ya existe
    
        }
        return "sucess";
    }
}