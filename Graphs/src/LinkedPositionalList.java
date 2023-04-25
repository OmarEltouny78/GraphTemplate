public class LinkedPositionalList<E> implements PositionalList<E>{
    private static class Node<E> implements Position<E> {
        private E element; // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node in the list
        private Node<E> next; // reference to the subsequent node in the list

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public LinkedPositionalList( ) {
         header = new Node<>(null, null, null); // create header
         trailer = new Node<>(null, header, null); // trailer is preceded by header
         header.setNext(trailer); // header is followed by trailer
         }
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
         if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
         Node<E> node = (Node<E>) p; // safe cast
         if (node.getNext( ) == null) // convention for defunct node
             throw new IllegalArgumentException("p is no longer in the list");
         return node;}
    private Position<E> position(Node<E> node) {
         if (node == header || node == trailer)
             return null; // do not expose user to the sentinels
         return node;
         }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Position<E> first() {
        return position(header.getNext( ));
    }

    @Override
    public Position<E> last() {
        return position(trailer.getPrev( ));
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev( ));
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext( ));
    }
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
         Node<E> newest = new Node<>(e, pred, succ); // create and link a new node
         pred.setNext(newest);
         succ.setPrev(newest);
         size++;
         return newest;
    }


    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e,header,header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e,trailer.getPrev(),trailer);
    }


    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev( ), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement( );
        node.setElement(e);
        return answer;
    }


    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev( );
        Node<E> successor = node.getNext( );
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size=size-1;
        E answer = node.getElement( );
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);
        return answer;

    }

    }
