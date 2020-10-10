/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author cristian
 */

@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {

    @Autowired
    @Qualifier("CinemaServices")
    CinemaServicesInterface cs;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas() {
        //obtener datos que se enviarán a través del API
        return new ResponseEntity<>(cs.getAllCinemas(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaByName(@PathVariable String name) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cs.getCinemaByName(name), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaServicesInterface.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{name}/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getFunctionsbyCinemaAndDate(@PathVariable String name, @PathVariable String date) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name,date), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaServicesInterface.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{name}/{date}/{mname}", method = RequestMethod.GET)
    public ResponseEntity<?> getFunctionsbyCinemaAndDateMname(@PathVariable String name, @PathVariable String date,@PathVariable String mname) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDateMname(name,date,mname), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaServicesInterface.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(path =  "/{name}", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCinema(@PathVariable String name,@RequestBody CinemaFunction cf){
        //name es el nombre del cine al que crearle la pelicula
        try {
            cs.createFuntion(name,cf);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaServicesInterface.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path =  "/{name}", method = RequestMethod.PUT)
    public ResponseEntity<?> setCinemaFuntion(@PathVariable String name,@RequestBody CinemaFunction cf){
        //name es el nombre del cine al que crearle la pelicula
        try {
            cs.setCinemaFuntion(name,cf);
            return new ResponseEntity<>(HttpStatus.UPGRADE_REQUIRED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaServicesInterface.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFunction(@PathVariable String name, @RequestBody CinemaFunction cf) {
        try {
            cs.deleteFunction(name, cf);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaServicesInterface.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{cinema}/{name}/{date}/{row}/{col}", method = RequestMethod.PUT)
    public ResponseEntity<?> buyTicket(@PathVariable String cinema, @PathVariable String name, @PathVariable String date,
                                       @PathVariable int row, @PathVariable int col){
        try {
            cs.buyTicket(row,col,cinema,date,name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CinemaException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }



}
