import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String urlBanco = "jdbc:mysql://localhost:3306/DbJava";
        String usuarioBanco = "root";
        String senha = "root";
        try {
            Connection connection = DriverManager.getConnection(urlBanco,usuarioBanco,senha);
//            inserir(connection,new Usuario(2,"kaique","kaique",17));
//            System.out.println(connection);
            System.out.println(buscarId(connection,1));
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    static void inserir(Connection connection, Usuario usuario){
        try {
            Statement statement = connection.createStatement();
            String comandoSQL = "INSERT INTO Usuario values("+  usuario.getId()+ ","+
                                                            "'"+usuario.getNome()+"', "+
                                                            "'"+usuario.getSenha()+"', "+
                                                            usuario.getIdade()+")";
            statement.execute(comandoSQL);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }


    static Set<Usuario> buscarTodos(Connection connection){
        try {
            Statement statement = connection.createStatement();
            String comandoSql = "select * from Usuario;";
            ResultSet resultSet = statement.executeQuery(comandoSql);

            Set<Usuario> listaUser = new HashSet<>();

            while (resultSet.next()){
                int id = resultSet.getInt("id_usuario");
                int idade = resultSet.getInt("idade");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");

                Usuario usuario = new Usuario(id,nome,senha,idade);
                listaUser.add(usuario);
            }
            return listaUser;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    static Usuario buscarId(Connection connection,Integer id){
        String comandoSql = "SELECT * FROM Usuario WHERE id_usuario = "+id;

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(comandoSql);
            resultSet.next();
                int idade = resultSet.getInt("idade");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                return new Usuario(id,nome,senha,idade);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
