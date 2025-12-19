package hust.soict.ict.aims.media;

import java.util.Comparator;

/**
 * Comparator for sorting Media by cost (descending),
 * then by title (alphabetically) if costs are equal
 */
public class MediaComparatorByCostTitle implements Comparator<Media> {
    
    @Override
    public int compare(Media o1, Media o2) {
        // First, compare by cost in descending order
        int costComparison = Float.compare(o2.getCost(), o1.getCost());
        
        if (costComparison != 0) {
            return costComparison;
        }
        
        // If costs are equal, compare by title alphabetically
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
