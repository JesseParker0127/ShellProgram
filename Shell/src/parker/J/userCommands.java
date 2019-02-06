package parker.J;

import java.nio.file.DirectoryStream;
import java.nio.file.*;
import java.util.Scanner;

public class userCommands {
	
	public String displayPrompt(Scanner input)
	{
		System.out.print("prompt> ");
		return input.nextLine().trim();
	}
	
	public void helpMenu()
	{
		System.out.printf("%nCOMMANDS:%n"
				  + "  help%30s%n  dir  [path]%28s%n  show file%25s%n  exit%23s%n",
				  "Show list of commands","List contents of directory", 
				  "Show contents of file", "Exit the shell");
	}
	
	public void listDir() throws Exception
	{
		Path path = Paths.get(".").toAbsolutePath();
		System.out.printf("%nDirectory of \"%s\"%n", path);
		
		if (Files.isDirectory(path)) 
		{
            DirectoryStream<Path> children = Files.newDirectoryStream(path);
           
            for (Path p : children)
            {
            	if (Files.isRegularFile(p))
            		System.out.printf("%-5s%,10d %-20s%n", Files.isDirectory(p) ? "d" : " ", 
            				Files.size(p), p.getFileName());
            	else
            		System.out.printf("%-15s %-20s%n", Files.isDirectory(p) ? "d" : " ", 
            				p.getFileName());
            }
            System.out.printf("%n");
        } 
		else
			System.out.printf("%nerror: Path is not a directory: \"%s\"%n", path);

	}
	
	public void listDirPath(String userInput) throws Exception
	{
		// Convert userInput to charArray
		char[] pathArray = userInput.toCharArray();
				
		// Get path from the user's input after removing the directory
		Path path = Paths.get(String.valueOf(pathArray, 4, (pathArray.length - 4)));
		
		// does exist
		if (Files.exists(path))
		{
			if (Files.isDirectory(path)) 
			{
				System.out.printf("%nDirectory of \"%s\"%n", path);
		
				DirectoryStream<Path> children 
										= Files.newDirectoryStream(path);
           
				for (Path p : children)
				{
					if (Files.isRegularFile(p))
						System.out.printf("%-5s%,10d %-20s%n", 
								Files.isDirectory(p) ? "d" : " ", 
								Files.size(p), p.getFileName());
					else
						System.out.printf("%-15s %-20s%n", 
								Files.isDirectory(p) ? "d" : " ", 
								p.getFileName());
				}
				System.out.printf("%n");
			
			} 
			else 
				System.out.printf("%nerror: Path is not a directory: \"%s\"%n", path);
		}
		else
			System.out.printf("%nerror: Path does not exist: \"%s\"%n", path);
	}
	
	public void showFile(String userInput) throws Exception
	{
		//char array
		char[] pathArray = userInput.toCharArray();
		
		// remove show and get path
		Path path = Paths.get(String.valueOf(pathArray, 5, (pathArray.length - 5)));
		
		// test if file exist
		if (!Files.exists(path)) 
		{
            System.out.printf("%nerror: Does not exist: \"%s\"%n", path);
		}
		else
		{
			//checking if regular file
			if (Files.isRegularFile(path))
			{
				System.out.printf("%nSHOW: \"%s\"%n%n", path);
				
				try (Scanner reader = new Scanner(path))
				{
					while (reader.hasNext()) 
					{
						String line = reader.nextLine();
						System.out.println(line);
					}
				}
			}
		
			else
				System.out.printf("%nerror: Path is not a regular file: \"%s\"%n", path);
		}
	}
}
