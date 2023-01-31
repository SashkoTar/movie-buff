package net.sparja.movie_buff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieParserTest {

    String quotesInPlot = "1901,Kansas Saloon Smashers,American,Unknown,,unknown,https://en.wikipedia.org/wiki/Kansas_Saloon_Smashers,\"A bartender is working at a saloon, servinrybody to leave.[1]\"";

    String getQuotesInTitle = "1901,\"Terrible Teddy, the Grizzly King\",American,Unknown,,unknown,\"https://en.wikipedia.org/wiki/Terrible_Teddy,_the_Grizzly_King\",\"Lasting just 61 seconds and consisting of two shots, the first shot is set in a wood during winter. The actor representing then vice-president Theodore Roosevelt enthusiastically hurries down\"";

    @Test
    public void shouldParseQuotesInPlot() {
        Movie movie = Movie.parse(quotesInPlot);
        assertEquals("1901", movie.getReleaseYear());
        assertEquals("Kansas Saloon Smashers", movie.getTitle());
        assertEquals("American", movie.getOrigin());
        assertEquals("Unknown", movie.getDirector());
        assertEquals("", movie.getCast());
        assertEquals("unknown", movie.getGenre());
        assertEquals("https://en.wikipedia.org/wiki/Kansas_Saloon_Smashers", movie.getWikiPage());
        assertEquals("A bartender is working at a saloon, servinrybody to leave.[1]", movie.getPlot());
    }

    @Test
    public void shouldParseQuotesInTitle() {
        Movie movie = Movie.parse(getQuotesInTitle);
        assertEquals("1901", movie.getReleaseYear());
        assertEquals("\"Terrible Teddy, the Grizzly King\"", movie.getTitle());
        assertEquals("American", movie.getOrigin());
        assertEquals("Unknown", movie.getDirector());
        assertEquals("", movie.getCast());
        assertEquals("unknown", movie.getGenre());
       // assertEquals("https://en.wikipedia.org/wiki/Kansas_Saloon_Smashers", movie.getWikiPage());
       // assertEquals("\"A bartender is working at a saloon, servinrybody to leave.[1]\"", movie.getPlot());
    }

}
