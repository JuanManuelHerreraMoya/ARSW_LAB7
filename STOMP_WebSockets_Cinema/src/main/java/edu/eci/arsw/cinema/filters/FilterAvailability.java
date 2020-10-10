/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.filters;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author JUAN NIETO
 */
@Service("FilterAvailability")
public class FilterAvailability implements FilterCinema {
    @Override
    public List<CinemaFunction> filter(List<CinemaFunction> cinemaF, String filter) throws CinemaException {
        List<CinemaFunction> res = new ArrayList<>();
        int asientos;
        try{
            asientos = Integer.parseInt(filter);
            if(asientos<0)throw new CinemaException("Los valores deben ser positivos");          
        } catch (NumberFormatException ex) {
            throw new CinemaException("Los valores deben ser numeros positivos");
        }
        for(CinemaFunction cf: cinemaF){
            int count = 0;
            for(int i=0; i<7; i++){
                for(int j=0; j<12; j++){
                    if(cf.getSeats().get(i).get(j).equals(false))
                        count++;
                }
            }
            if(count>asientos){
                res.add(cf);
            }
        }
        if(res.size()>0){
            return res;
        }else{
            throw new CinemaException("No hay funciones que cumplan sus requisitos");
        }
    }
}
