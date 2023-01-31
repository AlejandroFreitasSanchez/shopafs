/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopafs.views;

import com.mycompany.shopafs.controller.OpcionController;
import com.mycompany.shopafs.controller.TiendaController;

import com.mycompany.shopafs.controller.CategoriaController;

import com.mycompany.shopafs.model.Promocion;
import com.mycompany.shopafs.model.Opcion;
import com.mycompany.shopafs.model.Tienda;

import com.mycompany.shopafs.model.Categoria;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.crypto.AEADBadTagException;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Administrador
 */
@ViewScoped
@Named
public class PropiedadEditView implements Serializable {

   
    @Inject
    private CategoriaController categoriaController;
    @Inject
    private OpcionController opcionController;
    @Inject
    private TiendaController tiendaController;
    private Tienda tienda;
   

    private String destination = "";

    public PropiedadEditView() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.destination  = ctx.getExternalContext().getInitParameter("ruta_imagenes");
        //System.out.println("El valor es " + myConstantValue);
    }



    /**
     * @return the t
     */
    public String getTipo() {
        if (this.tienda.getCategoria() != null) {
            return this.tienda.getCategoria().getNombre();
        } else {
            return "";
        }

    }

    /**
     * @param t the t to set
     */
    public void setTipo(String item) {
        Optional<Categoria> consulta = this.categoriaController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.tienda.setCategoria(consulta.get());
        }
    }

    

    @PostConstruct
    public void init() {

        // this.tipo = new Tipo();
        if (this.tiendaController.getSelected() == null) {
            this.tienda = new Tienda();
        } else {
            //se clona por si se da a cancelar
            this.tienda = (Tienda) this.tiendaController.getSelected();//.clone(); //.getSelected();
           // this.provincia = this.provinciacontroller.getProvinciaByLocalidad(this.propiedad.getLocalidad());

        }
    }

    public void onProvinciaChange() {
        //Provincia p = this.provinciacontroller.getItems().get(0);

    }

   /* public void setProvincia(String item) {
        Optional<Provincia> consulta = this.provinciacontroller.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.provincia = (consulta.get());
        } else {
            this.provincia = null;
        }
    }
*/
    /*public List<Provincia> getProvincias() {
        return this.provinciacontroller.getItems();
    }

    public String getProvincia() {
        if (this.provincia != null) {
            return this.provincia.getNombre();
        } else {
            return "";
        }

    }
*/
   /* public List<Localidad> getLocalidades() {

        if (this.provincia != null) {
            return this.provincia.getLocalidades();
        } else {
            return new ArrayList<Localidad>();
        }
    }
*/
    public List<Opcion> getOpciones() {
        return this.opcionController.getItems();
    }

    public void setOpcion(String item) {
        Optional<Opcion> consulta = this.opcionController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.tienda.setOpcion(consulta.get());
        }
    }

    public String getLocalidad() {
        if (this.tienda.getPromocion() != null) {
            return this.tienda.getPromocion().getNombre();
        } else {
            return "";
        }

    }

    public String getOpcion() {
        if (this.tienda.getOpcion() != null) {
            return this.tienda.getOpcion().getNombre(); //this.getPropiedad().getOpcion();
        } else {
            return "";
        }
    }

    public List<Categoria> getTipos() {
        return this.categoriaController.getItems();
    }

    /**
     * @return the propiedad
     */
    public Tienda getPropiedad() {
        return tienda;
    }

    /**
     * @param propiedad the propiedad to set
     */
    public void setPropiedad(Tienda propiedad) {
        this.tienda = propiedad;
    }

    

    
    public String add() {
        Tienda p;
        if (this.tienda != null) {
            if (this.tienda.getId() != -1) {
                //se obtiene el original
                p = this.tiendaController.getTiendaById(tienda.getId());
                p.setDireccion(this.tienda.getDireccion());
                p.setActivo(tienda.isActivo());
                p.setPromocion(tienda.getPromocion());
                p.setOpcion(tienda.getOpcion());
                p.setCategoria(tienda.getCategoria());
                p.setPrecio(tienda.getPrecio());
                this.tiendaController.setSelected(null);
                return "sucess";
            } else {
                //nuevo
                this.tiendaController.setSelected(this.tienda);
                this.tiendaController.add();
                return "sucess";
            }
        } else {
            this.tiendaController.setSelected(null);
            return "failed";
        }

    }

    public String preEdit() {
        return "edit";
    }

    public String create() {
        this.tiendaController.setSelected(null);
        this.tienda = new Tienda();
        return "create";
    }

    public String cancel() {
        this.tienda = null;
        return "sucess";
    }

    public String precreate() {
        return "imageadd";
    }

    
}
