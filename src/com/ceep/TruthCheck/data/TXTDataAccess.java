
package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.exceptions.TXT.FileAccessException;
import com.ceep.TruthCheck.exceptions.TXT.FileReadException;
import com.ceep.TruthCheck.exceptions.TXT.FileWriteException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class TXTDataAccess implements DataAccess{

    @Override
    public void createStorage(String filename) throws FileWriteException{
        File file = new File(filename);
        try (PrintWriter output = new PrintWriter(file)){
        } catch (FileNotFoundException e) {
            throw new FileWriteException();
        }
    }

    @Override
    public void deleteStorage(String filename) throws FileAccessException{
        File file = new File(filename);
        if(!file.delete()) throw new FileAccessException("Could not delete the file");
    }

    @Override
    public void writeData(String filename, String data) throws FileWriteException{
        File file = new File(filename);
        try (PrintWriter output = new PrintWriter(new FileWriter(file, true))){
            output.println(data);
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Could not find the file");
        } catch (IOException e) {
            throw new FileWriteException("Error while writting to "+filename);
        }
    }

    @Override
    public void removeData(String filename, String data) throws FileWriteException{
        File file = new File(filename);
        File temp = new File("temp");
        try (BufferedReader input = new BufferedReader(new FileReader(file));
            PrintWriter output = new PrintWriter(new FileWriter(temp, true))){
            String reading;
            while ((reading = input.readLine()) != null) {
                if (reading.equalsIgnoreCase(data)) {
                    continue;
                }
                output.println(reading);
            }
            file.delete();
            temp.renameTo(file);
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Could not find the file");
        } catch (IOException e) {
            throw new FileWriteException("Error while writing to file "+filename);
        }
    }

    @Override
    public void modifyData(String storage_name, String data, String newData)  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> readData(String filename) throws FileReadException{
        File file = new File(filename);
        List<String> list = new ArrayList<>();
        try (BufferedReader entrada = new BufferedReader(new FileReader(file))){   
            String reading;
            while ((reading = entrada.readLine()) != null) {
                list.add(reading);
            }
        } catch (FileNotFoundException e) {
            throw new FileReadException("Could not find the file");
        } catch (IOException e) {
            throw new FileReadException("Error while reading "+filename);
        }
        return list;
    }

    @Override
    public String searchData(String filename, String search) throws FileReadException{
        List<String> data = readData(filename);
        
    }
    
}
