/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playlistApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Playlist {
    private Node head;

    public Playlist() {
        this.head = null;
    }
    
    public void addSong(String title , int duration)
    {
        Song s = new Song(title,duration);
        Node node = new Node(s);
        if(head == null)
        {
            head = node;
        }
        else
        {
            Node pointer = head;
            while(pointer.getNext() != null)
            {
                pointer = pointer.getNext();
            }
            pointer.setNext(node);
        }
    }
    
    public void removeSong(String title)
    {
        if(title == null || title.trim().isEmpty())
        {
            System.out.println("Invalid title.");
            return;
        }
        if(head == null)
        {
            System.out.println("The playlist is empty.");
            return;
        }
        String titlevalue = title.trim();
        Node current = head;
        Node prev = null;
        boolean found = false;
        
        while(current != null)
        {
            Song song = current.getSong();
            if(song.getTitle().equalsIgnoreCase(titlevalue))
            {
                found = true;
                if(current == head)
                {
                    head = head.getNext();
                }
                else
                {
                    prev.setNext(current.getNext());
                }
            }
            prev = current;
            current = current.getNext();
        }
        
        if(found)
        {
            System.out.println("Removed Song : " + titlevalue);
        }
        else
        {
            System.out.println("Song not found : " + titlevalue);
        }
    }
    
    public int countSongs() 
    {
        Node current = head;
        int count = 0;
        while (current != null) 
        {
            count++;
            current = current.getNext();
        }
        return count;
    }
    
    public String displayPlaylist()
    {
        if(head == null)
        {
            return "The playlist is empty.";
        }
        int count = 0;
        StringBuilder sb = new StringBuilder("This playlist has " + this.countSongs() + " Songs:\n");
        Node current = head;
        while (current != null) 
        {
            count++;
            sb.append("Song #").append(count).append(":\n")
              .append("Title: ").append(current.getSong().getTitle()).append("\n")
              .append("Duration: ").append(current.getSong().getDuration()).append(" seconds\n\n");
            current = current.getNext();
        }
        return sb.toString();
    }
    
    public Song getSong(String title)
    {
        if(title == null || title.trim().isEmpty())
        {
            System.out.println("Invalid title.");
            return null;
        }
        if(head == null)
        {
            System.out.println("The playlist is empty.");
            return null;
        }
        
        Node current = head;
        title = title.trim();
        while(current != null)
        {
            Song song = current.getSong();
            if(song.getTitle().equalsIgnoreCase(title))
            {
                return song;
            }
            current = current.getNext();
        }
        
        System.out.println("Song not found: " + title);
        return null;
    }
    
    public void sortPlaylistByTitle() 
    {
        if (head == null || head.getNext() == null) return;
        head = mergeSortByTitle(head);
    }

    private Node mergeSortByTitle(Node head) 
    {
        if (head == null || head.getNext() == null) return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.getNext();
        middle.setNext(null);
        Node left = mergeSortByTitle(head);
        Node right = mergeSortByTitle(nextOfMiddle);
        return sortedMergeByTitle(left, right);
    }

    private Node sortedMergeByTitle(Node left, Node right) 
    {
        if (left == null) return right;
        if (right == null) return left;
        Node result;
        if (left.getSong().getTitle().compareToIgnoreCase(right.getSong().getTitle()) <= 0) {
            result = left;
            result.setNext(sortedMergeByTitle(left.getNext(), right));
        } else {
            result = right;
            result.setNext(sortedMergeByTitle(left, right.getNext()));
        }
        return result;
    }

    
    public void sortPlaylistByDuration() 
    {
    if (head == null || head.getNext() == null) return;
    head = mergeSortByDuration(head);
    }

    private Node mergeSortByDuration(Node head) 
    {
        if (head == null || head.getNext() == null) 
        {
            return head;
        }

        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.getNext();
        middle.setNext(null); 

        Node left = mergeSortByDuration(head); 
        Node right = mergeSortByDuration(nextOfMiddle);

        return sortedMergeByDuration(left, right);
    }

    private Node sortedMergeByDuration(Node left, Node right) 
    {
        if (left == null) return right;
        if (right == null) return left;

        Node result;

        if (left.getSong().getDuration() <= right.getSong().getDuration()) 
        {
            result = left;
            result.setNext(sortedMergeByDuration(left.getNext(), right));
        } 
        else 
        {
            result = right;
            result.setNext(sortedMergeByDuration(left, right.getNext()));
        }
        return result;
    }

    private Node getMiddle(Node head) 
    {
        if (head == null) return head;

        Node slow = head, fast = head.getNext();
        while (fast != null && fast.getNext() != null) 
        {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
    
    public void shufflePlaylist() {
        if (head == null || head.getNext() == null) return;

        List<Song> songs = new ArrayList<>();
        Node current = head;
        while (current != null) 
        {
            songs.add(current.getSong());
            current = current.getNext();
        }

        Random random = new Random();
        for (int i = songs.size() - 1; i > 0; i--) 
        {
            int j = random.nextInt(i + 1);
            Song temp = songs.get(i);
            songs.set(i, songs.get(j));
            songs.set(j, temp);
        }

        head = null;
        for (Song song : songs) 
        {
            this.addSong(song.getTitle(), song.getDuration());
        }
    }
}
