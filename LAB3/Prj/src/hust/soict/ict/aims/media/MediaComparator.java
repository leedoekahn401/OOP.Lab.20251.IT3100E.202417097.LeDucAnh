package hust.soict.ict.aims.media;

import java.util.Comparator;

public class MediaComparator implements Comparator<Media> {
    public static final int BY_TITLE_COST = 1;
    public static final int BY_COST_TITLE = 2;
    private int sortingType;

    public MediaComparator(int sortingType) {
        this.sortingType = sortingType;
    }

    @Override
    public int compare(Media m1, Media m2) {
        if (sortingType == BY_TITLE_COST) {
            int titleCompare = m1.getTitle().compareTo(m2.getTitle());
            if (titleCompare != 0) {
                return titleCompare;
            }
            return Float.compare(m2.getCost(), m1.getCost());

        } else if (sortingType == BY_COST_TITLE) {
            int costCompare = Float.compare(m2.getCost(), m1.getCost());
            if (costCompare != 0) {
                return costCompare;
            }
            return m1.getTitle().compareTo(m2.getTitle());

        } else {
            return 0;
        }
    }
}