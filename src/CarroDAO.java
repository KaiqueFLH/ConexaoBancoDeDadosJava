import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class CarroDAO extends DAOPadrao<Carro,Integer>{


    public CarroDAO() throws SQLException {
        super("Carro");
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
    public Carro converter(ResultSet resultSet) throws SQLException {
        return new Carro(resultSet);
    }
}
