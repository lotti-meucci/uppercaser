package it.fi.itismeucci.uppercaser.client;
import java.util.*;

public class App
{
	public static final Scanner IN = new Scanner(System.in);

	public static void main(String[] args) throws Exception
	{
		StringClient sc = new StringClient("127.0.0.1", 60005);
		System.out.println("Uppercaser");

		for (;;)
		{
			System.out.print("> ");
			String string = IN.nextLine();

			if (string.equals("FINE"))
				break;

			string = sc.send(string);
			System.out.println(string == null ? "Communication error." : string);
		}
	}
}
