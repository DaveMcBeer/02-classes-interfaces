package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList,Iterable<Object> {

    private Element head;
    private int size=0;
    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    @Override
    public void add(Object o) {
        if(head == null)
            head = new Element(o,null);
        else {
            Element current = head;
            while (current.GetNext() != null) {
                current = current.next;
            }
            current.next = new Element(o, null);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();
        for(Object o : this){
            if(filter.include(o)){
                result.add(o);
            }
        }
        return result;
    }

    private static class Element{

        Element(Object item,Element next){
            this.item = item;
            this.next = next;
        }
        private Object item;
        public Object GetItem(){
            return item;
        }
        private Element next;
        public Element GetNext(){
            return next;
        }
    }

    private class SimpleIteratorImpl implements Iterator<Object>{

        private Element current = head;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
           Object temp = current.GetItem();
           current = current.GetNext();
           return temp;
        }
    }

}
