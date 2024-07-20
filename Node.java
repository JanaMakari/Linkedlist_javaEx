/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playlistApplication;

/**
 *
 * @author dell
 */
public class Node {
    private Song song;
    private Node next;

    public Node(Song s) {
        this.song = s;
        this.next = null;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song s) {
        this.song = s;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    
}
