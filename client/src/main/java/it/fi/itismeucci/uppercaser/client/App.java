package it.fi.itismeucci.uppercaser.client;
import java.util.*;

public class App
{
	public static final Scanner IN = new Scanner(System.in);
	public static final StringClient SC = new StringClient("127.0.0.1", 60005);

	public static void main(String[] args) throws Exception
	{
		System.out.println("Uppercaser");

		for (;;)
		{
			System.out.print("> ");
			String response = SC.send(IN.nextLine());
			System.out.println(response == null ? "Communication error." : response);
		}
	}
}
