package net.sparja.movie_buff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonConverter {


    public static void main(String[] args) throws IOException {
        Class clazz = JsonConverter.class;
        InputStream inputStream = clazz.getResourceAsStream("/wiki_movie_plots_deduped.csv");
        readFromInputStream(inputStream);
    }

    private static void readFromInputStream(InputStream inputStream)
            throws IOException {

        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null || counter < 100) {
                if (counter > 0) {
                    System.out.println(line);
                    Movie movie = Movie.parse(line);
                    System.out.println("Counter: " +counter + " Film: " + movie.getReleaseYear() + " - " + movie.getTitle() + " - " + movie.getOrigin() + " - " + movie.getDirector() + " - " + movie.getCast() + " - " + movie.getGenre());
                }
                counter++;
            }
        }
    }


}
