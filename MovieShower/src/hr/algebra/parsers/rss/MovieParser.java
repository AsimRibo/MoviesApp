/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.movie.Movie;
import hr.algebra.model.Person;
import hr.algebra.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author asim2
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=2";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";
    private static final String REGEX_HTML = "\\<.*?>";
    
    private static final String ACTORS_DATA_SEPARATOR = ",";
    private static final String DOT = ".";


    private MovieParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();

        HttpURLConnection connection = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = connection.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            StartElement startElement = null;
            Movie movie = null;
            Optional<RssTagType> rssTagType = Optional.empty();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        rssTagType = RssTagType.from(qName);
                        
                        if (rssTagType.isPresent() && rssTagType.get() == RssTagType.ITEM) {
                        movie = new Movie();
                        movies.add(movie);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        if (rssTagType.isPresent()) {
                            switch (rssTagType.get()) {
                                //In case of using url with number 1, uncomment case below and comment if case above
                                /*case ITEM:
                                movie = new Movie();
                                movies.add(movie);
                                break;*/
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUBLISHED_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPublishedDate(publishedDate);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(removeHtmlTags(data));
                                    }
                                    break;
                                case ORIGINAL_TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                    break;
                                case DIRECTOR:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDirector(getPerson(data));
                                    }
                                    break;
                                case ACTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Person> people = new ArrayList<>();
                                        String[] peopleInfo = data.split(ACTORS_DATA_SEPARATOR);
                                        for (String personInfo : peopleInfo) {
                                            people.add(getPerson(personInfo));
                                        }
                                        movie.setActors(people);
                                    }
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDuration(data);
                                    }
                                    break;
                                case GENRE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setGenre(data);
                                    }
                                    break;
                                case PICTURE:
                                    if (movie != null && !data.isEmpty()) {
                                        handlePicture(movie, data);
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }

        return movies;
    }
    

    private static void handlePicture(Movie movie, String pictureUrl) throws IOException {
        String ext = pictureUrl.substring(pictureUrl.lastIndexOf(DOT));
        if (ext.length() > 4) {
            ext = EXT;
        }
        
        
        String pictureName = UUID.randomUUID() + ext;
        String picturePath = DIR + File.separator + pictureName;
        
        FileUtils.copyFromUrl(pictureUrl, picturePath);
        
        movie.setPicturePath(picturePath);
    }
    
    
    private static String removeHtmlTags(String content) {
        return content.replaceAll(REGEX_HTML,"");
    }
    
    private static Person getPerson(String data) {
        String[] personInfo = data.trim().split(" ", 2);
        switch(personInfo.length){
            case 1:
                return new Person(personInfo[0], "");
            case 2:
                return new Person(personInfo[0], personInfo[1]);
        }
        throw new IllegalArgumentException("Data could not be handled");
    }

    private enum RssTagType {
        ITEM("item"),
        TITLE("title"),
        PUBLISHED_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIGINAL_TITLE("orignaziv"),
        DIRECTOR("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        PICTURE("plakat");

        private final String name;

        private RssTagType(String name) {
            this.name = name;
        }

        public static Optional<RssTagType> from(String name) {
            for (RssTagType value : RssTagType.values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }

    }

}
