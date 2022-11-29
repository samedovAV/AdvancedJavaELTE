import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import serialized.SomeData;

public class SaveToFile {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try (
			var oos = new ObjectOutputStream(new FileOutputStream("data.dat"));
		) {
			oos.writeObject(46538);
			oos.writeObject("ghkjdsgfydsk");

			var someData = new SomeData(213, "fdhsaf", new double[] {0.1, -1.245, Math.PI, Math.E});
			oos.writeObject(someData);
		}

		try (
			var ois = new ObjectInputStream(new FileInputStream("data.dat"));
		) {
			var num = (Integer)ois.readObject();
			var txt = (String)ois.readObject();

			System.out.println("This is the int+1: " + (num+1));
			System.out.println("This is the text: " + txt + " and its length is " + txt.length());

			var loadedData = (SomeData)ois.readObject();
			System.out.println(loadedData);
		}

	}
}
