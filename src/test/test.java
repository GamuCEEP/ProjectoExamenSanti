package test;

import com.ceep.TruthCheck.business.BusinesManager;
import com.ceep.TruthCheck.data.txtDatabase.DataAccess;
import com.ceep.TruthCheck.data.txtDatabase.DataType;
import com.ceep.TruthCheck.data.txtDatabase.TXTDataBase;
import com.ceep.TruthCheck.data.txtDatabase.Table;
import com.ceep.TruthCheck.domain.GameCharacter;
import com.ceep.TruthCheck.domain.GameItem;
import com.ceep.TruthCheck.exceptions.DatabaseNotFoundException;
import com.ceep.TruthCheck.exceptions.ReadException;
import com.ceep.TruthCheck.exceptions.TXT.FileReadException;
import com.ceep.TruthCheck.exceptions.WriteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test {

    public static void main(String[] args) throws WriteException, FileReadException, ReadException, DatabaseNotFoundException {
//        TXTDataBase txt = new TXTDataBase();
//        DataAccess da = new DataAccess(txt);
//        BusinesManager b = new BusinesManager(da);
//
//        String database = "patata";
//
//        b.createDatabase(database);
//        b.listDatabases().forEach(t -> System.out.println("BD - "+t));
//        b.conectToDatabase(database);
//        System.out.println("Base de datos conectada: "+b.getConectedDatabase());
//        
//        GameItem espadaDePalo = new GameItem(0, "Espada de palo", "Es un palo");
//        GameItem tapaDeOlla = new GameItem(1, "Tapa de olla", "El mejor escudo del mundo");
//        GameItem gemita = new GameItem(2,"Gema preciosa","Es un trozo de una botella");
//        
//        b.addEntry(espadaDePalo);
//        b.addEntry(tapaDeOlla);
//        b.addEntry(gemita);
//        
//        List<GameItem> inventory = new ArrayList<GameItem>();
//        inventory.add(gemita);
//        List<GameItem> equipment = new ArrayList<GameItem>();
//        equipment.add(espadaDePalo);
//        equipment.add(tapaDeOlla);
//        
//        
//        b.addEntry(new GameCharacter(3,"Antonio","La piedra mas furete del reino", inventory, equipment));
//
//        b.searchEntry("").forEach(t -> System.out.println(t));
//        List<String> lista = new ArrayList<>();

        Table t = new Table("Item", 
                Table.C("nombre", DataType.STRING),
                Table.C("descripcion", DataType.STRING));
        
        
        
        
        System.out.println(DataType.INT.check("Patata"));

    }

}
