package iterator;

//Iterators implementing this interface DO NOT allow for modifications during iteration
public interface LatticeIterator {

    int getNext();

    boolean hasNext();
}
