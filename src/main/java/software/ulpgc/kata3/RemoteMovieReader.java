package software.ulpgc.kata3;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class RemoteMovieReader implements MovieReader, Closeable {
    private static final String remoteURL = "https://datasets.imdbws.com/title.basics.tsv.gz";
    private final BufferedReader reader;

    public RemoteMovieReader() throws IOException {
        this.reader = createReader(createConnection());
        this.reader.readLine();
    }

    private URLConnection createConnection() throws IOException {
        URL url = new URL(remoteURL);
        URLConnection connection = url.openConnection();
        connection.connect();
        return connection;
    }

    private BufferedReader createReader(URLConnection connection) throws IOException {
        return new BufferedReader(new InputStreamReader(unzip(connection.getInputStream())));
    }

    private GZIPInputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream));
    }

    @Override
    public List<Movie> readAll() {
        List<Movie> movies = new ArrayList<>();
        while (true) {
            Movie movie = readMovie();
            if (movie == null) break;
            movies.add(movie);
        }
        return movies;
    }

    private Movie readMovie() {
        try {
            String line = reader.readLine();
            return line != null ? createMovie(line) : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Movie createMovie(String line) {
        return createMovie(line.split("\t"));
    }

    private Movie createMovie(String[] split) {
        return new Movie(split[2], toInteger(split[7]));
    }

    private int toInteger(String s) {
        return s.equals("\\N") ? 0 : Integer.parseInt(s);
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
