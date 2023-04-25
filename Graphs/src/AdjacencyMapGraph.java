import java.util.HashMap;
import java.util.Map;

public class AdjacencyMapGraph<V,E> {
    private class Vertex<V>{
        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>,Edge<E>>incoming,outgoing;

        public Vertex(V element,boolean graphisDirected) {
            this.element = element;
            outgoing=new HashMap<>();
            if(graphisDirected)
                incoming=new HashMap<>();
            else
                incoming=outgoing;
        }

        public V getElement() {
            return element;
        }

        public void setElement(V element) {
            this.element = element;
        }

        public Position<Vertex<V>> getPos() {
            return pos;
        }

        public void setPos(Position<Vertex<V>> pos) {
            this.pos = pos;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        public void setIncoming(Map<Vertex<V>, Edge<E>> incoming) {
            this.incoming = incoming;
        }

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public void setOutgoing(Map<Vertex<V>, Edge<E>> outgoing) {
            this.outgoing = outgoing;
        }
    }
    private class Edge<E>{
        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[ ] endpoints;

        public Edge(Vertex<V> u, Vertex<V> v,E element) {
            this.element = element;
            endpoints = (Vertex<V>[ ]) new Vertex[ ]{u,v};
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Position<Edge<E>> getPos() {
            return pos;
        }

        public void setPos(Position<Edge<E>> pos) {
            this.pos = pos;
        }

        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        public void setEndpoints(Vertex<V>[] endpoints) {
            this.endpoints = endpoints;
        }
    }
  


}
