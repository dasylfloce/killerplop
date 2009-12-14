package fr.emn.killerplop.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class ImportUsed {

	protected static ImportUsed importUsed;

	protected HashMap<String, Import> imports = new HashMap<String, Import>();
	protected LinkedList<String> srcPackages = new LinkedList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		importUsed = new ImportUsed();
		importUsed.start(new File("src"));
		System.out.println("\n\n************************************");
		importUsed.printResult();
	}

	public void start(File root) {
		if(!root.exists()) {
			System.err.println("File"+ root.getAbsolutePath()+" invalid !");
			System.exit(-1);
		}
		scanDirectory(root, 0);
	}

	public void scanDirectory(File root, int niveau) {
		System.out.println(getTab(niveau) + "Package " + root.getName());
		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				if (!file.getName().startsWith(".")) {
					if (niveau == 0)
						srcPackages.add(file.getName());
					scanDirectory(file, niveau + 1);
				}
			}
			if (file.isFile()) {
				if (file.getName().endsWith(".java")
						&& !file.getName().equals("ImportUsed.java"))
					try {
						scanFile(file, niveau + 1);
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}

	public void scanFile(File file, int niveau) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(file));
		String s;
		while ((s = f.readLine()) != null) {
			if (s.startsWith("import")) {
				s = s.substring(7);
				System.out.println(getTab(niveau + 1) + s);
				Import i = imports.get(s);
				if (i == null) {
					i = new Import(s);
					imports.put(s, i);
				}
				i.addUse(file);
			}
		}
		System.out.println(getTab(niveau) + file.getName());
	}

	public void printResult() {
		LinkedList<Import> allImports = new LinkedList<Import>(imports.values());
		Collections.sort(allImports);
		for (Import i : allImports) {
			if (!srcPackages.contains(i.getSrcPackage()))
				System.out.println(i.sumUp());
		}
	}

	public String getTab(int niveau) {
		String str = "";
		for (int i = 0; i < niveau; i++)
			str += "\t";
		return str;
	}

}

class Import implements Comparable<Import> {

	protected String importName;
	protected LinkedList<File> uses;

	public Import(String importName) {
		this.importName = importName;
		uses = new LinkedList<File>();
	}

	public void addUse(File file) {
		uses.add(file);
	}

	public String getImportName() {
		return importName;
	}

	public String getSrcPackage() {
		return importName.substring(0, importName.indexOf('.'));
	}

	public String sumUp() {
		StringBuilder str = new StringBuilder(importName + "\t(");
		for (File f : uses)
			str.append(f.getName() + "; ");
		return str.toString() + ")";
	}

	@Override
	public int compareTo(Import arg0) {
		return importName.compareTo(arg0.importName);
	}
}