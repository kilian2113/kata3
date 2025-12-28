package software.ulpgc.kata3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RemoteMovieLoader reader = new RemoteMovieLoader();
        List<Movie> movies = reader.loadAll();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
