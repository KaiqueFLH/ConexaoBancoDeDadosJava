import java.sql.Connection;
import java.util.Set;

public interface ICrud<T,ID> {
    void inserir(T object);

    T buscarId(ID id);

    Set<T> buscarTodos();

    void editar(T obj);

    void deletar(ID id);
}
