package engine.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CyclicInputStream extends InputStream{

	private ArrayList<Byte> bytes = new ArrayList<Byte>();
	private int pointer;
	private InputStream is;
	public CyclicInputStream(InputStream is) throws IOException {
		this.is = is;
		pointer = 0;
	}
	
	
	@Override
	public int read() throws IOException {
		if(is.available()>0&&pointer>=bytes.size()){
			bytes.add((byte) is.read());
		}
		if(pointer<bytes.size()){
			byte b = bytes.get(pointer);
			pointer++;
			return b;
		}else{
			throw new IOException();
		}
	}
	@Override
	public int available() throws IOException {
		if(is.available()>0){
			return is.available();
		}else{
			return bytes.size()-pointer;
		}
	}
	
	@Override
	public synchronized void reset() throws IOException {
		pointer = 0;
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		int n = 0;
		try{
			for (int i = 0; i < b.length; i++) {
				b[i] = (byte)read();
				n++;
			}
		}catch (Exception e) {
			
		}
		return n;
	}
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int n = 0;
		try{
			for (int i = off; i < len; i++) {
				b[i] = (byte)read();
				n++;
			}
		}catch (Exception e) {
		}
		return n;
	}
	@Override
	public void close() throws IOException {
		pointer = bytes.size();
	}
	@Override
	public boolean markSupported() {
		return false;
	}
	@Override
	public long skip(long n) throws IOException {
		if(n+pointer<bytes.size()){
			pointer+=n;
			return n;
		}else{
			long i = pointer+n-bytes.size();
			pointer = bytes.size();
			return i;
		}
	}
	
}
