package info.zoulquarnayn.kiosquelabari;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collection;

import info.zoulquarnayn.kiosquelabari.data.RssItem;

/**
 * Created by fahmedal on 25/02/2015.
 */
public class RssItemHandler extends DefaultHandler {
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_TITLE = "title";
    public static final String TAG_LINK = "link";
    public static final String TAG_COMMENTS = "comments";
    public static final String TAG_PUB_DATE = "pubDate";
    public static final String TAG_CONTENT = "content";
    private Boolean currentElement = false;
    private String currentValue = "";
    private static final String TAG_ITEM = "item";
    private Collection<RssItem> news = new ArrayList<RssItem>();
    private RssItem currentRssItem = null;

    public Collection<RssItem> getNews() {
        return news;
    }


    // Called when tag starts
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentElement = true;
        currentValue = "";
        if (TAG_ITEM.equalsIgnoreCase(localName)) {
            currentRssItem = new RssItem();
        }

    }

    // Called when tag closing
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currentElement = true;

        if (TAG_ITEM.equalsIgnoreCase(localName)) {
            news.add(currentRssItem);
        } else if (TAG_DESCRIPTION.equalsIgnoreCase(localName)) {
            currentRssItem.setDescription(currentValue);
        } else if (TAG_TITLE.equalsIgnoreCase(localName)) {
            currentRssItem.setTitle(currentValue);
        } else if (TAG_LINK.equalsIgnoreCase(localName)) {
            currentRssItem.setLink(currentValue);
        } else if (TAG_COMMENTS.equalsIgnoreCase(localName)) {
            currentRssItem.setComments(currentValue);
        } else if (TAG_PUB_DATE.equalsIgnoreCase(localName)) {
            currentRssItem.setPubDate(currentValue);
        } else if (TAG_CONTENT.equalsIgnoreCase(localName)) {
            currentRssItem.setContent(currentValue);
        }

    }

    // Called to get tag characters
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (currentElement) {
            currentValue = currentValue + new String(ch, start, length);
        }

    }

}

