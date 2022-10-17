package it.fi.itismeucci.uppercaser.server;
import java.io.*;

public class UppercaserServer extends StringServer
{
	private boolean running = true;

	public UppercaserServer(int port) throws IOException
	{
		super(port);
	}

	public UppercaserServer(int port, Runnable serverStopped) throws IOException
	{
		super(port, serverStopped);
	}

	@Override
	public String processRequest(String string)
	{
		if (string.equals("SPEGNI"))
		{
			running = false;
			return "";
		}

		return string == null ? "" : string.toUpperCase();
	}

	@Override
	public void socketClosed()
	{
		if (!running)
			stop();
	}

	@Override
	public boolean requestHandled(String response)
	{
		return running;
	}
}
