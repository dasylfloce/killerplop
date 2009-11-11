package map.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import map.maptiled.MapTiled;

public class MapTiledIO {

	public static void saveMap(MapTiled map, String path) {
		// Nous déclarons nos objets en dehors du bloc try/catch
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(new File(path))));
			
			//Ecriture de l'entête
			dos.writeChars("MapTiled");
			

			// Nous allons écrire chaque primitif
			dos.writeBoolean(true);
			dos.writeByte(100);
			dos.writeChar('C');
			dos.writeDouble(12.05);
			dos.writeFloat(100.52f);
			dos.writeInt(1024);
			dos.writeLong(123456789654321L);
			dos.writeShort(2);
			dos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MapTiled loadMap(String path) {
		DataInputStream dis;
		try {
			// On récupère maintenant les données !
			dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(new File(path))));

			System.out.println(dis.readBoolean());
			System.out.println(dis.readByte());
			System.out.println(dis.readChar());
			System.out.println(dis.readDouble());
			System.out.println(dis.readFloat());
			System.out.println(dis.readInt());
			System.out.println(dis.readInt());
			System.out.println(dis.readLong());
			System.out.println(dis.readShort());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void test() {
		// Nous déclarons nos objets en dehors du bloc try/catch
		DataInputStream dis;
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(new File("sdz.txt"))));

			// Nous allons écrire chaque primitif
			dos.writeBoolean(true);
			dos.writeByte(100);
			dos.writeChar('C');
			dos.writeDouble(12.05);
			dos.writeFloat(100.52f);
			dos.writeInt(1024);
			dos.writeInt(124);
			dos.writeLong(123456789654321L);
			dos.writeShort(2);
			dos.close();

			// On récupère maintenant les données !
			dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(new File("sdz.txt"))));

			System.out.println(dis.readBoolean());
			System.out.println(dis.readByte());
			System.out.println(dis.readChar());
			System.out.println(dis.readDouble());
			System.out.println(dis.readFloat());
			System.out.println(dis.readInt());
			System.out.println(dis.readInt());
			System.out.println(dis.readLong());
			System.out.println(dis.readShort());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		test();
	}
}
