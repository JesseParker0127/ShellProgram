package parker.J;
import java.util.Scanner;

public class ShellProject {

	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		userCommands userCommand = new userCommands();
		String shellInput = "";
		
		// Welcome message
		System.out.println("Velcome to zee Shell!");
		
		// Display prompt
		shellInput = userCommand.displayPrompt(input);
		
		while (!shellInput.equalsIgnoreCase("exit"))
		{
			if (shellInput.equals(""))
				shellInput = userCommand.displayPrompt(input);
						//Help menu
			else if (shellInput.equalsIgnoreCase("help"))
				userCommand.helpMenu();
			 			// List your dir
			else if (shellInput.equalsIgnoreCase("dir"))
				userCommand.listDir();
						// List the dir from path
			else if (shellInput.contains("dir"))
				userCommand.listDirPath(shellInput);
						// Error checking
			else if (shellInput.equalsIgnoreCase("show"))
				System.out.println("\nERROR: [file] parameter missing. "
						+ "Execute 'help' for more information");
						// Show what's in the file
			else if (shellInput.contains("show"))
				userCommand.showFile(shellInput);
						// Valid command error checking
			else 
			{
				System.out.printf("%n%nPlease enter a valid command"
						+ " %n");
				userCommand.helpMenu();
			}
			
			
			shellInput = userCommand.displayPrompt(input);
		}
		
		System.out.println("\nGood bye!");


	}

}
