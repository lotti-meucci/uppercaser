package it.fi.itismeucci.uppercaser.server;
import java.util.*;

public class App
{
	public static final int PORT = 60005;
	public static final Scanner IN = new Scanner(System.in);

	public static void main(String[] args) throws Exception
	{
		System.out.println("Uppercaser Server");

		try
		{
			UppercaserServer us = new UppercaserServer(PORT, () ->
			{
				System.out.println("Server stopped by a client");
			});

			new Thread(us).start();
			System.out.println("Server started on port " + us.getPort());
		}
		catch (Exception e)
		{
			System.out.println("Unable to create server");
		}
	}
}
