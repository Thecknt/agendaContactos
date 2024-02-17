package com.cris.contactos.controlador;

import com.cris.contactos.modelo.Contacto;
import com.cris.contactos.servicio.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactoControlador {

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServicio.listarContactos();
        modelo.put("contactos",contactos);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar() {
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForm") Contacto contacto){
        String enlaceGoogleMaps = "https://www.google.com/maps/search/" + contacto.getDomicilio() + "+" + contacto.getAltura() +"+" + contacto.getCodigoPostal();
        contacto.setGoogleMaps(enlaceGoogleMaps);
        System.out.println("Los datos del contacto son: "+ contacto.toString());
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idContacto, ModelMap modelo){
    Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
    modelo.put("contacto", contacto);
        return "editar";
    }

}
