/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package playlistApplication;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        Scanner input = new Scanner(System.in);
        
        boolean running = true;
        while (running) 
        {
            System.out.println("\n--- Playlist Menu ---");
            System.out.println("1. Add a song");
            System.out.println("2. Remove a song");
            System.out.println("3. Display playlist");
            System.out.println("4. Search for a song");
            System.out.println("5. Count songs");
            System.out.println("6. Sort playlist by title");
            System.out.println("7. Sort playlist by duration");
            System.out.println("8. Shuffle playlist");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine();
            String title;
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter song title: ");
                    title = input.nextLine();

                    boolean again;
                    do 
                    {
                        System.out.print("Enter song duration (in seconds): ");
                        again = false;
                        try 
                        {
                            int duration = input.nextInt();
                            input.nextLine();  
                            playlist.addSong(title, duration);
                        } catch (Exception e) 
                        {
                            System.out.println("Invalid. Try again.");
                            input.nextLine();  
                            again = true;
                        }
                    } while (again);
                    break;
                case 2:
                    System.out.print("Enter song title: ");
                    title = input.nextLine();
                    playlist.removeSong(title);
                    break;
                case 3:
                    System.out.print(playlist.displayPlaylist());
                    break;
                case 4:
                    System.out.print("Enter song title: ");
                    title = input.nextLine();
                    Song song = playlist.getSong(title);
                    if(null != song) System.out.println(song.toString());
                    break;
                case 5:
                    System.out.print("This playlist has "+playlist.countSongs()+" songs.");
                    break;
                case 6:
                    playlist.sortPlaylistByTitle();
                    System.out.println(playlist.displayPlaylist());
                    break;
                case 7:
                    playlist.sortPlaylistByDuration();
                    System.out.println(playlist.displayPlaylist());
                    break;
                case 8:
                    playlist.shufflePlaylist();
                    System.out.println(playlist.displayPlaylist());
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        input.close();
    
    }
}
