/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopafs.controller;

import com.mycompany.shopafs.model.Promocion;
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
public class PromocionController extends AbstractController<Promocion> {
    @Inject
    TiendaController tiendacontroller;
    public PromocionController() {
        super(Promocion::new);
        //this.load();
    }

    @Override
    @PostConstruct
    public void load() {
        this.create();
      
        //this.getSelected().setId(-1);
        this.getSelected().setNombre("50%");
        this.getSelected().setDescripcion("Promocion del 50 por ciento de descuento");
        this.getSelected().setFechaInicio("10 de mayo de 2020");
        this.getSelected().setFechaFin("30 de agosto de 2024");
        this.add();

        this.create();
        
        //this.getSelected().setId(-2);
        this.getSelected().setNombre("10%");
        this.getSelected().setDescripcion("Promocion del 10 por ciento de descuento");
        this.getSelected().setFechaInicio("14 de septiembre de 2022");
        this.getSelected().setFechaFin("2 de enero de 2023");
        this.add();

        this.create();
      
        //this.getSelected().setId(3);
        this.getSelected().setNombre("2 por 1");
        this.getSelected().setDescripcion("Promocion por el precio de 1, te llevas 2");
        this.getSelected().setFechaInicio("8 de mayo de 2021");
        this.getSelected().setFechaFin("23 de noviembre de 2024");
        this.add();
    }

    public String remove() {
        if (this.getSelected() != null) {
            if (this.tiendacontroller.getItems().stream().filter(item -> {
                return item.getPromocion()== this.getSelected();
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

    @Override
    public String preEdit() {

        return "edit";
    }

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