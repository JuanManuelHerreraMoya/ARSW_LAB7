package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;

import java.util.List;
import java.util.Map;

public interface CinemaServicesInterface {

    public void addNewCinema(Cinema c);
    public Map<String,Cinema> getAllCinemas();
    public Cinema getCinemaByName(String name) throws CinemaException;
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException;
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException;
    public List<CinemaFunction> getFilterG(String cinema, String date, String filtro) throws CinemaException;
    public List<CinemaFunction> getFilterA(String cinema, String date, String filtro) throws CinemaException;
    public List<CinemaFunction> getFunctionsbyCinemaAndDateMname(String cinema, String date,String mname) throws CinemaException;
    public void createFuntion(String name, CinemaFunction cinemaFunction) throws CinemaException;
    public void setCinemaFuntion(String name, CinemaFunction cf) throws CinemaException;
    public void deleteFunction(String name, CinemaFunction cf) throws CinemaException;
}
