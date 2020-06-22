package load;

import java.util.Iterator;

// wrapper class for CsvIterator, since for-each loop requires
// an instance of Iterable and not Iterator

public class CsvIterable implements Iterable<String[]> {
	CsvIterator iterator;
	
	public CsvIterable(String filename) {
		iterator = new CsvIterator(filename);
	}

	@Override
	public Iterator<String[]> iterator() {
		return iterator;
	}
	
	public void close() {
		iterator.close();
	}
	
	
}
