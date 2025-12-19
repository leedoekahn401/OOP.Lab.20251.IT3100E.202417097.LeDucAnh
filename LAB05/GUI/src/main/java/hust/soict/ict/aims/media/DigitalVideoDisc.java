package hust.soict.ict.aims.media;

import hust.soict.ict.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc() {
        super();
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, float cost, int length, String director) {
        super(title, category, cost, length, director);
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            System.err.println("ERROR: DVD length is non-positive!");
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "DVD[id=" + this.getId() + ", title=" + this.getTitle() + ", category=" + this.getCategory() + 
               ", cost=" + this.getCost() + ", length=" + this.getLength() + ", director=" + this.getDirector() + "]";
    }
}