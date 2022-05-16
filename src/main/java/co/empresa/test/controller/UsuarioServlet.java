package co.empresa.test.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import co.empresa.test.dao.UsuarioDao;
import co.empresa.test.modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.usuarioDAO = new UsuarioDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub-
		String action = request.getServletPath();
		try {
			switch(action) {
			case"/new":
				showNewForm(request, response);
				break;
			case"/insert":
				insertarUsuario(request, response);
				break;
			case"/delete":
				eliminarUsuario(request, response);
				break;
			case"/edit":
				showEditForm(request, response);
				break;
			case"/update":
				actualizarUsuario(request, response);
				break;
				default:		
					listUsuario(request, response);
					break;
			}
		}catch(SQLException e) {
			throw  new ServletException(e);
		}
		
	}

	



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException{
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		
		Usuario usuario = new Usuario(nombre, email, pais);
		
		usuarioDAO.insert(usuario);
		
		response.sendRedirect("list");		
	}
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, SQLException, IOException{
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			String pais = request.getParameter("pais");
			
			Usuario usuario = new Usuario(id, nombre, email, pais);
			
			usuarioDAO.update(usuario);
			
			response.sendRedirect("list");	
		}
		

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		Usuario usuarioActual = usuarioDAO.selec(id);
		
		request.setAttribute("usuario", usuarioActual);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
		
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException{
				
				int id = Integer.parseInt(request.getParameter("id"));				
				
				
				usuarioDAO.delete(id);
				
				response.sendRedirect("list");	
			}

	private void listUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException{
		
		List<Usuario> listUsuarios = usuarioDAO.selectALL();
		request.setAttribute("listUsuarios", listUsuarios);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuariolist.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}
}