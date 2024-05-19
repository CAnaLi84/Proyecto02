import java.util.ArrayList;

public class HistoricoCompras{
    private ArrayList<Memento> mementos = new ArrayList<>();

    public void guardar(Memento memento){
	mementos.add(memento);
    }

    public Memento getUltimoEstadoGuardado(){
	if(mementos.size() > 0){
	    return mementos.get(mementos.size() - 1);
	}
	return null;
    }

}
