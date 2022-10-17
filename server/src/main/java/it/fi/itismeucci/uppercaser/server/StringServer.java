package it.fi.itismeucci.uppercaser.server;
import java.util.*;
import java.io.*;
import java.net.*;

public abstract class StringServer implements Runnable
{
	private final ServerSocket listener;
	private final ArrayList<Socket> sockets = new ArrayList<>();
	protected final Runnable serverStopped;

	public StringServer(int port, Runnable serverStopped) throws IOException
	{
		listener = new ServerSocket(port);
		this.serverStopped = serverStopped;
	}

	public StringServer(int port) throws IOException
	{
		this(port, null);
	}

	public int getPort()
	{
		return listener.getLocalPort();
	}

	public boolean isRunning()
	{
		return !listener.isClosed();
	}

	public void run()
	{
		while (isRunning())
		{
			try
			{
				Socket socket = listener.accept();
				sockets.add(socket);

				new Thread(() ->
				{
					handleSocket(socket);
					sockets.remove(socket);
					socketClosed();
				}).start();
			}
			catch (Exception e)
			{
				if (!isRunning())
					break;
			}
		}

		if (serverStopped != null)
			serverStopped.run();
	}

	public void stop()
	{
		try
		{
			listener.close();
		}
		catch (Exception e) { }

		for (Socket socket : sockets)
		{
			try
			{
				socket.close();
			} catch (Exception e) { }
		}

		sockets.clear();
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

				if (!requestHandled(response))
					break;
			}
		}
		catch (Exception e) { }
	}

	public abstract String processRequest(String string);
	public abstract boolean requestHandled(String response);
	public abstract void socketClosed();
}
