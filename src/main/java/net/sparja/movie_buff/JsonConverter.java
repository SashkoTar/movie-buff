package net.sparja.movie_buff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonConverter {


    public static void main(String[] args) throws Exception {
        Class clazz = JsonConverter.class;
        InputStream inputStream = clazz.getResourceAsStream("/wiki_movie_plots_deduped.csv");
        List<Movie> movies = readFromInputStream(inputStream);

        ObjectMapper objectMapper = new ObjectMapper();

        movies.forEach(movie -> {
            try {
                System.out.println(objectMapper.writeValueAsString(movie));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private static List<Movie> readFromInputStream(InputStream inputStream)
            throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null || counter < 100) {
                if (counter > 0 && (line.startsWith("1") || line.startsWith("2")) && line.endsWith("\"")) {
                    //System.out.println(line);
                    try {
                        Movie movie = Movie.parse(line);
                        movies.add(movie);
                        //System.out.println("Counter: " +counter + " Film: " + movie.getReleaseYear() + " - " + movie.getTitle() + " - " + movie.getOrigin() + " - " + movie.getDirector() + " - " + movie.getCast() + " - " + movie.getGenre());
                    } catch (Exception ex) {
                        System.out.println("Film with Error: "  + line);
                    }
                }
                counter++;
            }
        }
        return movies;
    }


}
