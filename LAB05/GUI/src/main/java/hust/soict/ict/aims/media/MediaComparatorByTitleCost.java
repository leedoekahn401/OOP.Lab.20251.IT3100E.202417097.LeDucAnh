package hust.soict.ict.aims.media;

import java.util.Comparator;

/**
 * Comparator for sorting Media by title (alphabetically),
 * then by cost (descending) if titles are equal
 */
public class MediaComparatorByTitleCost implements Comparator<Media> {
    
    @Override
    public int compare(Media o1, Media o2) {
        // First, compare by title alphabetically
        int titleComparison = o1.getTitle().compareTo(o2.getTitle());
        
        if (titleComparison != 0) {
            return titleComparison;
        }
        
        // If titles are equal, compare by cost in descending order
        // Higher cost comes first (negative of Float.compare)
        return Float.compare(o2.getCost(), o1.getCost());
    }
}
