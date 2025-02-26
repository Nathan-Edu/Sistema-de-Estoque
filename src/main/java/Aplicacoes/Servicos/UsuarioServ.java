package Aplicacoes.Servicos;

import Aplicacoes.Dao.UsuarioDAO;
import Aplicacoes.Modelos.Usuario;

import java.util.List;
import java.util.logging.Logger;

public class UsuarioServ {
    private static final Logger LOGGER = Logger.getLogger(UsuarioServ.class.getName());
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void adicionaUsuario(Usuario usuario) {
        usuarioDAO.adicionaUsuario(usuario);
        LOGGER.info("Usuário adicionado com sucesso: " + usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioDAO.listarUsuarios().stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public boolean registrarUsuario(Usuario novoUsuario) {
        if (buscarUsuarioPorEmail(novoUsuario.getEmail()) == null) {
            adicionaUsuario(novoUsuario);
            return true;
        } else {
            LOGGER.warning("Email já registrado.");
            return false;
        }
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizarUsuario(usuario);
        LOGGER.info("Usuário atualizado com sucesso: " + usuario);
    }
}
