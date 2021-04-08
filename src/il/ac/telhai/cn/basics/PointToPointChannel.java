package il.ac.telhai.cn.basics;

import javax.management.RuntimeErrorException;

public class PointToPointChannel extends Channel {
	private int elapsed,delay,delivered,buffer,transit ,chunk;
	public PointToPointChannel(int bandwidth,int delay) {
		super(bandwidth);
		this.delay=delay;
		// TODO Auto-generated constructor stub
	}

	public static final int BITS_PER_BYTE = 8;
	
	public void tick(int msecs) {
		elapsed+=msecs;
		buffer-= buffer>0? chunk*msecs : 0;
		transit+=elapsed<=delay? bandwidth*msecs :0;
		if (elapsed>=delay) {
		if (buffer==0) {
			
		}
			transit-=buffer==0? bandwidth*msecs:0;
			delivered=(elapsed-delay)*chunk;
		}
		
		
	}
				
				
	public String toString() {
//		return null;
		return "Time Elapsed:" + elapsed + " msecs, Input Buffer:" 
		+ buffer+ " bytes, In Transit:" + transit + " bits, Delivered:" + delivered+ " bytes";
	}


	public void send(int bytesToSend) {
		// TODO Auto-generated method stub
		if (buffer!=0) {
			throw new RuntimeException("Buffer is not empty yet");
		}
		buffer=bytesToSend;
		elapsed=0;
		transit=0;
		delivered=0;
		chunk =bandwidth / BITS_PER_BYTE;
		
	}


	public boolean isEmpty() {
	return buffer==0 && transit==0;
		// TODO Auto-generated method stub
//		return false;
	}
}