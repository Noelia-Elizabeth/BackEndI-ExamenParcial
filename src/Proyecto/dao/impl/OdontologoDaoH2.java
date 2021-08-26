package Proyecto.dao.impl;

import Proyecto.dao.IDao;
import Proyecto.model.Odontologo;
import Proyecto.dao.configuracion.Configuration_JBDC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private Configuration_JBDC configuracionJDBC;
    private static final Logger logger = Logger.getLogger(String.valueOf(OdontologoDaoH2.class));

    public OdontologoDaoH2() {
        this.configuracionJDBC = new Configuration_JBDC();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Estableciendo conexión con la base de datos.");
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("INSERT INTO odontologos(matricula,nombre,apellido) VALUES ('%s','%s','%s')", odontologo.getNumeroDeMatricula(),odontologo.getNombre(),odontologo.getApellido());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                odontologo.setId(resultSet.getInt(1));
        } catch (SQLException exception) {
            /*logger.error("Error al querer guardar un odontólogo.");
            Como no me andaba con .error hice uno con .info*/
            logger.info("Error al querer guardar un odontólogo.");
            exception.printStackTrace();
        }
        return odontologo;
    }

    private Odontologo crearOdontologo(ResultSet resultSet) throws SQLException {
        logger.info("Creando un nuevo odontólogo.");
        Integer id = resultSet.getInt("id");
        String numeroDeMatricula = resultSet.getString("matricula");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        return new Odontologo(id,numeroDeMatricula, nombre, apellido);
    }

    @Override
    public ArrayList<Odontologo> buscarTodos() {
        logger.info("Estableciendo conexión con la base de datos.");
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = "SELECT *  FROM odontologos";
        ArrayList<Odontologo> odontologos = new ArrayList();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                odontologos.add(crearOdontologo(resultSet));
            }
            statement.close();
        } catch (SQLException exception) {
            /*logger.error("Error al querer guardar un odontólogo.");
            Como no me andaba con .error hice uno con .info*/
            logger.info("Error al listar los odontólogos.");
            exception.printStackTrace();
        }
        return odontologos;
    }
}
