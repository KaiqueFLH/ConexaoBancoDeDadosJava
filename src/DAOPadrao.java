import java.sql.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class DAOPadrao<T,ID> implements ICrud<T,ID> {
    protected Connection connection;
    protected String comandoSql;
    private String tabela;

    public DAOPadrao(String tabela) throws SQLException {
        this.connection = Banco.conectar();
        this.tabela = tabela;
    }

    public Set<T> buscarTodos() {
        try (Statement statement = connection.createStatement()){

            comandoSql = "select * from "+tabela+";";
            ResultSet resultSet = statement.executeQuery(comandoSql);

            Set<T> lista = new HashSet<>();

            while (resultSet.next()) {

                lista.add(converter(resultSet));
            }
            return lista;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public T buscarId(Integer id) {
        comandoSql = "SELECT * FROM "+tabela+" WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return converter(resultSet);
        } catch (SQLException e) {
            throw new NoSuchElementException(e);
        }
    }

    public void deletar(Integer id) {
        T removido = buscarId(id);
        comandoSql = "DELETE FROM "+tabela+" WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract T converter(ResultSet resultSet) throws SQLException;

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
