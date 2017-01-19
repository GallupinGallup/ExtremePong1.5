package model;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server {
	
	 private ObjectOutputStream output;
	 private ObjectInputStream input;
	 private ServerSocket server;
	 private Socket connection;
	 
	//set up and run the server
	 public void startRunning(){
		 try{
			 server = new ServerSocket(6666);
			 while(true){
				 try{
					 waitForConnection();
					 setupStreams();
					 whilePlaying();
				 }catch(EOFException eofException){
					 showMessage("\n Server ended the connection!");
				 }finally{
					 closeCrap();
				 }
			 }
		 }catch(IOException ioException){
			 ioException.printStackTrace();
		 }
	 }
	 //wait for connection, then display connection information
	 private void waitForConnection() throws IOException{
		 showMessage("Waiting for someone to connect! \n");
		 connection = server.accept();
		 showMessage("now connected" + connection.getInetAddress().getHostName());
	 }
	 
	 //get stream to send and receive data
	 private void setupStreams() throws IOException{
		 output = new ObjectOutputStream(connection.getOutputStream());
		 output.flush();
		 input = new ObjectInputStream(connection.getInputStream());
		 showMessage("\n Streams are now setup! \n");
	 }
	 //during the game
	 private void whilePlaying() throws IOException{
		 String message = "You are now connected";
		 showMessage(message);
	 }
	 
	 //close streams and sockets after done.
	 private void closeCrap(){
		 showMessage("\n Closeing connection...\n");
		 try{
			 output.close();
			 input.close();
			 connection.close();
		 }catch(IOException ioException){
			 ioException.printStackTrace();
		 }
	 }
	 //show a message to player
	 private void showMessage(String message){
		 
	 }
	 
}
