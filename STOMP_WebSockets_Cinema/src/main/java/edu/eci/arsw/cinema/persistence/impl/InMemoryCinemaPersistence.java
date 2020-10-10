/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.CinemaModelException;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cristian
 */
@Service("InMemoryCinemaPersistence")
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        List<CinemaFunction> functions2= new ArrayList<>();
        List<CinemaFunction> functions3= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Actio"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functionDate = "2018-12-18 20:30";
        CinemaFunction funct3 = new CinemaFunction(new Movie("Pinocho","Childish"),functionDate);
        CinemaFunction funct4 = new CinemaFunction(new Movie("Fight Club","Drama"),functionDate);
        functionDate = "2018-12-18 13:30";
        CinemaFunction funct5 = new CinemaFunction(new Movie("The Transporter","Action"),functionDate);
        CinemaFunction funct6 = new CinemaFunction(new Movie("Snatch","Suspense"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        functions2.add(funct3);
        functions2.add(funct4);
        functions3.add(funct5);
        functions3.add(funct6);
        Cinema c=new Cinema("cinemaX",functions);
        Cinema c2=new Cinema("Procinal",functions2);
        Cinema c3=new Cinema("CineMark",functions3);
        cinemas.put("cinemaX", c);
        cinemas.put("Procinal", c2);
        cinemas.put("CineMark", c3);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException, CinemaModelException {
        Cinema cn = getCinema(cinema);
        CinemaFunction cinemaF =cn.getFunctionByNameAndDate(movieName,date);
        if (cinemaF != null){
            cinemaF.buyTicket(row,col);
        }
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaModelException {
        List<CinemaFunction> functions= new ArrayList<>();
        for(Map.Entry<String, Cinema> fun : cinemas.entrySet()){
            Cinema temp = fun.getValue();
            if(fun.getKey().equals(cinema) && temp.getName().equals(cinema)){
                List<CinemaFunction> tmpFun = temp.getFunctions();
                for(CinemaFunction cf: tmpFun){
                    if(cf.getDate().contains(date)){
                        functions.add(cf);
                    }
                }
            }
        }
        return functions;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public void saveCinemaByFuntion(String name, CinemaFunction cinemaFunction) throws CinemaPersistenceException {
        for(Map.Entry<String, Cinema> fun : cinemas.entrySet()){
            if (fun.getValue().getName().equals(name)){
                fun.getValue().addCineFunction(cinemaFunction);
            }
        }
    }

    @Override
    public void setCinemaFuntion(String name, CinemaFunction cf) throws CinemaPersistenceException {
        for(Map.Entry<String, Cinema> fun : cinemas.entrySet()){
            if (fun.getValue().getName().equals(name)){
                fun.getValue().setCinemaFuntion(cf);
            }
        }
    }

    @Override
    public void deleteFunction(String name, CinemaFunction cf) throws CinemaPersistenceException {
        Cinema cinema = getCinema(name);
        for(CinemaFunction f: cinema.getFunctions()){
            if(f.getMovie().getName().equals(cf.getMovie().getName()) && f.getDate().equals(cf.getDate())){
                cinema.getFunctions().remove(f);
            }
        }
    }

    @Override
    public Cinema getCinema(String name)  {
        return cinemas.get(name);
    }

    @Override
    public Map<String,Cinema> getCinemas() {
        return cinemas;
    }




}
