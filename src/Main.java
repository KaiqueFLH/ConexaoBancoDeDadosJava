import java.sql.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String urlBanco = "jdbc:mysql://localhost:3306/DbJava";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(urlBanco, username, password);

            //Cadastrando um Usuário com um carro.
//            Usuario usuario = new Usuario(31, "Toretto", "careca123", 50, new Carro(1, "Dodge", "Preto", "Charger", 100000.0, 1979));
//            ICrud<Carro, Integer> crudCarro = new CarroDAO(connection);
//            Carro carro = null;
//            try {
//                carro = crudCarro.buscarId(2);
//            } catch (NoSuchElementException e) {
//                crudCarro.inserir(usuario.getCarro());
//            }
//
//            ICrud<Usuario, Integer> crudUsuario = new UsuarioDAO(connection);
//            crudUsuario.inserir(usuario);

            //================================================================================================================================================================

//            Cadastrando um Usuário sem um carro.
//            Usuario usuarioSemCarro = new Usuario(34, "Bryan", "Loiro321", 45);
//            ICrud<Carro, Integer> crudCarro2 = new CarroDAO(connection);
//
//            try {
//                crudCarro2.buscarId(usuarioSemCarro.getCarro().getId());
//            } catch (NoSuchElementException e) {
//                crudCarro2.inserir(usuarioSemCarro.getCarro());
//            } catch (NullPointerException ignore){
//
//            }
//
//            ICrud<Usuario, Integer> crudUsuario2= new UsuarioDAO(connection);
//            crudUsuario2.inserir(usuarioSemCarro);
//
//            DELETAR CARRO
//            ICrud<Carro,Integer> crudCarro3 = new CarroDAO(connection);
//            int idCarroDeletar=1;
//            ICrud<Usuario,Integer> crudUsuario3 = new UsuarioDAO(connection);
//            Set<Usuario> usuarios =crudUsuario3.buscarTodos();
//
//            for (Usuario usuario:usuarios){
//                try{
//                    if (usuario.getCarro().getId() == idCarroDeletar){
//                        usuario.setCarro(null);
//                        crudUsuario3.editar(usuario);
//                    }
//                }catch (NullPointerException ignore){}
//            }
//            crudCarro3.deletar(1);

            ICrud<Usuario,Integer> usuarioCrud = new UsuarioDAO(connection);
            ICrud<Carro,Integer> carroCrud = new CarroDAO(connection);
            Carro carro =new Carro(2,"Ford","preto","Ford Ka",24.0,2014);
//            carroCrud.inserir(carro);
            Usuario usuario = new Usuario(35,"Otavio","Batata@123",17, carro);

//            usuarioCrud.inserir(usuario);
            Usuario usuario1 = usuarioCrud.buscarId(35);
            usuario1.setCarro(carroCrud.buscarId(usuario1.getCarro().getId()));
            System.out.println(usuario1);


//            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


}
