package com.kidgeniusdesigns.reddit.parsers;
import java.util.List;

import com.kidgeniusdesigns.reddit.main.RedditItem;


public interface FeedParser {
	List<RedditItem> parse();
}
