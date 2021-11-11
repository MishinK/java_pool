import java.io.*;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Program {
    static File mainPath;
    public static void main(String[] args) {
        if(args.length != 1)
		{
            System.err.println("error: 1 arg expected");
			System.exit(-1);
		}
        String flag = args[0].substring(0, args[0].lastIndexOf("=") + 1);
        String path = args[0].substring(args[0].lastIndexOf("=") + 1);
        if(args[0].lastIndexOf("=") == -1 || !flag.equals("--current-folder=") || path.equals("")){
            System.err.println("expected: Program --current-folder=<path>");
			System.exit(-1);
        }
        getMainPath(path);
    }
    public static void getMainPath(String path) {
        mainPath = new File(path);
        if(!mainPath.exists() || !mainPath.isDirectory()) {
            System.err.println("error: bad path");
            System.exit(-1);
        }else {
			Scanner scan = new Scanner(System.in);
            System.out.println(mainPath.getAbsolutePath());
            while(scan.hasNext() == true) {
                String command = scan.next();
                if(command.equals("ls")) {
                    commandLs();
                }
                else if(command.equals("cd")) {
                    String subpath = scan.next();
                    commandCd(subpath);
                }
				else if(command.equals("mv")) {
                    String from = scan.next();
                    String to = scan.next();
                    commandMv(from, to);
                }
                else if(command.equals("exit")){
					scan.close();
                    System.exit(1);
                }
                else
                    System.err.println("command not found");
            }
			scan.close();
			System.exit(1);
        }
    }
    public static void commandCd(String subpath) {
        String newMainPath = mainPath.getAbsolutePath()+ "/" + subpath;
        File path = new File(newMainPath);
        if(!path.exists() || !path.isDirectory())
            System.err.println("folder does not exist");
        else {
            mainPath = path;
			System.out.println(mainPath.getAbsolutePath());
        }
    }
    public static void commandLs() {
        File[] children = mainPath.listFiles();
        for (File file : children) {
                String name = file.getName();
                long size = folderSize(file);
                System.out.println(name + " " + (size / 1024) + "KB");
        }
    }
    public static long folderSize(File directory) {
        long size = 0;
        if(directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile())
					size += file.length();
                else
					size += folderSize(file);
            }
        }
        else 
            size += directory.length();
        return size;
    }
	public static void commandMv(String from, String to) {
        File toIsPath = new File(mainPath.getAbsolutePath() + "/" + to);
		File fromIsPath = new File(mainPath.getAbsolutePath()+ "/" + from);
        if(fromIsPath.exists()){
			File[] children = mainPath.listFiles();
			if (toIsPath.isDirectory()) {
				for (File file : children) {
					if (file.getName().equals(from)) {
						String path = file.getAbsolutePath();
						path = changeDir(path, to) + "/" + from;
						try {
							Files.move(Paths.get(file.getAbsolutePath()), Paths.get(path));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
        	}
        	else
			{
				for (File file : children)
				{
					if(file.getName().equals(from)){
						String absPathFile = mainPath.getAbsolutePath() + "/" + from;
						File oldFile = new File(absPathFile);
						String newFilename = absPathFile.replace(from, to);
						if(!oldFile.renameTo(new File(newFilename)))
							System.err.println( "rename was failed");
					}
				}
			}
        }
        else 
            System.err.println("path does not exist");
    }
    private static String changeDir(String dirLocation, String newPath) {
        if (newPath.equals(".."))
            return (prevLevel(dirLocation));
        if (!newPath.startsWith("../"))
            return (dirLocation + "/" + newPath);
        return changeDir(prevLevel(prevLevel(dirLocation)), deletePath(newPath));
       
    }
    private static String deletePath(String toWhere) {
        return (toWhere.substring(toWhere.indexOf('/') + 1));
    }
    private static String prevLevel(String dirLocation) {
        return (dirLocation.substring(0, dirLocation.lastIndexOf('/')));
    }
}
