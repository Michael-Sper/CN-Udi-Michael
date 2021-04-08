package il.ac.telhai.cn.chapter2_io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

public class NumberGuesser implements Runnable {

	static PipedOutputStream po;
	static PipedInputStream pi;
	private int upper_lim, lower_lim, guess;
	private boolean inner_lock = false;

	public NumberGuesser(PipedInputStream pis, PipedOutputStream pos) throws IOException {
		po = pos;
		pi = pis;
		pi.connect(po);
		upper_lim = Integer.MAX_VALUE;
		lower_lim = 0;
	}
	

	
	@Override
	public void run() {
		Writer guessWriter = new OutputStreamWriter(po);
		InputStreamReader reader = new InputStreamReader(pi);
		int sign=3;
		int x=3;

		try {
		guessWriter.flush();
			while((sign =reader.read())!='=') {

			switch (sign) {
			case '<':
				System.out.println("smaller");
				break;
				
			case '>':
				System.out.println("bigger");

			default:
				break;
			}
			guessWriter.write(x+' ');
			guessWriter.flush();
			}
			
			
//			System.out.println(scanner.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}
