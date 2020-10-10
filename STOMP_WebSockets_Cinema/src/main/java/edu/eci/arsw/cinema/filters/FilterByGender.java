
package edu.eci.arsw.cinema.filters;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.services.CinemaException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FilterByGender")
public class FilterByGender implements FilterCinema {

    public FilterByGender() {
    }

    @Override
    public List<CinemaFunction> filter(List<CinemaFunction> cinemaF, String genero) throws CinemaException {
        List<CinemaFunction> PeliculasFiltradas = new ArrayList<>();
        if (cinemaF.size() != 0) {
            for (int i=0;i<cinemaF.size();i++){
                Movie pelicula = cinemaF.get(i).getMovie();
                if(pelicula.getGenre().equals(genero)){
                    PeliculasFiltradas.add(cinemaF.get(i));
                }
            }
            if (PeliculasFiltradas.size() == 0) {
                throw new CinemaException("No existen funciones con ese genero");
            }
        } else {
            throw new CinemaException("Erorre al tratar de filtrar peliculas");
        }
        return PeliculasFiltradas;
        }
}

