package edu.eci.arsw.cinema.filters;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaException;

import java.util.List;

public interface FilterCinema {

    public List<CinemaFunction> filter(List<CinemaFunction> cinemaF, String filter) throws CinemaException;

}
