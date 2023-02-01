package net.sparja.movie_buff;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Movie {

    private String id = UUID.randomUUID().toString();
    private String releaseYear;
    private String title;
    private String origin;
    private String director;
    private String cast;
    private String genre;
    private String wikiPage;
    private String plot;

    public String getId() {
        return id;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWikiPage() {
        return wikiPage;
    }

    public void setWikiPage(String wikiPage) {
        this.wikiPage = wikiPage;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    static List<String> splitToFields(String line) {
        List<String> fields = new ArrayList<>();
        boolean endOfLine = false;
        int fieldEndIndex = 0;
         while (!endOfLine) {
            if(line.startsWith("\"")) {
                fieldEndIndex = line.indexOf("\"", 1);
                try {
                    fields.add(line.substring(1, fieldEndIndex ));
                } catch (Exception ex) {
                    throw new RuntimeException("Substring from: " + line + ".substring(1, " +fieldEndIndex + ")", ex);
                }

                line = line.substring(fieldEndIndex);
                if(line.contains(",")) {
                    line = line.substring(1);
                } else {
                    endOfLine = true;
                }
            } else {
                if(line.startsWith(",")) {
                    fields.add("");
                    line = line.substring(1);
                }else {
                    fieldEndIndex = line.indexOf(",", 1);
                    fields.add(line.substring(0, fieldEndIndex));
                    line = line.substring(fieldEndIndex + 1);
                }
            }
         }
        return fields;
    }

    public static Movie parse(String line) {
        final Movie movie = new Movie();
        /*
        List<Consumer<String>> fields2 = new ArrayList<Consumer<String>>() {
            {
                add(movie::setReleaseYear);
                add(movie::setTitle);
                add(movie::setDirector);
                add(movie::setCast);
                add(movie::setGenre);
                add(movie::setWikiPage);
                add(movie::setPlot);
            }
        };

        */

        List<String> splittedToColumn = splitToFields(line);
        movie.setReleaseYear(splittedToColumn.get(0));
        movie.setTitle(splittedToColumn.get(1));
        movie.setOrigin(splittedToColumn.get(2));
        movie.setDirector(splittedToColumn.get(3));
        movie.setCast(splittedToColumn.get(4));
        movie.setGenre(splittedToColumn.get(5));
        movie.setWikiPage(splittedToColumn.get(6));
        movie.setPlot(splittedToColumn.get(7));
        return movie;
    }
}
