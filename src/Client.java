import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
  public static void main(String[] args2) {

    try {
      Socket echoSocket = new Socket("localhost", 8080);   //Set host to localhost or find host name via ipconfig/all using command prompt

      //get input/output from server
      PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
      //get input from client (stdIn)
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

      String userInput = null;
      //print out valid selections for the user to select
      System.out.println("- Enter mq for a 5 question multiplication quiz");
      System.out.println("- Enter rps (choice) to play rock paper scissors, Valid Choices: rock, paper, scissors");
      System.out.println("- Enter sphereVolume (radius) to compute the volume of a sphere");
      System.out.println("- Enter conch (yes/no question) to hear what the magic conch shell has to say about your question");

      //start reading user input
      while ((userInput = stdIn.readLine()) != null)  {
        out.println(userInput); //send input to server

        System.out.println("Server Sent: \n" + in.readLine());//print output from server

      }

    } catch (UnknownHostException e) { //exit if exception caught
      System.exit(1);
    } catch (IOException e) {
      System.exit(1);
    }
  }
}
