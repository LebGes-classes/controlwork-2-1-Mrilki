import org.example.MyHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyHashMapTest {
    MyHashMap<String, Integer> testMap = new MyHashMap<>();

    @Test
    public void addAndGet(){
        testMap.add("Первый", 1);
        Assertions.assertEquals(1, testMap.get("Первый"));
    }
    @Test
    public void remove(){
        testMap.add("Первый", 1);
        testMap.add("Второй", 2);
        testMap.remove("Второй");
        Assertions.assertEquals(null,testMap.get("Второй"));
    }
    @Test
    public void addSame(){
        testMap.add("Первый", 1);
        testMap.add("Первый", 2);
        Assertions.assertEquals(2, testMap.get("Первый"));
    }
    @Test
    public void resize(){
        for (int i = 0; i < 17; i++) {
            testMap.add(("Первый"+i), i);
        }
        Assertions.assertEquals(32, testMap.getCapacity());
    }
}
