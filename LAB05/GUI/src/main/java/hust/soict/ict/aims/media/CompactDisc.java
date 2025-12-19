package hust.soict.ict.aims.media;

import java.util.ArrayList;
import hust.soict.ict.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc() {
        super();
    }

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    // ===== Thêm setter =====
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track already exists: " + track.getTitle());
        } else {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track t : tracks) {
            totalLength += t.getLength();
        }
        return totalLength;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle() + " by " + this.getArtist());
            System.out.println("Total CD length: " + this.getLength());
            System.out.println("Tracks:");
            java.util.Iterator iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = (Track) iter.next();
                try {
                    nextTrack.play();
                } catch (PlayerException e) {
                    // Print comprehensive error information for track playback
                    System.err.println("\n=== TRACK PLAYBACK ERROR ===");
                    System.err.println("CD Title: " + this.getTitle());
                    System.err.println("Track Title: " + nextTrack.getTitle());
                    System.err.println("Track Length: " + nextTrack.getLength() + " seconds");
                    System.err.println("Error Message: " + e.getMessage());
                    System.err.println("Exception Type: " + e.toString());
                    System.err.println("Stack Trace:");
                    e.printStackTrace();
                    System.err.println("===========================\n");
                    throw e;
                }
            }
        } else {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "CD[id=" + this.getId() + ", title=" + this.getTitle() + ", category=" + this.getCategory() + 
               ", cost=" + this.getCost() + ", artist=" + artist + "]";
    }
}