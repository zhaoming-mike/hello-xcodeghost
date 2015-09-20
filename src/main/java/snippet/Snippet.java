package snippet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Snippet {
	public static byte[] readBytes(InputStream in) throws IOException {
			BufferedInputStream bufin = new BufferedInputStream(in);
			int buffSize = 1024;
			ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
	
			// System.out.println("Available bytes:" + in.available());
	
			byte[] temp = new byte[buffSize];
			int size = 0;
			while ((size = bufin.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			bufin.close();
	
			byte[] content = out.toByteArray();
			return content;
		}
}

