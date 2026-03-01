package at.spengergasse;

import java.util.LinkedList;
import java.util.List;

public class Notizen {
    private List<String> notizen;

    public Notizen(){
        notizen = new LinkedList<>();
    }

    public boolean erstellen(String notiz){
        if (notizen.isEmpty()){
            return false;
        }return notizen.add(notiz);
    }

    public void notizenAusgeben(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String no : notizen){
            stringBuilder.append(no).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public void sortieren(){
        notizen.sort(null);
    }

}
