package edu.eci.arsw.cinema.main;

import edu.eci.arsw.cinema.services.CinemaException;

/*
public class Main {
    public static void main(String[] args) throws CinemaException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("cinemaContext.xml");
        CinemaServices cine = applicationContext.getBean(CinemaServices.class);
        
        
        registerCinemas("LAB3 Cinemas",cine);
        consultCinemas(cine);
        obtainFuntionByCinema("LAB3 Cinemas",cine);
        buyTickets(5, 5, "LAB3 Cinemas", "2020-9-1 15:30", "The Gentleman",cine);
        buyTickets(1, 5, "LAB3 Cinemas", "2020-9-1 15:30", "The Gentleman",cine);
        buyTickets(1, 1, "LAB3 Cinemas", "2020-9-1 15:30", "The Gentleman",cine);
        filterByGender("Action","LAB3 Cinemas", "2020-9-1 15:30",cine);
        filterByAvailabity("2","LAB3 Cinemas", "2020-9-1 15:30",cine);
    }
    
    public static void registerCinemas(String cinema,CinemaServices cine){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Registrando Cinemas a "+cinema);
        String functionDate = "2020-9-1 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("The Gentleman","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("Pinocho","Childish"),functionDate);
        CinemaFunction funct3 = new CinemaFunction(new Movie("Greyhound: En la Mira del Enemigo Pelicula Completa","Action"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        functions.add(funct3);
        Cinema c = new Cinema(cinema,functions);
        cine.addNewCinema(c);
        System.out.println("Cinemas agregado");
    }
    
    public static void consultCinemas(CinemaServices cine){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Consultado cinemas disponibles");
        Map<String,Cinema> cinemas = cine.getAllCinemas();
        for(Map.Entry<String, Cinema> fun : cinemas.entrySet()){
         System.out.println(fun.getValue().getName());   
        } 
    }
    
    public static void obtainFuntionByCinema(String name,CinemaServices cine){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Obteniendo funcion por titulo de un cinema");
        Map<String,Cinema> cinemas = cine.getAllCinemas();
        for(Map.Entry<String, Cinema> fun : cinemas.entrySet()){
            Cinema temp = fun.getValue();
            System.out.println(fun.getValue().getName());
            for(CinemaFunction cin : temp.getFunctions()){
                System.out.println(cin.getMovie().getName()+" "+cin.getDate());
            }       
        }
    }
   
    
    public static void buyTickets(int row, int col, String cineName, String date, String movieName, CinemaServices cinema){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Comprando ticket");
        try {
            cinema.buyTicket(row, col, cineName, date, movieName);
        } catch (CinemaException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void filterByGender(String filtro, String name, String date,CinemaServices cine) throws CinemaException{
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Filtrando");
        List<CinemaFunction> cinf = cine.getFilterG(name, date, filtro);
        for(CinemaFunction cf : cinf){
            System.out.println(cf.getMovie().getName()+" "+cf.getMovie().getGenre());
        }        
    }
    
    public static void filterByAvailabity(String filtro, String name, String date, CinemaServices cine) throws CinemaException{
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.println("Obteniendo funcion por disponibilidad");
        List<CinemaFunction> cinf = cine.getFilterA(name, date, filtro);
        for(CinemaFunction cf : cinf){
            System.out.println(cf.getMovie().getName());
        }
    }

    
}
*/