interface Agrupacion<T> extends Iterable<T>{
	public boolean anyadir(T t);
	public boolean borrarUltimo();
}