public interface EstruturaDeDados {
    public void insert(int chave);
    public void delete(int chave);
    public boolean search(int chave);
    public int minimum();
    public int maximum();
    public int sucessor(int chave);
    public int prodessor(int chave);
}
