/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.gerenprod.loginServlet;

import br.senac.tads.pi3.gerenprod.model.Usuario;
import br.senac.tads.pi3.gerenprod.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruna
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/"})
public class LoginServlet extends HttpServlet {

  private final UsuarioDAO usuarioDAO = new UsuarioDAO();
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.getRequestDispatcher("/login.jsp").forward(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {

    Usuario usuario = new Usuario();
    
    usuario.setEmail(request.getParameter("email"));
    usuario.setSenha(request.getParameter("senha"));

    usuario = usuarioDAO.login(usuario);
    
    if (usuario.getIdUsuario() != 0){
      
      usuario.setSession(request);
      response.sendRedirect(request.getContextPath() + "/produto");
      
    } else {
      
      request.setAttribute("mensagem", "Email e/ou senha incorretos");
      request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
  }
}