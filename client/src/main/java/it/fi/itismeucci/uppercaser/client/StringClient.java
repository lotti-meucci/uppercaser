package it.fi.itismeucci.uppercaser.client;
import java.io.*;
import java.net.*;

public class StringClient
{
	protected final String serverAddress;
	protected final int serverPort;

	public StringClient(String serverAddress, int serverPort)
	{
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
	}

	public String getServerAddress()
	{
		return this.serverAddress;
	}

	public int getServerPort()
	{
		return this.serverPort;
	}

	public String send(String string)
	{
		try
		{
			if (string == null)
				string = "";

			Socket socket = new Socket(getServerAddress(), getServerPort());
			DataOutput out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(string.length());
			out.writeBytes(string);
			DataInput in = new DataInputStream(socket.getInputStream());
			byte[] bytes = new byte[in.readInt()];
			in.readFully(bytes);
			socket.close();
			return new String(bytes);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
