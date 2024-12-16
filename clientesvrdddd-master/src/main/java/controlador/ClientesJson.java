package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Max;
import modelos.Clientes;
import services.ClientesService;
import services.ClientesServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Implementamos el Path
@WebServlet({"/clientes.json", "/clienteshtml"})
public class ClientesJson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientesService service = new ClientesServiceImplement();
        List<Clientes> clientes = service.listar();

        // Obtenemos el path solicitado
        String servletPath = req.getServletPath();
        boolean esJson = servletPath.endsWith(".json");

        if (esJson) {
            // Configuración para enviar el archivo JSON
            resp.setContentType("application/json;charset=UTF-8");
            resp.setHeader("Content-Disposition", "attachment; filename=clientes.json");

            try (PrintWriter out = resp.getWriter()) {
                // Generamos el JSON con formato
                out.print("[\n");
                for (int i = 0; i < clientes.size(); i++) {
                    Clientes cliente = clientes.get(i);
                    out.print("  {\n");
                    out.print("    \"idCliente\": \"" + cliente.getIdClientes() + "\",\n");
                    out.print("    \"nombre\": \"" + cliente.getNombre() + "\",\n");
                    out.print("    \"apellido\": \"" + cliente.getApellido() + "\",\n");
                    out.print("    \"genero\": \"" + cliente.getGenero() + "\",\n");
                    out.print("    \"telefono\": \"" + cliente.getTelefono() + "\"\n");
                    out.print("  }");
                    if (i < clientes.size() - 1) {
                        out.print(",\n");
                    }
                }
                out.print("\n]");
            }
        } else {
            // Configuración para mostrar la tabla HTML
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Listado Clientes</title>");
                // Estilos en línea para replicar el formato de la tabla
                out.println("<style>");
                out.println("table");
                out.println("body");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listado de Clientes</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/clientes.json" + "\">Descargar JSON</a></p>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID Cliente</th>");
                out.println("<th>NOMBRE</th>");
                out.println("<th>APELLIDO</th>");
                out.println("<th>GENERO</th>");
                out.println("<th>TELEFONO</th>");
                out.println("</tr>");
                // Rellenar la tabla con datos
                for (Clientes cliente : clientes) {
                    out.println("<tr>");
                    out.println("<td>" + cliente.getIdClientes() + "</td>");
                    out.println("<td>" + cliente.getNombre() + "</td>");
                    out.println("<td>" + cliente.getApellido() + "</td>");
                    out.println("<td>" + cliente.getGenero() + "</td>");
                    out.println("<td>" + cliente.getTelefono() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
