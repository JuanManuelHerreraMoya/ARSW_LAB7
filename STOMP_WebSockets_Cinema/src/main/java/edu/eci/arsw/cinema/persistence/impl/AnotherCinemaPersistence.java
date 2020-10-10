package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class AnotherCinemaPersistence implements CinemaPersitence {

    public AnotherCinemaPersistence(){
    }

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaPersistenceException {
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        return null;
    }

    @Override
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException {
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return null;
    }

    @Override
    public Map<String, Cinema> getCinemas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCinemaByFuntion(String name, CinemaFunction cinemaFunction) throws CinemaPersistenceException {

    }

    @Override
    public void setCinemaFuntion(String name, CinemaFunction cf) throws CinemaPersistenceException {
    }

    @Override
    public void deleteFunction(String name, CinemaFunction cf) throws CinemaPersistenceException {

    }


}

