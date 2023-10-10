import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class CarroDAO implements ICrud<Carro, Integer> {
    private Connection connection;
    private String comandoSql="";

    public CarroDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Carro obj) {
        comandoSql = "INSERT INTO carro values(?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getMarca());
            statement.setString(3, obj.getCor());
            statement.setString(4, obj.getModelo());
            statement.setDouble(5, obj.getPreco());
            statement.setInt(6, obj.getAno());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Carro buscarId(Integer id) {
        comandoSql = "SELECT * FROM carro WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String cor = resultSet.getString("cor");
                String modelo = resultSet.getString("modelo");
                Double preco = resultSet.getDouble("preco");
                int ano = resultSet.getInt("ano");
                return new Carro(id, marca, cor, modelo, preco, ano);
            }
            throw new NoSuchElementException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Carro> buscarTodos() {
        comandoSql = "SELECT * FROM carro";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            ResultSet resultSet = statement.executeQuery();
            Set<Carro> listaCarros = new HashSet<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String marca = resultSet.getString("marca");
                String cor = resultSet.getString("cor");
                String modelo = resultSet.getString("modelo");
                Double preco = resultSet.getDouble("preco");
                int ano = resultSet.getInt("ano");

                Carro carro = new Carro(id, marca, cor, modelo, preco, ano);
                listaCarros.add(carro);
            }
            return listaCarros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editar(Carro obj) {
//        Carro carroBuscado = buscarId(connection,obj.getId());
        comandoSql = "UPDATE carro SET marca = ?, cor = ?, modelo = ?, preco = ?, ano = ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(comandoSql)){
            statement.setString(1,obj.getMarca());
            statement.setString(2,obj.getCor());
            statement.setString(3,obj.getModelo());
            statement.setDouble(4,obj.getPreco());
            statement.setInt(5,obj.getAno());
            statement.setInt(6,obj.getId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(Integer id) {
        Carro removido = buscarId(id);
        comandoSql = "DELETE FROM carro WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
