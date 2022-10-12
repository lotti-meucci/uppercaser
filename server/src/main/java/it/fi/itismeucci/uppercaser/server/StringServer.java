package it.fi.itismeucci.uppercaser.server;
import java.io.*;
import java.net.*;

public abstract class StringServer implements Runnable
{
	private final ServerSocket listener;

	public StringServer(int port) throws IOException
	{
		listener = new ServerSocket(port);
	}

	public int getPort()
	{
		return listener.getLocalPort();
	}

	public void run()
	{
		try
		{
			for	(;;)
			{
				Socket socket = listener.accept();
				new Thread(() -> handleSocket(socket)).start();
			}
		}
		catch (Exception e) { }
	}

	public void stop()
	{
		try
		{
			listener.close();
		}
		catch (Exception e) { }
	}

	protected void handleSocket(Socket socket)
	{
		if (socket == null)
			return;

		try
		{
			DataInput in = new DataInputStream(socket.getInputStream());
			DataOutput out = new DataOutputStream(socket.getOutputStream());

			for (;;)
			{
				if (socket.isClosed())
					break;

				byte[] bytes = new byte[in.readInt()];
				in.readFully(bytes);
				String response = processRequest(new String(bytes));
				out.writeInt(response.length());
				out.writeBytes(response);
			}
		}
		catch (Exception e) { }
	}

	public abstract String processRequest(String string);
}
