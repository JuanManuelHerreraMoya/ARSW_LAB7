/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filters.FilterAvailability;
import edu.eci.arsw.cinema.filters.FilterCinema;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.CinemaModelException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 *
 * @author cristian
 */
@Service("CinemaServices")
public class CinemaServices implements CinemaServicesInterface {

    @Autowired
    @Qualifier("InMemoryCinemaPersistence")
    CinemaPersitence cps=null;

    @Autowired
    @Qualifier("FilterByGender")
    FilterCinema fc=null;
    
    @Autowired
    @Qualifier("FilterAvailability")
    FilterAvailability fa=null;

    public void addNewCinema(Cinema c){
        try {
            cps.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Map<String,Cinema> getAllCinemas(){
        return cps.getCinemas();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException{
        try{
            return cps.getCinema(name);
        }catch (CinemaPersistenceException e){
            throw new CinemaException(e.getMessage(), e);
        }
    }

    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        try{
            cps.buyTicket(row, col, cinema, date, movieName);
        }catch (CinemaPersistenceException | CinemaModelException e){
            throw new CinemaException(e.getMessage(), e);
        };

    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException {
        List<CinemaFunction> cinemaF;
        try{
            cinemaF = cps.getFunctionsbyCinemaAndDate(cinema,date);
            return cinemaF;
        }catch (CinemaPersistenceException | CinemaModelException e){
            throw new CinemaException(e.getMessage(), e);
        }
    }

    public List<CinemaFunction> getFunctionsbyCinemaAndDateMname(String cinema, String date,String mname) throws CinemaException {
        List<CinemaFunction> cinemaF = new ArrayList<>();;
        try{
            for( CinemaFunction i : cps.getFunctionsbyCinemaAndDate(cinema,date)) {
                if(i.getMovie().getName().equals(mname)){
                    cinemaF.add(i);
                }
            }
            return cinemaF;
        }catch (CinemaPersistenceException | CinemaModelException e){
            throw new CinemaException(e.getMessage(), e);
        }
    }

    @Override
    public void createFuntion(String name, CinemaFunction cinemaFunction) {
        try {
            cps.saveCinemaByFuntion(name,cinemaFunction);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setCinemaFuntion(String name, CinemaFunction cf) throws CinemaException {
        try {
            cps.setCinemaFuntion(name,cf);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteFunction(String name, CinemaFunction cf) throws CinemaException {
        try {
            cps.deleteFunction(name,cf);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<CinemaFunction> getFilterG(String cinema, String date, String filtro) throws CinemaException{
        try {
            List<CinemaFunction> cinemaF = cps.getFunctionsbyCinemaAndDate(cinema, date);
            return fc.filter(cinemaF, filtro);
        } catch (CinemaPersistenceException | CinemaModelException e) {
            throw new CinemaException(e.getMessage(), e);
        }
    }
    
    public List<CinemaFunction> getFilterA(String cinema, String date, String filtro) throws CinemaException{
        try {
            List<CinemaFunction> cinemaF = cps.getFunctionsbyCinemaAndDate(cinema, date);
            return fa.filter(cinemaF, filtro);
        } catch (CinemaPersistenceException | CinemaModelException e) {
            throw new CinemaException(e.getMessage(), e);
        }
    }

}
