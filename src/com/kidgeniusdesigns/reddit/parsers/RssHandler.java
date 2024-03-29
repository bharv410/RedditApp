package com.kidgeniusdesigns.reddit.parsers;
import static com.kidgeniusdesigns.reddit.parsers.BaseFeedParser.DESCRIPTION;
import static com.kidgeniusdesigns.reddit.parsers.BaseFeedParser.ITEM;
import static com.kidgeniusdesigns.reddit.parsers.BaseFeedParser.LINK;
import static com.kidgeniusdesigns.reddit.parsers.BaseFeedParser.PUB_DATE;
import static com.kidgeniusdesigns.reddit.parsers.BaseFeedParser.TITLE;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kidgeniusdesigns.reddit.main.RedditItem;


public class RssHandler extends DefaultHandler{
	private List<RedditItem> messages;
	private RedditItem currentMessage;
	private StringBuilder builder;
	
	public List<RedditItem> getMessages(){
		return this.messages;
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		super.endElement(uri, localName, name);
		if (this.currentMessage != null){
			if (localName.equalsIgnoreCase(TITLE)){
				currentMessage.setTitle(builder.toString());
			} else if (localName.equalsIgnoreCase(LINK)){
				currentMessage.setLink(builder.toString());
			} else if (localName.equalsIgnoreCase(DESCRIPTION)){
				currentMessage.setDescription(builder.toString());
			} else if (localName.equalsIgnoreCase(PUB_DATE)){
				currentMessage.setDate(builder.toString());
			} else if (localName.equalsIgnoreCase(ITEM)){
				messages.add(currentMessage);
			}
			builder.setLength(0);	
		}
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		messages = new ArrayList<RedditItem>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(ITEM)){
			this.currentMessage = new RedditItem();
		}
	}
}