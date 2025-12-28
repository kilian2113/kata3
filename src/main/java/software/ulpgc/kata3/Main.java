package software.ulpgc.kata3;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (RemoteMovieReader reader = new RemoteMovieReader()) {
            List<Movie> movies = reader.readAll();
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
