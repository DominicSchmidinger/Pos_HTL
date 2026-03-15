package at.spengergasse;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WerkstoffzentrumVerwaltung {

    private List<MetallProbe> metallerProbe;

    public WerkstoffzentrumVerwaltung() {
        metallerProbe = new LinkedList<MetallProbe>();
    }

    public boolean probenaufnehmen(MetallProbe probe) {
        if (probe == null || metallerProbe.contains(probe)) {
            throw new IllegalArgumentException("Fehler bei der Validierung: " + probe);
        }
        return metallerProbe.add(probe);

    }
public boolean removeProbe(MetallProbe probe){
        Iterator<MetallProbe> iterator = metallerProbe.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(probe.getId())) {
                iterator.remove();
            }
        }
        return false;
}
public boolean removeAllProbe(){
        Iterator<MetallProbe> iterator = metallerProbe.iterator();
        while (iterator.hasNext()){
            
        }
}
}
