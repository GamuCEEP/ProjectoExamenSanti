package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.TXT.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TXTDataBase implements DataBase {

    @Override
    public void createStorage(String filename) 
    		throws FileWriteException {
    	
        Path path = Paths.get(filename);
        try {
            Files.createDirectories(path);

            for (GameObjectType type : GameObjectType.values()) {
                File file = new File(filename + "/" + type.name());
                PrintWriter output = new PrintWriter(file);
                output.close();
            }

        } catch (IOException ex) {
            throw new FileWriteException("Error while creating the storage " + filename);
        }
    }

    @Override
    public void deleteStorage(String filename) 
    		throws FileAccessException {
    	
        File file = new File(filename);
        for (File subfile : file.listFiles()) {
            if (!subfile.delete()) {
                throw new FileAccessException("Could not delete the file "+subfile.getAbsolutePath());
            }
        }
    }

    @Override
    public void writeData(String filename, String data, GameObjectType type) 
    		throws FileWriteException {
    	
        File file = new File(filename + "/" + type.name());
        try (PrintWriter output = new PrintWriter(new FileWriter(file, true))) {
            output.println(data);
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Could not find the file "+file.getAbsolutePath());
        } catch (IOException e) {
            throw new FileWriteException("Error while writting to " + filename);
        }
    }

    @Override
    public void removeData(String filename, String data, GameObjectType type) 
    		throws FileWriteException, FileReadException {
    	
        List<String> filedata = readData(filename, type);

        for (String entry : filedata) {
            if (entry.equals(data)) {
                continue;
            }
            writeData(filename, entry, type);
        }
    }

    @Override
    public void modifyData(String filename, String data, String newData, GameObjectType type) 
    		throws FileWriteException, FileReadException {
    	
        List<String> filedata = readData(filename, type);

        for (String entry : filedata) {
            if (entry.equals(data)) {
                writeData(filename, newData, type);
                continue;
            }
            writeData(filename, entry, type);
        }
    }

    @Override
    public List<String> readData(String filename, GameObjectType type) 
    		throws FileReadException {
    	
        File file = new File(filename + "/" + type);
        List<String> results = new ArrayList<>();
        try (BufferedReader entrada = new BufferedReader(new FileReader(file))) {
            String reading;
            while ((reading = entrada.readLine()) != null) {
                results.add(reading);
            }
        } catch (FileNotFoundException e) {
            throw new FileReadException("Could not find the file");
        } catch (IOException e) {
            throw new FileReadException("Error while reading " + filename);
        }
        return results;
    }

    @Override
    public List<String> searchData(String filename, String search, GameObjectType type) throws FileReadException {
        List<String> data = readData(filename, type);
        List<String> results = new ArrayList<>();
        for (String entry : data) {
            if (entry.contains(search)) {
                results.add(entry);
            }
        }
        return results;
    }
}
