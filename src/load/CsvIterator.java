package load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class CsvIterator implements Iterator<String[]> {
	
	Scanner scanner;
	
	public CsvIterator(String filename) {
		File file = new File(filename);
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		if (scanner.hasNextLine()) {
			scanner.nextLine(); // ignore the column names
		}
	}

	@Override
	public boolean hasNext() {
		return scanner.hasNextLine();
	}

	@Override
	public String[] next() {
		String line = scanner.nextLine();
		return line.split(",", -1);
	}
	
	public void close() {
		scanner.close();
	}


}
