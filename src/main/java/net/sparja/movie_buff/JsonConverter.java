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
                int wikiPageStartIndex = line.indexOf("https");
                if (wikiPageStartIndex > 0) {
                    String[] fields = line.substring(0, wikiPageStartIndex).split(",");
                    String year = fields[0];
                    String title = fields[1];
                    String origin = fields[2];
                    String director = fields[3];
                    String cast = fields[4];
                    String genre = fields[5];
                    System.out.println("Counter: " +counter + " Film: " + year + " - " + title + " - " + origin + " - " + director + " - " + cast + " - " + genre);
                }
                counter++;
            }
        }
    }

    public static class Movie {
        private String releaseYear;
        private String title;
        private String origin;
        private String director;
        private String cast;
        private String genre;
        private String wikiPage;
        private String plot;

    }

}
