package it.fi.itismeucci.uppercaser.client;
import java.util.*;

public class App
{
	public static final Scanner IN = new Scanner(System.in);

	public static void main(String[] args) throws Exception
	{
		System.out.println("Uppercaser");
		StringClient sc = null;

		while (sc == null)
		{
			try
			{
				sc = new StringClient("127.0.0.1", 60005);
			}
			catch (Exception e)
			{
				System.out.println("Server unreachable. Retrying...");
				Thread.sleep(5000);
			}
		}

		for (;;)
		{
			System.out.print("> ");
			String string = IN.nextLine();

			if (string.equals("FINE"))
				break;

			String response = sc.send(string);

			if (string.equals("SPEGNI"))
				break;

			System.out.println(response == null ? "Communication error." : response);
		}
	}
}
