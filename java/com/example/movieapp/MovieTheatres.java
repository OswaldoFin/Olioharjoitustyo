package com.example.movieapp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MovieTheatres
{
    private static MovieTheatres movieTheatres;
    ArrayList<Theatre> theatres;

    public static MovieTheatres getInstance()
    {
        if (movieTheatres == null)
        {
            movieTheatres = new MovieTheatres();
        }
        return movieTheatres;
    }

    public void fetchTheatres()
    {
        theatres = new ArrayList<>();
        try
        {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String theatreAreas = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document document = documentBuilder.parse(theatreAreas);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("Name").item(0).getTextContent().matches(".*:.*"))
                    {
                        Theatre theatre = new Theatre(element);
                        theatres.add(theatre);
                    }
                }
            }
        }
        catch (IOException | ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTheatreNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for (Theatre theatre : theatres)
        {
            names.add(theatre.getName());
        }
        return names;
    }

    public Theatre getTheatre(int index)
    {
        return theatres.get(index);
    }
}

