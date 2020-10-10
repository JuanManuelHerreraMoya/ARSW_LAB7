/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 *
 * @author cristian
 */

public class Cinema {
    private String name;
    private List<CinemaFunction> functions; 
    
    
    public Cinema(){}
    
    public Cinema(String name,List<CinemaFunction> functions){
        this.name=name;
        this.functions=functions;
    }

    public CinemaFunction getFunctionByNameAndDate(String name, String date) throws CinemaModelException {
        CinemaFunction cinemaF = null;
        for (int i=0;i< functions.size();i++) {
            if (functions.get(i).getMovie().getName().equals(name) && functions.get(i).getDate().equals(date)) {
                cinemaF= functions.get(i);
                return  cinemaF;
            }
        }
        if (cinemaF == null) {
            throw new CinemaModelException("The Function Don't exist");
        }
        return cinemaF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CinemaFunction> getFunctions() {
        return this.functions;
    }

    public void setSchedule(List<CinemaFunction> functions) {
        this.functions = functions;
    }

    public void addCineFunction(CinemaFunction cinemaFunction) {
        functions.add(cinemaFunction);
    }

    public void setCinemaFuntion(CinemaFunction cf){
        for(CinemaFunction i: functions){
            System.out.println(i.getMovie().getName().equals(cf.getMovie().getName()));
            if(i.getMovie().getName().equals(cf.getMovie().getName())){
                i.setDate(cf.getDate());
                i.setMovie(cf.getMovie());
                i.setSeats(cf.getSeats());
                //System.out.println(i.getDate()+" ljfasdhfljah ");
            }
        }
    }
}
