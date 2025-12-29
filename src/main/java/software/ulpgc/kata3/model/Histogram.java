package software.ulpgc.kata3.model;

import java.util.HashMap;
import java.util.Iterator;

public class Histogram implements Iterable<Integer> {
    private final HashMap<Integer, Integer> map;

    public Histogram() {
        this.map = new HashMap<>();
    }

    public void add(int key){
        map.put(key, count(key)+1);
    }

    public int count(int key) {
        return map.getOrDefault(key, 0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }
}
