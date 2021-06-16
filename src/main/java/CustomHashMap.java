public class CustomHashMap<K, V> {


    int capacity = 5;

    Entry<K, V>[] table;


    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public CustomHashMap(int capacity) {
        this.table = new Entry[capacity];
    }


    private void put(K key, V value) {
        if (key == null)
            return;

        int hash = hash(key);

        Entry<K, V> newEntry = new Entry<>(key, value, null);

        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) {

                if (current.key.equals(key)) {
                    newEntry.next = current.next;
                    if (previous == null) {

                        table[hash] = newEntry;
                        return;
                    } else {
                        previous.next = current.next;
                        return;
                    }
                }

                previous = current;
                current = current.next;
            }

            previous.next = newEntry;

        }

    }


    private V get(K key) {
        int hash = hash(key);
        if (table[hash] == null) {

            return null;
        } else {

            Entry<K, V> current = table[hash];

            while (current != null) {

                if (current.key.equals(key)) {
                    return current.value;
                }

                current = current.next;
            }


        }

        return null;
    }

    private boolean remove(K key) {
        int hash = hash(key);

        if (table[hash] == null) {
            return false;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        table[hash] = table[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }

                }

                previous = current;
                current = current.next;

            }
        }
        return false;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

}
