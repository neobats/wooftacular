package scoringSheet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountLines {
	// Thanks to user martinus on Stack Overflow for this.
	public static int linesInFile(File fileName) throws IOException{
		InputStream is = new BufferedInputStream(new FileInputStream(fileName));
		try {
			byte[] c = new byte[1024];

			int readChars = is.read(c);
			if (readChars == -1) {
				// bail out if nothing to read
				return 0;
			}

			// make it easy for the optimizer to tune this loop
			int count = 0;
			while (readChars == 1024) {
				for (int i = 0; i < 1024;) {
					if (c[i++] == '\n') {
						count++;
					}
				}
				readChars = is.read(c);
			}

			// count remaining characters
			while (readChars != -1) {
				for (int i = 0; i < readChars; i++) {
					if (c[i] == '\n') {
						count++;
					}
				}
				readChars = is.read(c);
			}

			return count == 0 ? 1 : count;
		} finally {
			is.close();
		}
	}
}
