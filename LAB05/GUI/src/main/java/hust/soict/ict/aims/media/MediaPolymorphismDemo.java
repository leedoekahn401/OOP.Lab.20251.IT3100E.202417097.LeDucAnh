package hust.soict.ict.aims.media;

import hust.soict.ict.aims.media.*;
import java.util.ArrayList;

public class MediaPolymorphismDemo {
    public static void main(String[] args) {

        ArrayList<Media> mediaList = new ArrayList<>();

        Book book1 = new Book();
        book1.setTitle("Java Programming");
        book1.setCategory("Education");
        book1.setCost(29.99f);

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Avengers", "Action", 15.99f, 120, "Joss Whedon");

        CompactDisc cd1 = new CompactDisc();
        cd1.setTitle("Greatest Hits");
        cd1.setCategory("Music");
        cd1.setCost(12.99f);
        cd1.setArtist("Queen");

        Track track1 = new Track("Bohemian Rhapsody", 6);
        Track track2 = new Track("We Will Rock You", 3);
        cd1.addTrack(track1);
        cd1.addTrack(track2);

        mediaList.add(book1);
        mediaList.add(dvd1);
        mediaList.add(cd1);

        for (Media m : mediaList) {
            System.out.println(m.toString());
        }
    }
}