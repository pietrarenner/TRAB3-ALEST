//terras dos filhos divididas igualmente entre seus filhos

public class Arvore {
    private class Nodo{
        private int terras; // valor armazenado no nodo
        private Nodo pai; // pai do nodo
        private Nodo [] filhos; // filhos do nodo
        private int nFilhos; // número de filhos do nodo
        private int nivel; // nível do nodo
        private String nome;

        public Nodo (Integer element, String nome){
            pai = null;
            filhos = new Nodo[10];
            terras = element;
            nFilhos = 0;
            this.nome = nome;
        }

        public int terrasFilho(){
            //aux.getTerras()/aux.getTamanhoSubArvore()
            return terras/nFilhos;
        }
        
        public int getTerras()
        {
            return terras;
        }

        public void addterras(Integer element){
            this.terras += element;
        }
        
        public void setPai(Nodo pai){
            this.pai = pai;
        }

        public void setNivel(int nivel){
            this.nivel = nivel;
        }
        
        public void addSubArvore (Nodo n){
            if(nFilhos == filhos.length)
                grow();
            filhos[nFilhos] = n;
            n.pai = this;
            nFilhos++;
            n.nivel = this.nivel; //aumenta nivel da criança
        }
        
        private void grow(){
            Nodo aux [] = new Nodo[filhos.length*2];
            for(int i=0; i<filhos.length; i++)
              aux[i] = filhos[i];
            filhos = aux;            
        }

        // busca subtree pelo indice dentro da lista de filhos
        Nodo getSubArvore(int i){
            if(i>=0 && i<nFilhos)
              return filhos[i];
            return null;
        }

        //retorna o número de filhos
        int getTamanhoSubArvore(){
            return nFilhos;
        }  

        int getNivel(){
            return nivel;
        }

        String getNome(){
            return nome;
        }

        Nodo getFilhos(int i){
            return filhos[i];
        }
    }

    private Nodo raiz;
    private int tamanho;
    private int maiorNivel;

    public Arvore(){
        this.raiz = null;
        this.tamanho = 0;
        this.maiorNivel = 0;
    }

    private Nodo procuraNodo(String pai, Nodo ref){

        if(ref!=null){
            if(ref.nome.equals(pai))
                return ref;
            else{
                Nodo aux=null;
                for(int i=0; i<ref.getTamanhoSubArvore(); i++){
                    aux = procuraNodo(pai, ref.getSubArvore(i));
                    if(aux != null)
                        return aux;
                }
                return null;
            }
        }
        else
            return null;

    }

    //insere o elemento e como filho de father
    // Versao 0.1 -> Inclui root e inclui filho de root
    // Versao 0.2 -> Permite a inclusão de mais níveis na árvore
    public boolean add(Integer e, String pai, String nome){
        Nodo aux;
        if(tamanho==0){
            this.raiz=new Nodo(e, nome);
            raiz.setNivel(1);
        }
        else{
            aux = procuraNodo(pai, raiz);
            if(aux==null)
                return false;
            else{
                Nodo jose = new Nodo(e, nome);
                aux.addSubArvore(jose);
                jose.setNivel(aux.getNivel()+1);
            }
        }
        tamanho++;

        return true;
    }

    // percorremos a árvore com caminhamento em largura e adicionamos as terras para os devidos filhos
    public Nodo[] quaseMata(){
        Nodo[] nodos = new Nodo[tamanho];
        int indice = 0;
        int posicao = 0;

        nodos[indice] = raiz;
        indice++;
        while(indice<tamanho){
            Nodo aux = procuraNodo(nodos[posicao].nome, raiz); //aux é o pai
            posicao++;
            if(aux!=null)
                for(int i = 0; i<aux.getTamanhoSubArvore(); i++){
                    aux.getSubArvore(i).addterras(aux.terrasFilho());
                    nodos[indice] = aux.getSubArvore(i);// aux = searchNode(aux.children[i], aux)
                    indice++;
                    if(aux.getFilhos(i).getNivel() > maiorNivel){
                        this.maiorNivel = aux.getSubArvore(i).getNivel();
                    }
                }
            //pegar numero de terras do pai - feito
            //dividir terras para cada filho - feito
        }
        return nodos;
    }

    //perguntar: se fazemos um caminhamento de pós-ordem ou se fazemos em largura para chegar no maior nível e, depois disso, achar o mais rico
    //ou se fazemos um vetor com nodos do maior nivel e, depois, vemos qual deles é mais rico 
    public void maisRico(Nodo[] nodos){
        Nodo Rico = raiz;
        for(int i = 0; i < nodos.length; i++){
            for(int j = 1; j < nodos.length-1; j++)
                if(nodos[i].getNivel() == maiorNivel && nodos[j].getNivel() == maiorNivel) {
                    if(nodos[i].getTerras() > nodos[j].getTerras())
                        Rico = nodos[i];
                    else
                        Rico = nodos[j];
                }
                System.out.println(nodos[i].getNome() +", " +nodos[i].getTerras());
            }
        System.out.println("O mais rico da geração " +Rico.getNivel()+ " foi " +Rico.getNome()+ " com " +Rico.getTerras()+ " terras.");
    }

}




















//  int [] positionsWidth(){
    //     if(nElements==0)
    //         return null;
        
    //     int [] lista = new int[this.nElements]; = tamanho
    //     int idx=0; - total de elementos percorridos
    //     int pos=0;

    //     lista[idx++]=root.value; - lista[0]= raiz / idx = 1
    //     while(idx<nElements){ - percorre todos os elementos
    //         TreeNode aux = searchNode(lista[pos++], this.root); - lista[0] comeca na raiz - lista[1]
    //         if(aux!=null)
    //             for(int i=0; i<aux.getSubtreeSize(); i++) - percorre os filhos da raiz
    //                 lista[idx++]=aux.getSubtree(i).value; - adiciona filhos da raiz na lista
    //     }

    //     return lista;


    //matar nodos e dividir número entre os filhos - feito

    //descobrir maior nível?

    //pegar mais rico do maior nível