package it.fi.itismeucci.uppercaser.client;
import java.io.*;
import java.net.*;

public class StringClient
{
	protected final String serverAddress;
	protected final int serverPort;
	private final Socket socket;
	private final DataOutput out;
	private final DataInput in;

	public StringClient(String serverAddress, int serverPort) throws
		UnknownHostException,
		IOException
	{
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.socket = new Socket(getServerAddress(), getServerPort());
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
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
				return "";

			out.writeInt(string.length());
			out.writeBytes(string);
			byte[] bytes = new byte[in.readInt()];
			in.readFully(bytes);
			return new String(bytes);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
