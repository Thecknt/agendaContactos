package com.cris.contactos.controlador;

import com.cris.contactos.modelo.Contacto;
import com.cris.contactos.servicio.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/agregar")
    public String guardarContacto(@ModelAttribute Contacto contacto, Model model) {
        String enlaceGoogleMaps = "https://www.google.com/maps/search/" + contacto.getDomicilio() + "+" + contacto.getAltura() +"+" + contacto.getCodigoPostal();
        contacto.setGoogleMaps(enlaceGoogleMaps);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

}
