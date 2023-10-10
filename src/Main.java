import java.sql.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Usuario usuario = new Usuario(36, "Toretto", "careca123", 50, new Carro(3, "Dodge", "Preto", "Charger", 100000.0, 1979));
        try (ICrud<Carro, Integer> crudCarro = new CarroDAO()) {
            ICrud<Usuario, Integer> crudUsuario = new UsuarioDAO();
            try {
                crudCarro.buscarId(usuario.getCarro().getId());
            } catch (NoSuchElementException e) {
                crudCarro.inserir(usuario.getCarro());
            }

            crudUsuario.inserir(usuario);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }



        //Cadastrando um Usuário com um carro.


//            Carro carro =new Carro(1,"Teste","teste","teste",24.0,2014);
//            carroCrud.inserir(carro);
//            Usuario usuario = new Usuario(35,"Otavio","Batata@123",17, carro);
//
////            usuarioCrud.inserir(usuario);
//            Usuario usuario1 = usuarioCrud.buscarId(35);
//            usuario1.setCarro(carroCrud.buscarId(usuario1.getCarro().getId()));
//            System.out.println(usuario1);
//
//
////            connection.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }




    }


}
