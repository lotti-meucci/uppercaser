package it.fi.itismeucci.uppercaser.server;
import java.util.*;

public class App
{
	public static final int PORT = 60005;
	public static final Scanner IN = new Scanner(System.in);

	public static void main(String[] args) throws Exception
	{
		System.out.println("Uppercaser Server");
		UppercaserServer us = new UppercaserServer(PORT);
		new Thread(us).start();
		System.out.println("Server started on port " + us.getPort());
		System.out.print("Stop? ");
		IN.nextLine();
		us.stop();
		System.out.println("Server stopped");
	}
}
