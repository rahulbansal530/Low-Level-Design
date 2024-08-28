### Requirements
It should ;ist the cities where theatres are located
Each cinemas can have multiple Audis and each Audi can run one movie at a time
Each movie can have multiple shows
user can search a movie by name, release date, city....
Customers can select a movie, then cinema, then show...
customers can see the seats layout where he can select multiple seats and book them
customer should be able to identify between available and booked seat


class MovieBookingService {
    List<Theatre> theatres;

    public List<Movie> getMovies(Date date, String city);
    public List<Theatre> geTheatres(String city);
}

class Theatre {
    String id;
    String name;
    Location location;
    List<Audi> audis;

}


