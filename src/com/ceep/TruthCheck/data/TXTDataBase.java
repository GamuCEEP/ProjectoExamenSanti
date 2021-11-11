package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.exceptions.TXT.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TXTDataBase implements DataBase {

	@Override
	public void createDatabase(String filename) throws FileWriteException {
		try {
			Path dir = Paths.get(filename);
			Files.createDirectories(dir);
		} catch (IOException ex) {
			throw new FileWriteException("Error while creating the Database " + filename);
		}
	}
	@Override
	public void createTable(String database_name, String table_name)  throws FileWriteException {
		Path path = Paths.get(database_name, table_name);
		try(PrintWriter output = new PrintWriter(path.toFile())){
		} catch (FileNotFoundException e) {
			throw new FileWriteException("Error while creating the Table " + database_name+":"+table_name);
		}
	}

	@Override
	public void deleteDatabase(String filename) throws FileAccessException {

		File file = new File(filename);
		for (File subfile : file.listFiles()) {
			if (!subfile.delete()) {
				throw new FileAccessException("Could not delete the file " + subfile.getAbsolutePath());
			}
		}
	}
	@Override
	public void deleteTable(String database_name, String table_name) throws FileAccessException{
		Path path = Paths.get(database_name, table_name);
		File file = path.toFile();
		
		if(!file.delete()) throw new FileAccessException("Could not delete the file "+file.getAbsolutePath());
	}

	@Override
	public void writeData(String database_name, String table_name, String data) throws FileWriteException {

		File file = Paths.get(database_name, table_name).toFile();
		try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
			output.println(data);
		} catch (FileNotFoundException e) {
			throw new FileWriteException("Could not find the file " + file.getAbsolutePath());
		} catch (IOException e) {
			throw new FileWriteException("Error while writting to " + database_name+":"+table_name);
		}
	}

	@Override
	public void removeData(String database_name, String table_name, String data)
			throws FileWriteException, FileReadException {

		List<String> filedata = readData(database_name, table_name);

		for (String entry : filedata) {
			if (entry.equals(data)) {
				continue;
			}
			writeData(database_name, table_name, entry);
		}
	}

	@Override
	public void modifyData(String database_name, String table_name, String data, String newData)
			throws FileWriteException, FileReadException {

		List<String> filedata = readData(database_name, table_name);

		for (String entry : filedata) {
			if (entry.equals(data)) {
				writeData(database_name, table_name, newData);
				continue;
			}
			writeData(database_name, table_name, entry);
		}
	}

	@Override
	public List<String> readData(String database_name, String table_name) throws FileReadException {

		File file = Paths.get(database_name, table_name).toFile();
		List<String> results = new ArrayList<>();
		try (BufferedReader entrada = new BufferedReader(new FileReader(file))) {
			String reading;
			while ((reading = entrada.readLine()) != null) {
				results.add(reading);
			}
		} catch (FileNotFoundException e) {
			throw new FileReadException("Could not find the file");
		} catch (IOException e) {
			throw new FileReadException("Error while reading " + database_name+":"+table_name);
		}
		return results;
	}

	@Override
	public List<String> searchData(String database_name, String table_name, String search) throws FileReadException {
		List<String> data = readData(database_name, table_name);
		List<String> results = new ArrayList<>();
		for (String entry : data) {
			if (entry.contains(search)) {
				results.add(entry);
			}
		}
		return results;
	}
}
