package Innlevering_1.io.extern_input;

import java.util.Scanner;

public class TerminalInput
{
    private Scanner input;

    public TerminalInput()
    {
        input = new Scanner(System.in);
    }

    public String getTerminalInput() { return (input.hasNext()) ? input.nextLine() : null; }

    public void close()
    {
        input.close();
    }
}
