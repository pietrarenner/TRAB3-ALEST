//terras dos filhos divididas igualmente entre seus filhos

public class Arvore {
    private class Nodo{
        private int value; // valor armazenado no nodo
        private Nodo father; // pai do nodo
        private Nodo [] children; // filhos do nodo
        private int nChild; // número de filhos do nodo

        public Nodo (Integer element){
            father=null;
            children=new Nodo[10];
            value=element;
            nChild=0;
        }
        public void setValue(Integer element){
            this.value = element;
        }
        public void setPai(Nodo pai){
            this.father = pai;
        }
        public void addSubArvore (Nodo n){
            if(nChild==children.length)
                grow();
            children[nChild] = n;
            n.father=this;
            nChild++;
        }
        private void grow(){
            Nodo aux [] = new Nodo[children.length*2];
            for(int i=0; i<children.length; i++)
              aux[i]=children[i];
            children=aux;            
        }
        boolean removerSubArvore(Nodo n){
            if(n==null)
                return false;
            
            int idx=-1;
            for(int i=0; i<nChild; i++)
                if(children[i]==n){
                    idx=i;
                    break;
                }
            
            if(idx==-1)
                return false;

            for(int i=idx; i<nChild; i++)
                children[i]=children[i+1];
            nChild--;
            children[nChild]=null;
            
            return true;
        }
        // busca subtree pelo indice dentro da lista de filhos
        Nodo getSubtArvor(int i){
            if(i>=0 && i<nChild)
              return children[i];
            return null;
        }
        //retorna o número de filhos
        int getTamanhoSubArvore(){
            return nChild;
        }  
    }

    private Nodo raiz;
    private int tamanho;

    public Arvore(){
        this.raiz = null;
        this.tamanho = 0;
    }

    //adicionar nodos
    public void add(Integer novo, Integer pai){
        if(tamanho==0){
            this.raiz = new Nodo(novo);
        }
        else{
            Nodo auxiliar = new Nodo(novo);
        }
    }

    //matar nodos e dividir número entre os filhos

    //descobrir maior nível?

    //pegar mais rico do maior nível
}