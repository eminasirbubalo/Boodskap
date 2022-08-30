package boodskap;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

//this class will handle multiple clients being connected to the same server at the same time

public class ClientHandler implements Runnable{
	
	public static ArrayList<ClientHandler> clients=new ArrayList();
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String username;
	
	public ClientHandler(Socket socket)
	{
		try {
			this.socket=socket;
			this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream())); //return input stream of a socket
			this.writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //return output stream of a socket
			this.username=reader.readLine();
			clients.add(this);
			broadcast("Boodie "+username +" has joined! Say Hi!"); 
			
		}
		catch(Exception e)
		{
			close(socket,reader,writer);
		}
		
	}

	@Override
	public void run() {
		String message;
		
		while(socket.isConnected())
		{
			try
			{
				message=reader.readLine();    //blocking operation , why we need threads
				broadcast(message);
			}
			catch(Exception e) {
				close(socket,reader,writer);
				break;
			}
		}
		
	}
	
	public void broadcast(String sendmessage)
	{
		for(ClientHandler client: clients)
		{
			try
			{
				if(this!=client)
				{
					client.writer.write(sendmessage);
					client.writer.newLine();
					client.writer.flush();
				}
			} 
			catch(Exception e)
			{
				close(socket,reader,writer);
			}
		}
	}
	
	public void removeclient()
	{
		clients.remove(this);
		broadcast("Boodie " +username+ " has left the chat. ");
	}
	
	public void close(Socket socket, BufferedReader reader, BufferedWriter writer)
	{
		removeclient();
		try
		{
			if(reader!=null)
			{
				reader.close();
			}
			if(writer!=null)
			{
				writer.close();
			}
			if(socket!=null)
			{
				socket.close();
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
