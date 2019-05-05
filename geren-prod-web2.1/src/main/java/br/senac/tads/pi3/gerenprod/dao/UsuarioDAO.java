/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.gerenprod.dao;

import br.senac.tads.pi3.gerenprod.db.DB;
import br.senac.tads.pi3.gerenprod.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Bruna
 */
public class UsuarioDAO implements CrudInterface<Usuario> {

  public Usuario login (Usuario usuario) {
    DB db = new DB(true);
    try {
      
      String sql = "select usuario.idUsuario, usuario.NomeUsuario, usuario.Email, usuario.Senha, usuario.Ativo, usuario.idDepartamento, departamento.NomeDepartamento, usuario.idFilial, filial.NomeFilial from usuario inner join departamento on departamento.idDepartamento = usuario.idDepartamento inner join filial on filial.idFilial = usuario.idFilial "
                 + "where usuario.Email = '" + usuario.getEmail() + "' and usuario.Senha = '" + usuario.getSenha() + "' and usuario.Ativo = true;";
      
      ResultSet rs = db.executarConsulta(sql) != null ? db.executarConsulta(sql) : null;
      
      if(rs == null) {
        return usuario;
      }
      
      while (rs.next()) {
        usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNome(rs.getString("NomeUsuario"));
        usuario.setEmail(rs.getString("Email"));
        usuario.setSenha(rs.getString("Senha"));
        usuario.setAtivo(rs.getBoolean("Ativo"));
        usuario.setIdDepartamento(rs.getInt("idDepartamento"));
        usuario.setIdFilial(rs.getInt("idFilial"));
        usuario.setNomeDepartamento(rs.getString("NomeDepartamento"));
        usuario.setNomeFilial(rs.getString("NomeFilial"));
      }
      db.close();
      
      return usuario;
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      db.close();
      return null;
    }
  }
  
  @Override
  public ArrayList<Usuario> listar(int idFilial) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Usuario mostrar(int ID) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean editar(Usuario objeto) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean salvar(Usuario objeto) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean desativar(int ID) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}