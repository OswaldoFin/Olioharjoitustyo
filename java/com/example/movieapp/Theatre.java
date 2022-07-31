package com.example.movieapp;

import androidx.annotation.NonNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Theatre implements Serializable
{
    private int id;
    private String name;

    ArrayList<Movie> movies = new ArrayList<>();

    Theatre(@NonNull Element theaterInfo)
    {
        this.id = Integer.parseInt(theaterInfo.getElementsByTagName("ID").item(0).getTextContent());
        this.name = theaterInfo.getElementsByTagName("Name").item(0).getTextContent();
    }

    void fetchMovies(String date, LocalTime filterTimePeriodStart, LocalTime filterTimePeriodEnd)
    {
        if(!movies.isEmpty())
        {
            movies.clear();
        }

        String url;
        if(date != null)
        {
            url = "https://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + date;
        }
        else
        {
            url = "https://www.finnkino.fi/xml/Schedule/?area=" + id;
        }

        if(filterTimePeriodStart == null)
        {
            filterTimePeriodStart = LocalTime.MIDNIGHT;
        }
        if(filterTimePeriodEnd == null)
        {
            filterTimePeriodEnd = LocalTime.MAX;
        }

        try
        {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(url);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    String movieStartTime = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    LocalTime movieStartLocalTime = LocalTime.parse(movieStartTime.split("T")[1]);
                    if (movieStartLocalTime.isAfter(filterTimePeriodStart) && movieStartLocalTime.isBefore(filterTimePeriodEnd))
                    {
                        Movie movie = new Movie(element);
                        movies.add(movie);

                    }
                }
            }
        }
        catch (IOException | ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMovieNames()
    {
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie : movies)
        {
            movieNames.add(movie.getTitle());
        }
        return movieNames;
    }

    public Movie getMovie(int index)
    {
        return movies.get(index);
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

}
