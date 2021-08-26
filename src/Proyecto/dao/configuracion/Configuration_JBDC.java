package Proyecto.dao.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration_JBDC {


        private String jdbcDriver;
        private String dbUrl;
        private String nombreUsuario;
        private String contrasena;


        public Configuration_JBDC(String jdbcDriver, String dbUrl, String nombreUsuario, String contrasena) {
            this.jdbcDriver = jdbcDriver;
            this.dbUrl = dbUrl;
            this.nombreUsuario = nombreUsuario;
            this.contrasena = contrasena;
        }

        public Configuration_JBDC() {
            this.jdbcDriver = "org.h2.Driver";
            this.dbUrl = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
            this.nombreUsuario = "sa"; //colocar su usuario
            this.contrasena = null; //colocar su contraseña
        }

        public Connection conectarConBaseDeDatos() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dbUrl, nombreUsuario, contrasena);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return connection;
        }

}
