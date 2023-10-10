import java.sql.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class UsuarioDAO implements ICrud<Usuario,Integer>{
    private Connection connection;
    private String comandoSql="";

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Usuario usuario) {
        comandoSql = "INSERT INTO Usuario VALUES(?,?,?,?,?);";

        try(PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1,usuario.getId());
            statement.setString(2,usuario.getNome());
            statement.setString(3,usuario.getSenha());
            statement.setInt(4,usuario.getIdade());
            try {
                statement.setInt(5,usuario.getCarro().getId());
            } catch (NullPointerException e) {
                statement.setNull(5,0);
            }
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public Set<Usuario> buscarTodos() {
        try (Statement statement = connection.createStatement()){

            comandoSql = "select * from Usuario;";
            ResultSet resultSet = statement.executeQuery(comandoSql);

            Set<Usuario> listaUser = new HashSet<>();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_usuario");
                Integer idade = resultSet.getInt("idade");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Integer id_carro = resultSet.getInt("id_carro");
                Usuario usuario = new Usuario(id, nome, senha, idade,new Carro(id_carro));
                listaUser.add(usuario);
            }
            return listaUser;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Usuario buscarId(Integer id) {
        comandoSql = "SELECT * FROM Usuario WHERE id_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int idade = resultSet.getInt("idade");
            String nome = resultSet.getString("nome");
            String senha = resultSet.getString("senha");
            int idCarro = resultSet.getInt("id_carro");


            return new Usuario(id, nome, senha, idade,new Carro(idCarro));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void editar(Usuario usuario) {
//        Usuario usuarioBuscado = buscarId(connection, usuario.getId());

        comandoSql = "UPDATE Usuario SET nome = ?, senha = ?, idade = ?, id_carro = ? WHERE id_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setString(1,usuario.getNome());
            statement.setString(2,usuario.getSenha());
            statement.setInt(3,usuario.getIdade());
            try {
                statement.setInt(4,usuario.getCarro().getId());
            }catch (NullPointerException e){
                statement.setNull(4,0);
            }
            statement.setInt(5,usuario.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deletar(Integer id) {
        Usuario removido = buscarId(id);
        comandoSql = "DELETE from Usuario where id_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(comandoSql)) {
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
