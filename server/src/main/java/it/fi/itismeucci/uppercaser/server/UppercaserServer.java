package it.fi.itismeucci.uppercaser.server;
import java.io.*;

public class UppercaserServer extends StringServer
{
	public UppercaserServer(int port) throws IOException
	{
		super(port);
	}

	@Override
	public String processRequest(String string)
	{
		return string == null ? "" : string.toUpperCase();
	}
}
